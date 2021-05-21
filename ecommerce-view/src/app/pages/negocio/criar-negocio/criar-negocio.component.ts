import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { ToastrService } from 'ngx-toastr';

import { Endereco } from 'src/app/shared/models/endereco.entity';
import { Negocio } from 'src/app/shared/models/negocio.entity';
import { Perfil } from 'src/app/shared/models/perfil.entity';
import { Usuario } from 'src/app/shared/models/usuario.entity';
import { NegocioService } from 'src/app/shared/services/negocio.service';
import { UsuarioService } from 'src/app/shared/services/usuario.service';
import { PasswordValidator } from 'src/app/shared/validators/password.validator';

@Component({
  selector: 'app-criar-negocio',
  templateUrl: './criar-negocio.component.html',
  styleUrls: ['./criar-negocio.component.scss']
})
export class CriarNegocioComponent implements OnInit {

  public informacoesPessoais: FormGroup;
  public negocio: FormGroup;
  public credenciais: FormGroup;
  public endereco: FormGroup;
  public carregamento: boolean = false;

  public constructor(
                        private readonly _fb: FormBuilder,
                        private readonly router: Router,
                        private readonly toastr: ToastrService,
                        private readonly servicoUsuario: UsuarioService,
                        private readonly servicoNegocio: NegocioService,
                    ) { }

  public criarUsuario() {
    this.carregamento = true;
    const novoUsuario = new Usuario({
      id: null,
      nome: this.informacoesPessoais.value.nome,
      sobrenome: this.informacoesPessoais.value.sobrenome,
      telefone: this.informacoesPessoais.value.telefone,
      email: this.informacoesPessoais.value.email,
      senha: this.credenciais.value.senha,
      estaAtivo: true,
      endereco: new Endereco({
        id: this.endereco.value.id,
        rua: this.endereco.value.rua,
        numero: this.endereco.value.numero,
        cep: this.endereco.value.cep,
        referencia: this.endereco.value.referencia,
        bairro: this.endereco.value.bairro,
        municipio: this.endereco.value.municipio
      }),
      perfis: [
        new Perfil({
          id: 2,
          nome: "ROLE_ADMIN"
        })
      ]
    });

    const novoNegocio = new Negocio({
      id: null,
      nomeFantasia: this.negocio.value.nomeFantasia,
      descricao: this.negocio.value.descricao,
      receita: 0,
      pedidosConcluidos: 0,
      pedidosCancelados: 0,
      totalClientes: 0,
      totalProdutosVendidos: 0,
      usuario: novoUsuario
    });
    
    this.servicoNegocio.criarNegocio(novoNegocio).subscribe(response => {
      this.toastr.success('Criado com sucesso', 'Tudo ok!', { progressBar: true, positionClass: 'toast-bottom-center' });
    },
    err => {
      this.toastr.error(err.error.mensagem, 'ERRO', { progressBar: true, positionClass: 'toast-bottom-center' });
    }).add(() =>{
      this.carregamento = false;
    });
  }

  private criarNegocio(novoUsuario: Usuario): void {
    const novoNegocio = new Negocio({
      id: null,
      nomeFantasia: this.negocio.value.nomeFantasia,
      descricao: this.negocio.value.descricao,
      receita: 0,
      pedidosConcluidos: 0,
      pedidosCancelados: 0,
      totalClientes: 0,
      totalProdutosVendidos: 0,
      usuario: novoUsuario
    });
    console.log(novoNegocio);
    this.servicoNegocio.criarNegocio(novoNegocio).subscribe(response => {
      this.toastr.success('Criado com sucesso', 'Tudo ok!', { progressBar: true, positionClass: 'toast-bottom-center' });
    },
    err => {
      this.toastr.error(err.error.mensagem, 'ERRO', { progressBar: true, positionClass: 'toast-bottom-center' });
    }).add(() =>{
      this.carregamento = false;
    });
  }

  public irParaLogin(): void {
    this.router.navigateByUrl('/negocio/login');
  }

  ngOnInit(): void {
    this.informacoesPessoais = this._fb.group({
      nome: ['Madalena', [Validators.required, Validators.minLength(2), Validators.maxLength(60)]],
      sobrenome: ['Souza', [Validators.required, Validators.minLength(2), Validators.maxLength(60)]],
      telefone: ['85988180673', [Validators.required, Validators.maxLength(11)]],
      email: ['madalenasouza@gmail.com', [Validators.required, Validators.email, Validators.maxLength(255)]],
      nomeFantasia: ['Mix Food Best', [Validators.required, Validators.maxLength(255)]],
    });

    this.negocio = this._fb.group({
      nomeFantasia: ['Mix Food Best', [Validators.required, Validators.maxLength(255)]],
      descricao: ['Somos uma lanchonete referência em vitaminas, sucos, massas, salgados e muito mais. Nossa prioridade e a satisfação do cliente', [Validators.required, Validators.maxLength(512)]]
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
      referencia: ['Próximo a fazenda do Tabosa', Validators.maxLength(512)],
      bairro: ['Água Verde', [Validators.required, Validators.minLength(3), Validators.maxLength(60)]],
      municipio: ['Guaiúba', [Validators.required, Validators.minLength(3), Validators.maxLength(60)]]
    });
  }
}