import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { ToastrService } from 'ngx-toastr';

import { Cliente } from 'src/app/shared/models/cliente.entity';
import { Pedido } from 'src/app/shared/models/pedido.entity';
import { CarrinhoService } from 'src/app/shared/services/carrinho.service';
import { ClienteService } from 'src/app/shared/services/cliente.service';
import { PedidoService } from 'src/app/shared/services/pedido.service';

@Component({
  selector: 'app-carrinho',
  templateUrl: './carrinho.component.html',
  styleUrls: ['./carrinho.component.scss']
})
export class CarrinhoComponent implements OnInit {

  public carregamento: boolean = false;
  public pedido: Pedido = new Pedido();
  public cliente: Cliente = new Cliente({ feitoEm: new Date() });
  public quantidadeForm = new FormControl("", { validators: Validators.required});

  public constructor(
                        private readonly router: Router,
                        private readonly toastr: ToastrService,
                        private readonly servicoCarrinho: CarrinhoService, 
                        private readonly servicoCliente: ClienteService,
                        private readonly servicoPedido: PedidoService
                        
                    ) { }

  public removerItem(indice: number): void {
    this.servicoCarrinho.removerItem(indice);
    this.carregarCarrinho();
  }

  public carregarCarrinho(): void {
    const negocioId = 1;
    this.pedido.custo = this.servicoCarrinho.custo;
    this.pedido.itens = this.servicoCarrinho.items;
    this.pedido.negocioId = negocioId;
    this.pedido.situacao = 'PEDIDO_RECEBIDO';
  }

  public carregarCliente() {
    const clienteId = 1;
    this.carregamento = true;
    this.servicoCliente.buscarClientePorId(clienteId).subscribe(response => {
      this.cliente = response;
    },
    err => {
      this.toastr.error('Não foi carregar o cliente', 'Há não!', { progressBar: true, positionClass: 'toast-bottom-center' });
    }).add(() => {
      this.carregamento = false;
    });  
  }

  public fazerPedido(cliente: Cliente) {
    this.carregamento = true;
    this.pedido.cliente = cliente;
    console.log(this.pedido);
    this.servicoPedido.fazerPedido(this.pedido).subscribe( response => {
      this.toastr.success('Pedido enviado', 'Tudo ok!', { progressBar: true, positionClass: 'toast-bottom-center' });
      this.servicoCarrinho.esvaziarCarrinho();
      this.router.navigateByUrl('loja/produtos');
    }, err => {
      this.toastr.error(err.error.mensagem, 'Há não!', { progressBar: true, positionClass: 'toast-bottom-center' });
    }).add(() => {
      this.carregamento = false;
    });
  }

  public continuarComprando(): void {
    this.router.navigateByUrl('loja/produtos');
  }

  public verProduto(produtoId: number): void {
    this.router.navigateByUrl(`/loja/produtos/${produtoId}/ver`);
  }

  ngOnInit(): void {
    this.carregarCarrinho();    
    this.carregarCliente();
  }
}