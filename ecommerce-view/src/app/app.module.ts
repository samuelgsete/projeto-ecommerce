
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app.routing.module';
import { ProdutosModule } from './pages/produtos/produtos.module';
import { CarrinhoService } from './shared/services/carrinho.service';
import { ClienteModule } from './pages/cliente/cliente.module';
import { LojaModule } from './pages/loja/loja.module';
import { NegocioModule } from './pages/negocio/negocio.module';
import { AuthService } from './shared/services/auth.service';
import { AuthUserInterceptor } from './shared/auth/auth-user.interceptor';
import { AuthAdminGuard } from './shared/auth/auth-admin.guard';
import { AuthClientGuard } from './shared/auth/auth-cliente.guard';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    FormsModule,
    AppRoutingModule,
    ProdutosModule,
    ClienteModule,
    LojaModule,
    NegocioModule
  ],
  providers: [
    CarrinhoService,
    AuthService,
    AuthAdminGuard,
    AuthClientGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthUserInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }