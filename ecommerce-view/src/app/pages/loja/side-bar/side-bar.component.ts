import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { ToastrService } from 'ngx-toastr';

import { LogoutService } from 'src/app/shared/services/Logout.service';

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.scss']
})
export class SideBarComponent implements OnInit {

  public itens = [
    { icone: 'shop', path: '/loja/produtos', detalhes: 'Página inicial' },
    { icone: 'edit_note', path: '/cliente/pedidos', detalhes: 'Meus pedidos' },
    { icone: 'favorite', path: '/cliente/produtos/favoritos', detalhes: 'Produtos favoritos' },
    { icone: 'manage_accounts', path: '/cliente/conta', detalhes: 'Minha conta' },
    { icone: 'place', path: '/cliente/endereco', detalhes: 'Meu endereço' }
  ];

  public constructor(
                        private readonly router: Router, 
                        private readonly route: ActivatedRoute,
                        private readonly toastr: ToastrService,
                        private readonly logoutService: LogoutService
                    ) {}

  public redirecionar(rota: string): void {
    this.router.navigate([rota], { queryParamsHandling: 'preserve' });
  }

  public fazerLogout(): void {
    this.logoutService.fazerLogout().subscribe(response => {
      this.toastr.success('Até logo!', 'Tudo ok!', { progressBar: true, positionClass: 'toast-bottom-center' });
      const adminId = this.route.snapshot.queryParams['admin_id'];
      this.router.navigate([`/loja/cliente/login`], { queryParams: { admin_id: adminId }});
      localStorage.removeItem('access_token');
    }, err => {
      this.toastr.error('Erro ao desconectar!', 'ERRO', { progressBar: true, positionClass: 'toast-bottom-center' });
    });
  }

  ngOnInit(): void {}
}