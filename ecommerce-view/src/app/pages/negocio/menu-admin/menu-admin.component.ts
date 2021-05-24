import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { ToastrService } from 'ngx-toastr';

import { LogoutService } from 'src/app/shared/services/Logout.service';
import { NegocioComponent } from '../negocio.component';

@Component({
  selector: 'app-menu-admin',
  templateUrl: './menu-admin.component.html',
  styleUrls: ['./menu-admin.component.scss']
})
export class MenuAdminComponent implements OnInit {

  public usuario: string = '';
  @Input('negocio') negocio: NegocioComponent;

  public constructor(
                          private readonly router: Router,
                          private readonly route: ActivatedRoute, 
                          private readonly toastr: ToastrService,
                          private readonly logoutService: LogoutService
                    ) { 
    this.usuario = this.route.snapshot.queryParams['name'];
  }

  public fazerLogout():void {
    this.logoutService.fazerLogout().subscribe(response => {
      this.toastr.success('Até logo!', 'Tudo ok!', { progressBar: true, positionClass: 'toast-bottom-center' });
      this.router.navigateByUrl("negocio/login");
      localStorage.removeItem('access_token');
    },
    err => {
      this.toastr.error('Erro ao desconectar!', 'ERRO', { progressBar: true, positionClass: 'toast-bottom-center' });
    });
  }

  public ocultarOuExibirSideBar(): void {
    this.negocio.ocultarOuExibirSideBar();
  }

  ngOnInit(): void {
  }

}
