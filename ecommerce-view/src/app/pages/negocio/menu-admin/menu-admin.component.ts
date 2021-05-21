import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NegocioComponent } from '../negocio.component';

@Component({
  selector: 'app-menu-admin',
  templateUrl: './menu-admin.component.html',
  styleUrls: ['./menu-admin.component.scss']
})
export class MenuAdminComponent implements OnInit {

  public usuario: string = '';
  @Input('negocio') negocio: NegocioComponent;

  public constructor(private readonly route: ActivatedRoute) { 
    this.usuario = this.route.snapshot.queryParams['name'];
  }

  public desconectar():void {}

  public ocultarOuExibirSideBar(): void {
    this.negocio.ocultarOuExibirSideBar();
  }

  ngOnInit(): void {
  }

}
