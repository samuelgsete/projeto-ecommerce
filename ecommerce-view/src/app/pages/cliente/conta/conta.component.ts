import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { ToastrService } from 'ngx-toastr';

import { Cliente } from 'src/app/shared/models/cliente.entity';
import { Usuario } from 'src/app/shared/models/usuario.entity';
import { ClienteService } from 'src/app/shared/services/cliente.service';
import { UsuarioService } from 'src/app/shared/services/usuario.service';

@Component({
  selector: 'app-conta',
  templateUrl: './conta.component.html',
  styleUrls: ['./conta.component.scss']
})
export class ContaComponent implements OnInit {

  public form: FormGroup;
  public usuario = new Usuario();
  public carregamento: boolean = false;

  public constructor(
                        private readonly _fb: FormBuilder, 
                        private readonly router: Router,
                        private readonly route: ActivatedRoute,
                        private readonly toastr: ToastrService,
                        private readonly servicoUsuario: UsuarioService
                      ) { }

  public buscarClientePorId() {
    const clienteId = parseInt(this.route.snapshot.queryParams['client_id']);
    this.carregamento = true;
    this.servicoUsuario.buscarUsuarioPorId(clienteId).subscribe(response => {
      this.usuario = response;
      this.atualizarFormulario(this.usuario);
    }, err => {
      this.toastr.error('Não foi carregar o cliente', 'ERRO', { progressBar: true, positionClass: 'toast-bottom-center' });
    }).add(() => {
      this.carregamento = false;
    });
  }

  public atualizarConta(clienteId: number, usuario: Usuario) {
    this.carregamento = true;
    const usuarioAtualizado = new Usuario({
      id: usuario.id,
      nome: usuario.nome,
      sobrenome: usuario.sobrenome,
      telefone: usuario.telefone,
      senha: this.usuario.senha,
      email: this.usuario.email,
      endereco: this.usuario.endereco,
      estaAtivo: this.usuario.estaAtivo,
      perfis: this.usuario.perfis
    });
    this.servicoUsuario.atualizarUsuario(clienteId, usuarioAtualizado).subscribe(response => {
      this.toastr.success('Atualizado com sucesso', 'Tudo ok!', { progressBar: true, positionClass: 'toast-bottom-center' });
    },
    err => {
      this.toastr.error('Não foi possível atualizar', 'ERRO', { progressBar: true, positionClass: 'toast-bottom-center' });
    }).add(() => {
      this.carregamento = false;
    });

  }

  public atualizarFormulario(usuario: Usuario): void {
    this.form.patchValue({
      id: usuario.id,
      nome: usuario.nome,
      sobrenome: usuario.sobrenome,
      telefone: usuario.telefone,
    });
  }

  public continuarComprando(): void {
    this.router.navigate(["loja/produtos"], { queryParamsHandling: 'preserve'});
  }

  public verMeuEndereco(): void {
    this.router.navigate(["cliente/endereco"], { queryParamsHandling: 'preserve'})
  }

  ngOnInit(): void {
    this.buscarClientePorId();
    this.form = this._fb.group({
      id: [null],
      nome: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(60)]],
      sobrenome: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(60)]],
      telefone: ['', [Validators.required, Validators.maxLength(14)]]
    });
  }
}