
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
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
    CarrinhoService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }