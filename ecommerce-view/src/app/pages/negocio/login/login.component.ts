import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { ToastrService } from 'ngx-toastr';

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
                        private readonly toastr: ToastrService
                    ) {}
  
  public fazerLogin(): void {}

  public irParaCadastroNegocio(): void {
    this.router.navigateByUrl("/negocio/criar");
  }

  ngOnInit(): void {
    this.form = this._fb.group({
      email: ['', [Validators.required, Validators.email, Validators.maxLength(255)]],
      senha: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(15)]],
    });
  }
}