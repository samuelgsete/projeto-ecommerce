import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { ToastrService } from 'ngx-toastr';
import Swal from 'sweetalert2';

import { Pedido } from 'src/app/shared/models/pedido.entity';
import { PedidoService } from 'src/app/shared/services/pedido.service';

@Component({
  selector: 'app-ver-pedido',
  templateUrl: './ver-pedido.component.html',
  styleUrls: ['./ver-pedido.component.scss']
})
export class VerPedidoComponent implements OnInit {

  public pedido: Pedido = new Pedido();
  public carregamento: boolean = true;

  public constructor(
                      private readonly router: Router,
                      private readonly toastr: ToastrService,
                      private readonly servicoPedido: PedidoService
                    ) { }

  public carregarPedido() {
    const pedidoId = parseInt(this.router.url.split('/')[3]);
    this.carregamento = true;
    this.servicoPedido.buscarPedidoPorId(pedidoId).subscribe(response => {
      this.pedido = response;
    },
    err => {
      this.toastr.error('Não foi carregar o pedido', 'ERRO', { progressBar: true, positionClass: 'toast-bottom-center' });
    }).add(() =>{
      this.carregamento = false;
    });
  }

  public verPedidos(): void  {
    this.router.navigate(['cliente/pedidos'], { queryParamsHandling: 'preserve'});
  }

  public irParaLoja(): void {
    this.router.navigate(['loja/produtos'], { queryParamsHandling: 'preserve'});
  }

  public verProduto(produtoId: number): void {
    this.router.navigate([`/loja/produtos/${produtoId}/ver`], { queryParamsHandling: 'preserve'});
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
          this.carregarPedido();
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

  ngOnInit(): void {
    this.carregarPedido();
  }
}