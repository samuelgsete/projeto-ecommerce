import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

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
  public quantidadeForm: FormControl = new FormControl("", { validators: Validators.required});

  public constructor(
                        private readonly router: Router,
                        private readonly route: ActivatedRoute,
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
    const adminId = parseInt(this.route.snapshot.queryParams['admin_id']);
    this.pedido.custo = this.servicoCarrinho.custo;
    this.pedido.itens = this.servicoCarrinho.items;
    this.pedido.adminId = adminId;
    this.pedido.situacao = 'PEDIDO_RECEBIDO';
  }

  public carregarCliente() {
    this.carregamento = true;
    const clienteId = parseInt(this.route.snapshot.queryParams['client_id']);
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
    this.servicoPedido.fazerPedido(this.pedido).subscribe(response => {      
      this.toastr.success('Pedido enviado', 'Tudo ok!', { progressBar: true, positionClass: 'toast-bottom-center' });
      this.servicoCarrinho.esvaziarCarrinho();
      this.continuarComprando();
    }, err => {
      this.toastr.error(err.error.mensagem, 'Há não!', { progressBar: true, positionClass: 'toast-bottom-center' });
    }).add(() => {
      this.carregamento = false;
    });
  }

  public continuarComprando(): void {
    this.router.navigate(['loja/produtos'], { queryParamsHandling: 'preserve' });
  }

  public verProduto(produtoId: number): void {
    this.router.navigateByUrl(`/loja/produtos/${produtoId}/ver`);
  }

  ngOnInit(): void {
    this.carregarCarrinho();    
    this.carregarCliente();
  }
}