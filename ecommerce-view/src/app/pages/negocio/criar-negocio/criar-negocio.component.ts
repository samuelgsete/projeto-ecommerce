import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { ToastrService } from 'ngx-toastr';
import { Endereco } from 'src/app/shared/models/endereco.entity';
import { Negocio } from 'src/app/shared/models/negocio.entity';

import { NegocioService } from 'src/app/shared/services/negocio.service';
import { PasswordValidator } from 'src/app/shared/validators/password.validator';

@Component({
  selector: 'app-criar-negocio',
  templateUrl: './criar-negocio.component.html',
  styleUrls: ['./criar-negocio.component.scss']
})
export class CriarNegocioComponent implements OnInit {

  public formNegocio: FormGroup;
  public formContato: FormGroup;
  public formSenha: FormGroup;
  public formEndereco: FormGroup;

  public carregamento: boolean = false;

  public constructor(
                        private readonly _fb: FormBuilder,
                        private readonly router: Router,
                        private readonly toastr: ToastrService,
                        private readonly servicoNegocio: NegocioService,
                    ) { }

  public criarNegocio() {
    const novoNegocio = new Negocio({
      id: null,
      proprietario: this.formNegocio.value.proprietario,
      nomeFantasia: this.formNegocio.value.nomeFantasia,
      descricao: this.formNegocio.value.descricao,
      telefone: this.formContato.value.telefone,
      email: this.formContato.value.email,
      senha: this.formSenha.value.senha,
      endereco: new Endereco({
        id: null,
        rua: this.formEndereco.value.rua,
        numero: this.formEndereco.value.numero,
        cep: this.formEndereco.value.cep,
        bairro: this.formEndereco.value.bairro,
        municipio: this.formEndereco.value.municipio
      }),
      receita: 0,
      pedidosConcluidos: 0,
      pedidosCancelados: 0,
      totalClientes: 0,
      totalProdutosVendidos: 0
    });
    this.carregamento = true;
    console.log(novoNegocio);
    this.servicoNegocio.criarNegocio(novoNegocio).subscribe(response => {
      this.toastr.success('Criado com sucesso', 'Tudo ok!', { progressBar: true, positionClass: 'toast-bottom-center' });
      this.irParaLogin();
    },
    err => {
      this.toastr.error(err.error.mensagem, 'ERRO', { progressBar: true, positionClass: 'toast-bottom-center' });
    }).add(() => {
      this.carregamento = false;
    });
  }

  public irParaLogin(): void {
    this.router.navigateByUrl('/negocio/login');
  }

  ngOnInit(): void {
    this.formNegocio = this._fb.group({
      proprietario: ['Samuel de Souza Taveira', [Validators.required, Validators.minLength(2), Validators.maxLength(120)]],
      nomeFantasia: ['Food Mix', [Validators.required, Validators.minLength(4), Validators.maxLength(60)]],
      descricao: ['Somos uma lanchonete especializada em salgados, massas, sucos e vitaminas. Nosso foco é atingir a satisfação de nossos clientes.', [Validators.required, Validators.minLength(15), Validators.maxLength(512)]]
    });

    this.formContato = this._fb.group({
      telefone: ['85989711010', [Validators.required, Validators.maxLength(11)]],
      email: ['samuelgsete@hotmail.com', [Validators.required, Validators.email, Validators.maxLength(255)]],
    });

    this.formSenha = this._fb.group({
      senha: ['123456', [Validators.required, Validators.minLength(4), Validators.maxLength(15)]],
      confirmacaoSenha: ['123456', [Validators.required, Validators.minLength(4), Validators.maxLength(15)]]
    }, {
      validators: new PasswordValidator().confirmed('senha', 'confirmacaoSenha')
    });

    this.formEndereco = this._fb.group({
      rua: ['Paulo Henrique Cavalcante', [Validators.required, Validators.minLength(5), Validators.maxLength(255)]],
      numero: ['141', Validators.required],
      cep: ['61895000', [Validators.required, Validators.maxLength(8)]],
      bairro: ['Água Verde', [Validators.required, Validators.minLength(3), Validators.maxLength(60)]],
      municipio: ['Guaiúba', [Validators.required, Validators.minLength(3), Validators.maxLength(60)]]
    });
  }
}