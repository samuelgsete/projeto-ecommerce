import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { ToastrService } from 'ngx-toastr';

import { Produto } from 'src/app/shared/models/produto.entity';
import { ProdutoService } from 'src/app/shared/services/produto.service';
import { Paginacao } from 'src/app/shared/models/paginacao.entity';

@Component({
  selector: 'app-produtos',
  templateUrl: './produtos.component.html',
  styleUrls: ['./produtos.component.scss']
})
export class ProdutosComponent implements OnInit {

  public produtos: Array<Produto> = [];
  public carregamento: boolean = false;
  public paginacao: Paginacao = new Paginacao();
  
  public constructor(
                      private readonly router: Router,
                      private readonly route: ActivatedRoute,
                      private readonly toastr: ToastrService,
                      private readonly servicoProduto: ProdutoService,
  ) { }

  public listarProdutosPaginado(paginacao: Paginacao): void {
    const adminId = parseInt(this.route.snapshot.queryParams['admin_id']);
    this.carregamento = true;
    this.servicoProduto.buscarProdutosPorIdAdminPaginado(adminId, paginacao).subscribe( response => {
      this.produtos = response.content;
      paginacao.ultima = response.last;
      paginacao.primeira = response.first;
      paginacao.totalElementos = response.totalElements;
    },
    err => {
      this.toastr.error('Não foi possivel carregar os dados', 'ERRO', { progressBar: true, positionClass: 'toast-bottom-center' });
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

  public maiorPreco(): void {
    this.paginacao = new Paginacao({ filtro: 'maior_preco'});
    this.listarProdutosPaginado(this.paginacao);
  }

  public menorPreco(): void {
    this.paginacao = new Paginacao({ filtro: 'menor_preco'});
    this.listarProdutosPaginado(this.paginacao);
  }

  public comprarProduto(produtoId: number): void {
    this.router.navigate([`loja/produtos/${produtoId}/ver`], { queryParamsHandling: 'preserve' });
  }

  ngOnInit(): void {
    this.listarProdutosPaginado(this.paginacao);
  }
}