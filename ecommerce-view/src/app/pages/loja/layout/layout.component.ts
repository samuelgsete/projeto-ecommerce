import { Component, OnInit } from '@angular/core';
import {
  trigger,
  state,
  style,
  animate,
  transition,
} from '@angular/animations';

@Component({
  selector: 'app-layout',
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
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss']
})
export class LayoutComponent implements OnInit {

  public estaAberto: boolean = false;

  public constructor() { }

  public ocultarOuExibir(): void {
    this.estaAberto = !this.estaAberto;
  }

  ngOnInit(): void {}
}