import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-side-bar-admin',
  templateUrl: './side-bar-admin.component.html',
  styleUrls: ['./side-bar-admin.component.scss']
})
export class SideBarAdminComponent implements OnInit {

  public itens = [
    { icone: 'store', path: '/loja/produtos', detalhes: 'Página inicial' },
    { icone: 'fastfood', path: '/negocio/admin/produtos', detalhes: 'Meus Produtos' },
    { icone: 'supervisor_account', path: '/negocio/admin/clientes', detalhes: 'Meus Clientes' },
    { icone: 'edit_note', path: '/negocio/admin/pedidos', detalhes: 'Pedidos' },
    { icone: 'place', path: '/negocio/admin', detalhes: 'Meu Endereço' },
    { icone: 'manage_accounts', path: '/negocio/admin', detalhes: 'Minha Conta' },
    { icone: 'dashboard', path: '/negocio/admin', detalhes: 'Meu Dashboard' },
  ];

  public constructor(private readonly router: Router) { }

  public redirecionar(rota: string): void {
    this.router.navigateByUrl(rota);
  }

  ngOnInit(): void {}
}