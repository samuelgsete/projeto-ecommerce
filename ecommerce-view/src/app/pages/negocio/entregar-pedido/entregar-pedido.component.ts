import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { ToastrService } from 'ngx-toastr';

import { Pedido } from 'src/app/shared/models/pedido.entity';
import { PedidoService } from 'src/app/shared/services/pedido.service';

@Component({
  selector: 'app-entregar-pedido',
  templateUrl: './entregar-pedido.component.html',
  styleUrls: ['./entregar-pedido.component.scss']
})
export class EntregarPedidoComponent implements OnInit {

  public pedido: Pedido = new Pedido();
  public carregamento: boolean = false;
  public situacaoControl: FormControl;
  public situacaoPedido = [
    'PEDIDO_RECEBIDO',
    'SAIU_PARA_ENTEGA',
    'PEDIDO_ENTREGUE'
  ];
  
  public constructor(
                        private readonly router: Router,
                        private readonly toastr: ToastrService,
                        private readonly servicoPedido: PedidoService
                    ) {}

  public carregarPedido(): void {
    const pedidoId = parseInt(this.router.url.split('/')[4]);
    this.carregamento = true;
    this.servicoPedido.buscarPedidoPorId(pedidoId).subscribe(response => {
      this.pedido = response;
    },
    err => {
      this.toastr.error(err.error.mensagem, 'Tudo ok!', { progressBar: true, positionClass: 'toast-bottom-center' });
    }).add(() =>{
      this.carregamento = false;
    });
  }

  public atualizarSituacaoPedido(): void {
    const pedidoId = parseInt(this.router.url.split('/')[4]);
    this.carregamento = true;
    this.servicoPedido.atualizarSituacaoPedido(pedidoId, this.situacaoControl.value).subscribe(response => {
      this.toastr.success('Pedido atualizado', 'ERRO', { progressBar: true, positionClass: 'toast-bottom-center' });
      this.carregarPedido();
    },
    err => {
      this.toastr.error(err.error.mensagem, 'ERRO', { progressBar: true, positionClass: 'toast-bottom-center' });
    }).add(() =>{
      this.carregamento = false;
    });
  }

  public verPedidos(): void  {
    this.router.navigate(['/negocio/admin/pedidos'], { queryParamsHandling: 'preserve' });
  }

  public verProduto(): void {
    
  }

  ngOnInit(): void {
    this.carregarPedido();
    this.situacaoControl = new FormControl('', {
      validators: Validators.required
    })
  }
}