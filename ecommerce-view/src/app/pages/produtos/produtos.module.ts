import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProdutoDetalhesComponent } from './produto-detalhes/produto-detalhes.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { ProdutoService } from 'src/app/shared/services/produto.service';
import { CriarProdutoComponent } from './criar-produto/criar-produto.component';
import { ImagemService } from 'src/app/shared/services/imagem.service';
import { EditarProdutoComponent } from './editar-produto/editar-produto.component';
import { ProdutosComponent } from './produtos.component';

@NgModule({
  declarations: [
    ProdutoDetalhesComponent,
    CriarProdutoComponent,
    EditarProdutoComponent,
    ProdutosComponent
  ],
  imports: [
    CommonModule,
    SharedModule
  ],
  exports: [
    ProdutoDetalhesComponent,
    CriarProdutoComponent
  ],
  providers: [
    ProdutoService,
    ImagemService
  ]
})
export class ProdutosModule { }