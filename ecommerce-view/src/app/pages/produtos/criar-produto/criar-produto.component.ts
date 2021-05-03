import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { ToastrService } from 'ngx-toastr';

import { Produto } from 'src/app/shared/models/produto.entity';
import { ImagemService } from 'src/app/shared/services/imagem.service';
import { ProdutoService } from 'src/app/shared/services/produto.service';

@Component({
  selector: 'app-criar-produto',
  templateUrl: './criar-produto.component.html',
  styleUrls: ['./criar-produto.component.scss']
})
export class CriarProdutoComponent implements OnInit {

  public form: FormGroup;
  public carregamento: boolean = false;

  public constructor(
                        private readonly _fb: FormBuilder,
                        private readonly router: Router,
                        private readonly toastr: ToastrService,
                        private readonly servicoProduto: ProdutoService,
                        private readonly servicoImagem: ImagemService
                    ) { }

  public criarProduto(produto: Produto) {
    this.carregamento = true;
    const novoProduto = new Produto({
      id: produto.id,
      nome: produto.nome,
      preco: produto.preco,
      estoque: produto.estoque,
      unidadesVendidas: 0,
      detalhes: produto.detalhes,
      urlImagem: produto.urlImagem,
      negocioId: 1,
    });
    this.servicoProduto.criarProduto(novoProduto).subscribe( response => {
      this.toastr.success('Criado com sucesso', 'Tudo ok!', { progressBar: true, positionClass: 'toast-bottom-center' });
      this.verProdutos();
    },
    err => {
      this.toastr.error('Não foi possivel criar', 'ERRO', { progressBar: true, positionClass: 'toast-bottom-center' });
    }).add(() => {
      this.carregamento = false;
    });
  }

  public carregarImagem(evento: any, imagem: any) {   
    this.carregamento = true;
    const arquivo = evento.target.files[0];
    let formData = new FormData();
    formData.append('arquivo', arquivo, arquivo.name);
    this.servicoImagem.carregarFotoProduto(formData).subscribe( response => {
      this.form.patchValue({ urlImagem: response.url });
      imagem.src = `${response.url}`;
      imagem.hidden = false;
    },
    err => {
      this.toastr.error('Não foi possivel carregar a imagem', 'ERRO', { progressBar: true, positionClass: 'toast-bottom-center' });
    }).add(() => {
      this.carregamento = false;
    }); 
  }

  public verProdutos(): void {
    this.router.navigateByUrl('/negocio/admin/produtos');
  }

  ngOnInit(): void {
    this.form = this._fb.group({
      id: [null],
      nome: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(255)]],
      preco: ['', [Validators.required]],
      detalhes: ['', [Validators.minLength(4), Validators.maxLength(512)]],
      urlImagem: ['', Validators.required],
      estoque: ['']
    });
  }
}