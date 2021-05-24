  
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ProdutoDetalhesComponent } from './pages/produtos/produto-detalhes/produto-detalhes.component';
import { CriarProdutoComponent } from './pages/produtos/criar-produto/criar-produto.component';
import { EditarProdutoComponent } from './pages/produtos/editar-produto/editar-produto.component';
import { ProdutosComponent } from './pages/produtos/produtos.component';
import { CriarClienteComponent } from './pages/cliente/criar-cliente/criar-cliente.component';
import { LojaComponent } from './pages/loja/loja.component';
import { CarrinhoComponent } from './pages/loja/carrinho/carrinho.component';
import { ClienteComponent } from './pages/cliente/cliente.component';
import { ContaComponent } from './pages/cliente/conta/conta.component';
import { EnderecoComponent } from './pages/cliente/endereco/endereco.component';
import { DashboardComponent } from './pages/cliente/dashboard/dashboard.component';
import { PedidosComponent } from './pages/cliente/pedidos/pedidos.component';
import { VerPedidoComponent } from './pages/cliente/ver-pedido/ver-pedido.component';
import { LayoutComponent } from './pages/loja/layout/layout.component';
import { CriarNegocioComponent } from './pages/negocio/criar-negocio/criar-negocio.component';
import { LoginComponent } from './pages/negocio/login/login.component';
import { LoginClienteComponent } from './pages/cliente/login/login-cliente.component';
import { NegocioComponent } from './pages/negocio/negocio.component';
import { ListarProdutosComponent } from './pages/negocio/listar-produtos/listar-produtos.component';
import { ListarClientesComponent } from './pages/negocio/listar-clientes/listar-clientes.component';
import { ListarPedidosComponent } from './pages/negocio/listar-pedidos/listar-pedidos.component';
import { EntregarPedidoComponent } from './pages/negocio/entregar-pedido/entregar-pedido.component';
import { ProdutosFavoritosComponent } from './pages/cliente/produtos-favoritos/produtos-favoritos.component';
import { HistoricoClienteComponent } from './pages/negocio/historico-cliente/historico-cliente.component';
import { AdminEnderecoComponent } from './pages/negocio/admin-endereco/admin-endereco.component';
import { EditarContaComponent } from './pages/negocio/editar-conta/editar-conta.component';
import { AuthAdminGuard } from './shared/auth/auth-admin.guard';
import { AuthClientGuard } from './shared/auth/auth-cliente.guard';

@NgModule({
    imports: [
        RouterModule.forRoot([
            {
                path: '',
                component: LayoutComponent,
                children: [
                    { 
                        path: 'loja', 
                        component: LojaComponent , 
                        children: [
                            { path: 'produtos', component: ProdutosComponent },
                            { path: 'produtos/:id/ver', component: ProdutoDetalhesComponent },
                            { path: 'carrinho', component: CarrinhoComponent },
                        ]
                    },
                    {
                        path: 'cliente',
                        component: ClienteComponent,
                        canActivate: [AuthClientGuard],
                        children: [
                            { path: 'dashboard', component: DashboardComponent },
                            { path: 'conta', component: ContaComponent },
                            { path: 'endereco', component: EnderecoComponent },
                            { path: 'pedidos', component: PedidosComponent },
                            { path: 'pedido/:id/ver', component: VerPedidoComponent },
                            { path: 'produtos/favoritos', component: ProdutosFavoritosComponent }
                        ]
                    }
                ]
            },
            { path: 'negocio/criar', component: CriarNegocioComponent },
            { path: 'negocio/login', component: LoginComponent },
            { path: 'loja/cliente/login', component: LoginClienteComponent },
            { path: 'loja/cliente/criar', component: CriarClienteComponent },
            {
                path: 'negocio/admin',
                component: NegocioComponent,
                canActivate: [AuthAdminGuard],
                children: [
                    { path: 'editar', component: EditarContaComponent },
                    { path: 'endereco', component: AdminEnderecoComponent },
                    { path: 'produtos', component: ListarProdutosComponent },
                    { path: 'produtos/criar', component: CriarProdutoComponent },
                    { path: 'produtos/:id/editar', component: EditarProdutoComponent },
                    { path: 'clientes', component: ListarClientesComponent },
                    { path: 'pedidos', component: ListarPedidosComponent },
                    { path: 'pedidos/:id/ver', component: EntregarPedidoComponent },
                    { path: 'clientes/:id/historico', component: HistoricoClienteComponent }
                ]
            }
        ])
    ],
    exports: [
        RouterModule
    ]
})
export class AppRoutingModule {}