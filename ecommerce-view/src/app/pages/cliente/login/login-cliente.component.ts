import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login-cliente',
  templateUrl: './login-cliente.component.html',
  styleUrls: ['./login-cliente.component.scss']
})
export class LoginClienteComponent implements OnInit {

  public form: FormGroup;
  public carregamento: boolean = false;

  public constructor(
                        private readonly _fb: FormBuilder,
                        private readonly router: Router,
                        private readonly toastr: ToastrService
                    ) {}
  
  public fazerLogin(): void {}

  public irParaCadastroCliente(): void {
    this.router.navigateByUrl("loja/1/cliente/criar");
  }

  ngOnInit(): void {
    this.form = this._fb.group({
      email: ['', [Validators.required, Validators.email, Validators.maxLength(255)]],
      senha: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(15)]],
    });
  }
}