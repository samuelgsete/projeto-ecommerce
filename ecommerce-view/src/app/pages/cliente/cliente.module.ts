import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SharedModule } from 'src/app/shared/shared.module';
import { AppRoutingModule } from 'src/app/app.routing.module';
import { EnderecoComponent } from './endereco/endereco.component';
import { ContaComponent } from './conta/conta.component';
import { ClienteService } from 'src/app/shared/services/cliente.service';
import { CriarClienteComponent } from './criar-cliente/criar-cliente.component';
import { ClienteComponent } from './cliente.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { PedidosComponent } from './pedidos/pedidos.component';
import { PedidoService } from 'src/app/shared/services/pedido.service';
import { VerPedidoComponent } from './ver-pedido/ver-pedido.component';
import { LoginClienteComponent } from './login/login-cliente.component';
import { ProdutosFavoritosComponent } from './produtos-favoritos/produtos-favoritos.component';

@NgModule({
  declarations: [
    ClienteComponent,
    EnderecoComponent,
    ContaComponent,
    CriarClienteComponent,
    DashboardComponent,
    PedidosComponent,
    VerPedidoComponent,
    LoginClienteComponent,
    ProdutosFavoritosComponent
  ],
  imports: [
    CommonModule,
    AppRoutingModule,
    SharedModule
  ],
  exports: [
    ClienteComponent,
    EnderecoComponent,
    ContaComponent,
    CriarClienteComponent,
    DashboardComponent,
    PedidosComponent,
    VerPedidoComponent,
    LoginClienteComponent,
    ProdutosFavoritosComponent
  ],
  providers: [
    ClienteService,
    PedidoService
  ]
})
export class ClienteModule { }