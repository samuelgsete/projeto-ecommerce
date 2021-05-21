import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { ToastrService } from 'ngx-toastr';

import { Paginacao } from 'src/app/shared/models/paginacao.entity';
import { ClienteService } from 'src/app/shared/services/cliente.service';

@Component({
  selector: 'app-historico-cliente',
  templateUrl: './historico-cliente.component.html',
  styleUrls: ['./historico-cliente.component.scss']
})
export class HistoricoClienteComponent implements OnInit {

  public paginacao: Paginacao = new Paginacao();
  public historicoCliente: Array<any> = [];
  public cliente: string = '';

  public constructor(
                        private readonly router: Router,
                        private readonly route:ActivatedRoute,
                        private readonly toastr: ToastrService,
                        private readonly servicoCliente: ClienteService
                      ) {}

  public carregarHistoricoCompras(paginacao: Paginacao) {
    const clienteId = parseInt(this.router.url.split('/')[4]);
    this.servicoCliente.verHistorico(clienteId, paginacao).subscribe(response => {
      this.historicoCliente = response.content;
      paginacao.ultima = response.last;
      paginacao.primeira = response.first;
      paginacao.totalElementos = response.totalElements;
    }, err => {
      this.toastr.error('Não foi possivel carregar o histórico', 'ERRO', { progressBar: true, positionClass: 'toast-bottom-center' });
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
    this.carregarHistoricoCompras(this.paginacao);
  }

  public verCliente(): void {
    this.router.navigate(['negocio/admin/clientes'], { queryParamsHandling: 'preserve' });
  }

  ngOnInit(): void {
    this.cliente = this.route.snapshot.queryParams.cliente;
    this.carregarHistoricoCompras(this.paginacao);
  }
}