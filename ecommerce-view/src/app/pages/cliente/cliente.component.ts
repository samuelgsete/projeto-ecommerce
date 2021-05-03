import { Component, OnInit } from '@angular/core';

import { ToastrService } from 'ngx-toastr';
import { Cliente } from 'src/app/shared/models/cliente.entity';

import { ClienteService } from 'src/app/shared/services/cliente.service';

@Component({
  selector: 'app-cliente',
  templateUrl: './cliente.component.html',
  styleUrls: ['./cliente.component.scss']
})
export class ClienteComponent implements OnInit {

  public cliente: Cliente = new Cliente();
  public carregamento: boolean = false;
 
  public constructor(
                       private readonly toastr: ToastrService,
                       private readonly servicoCliente: ClienteService
                    ) { }

  public carregarCliente(): void {
    const clienteId = 1;
    this.carregamento = true;
    this.servicoCliente.buscarClientePorId(clienteId).subscribe(response => {
      this.cliente = response;
    },
    err => {
      this.toastr.error('Não foi carregar o cliente', 'Há não!', { progressBar: true, positionClass: 'toast-bottom-center' });
    }).add(() => {
      this.carregamento = false;
    })
  }

  ngOnInit(): void {
    this.carregarCliente();
  }
}