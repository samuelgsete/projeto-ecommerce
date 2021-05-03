import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { ToastrService } from 'ngx-toastr';

import { Paginacao } from 'src/app/shared/models/paginacao.entity';
import { Produto } from 'src/app/shared/models/produto.entity';
import { ProdutoService } from 'src/app/shared/services/produto.service';

@Component({
  selector: 'app-listar-produtos',
  templateUrl: './listar-produtos.component.html',
  styleUrls: ['./listar-produtos.component.scss']
})
export class ListarProdutosComponent implements OnInit {

  public produtos: Array<Produto> = [];
  public carregamento: boolean = false;
  public paginacao: Paginacao = new Paginacao();
  public estoqueControl: FormControl;
  public produtoId: number = 0;

  public constructor(
                        private readonly router: Router,
                        private readonly toastr: ToastrService,
                        private readonly servicoProduto: ProdutoService
                    ) {}

  public listarProdutosPaginado(paginacao: Paginacao): void {
    const negocioId = 1;
    this.carregamento = true;
    this.servicoProduto.buscarProdutosPorIdNegocio(negocioId, paginacao).subscribe(response => {
      this.produtos = response.content;
      paginacao.ultima = response.last;
      paginacao.primeira = response.first;
      paginacao.totalElementos = response.totalElements;
    },
    err => {
      this.toastr.error(err.error.mensagem, 'ERRO', { progressBar: true, positionClass: 'toast-bottom-center' });
    }).add(() => {
      this.carregamento = false;
    });
  }

  public mudarPagina(direcao: boolean): void {
    const paginaAtual = this.paginacao.pagina;
    if(direcao) {
      this.paginacao.pagina = paginaAtual + 1;
    }
    else {
      this.paginacao.pagina = paginaAtual - 1;
    }
    this.listarProdutosPaginado(this.paginacao);
  }

  public maisPopulares(): void {
    this.paginacao = new Paginacao({ filtro: 'populares'});
    this.listarProdutosPaginado(this.paginacao);
  }

  public verProduto(produtoId: number): void {
    this.router.navigateByUrl(`negocio/admin/produtos/${produtoId}/editar`);
  }

  public criarProduto(): void {
    this.router.navigateByUrl('negocio/admin/produtos/criar');
  }

  public abrirModalEstoque(produto: Produto, modalEstoque: any): void {
    modalEstoque.show();
    this.estoqueControl.patchValue(produto.estoque);
    this.produtoId = produto.id;
  }

  public atualizarEstoque(modal: any): void {
    this.carregamento = true;
    this.servicoProduto.atualizarEstoque(this.produtoId, this.estoqueControl.value).subscribe(response => {
      this.toastr.success('O Estoque foi atualizado', 'Tudo ok!', { progressBar: true, positionClass: 'toast-bottom-center' });
      this.listarProdutosPaginado(this.paginacao);
    },
    err => {
      console.log(err);
      this.toastr.error(err.error.mensagem, 'ERRO', { progressBar: true, positionClass: 'toast-bottom-center' });
    }).add(() => {
      this.carregamento = false;
      modal.hide();
    });
  }

  ngOnInit(): void {
    this.listarProdutosPaginado(this.paginacao);
    this.estoqueControl = new FormControl('', {
      validators: [Validators.required]
    });
  }
}