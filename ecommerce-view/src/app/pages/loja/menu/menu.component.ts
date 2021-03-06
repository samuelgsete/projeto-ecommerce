import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { CarrinhoService } from 'src/app/shared/services/carrinho.service';
import { LayoutComponent } from '../layout/layout.component';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {

  public cliente: string = "Layla";
  public quantidade: number;
  @Input('layout') layout: LayoutComponent;

  public constructor(
                        private readonly servicoCarrinho: CarrinhoService, 
                        private readonly router: Router,
                        private readonly route: ActivatedRoute
                    ) 
  { 
    this.cliente = this.route.snapshot.queryParams['name'];

    this.servicoCarrinho.adicionarItem.subscribe(item => {
      this.quantidade = servicoCarrinho.items.length;
    });
  }

  public ocultarOuExibirSideBar(): void {
    this.layout.ocultarOuExibir();
  }

  public irParaCarrinho() {
    this.router.navigate(["loja/carrinho"], { queryParamsHandling: 'preserve' });
  }

  ngOnInit(): void {}
}