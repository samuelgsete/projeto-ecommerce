import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { ToastrService } from 'ngx-toastr';
import { Cliente } from 'src/app/shared/models/cliente.entity';
import { Endereco } from 'src/app/shared/models/endereco.entity';
import { ClienteService } from 'src/app/shared/services/cliente.service';
import { PasswordValidator } from 'src/app/shared/validators/password.validator';

@Component({
  selector: 'app-criar-cliente',
  templateUrl: './criar-cliente.component.html',
  styleUrls: ['./criar-cliente.component.scss']
})
export class CriarClienteComponent implements OnInit {

  public informacoesPessoais: FormGroup;
  public credenciais: FormGroup;
  public endereco: FormGroup;
  public carregamento: boolean = false;
  public negocioId: number;

  public constructor(
                      private readonly _fb: FormBuilder, 
                      private readonly router: Router,
                      private readonly toastr: ToastrService,
                      private readonly servicoCliente: ClienteService
                    ) { }

  public criarCliente(): void {
    this.carregamento = true;
    const novoCliente = new Cliente({
      id: this.informacoesPessoais.value.id,
      nome: this.informacoesPessoais.value.nome,
      sobrenome: this.informacoesPessoais.value.sobrenome,
      telefone: this.informacoesPessoais.value.telefone,
      email: this.informacoesPessoais.value.email,
      senha: this.credenciais.value.senha,
      endereco: new Endereco({
        id: this.endereco.value.id,
        rua: this.endereco.value.rua,
        numero: this.endereco.value.numero,
        cep: this.endereco.value.cep,
        bairro: this.endereco.value.bairro,
        municipio: this.endereco.value.municipio
      }),
      totalPedidos: 0,
      totalConsumido:0,
      totalGasto: 0,
      negocioId: 1,
    });
    this.servicoCliente.criarCliente(novoCliente).subscribe(response => {
      this.toastr.success('Criado com sucesso', 'Tudo ok!', { progressBar: true, positionClass: 'toast-bottom-center' });
    },
    err => {
      this.toastr.error(err.error.mensagem, 'ERRO', { progressBar: true, positionClass: 'toast-bottom-center' });
    }).add(() =>{
      this.carregamento = false;
    });
  }

  public irParaLogin(): void {
    this.router.navigateByUrl("/loja/1/cliente/login");
  }

  ngOnInit(): void {
    this.negocioId = 1;
    this.informacoesPessoais = this._fb.group({
      id: [null],
      nome: ['Samuel', [Validators.required, Validators.minLength(2), Validators.maxLength(60)]],
      sobrenome: ['Taveira', [Validators.required, Validators.minLength(2), Validators.maxLength(60)]],
      telefone: ['85989711010', [Validators.required, Validators.maxLength(11)]],
      email: ['samuelgsete@hotmail.com', [Validators.required, Validators.email, Validators.maxLength(255)]],
    });

    this.credenciais = this._fb.group({
      senha: ['123456', [Validators.required, Validators.minLength(4), Validators.maxLength(15)]],
      confirmacaoSenha: ['123456', [Validators.required, Validators.minLength(4), Validators.maxLength(15)]]
    }, {
      validators: new PasswordValidator().confirmed('senha', 'confirmacaoSenha')
    });

    this.endereco = this._fb.group({
      id: [null],
      rua: ['Paulo Henrique Cavalcante', [Validators.required, Validators.minLength(5), Validators.maxLength(255)]],
      numero: ['141', Validators.required],
      cep: ['61895000', [Validators.required, Validators.maxLength(8)]],
      bairro: ['Água Verde', [Validators.required, Validators.minLength(3), Validators.maxLength(60)]],
      municipio: ['Guaiúba', [Validators.required, Validators.minLength(3), Validators.maxLength(60)]]
    });
  }
}