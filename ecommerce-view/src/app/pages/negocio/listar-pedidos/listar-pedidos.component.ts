import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { ToastrService } from 'ngx-toastr';

import { Paginacao } from 'src/app/shared/models/paginacao.entity';
import { Pedido } from 'src/app/shared/models/pedido.entity';
import { PedidoService } from 'src/app/shared/services/pedido.service';

@Component({
  selector: 'app-listar-pedidos',
  templateUrl: './listar-pedidos.component.html',
  styleUrls: ['./listar-pedidos.component.scss']
})
export class ListarPedidosComponent implements OnInit {

  public pedidos: Array<Pedido> = [];
  public carregamento: boolean = false;
  public paginacao:Paginacao = new Paginacao();

  public constructor(
                        private readonly router: Router,
                        private readonly toastr: ToastrService,
                        private readonly servicoPedido: PedidoService
                    ) {}
  public listarPedidosPaginado(paginacao: Paginacao): void {
    const negocioId = 1;
    this.carregamento = true;
    this.servicoPedido.buscarPedidosPorIdNegocio(negocioId, paginacao).subscribe(response => {
      this.pedidos = response.content;
      paginacao.ultima = response.last;
      paginacao.primeira = response.first;
      paginacao.totalElementos = response.totalElements;
    },
    err => {
      this.toastr.error(err.error.mensagem, 'ERRO', { progressBar: true, positionClass: 'toast-bottom-center' });
    }).add(() => {
      this.carregamento = false;
    });
  }

  public maisRecentes() {
    this.paginacao = new Paginacao({ filtro: 'recentes'});
    this.listarPedidosPaginado(this.paginacao);
  }

  public maisAntigos() {
    this.paginacao = new Paginacao({ filtro: 'antigos'});
    this.listarPedidosPaginado(this.paginacao);
  }

  public mudarPagina(direcao: boolean): void {
    const paginaAtual = this.paginacao.pagina;
    if(direcao) {
      this.paginacao.pagina = paginaAtual + 1;
    }
    else {
      this.paginacao.pagina = paginaAtual - 1;
    }
    this.listarPedidosPaginado(this.paginacao);
  }

  public verPedido(pedidoId: number): void {
    this.router.navigate([`negocio/admin/pedidos/${pedidoId}/ver`], { queryParamsHandling: 'preserve' });
  }

  ngOnInit(): void {
    this.listarPedidosPaginado(this.paginacao);
  }
}