import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { ToastrService } from 'ngx-toastr';
import { ItemPedido } from 'src/app/shared/models/item-pedido';

import { Produto } from 'src/app/shared/models/produto.entity';
import { CarrinhoService } from 'src/app/shared/services/carrinho.service';
import { ProdutoService } from 'src/app/shared/services/produto.service';

@Component({
  selector: 'app-produto-detalhes',
  templateUrl: './produto-detalhes.component.html',
  styleUrls: ['./produto-detalhes.component.scss']
})
export class ProdutoDetalhesComponent implements OnInit {

  public produto: Produto = new Produto();
  public quantidadeForm: FormControl = new FormControl();
  public carregamento: boolean = true;

  public constructor(
                      private readonly router: Router,
                      private readonly toastr: ToastrService,
                      private readonly servicoProduto: ProdutoService,
                      private readonly servicoCarrinho: CarrinhoService
                    ) { }

  public carregarProduto(): void {
    const produtoId = parseInt(this.router.url.split('/')[3]);
    this.carregamento = true;
    this.servicoProduto.buscarProdutoPorId(produtoId).subscribe( response => {
      this.produto = response;
      this.quantidadeForm = new FormControl('', {
        validators: [
          Validators.required,
          Validators.min(1),
          Validators.max(this.produto.estoque)
        ]
      });
    },
    err => {
      this.toastr.error('Não foi possivel carregar os dados', 'ERRO', { progressBar: true, positionClass: 'toast-bottom-center' });
    }).add(() => {
      this.carregamento = false;
    });
  }

  public continuarComprando(): void {
    this.router.navigate(["/loja/produtos"], { queryParamsHandling: 'preserve' });
  }

  public meusPedidos(): void {
    this.router.navigate(["/cliente/pedidos"], { queryParamsHandling: 'preserve' });
  }

  public adicionarItem(quantidade: number, produto: Produto): void {
    this.servicoCarrinho.adicionar(
      new ItemPedido({
        quantidade: quantidade,
        produto: produto
      })
    )
    this.toastr.success('O produto foi adicionado ao carrinho', 'Tudo certo!', { progressBar: true, positionClass: 'toast-bottom-center' });
  }

  ngOnInit(): void {
    this.carregarProduto();
  }
}