import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.scss']
})
export class SideBarComponent implements OnInit {

  public itens = [
    { icone: 'shop', path: '/loja/produtos', detalhes: 'Página inicial' },
    { icone: 'edit_note', path: '/cliente/pedidos', detalhes: 'Meus pedidos' },
    { icone: 'favorite', path: '/cliente/produtos/favoritos', detalhes: 'Produtos favoritos' },
    { icone: 'manage_accounts', path: '/cliente/conta', detalhes: 'Minha conta' },
    { icone: 'place', path: '/cliente/endereco', detalhes: 'Meu endereço' },
    { icone: 'dashboard', path: '/cliente/dashboard', detalhes: 'Ir para o dashboard' },
  ];

  public constructor(private readonly router: Router) { }

  public redirecionar(rota: string): void {
    this.router.navigateByUrl(rota);
  }

  ngOnInit(): void {}
}