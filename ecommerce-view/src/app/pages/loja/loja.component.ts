import { Component, OnInit } from '@angular/core';

import { NegocioService } from 'src/app/shared/services/negocio.service';
import { Negocio } from 'src/app/shared/models/negocio.entity';

@Component({
  selector: 'app-loja',
  templateUrl: './loja.component.html',
  styleUrls: ['./loja.component.scss']
})
export class LojaComponent implements OnInit {

  public negocio: Negocio = new Negocio();

  public constructor(private readonly servicoNegocio: NegocioService) { }

  public carregarNegocio() {
    const negocioId = 1;
    this.servicoNegocio.buscarNegocioPorId(negocioId).subscribe(response => {
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