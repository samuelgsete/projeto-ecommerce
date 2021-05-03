import { Component, Input, OnInit } from '@angular/core';

import { NegocioComponent } from '../negocio.component';

@Component({
  selector: 'app-menu-admin',
  templateUrl: './menu-admin.component.html',
  styleUrls: ['./menu-admin.component.scss']
})
export class MenuAdminComponent implements OnInit {

  public proprietario: string = "Madalena";
  @Input('negocio') negocio: NegocioComponent;

  public constructor() { }

  public desconectar():void {}

  public ocultarOuExibirSideBar(): void {
    this.negocio.ocultarOuExibirSideBar();
  }

  ngOnInit(): void {
  }

}
