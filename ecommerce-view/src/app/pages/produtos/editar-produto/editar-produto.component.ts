import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { ToastrService } from 'ngx-toastr';
import Swal from 'sweetalert2';

import { Produto } from 'src/app/shared/models/produto.entity';
import { ImagemService } from 'src/app/shared/services/imagem.service';
import { ProdutoService } from 'src/app/shared/services/produto.service';

@Component({
  selector: 'app-editar-produto',
  templateUrl: './editar-produto.component.html',
  styleUrls: ['./editar-produto.component.scss']
})
export class EditarProdutoComponent implements OnInit {

  public form: FormGroup;
  public produto: Produto = new Produto();
  public carregamento: boolean = false;
  
  public constructor(
                        private readonly _fb: FormBuilder,
                        private readonly toastr: ToastrService,
                        private readonly router: Router,
                        private readonly servicoProduto: ProdutoService,
                        private readonly servicoImagem: ImagemService
                    ) { }

  public carregarProduto(): void {
    const produtoId = parseInt(this.router.url.split('/')[4]);
    this.carregamento = true;
    this.servicoProduto.buscarProdutoPorId(produtoId).subscribe( response => {
      this.produto = response;
      this.atualizarFormulario(this.produto);
    },
    err => {
      this.toastr.error('Não foi possivel carregar o produto', 'ERRO', { progressBar: true, positionClass: 'toast-bottom-center' });
    }).add(() => {
      this.carregamento = false;
    });
  }

  public atualizarProduto(produto: Produto, produtoId: number): void {
    this.carregamento = true;
    const produtoAtualizado = new Produto({
      id: produtoId,
      nome: produto.nome,
      preco: produto.preco,
      estoque: produto.estoque,
      unidadesVendidas: produto.unidadesVendidas,
      detalhes: produto.detalhes,
      urlImagem: produto.urlImagem,
      negocioId: produto.negocioId,
    });
    this.servicoProduto.atualizarProduto(produtoId, produtoAtualizado).subscribe( response => {
      this.toastr.success('Atualizado com sucesso', 'Tudo ok!', { progressBar: true, positionClass: 'toast-bottom-center' });
    },
    err => {
      this.toastr.error('Não foi possivel atualizar', 'ERRO', { progressBar: true, positionClass: 'toast-bottom-center' });
    }).add(() => {
      this.carregamento = false;
    });
  }

  public atualizarFormulario(produto: Produto): void {
    this.form.patchValue({
      id: produto.id,
      nome: produto.nome,
      preco: produto.preco,
      estoque: produto.estoque,
      detalhes: produto.detalhes,
      unidadesVendidas: produto.unidadesVendidas,
      urlImagem: produto.urlImagem,
      negocioId: produto.negocioId
    });
  }

  public carregarImagem(evento: any, imagem: any): void {   
    this.carregamento = true;
    const arquivo = evento.target.files[0];
    let formData = new FormData();
    formData.append('arquivo', arquivo, arquivo.name);
    this.servicoImagem.carregarFotoProduto(formData).subscribe( response => {
      this.form.patchValue({ urlImagem: response.url });
      imagem.src = `${response.url}`;
    },
    err => {
      this.toastr.error('Não foi possivel carregar a imagem', 'ERRO', { progressBar: true, positionClass: 'toast-bottom-center' });
    }).add(() => {
      this.carregamento = false;
    }); 
  }

  public removerProduto(produtoId: number) {
    Swal.fire({
      title: 'Tem certeza que deseja cencelar o pedido?',
      text: 'Você não poderá desfazer essa operação',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sim',
      cancelButtonText: 'Não'
    }).then((result) => {
      if (result.value) {
        this.servicoProduto.removerProduto(produtoId).subscribe(response => {
          this.toastr.success('O produto foi removido', 'Tudo ok!', { progressBar: true, positionClass: 'toast-bottom-center' });
        },
        err => {
          this.toastr.error(err.error.mensagem, 'ERRO', { progressBar: true, positionClass: 'toast-bottom-center' });
        }).add(() =>{
          this.carregamento = false;
        });
      } 
    })
  }

  public verProdutos(): void {
    this.router.navigateByUrl('/negocio/admin/produtos');
  }

  public criarProduto(): void {
    this.router.navigateByUrl('/negocio/admin/produtos/criar');
  }

  ngOnInit(): void {
    this.carregarProduto();
    this.form = this._fb.group({
      id: [''],
      nome: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(255)]],
      preco: ['', [Validators.required]],
      detalhes: ['', [Validators.minLength(4), Validators.maxLength(512)]],
      urlImagem: ['', Validators.required],
      estoque: ['', Validators.required],
      unidadesVendidas: [''],
      negocioId: ['']
    });
  }
}