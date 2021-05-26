import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import {
  trigger,
  state,
  style,
  animate,
  transition,
} from '@angular/animations';

import { NegocioService } from 'src/app/shared/services/negocio.service';
import { Negocio } from 'src/app/shared/models/negocio.entity';

@Component({
  selector: 'app-negocio',
  animations: [
    trigger('openClose', [
      state('open', style({
        opacity: 0,
        pointerEvents: 'none',
        width: '0%',
      })),
      state('closed', style({ 
        width: '5%',
      })),
      transition('open => closed', [
        animate('0.25s')
      ]),
      transition('closed => open', [
        animate('0.2s')
      ]),
    ]),
  ],
  templateUrl: './negocio.component.html',
  styleUrls: ['./negocio.component.scss']
})
export class NegocioComponent implements OnInit {

  public estaAberto: boolean = false;
  public negocio = new Negocio();

  public constructor(private readonly route: ActivatedRoute, private readonly servicoNegocio: NegocioService) { }

  public carregarNegocio(negocioId: number): void {
    this.servicoNegocio.buscarNegocioPorId(negocioId).subscribe(response => {
      this.negocio = response;
    }, err => {
      console.log(err);
    });
  }

  public ocultarOuExibirSideBar(): void {
    this.estaAberto = !this.estaAberto;
  }

  ngOnInit(): void {
    const negocioId = this.route.snapshot.queryParams['admin_id'];
    this.carregarNegocio(negocioId);
  }
}