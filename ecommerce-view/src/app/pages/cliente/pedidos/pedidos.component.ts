import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { ToastrService } from 'ngx-toastr';
import Swal from 'sweetalert2';

import { Paginacao } from 'src/app/shared/models/paginacao.entity';
import { Pedido } from 'src/app/shared/models/pedido.entity';
import { PedidoService } from 'src/app/shared/services/pedido.service';

@Component({
  selector: 'app-pedidos',
  templateUrl: './pedidos.component.html',
  styleUrls: ['./pedidos.component.scss']
})
export class PedidosComponent implements OnInit {

  public pedidos: Pedido[] = [];
  public paginacao: Paginacao = new Paginacao();
  public carregamento: boolean = false;

  public constructor(
                      private readonly router: Router,
                      private readonly toastr: ToastrService,
                      private readonly servicoPedido: PedidoService
                    ) { }

  public buscarPedidosPorIdCliente(): void {
    const clienteId = 1;
    this.carregamento = true;
    this.servicoPedido.buscarPedidosPorIdCliente(clienteId, this.paginacao).subscribe(response => {
      this.pedidos = response.content;
    },
    err => {
      this.toastr.error('Não foi carregar os pedidos', 'ERRO', { progressBar: true, positionClass: 'toast-bottom-center' });
    }).add(() =>{
      this.carregamento = false;
    });
  }

  public cancelarPedido(pedidoId: number): void {
    this.carregamento = true;
    Swal.fire({
      title: 'Tem certeza que deseja cencelar o pedido?',
      text: 'Você não poderá desfazer essa operação',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sim',
      cancelButtonText: 'Não'
    }).then((result) => {
      if (result.value) {
        this.servicoPedido.cancelarPedido(pedidoId).subscribe(response => {
          this.buscarPedidosPorIdCliente();
          this.toastr.success('O pedido foi cancelado', 'Tudo ok!', { progressBar: true, positionClass: 'toast-bottom-center' });
        },
        err => {
          this.toastr.error(err.error.mensagem, 'ERRO', { progressBar: true, positionClass: 'toast-bottom-center' });
        }).add(() =>{
          this.carregamento = false;
        });
      } 
    });
  }

  public verPedido(pedidoId: number): void {
    this.router.navigateByUrl(`cliente/pedido/${pedidoId}/ver`);
  }

  public continuarComprando(): void {
    this.router.navigateByUrl("/loja/produtos");
  }

  ngOnInit(): void {
    this.buscarPedidosPorIdCliente();
  }
}