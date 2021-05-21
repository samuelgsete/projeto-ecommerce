import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { ToastrService } from 'ngx-toastr';

import { AuthService } from 'src/app/shared/services/auth.service';
import { SecurityService } from 'src/app/shared/services/security.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  public form: FormGroup;
  public carregamento: boolean = false;

  public constructor(
                        private readonly _fb: FormBuilder,
                        private readonly router: Router,
                        private readonly toastr: ToastrService,
                        private readonly authService: AuthService,
                        private readonly securityService: SecurityService
                    ) {}
  
  public fazerLogin(): void {
    this.carregamento = true;
    const email = this.form.value.email;
    const senha = this.form.value.senha;
    
    this.authService.fazerLogin(email, senha).subscribe(response => {
      localStorage.setItem('access_token', response.access_token);
      this.recuperarUsuarioLogado(response.access_token);
    },
    err => {
      this.toastr.error('Não foi possível autenticar', 'ERRO!', { progressBar: true, positionClass: 'toast-bottom-center' });
    }).add(() => {
      this.carregamento = false;
    });
  }

  public recuperarUsuarioLogado(token: string) {
    this.carregamento = true;
    this.securityService.obterUsuarioLogado().subscribe(response => {
      this.router.navigate(['negocio/admin/produtos'], { queryParams: { admin_id: response.id, name: response.nome, access_token: token } })
    },
    err => {
      console.log(err)
    }).add(() => {
      this.carregamento = false;
    });
  }

  public irParaCadastroNegocio(): void {
    this.router.navigateByUrl("/negocio/criar");
  }

  ngOnInit(): void {
    this.form = this._fb.group({
      email: ['madalenasouza@gmail.com', [Validators.required, Validators.email, Validators.maxLength(255)]],
      senha: ['123456', [Validators.required, Validators.minLength(4), Validators.maxLength(15)]],
    });
  }
}