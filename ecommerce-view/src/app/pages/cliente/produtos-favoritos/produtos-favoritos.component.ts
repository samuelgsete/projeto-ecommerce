import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { ToastrService } from 'ngx-toastr';

import { Paginacao } from 'src/app/shared/models/paginacao.entity';
import { ClienteService } from 'src/app/shared/services/cliente.service';

@Component({
  selector: 'app-produtos-favoritos',
  templateUrl: './produtos-favoritos.component.html',
  styleUrls: ['./produtos-favoritos.component.scss']
})
export class ProdutosFavoritosComponent implements OnInit {

  public carregamento: boolean = false;
  public paginacao: Paginacao = new Paginacao();
  public produtosFavoritos: Array<any> = [];

  public constructor(
                        private readonly router: Router,
                        private readonly toastr: ToastrService,
                        private readonly servicoCliente: ClienteService
                       ) {}
  
  public listarMeusProdutosFavoritos(paginacao: Paginacao) {
    const clienteId = 1;
    this.carregamento = true;
    this.servicoCliente.listarProdutosFavoritos(clienteId, this.paginacao).subscribe(response => {
      this.produtosFavoritos = response.content;
      paginacao.ultima = response.last;
      paginacao.primeira = response.first;
      paginacao.totalElementos = response.totalElements;
    },
    err => {
      console.log(err);
      this.toastr.error('Não foi carregar os produtos favoritos', 'Há não!', { progressBar: true, positionClass: 'toast-bottom-center' });
    }).add(() => {
      this.carregamento = false;
    });
  } 

  public mudarPagina(direcao: boolean): void {
    const paginaAtual = this.paginacao.pagina;
    if(direcao) {
      this.paginacao.pagina = paginaAtual + 1;
    }
    else {
      this.paginacao.pagina = paginaAtual - 1;
    }
    this.listarMeusProdutosFavoritos(this.paginacao);
  }
  
  public verPedidos(): void  {
    this.router.navigateByUrl('cliente/pedidos');
  }

  public irParaLoja(): void {
    this.router.navigateByUrl('loja/produtos');
  }

  public verProduto(produtoId: number): void {
    this.router.navigateByUrl(`/loja/produtos/${produtoId}/ver`);
  }
  
  ngOnInit(): void {
    this.listarMeusProdutosFavoritos(this.paginacao);
  }
}