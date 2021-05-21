import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NegocioService } from 'src/app/shared/services/negocio.service';
import { Negocio } from 'src/app/shared/models/negocio.entity';

@Component({
  selector: 'app-loja',
  templateUrl: './loja.component.html',
  styleUrls: ['./loja.component.scss']
})
export class LojaComponent implements OnInit {

  public negocio: Negocio = new Negocio();

  public constructor( 
                        private readonly servicoNegocio: NegocioService, 
                        private readonly route: ActivatedRoute
                    ) { }

  public carregarNegocio() {
    const adminId = parseInt(this.route.snapshot.queryParams['admin_id']);
    this.servicoNegocio.buscarNegocioPorId(adminId).subscribe(response => {
      this.negocio = response;
    },
    err => {
      console.log(err);
    });
  }

  ngOnInit(): void {
    this.carregarNegocio();
  }
}