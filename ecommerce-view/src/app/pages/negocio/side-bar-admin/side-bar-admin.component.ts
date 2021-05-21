import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-side-bar-admin',
  templateUrl: './side-bar-admin.component.html',
  styleUrls: ['./side-bar-admin.component.scss']
})
export class SideBarAdminComponent implements OnInit {

  public itens = [
    { icone: 'store', path: '/loja/produtos', tooltip: 'Página inicial' },
    { icone: 'fastfood', path: '/negocio/admin/produtos', tooltip: 'Meus Produtos' },
    { icone: 'supervisor_account', path: '/negocio/admin/clientes', tooltip: 'Meus Clientes' },
    { icone: 'edit_note', path: '/negocio/admin/pedidos', tooltip: 'Pedidos' },
    { icone: 'place', path: '/negocio/admin/endereco', tooltip: 'Meu Endereço' },
    { icone: 'manage_accounts', path: '/negocio/admin/editar', tooltip: 'Editar Conta' },
    { icone: 'dashboard', path: '/negocio/admin', tooltip: 'Meu Dashboard' },
  ];

  public constructor(private readonly router: Router) { }

  public redirecionar(rota: string): void {
    this.router.navigate([rota], { queryParamsHandling: 'preserve' })
  }

  ngOnInit(): void {}
}