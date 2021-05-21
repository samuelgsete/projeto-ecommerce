import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { ToastrService } from 'ngx-toastr';

import { Cliente } from 'src/app/shared/models/cliente.entity';
import { Paginacao } from 'src/app/shared/models/paginacao.entity';
import { ClienteService } from 'src/app/shared/services/cliente.service';

@Component({
  selector: 'app-listar-clientes',
  templateUrl: './listar-clientes.component.html',
  styleUrls: ['./listar-clientes.component.scss']
})
export class ListarClientesComponent implements OnInit {

  public clientes: Array<Cliente> = [];
  public carregamento: boolean = false;
  public paginacao = new Paginacao();

  public constructor(
                        private readonly router: Router,
                        private readonly toastr: ToastrService,
                        private readonly servicoCliente: ClienteService
                      ) {}

  public listarClientesPaginado(paginacao: Paginacao) {
    const negocioId = 1;
    this.carregamento = true;
    this.servicoCliente.buscarClientesPorIdNegocio(negocioId, paginacao).subscribe(response => {
      this.clientes = response.content;
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

  public mudarPagina(direcao: boolean): void {
    const paginaAtual = this.paginacao.pagina;
    if(direcao) {
      this.paginacao.pagina = paginaAtual + 1;
    }
    else {
      this.paginacao.pagina = paginaAtual - 1;
    }
    this.listarClientesPaginado(this.paginacao);
  }

  public verHitoricoCliente(clienteId: number, cliente: string) {
    this.router.navigate([`negocio/admin/clientes/${clienteId}/historico`], { queryParams: { cliente: cliente }, queryParamsHandling: 'merge' });
  }

  ngOnInit(): void {
    this.listarClientesPaginado(this.paginacao);
  }
}