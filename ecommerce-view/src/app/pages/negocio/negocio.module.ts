import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SharedModule } from 'src/app/shared/shared.module';
import { RouterModule } from '@angular/router';
import { ClienteService } from 'src/app/shared/services/cliente.service';
import { NegocioService } from 'src/app/shared/services/negocio.service';
import { ProdutoService } from 'src/app/shared/services/produto.service';
import { PedidoService } from 'src/app/shared/services/pedido.service';
import { CriarNegocioComponent } from './criar-negocio/criar-negocio.component';
import { LoginComponent } from './login/login.component';
import { MenuAdminComponent } from './menu-admin/menu-admin.component';
import { NegocioComponent } from './negocio.component';
import { SideBarAdminComponent } from './side-bar-admin/side-bar-admin.component';
import { FooterAdminComponent } from './footer-admin/footer-admin.component';
import { ListarProdutosComponent } from './listar-produtos/listar-produtos.component';
import { ListarClientesComponent } from './listar-clientes/listar-clientes.component';
import { ListarPedidosComponent } from './listar-pedidos/listar-pedidos.component';
import { EntregarPedidoComponent } from './entregar-pedido/entregar-pedido.component';
import { HistoricoClienteComponent } from './historico-cliente/historico-cliente.component';

@NgModule({
  declarations: [
    CriarNegocioComponent,
    LoginComponent,
    MenuAdminComponent,
    SideBarAdminComponent,
    NegocioComponent,
    FooterAdminComponent,
    ListarProdutosComponent,
    ListarClientesComponent,
    ListarPedidosComponent,
    EntregarPedidoComponent,
    HistoricoClienteComponent,
  ],
  imports: [
    CommonModule,
    SharedModule,
    RouterModule
  ],
  exports: [
    CriarNegocioComponent,
    LoginComponent,
    MenuAdminComponent,
    SideBarAdminComponent,
    NegocioComponent,
    ListarProdutosComponent,
    ListarClientesComponent,
    ListarPedidosComponent,
    EntregarPedidoComponent,
    HistoricoClienteComponent
  ],
  providers: [
    NegocioService,
    ProdutoService,
    ClienteService,
    PedidoService
  ]
})
export class NegocioModule { }