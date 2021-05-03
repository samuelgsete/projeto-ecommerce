import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { SharedModule } from 'src/app/shared/shared.module';
import { LojaComponent } from './loja.component';
import { MenuComponent } from './menu/menu.component';
import { SideBarComponent } from './side-bar/side-bar.component';
import { CarrinhoComponent } from './carrinho/carrinho.component';
import { FooterComponent } from './footer/footer.component';
import { LayoutComponent } from './layout/layout.component';
import { NegocioService } from 'src/app/shared/services/negocio.service';

@NgModule({
  declarations: [
    LojaComponent,
    CarrinhoComponent,
    MenuComponent,
    SideBarComponent,
    FooterComponent,
    LayoutComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    SharedModule
  ],
  exports: [
    LojaComponent,
    CarrinhoComponent,
    MenuComponent,
    SideBarComponent,
    FooterComponent,
    LayoutComponent
  ],
  providers: [
    NegocioService
  ]
})
export class LojaModule { }