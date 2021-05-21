import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { ToastrService } from 'ngx-toastr';

import { Endereco } from 'src/app/shared/models/endereco.entity';
import { UsuarioService } from 'src/app/shared/services/usuario.service';

@Component({
  selector: 'app-endereco',
  templateUrl: './endereco.component.html',
  styleUrls: ['./endereco.component.scss']
})
export class EnderecoComponent implements OnInit {

  public form: FormGroup;
  public endereco: Endereco = new Endereco();
  public clienteId: number = 0;
  public carregamento: boolean = false;
  
  public constructor(
                        private readonly _fb: FormBuilder, 
                        private readonly router: Router,
                        private readonly route: ActivatedRoute,
                        private readonly toastr: ToastrService,
                        private readonly servicoUsuario: UsuarioService,
                    ) { }

  public buscarEnderecoDoUsuario(clienteId: number): void {
    this.carregamento = true;
    this.servicoUsuario.buscarEnderecoDoUsuario(clienteId).subscribe(response => {
      this.endereco = response;
      this.atualizarFormulario(this.endereco);
    }, err => {
      this.toastr.error('Não foi carregar o endereço', 'ERRO', { progressBar: true, positionClass: 'toast-bottom-center' });
    }).add(() => {
      this.carregamento = false;
    });
  }

  public atualizarConta(clienteId: number, endereco: Endereco): void {
    this.carregamento = true;
    const enderecoAtualizado = new Endereco({
      id: endereco.id,
      rua: endereco.rua,
      numero: endereco.numero,
      cep: endereco.cep,
      referencia: endereco.referencia,
      bairro: endereco.bairro,
      municipio: endereco.municipio
    });
   
    this.servicoUsuario.atualizarEnderecoDoUsuario(clienteId, enderecoAtualizado).subscribe(response => {
      this.toastr.success('Atualizado com sucesso', 'Tudo ok!', { progressBar: true, positionClass: 'toast-bottom-center' });
    },
    err => {
      this.toastr.error('Não foi possível atualizar', 'ERRO', { progressBar: true, positionClass: 'toast-bottom-center' });
    }).add(() => {
      this.carregamento = false;
    });

  }

  public atualizarFormulario(endereco: Endereco): void {
    this.form.patchValue({
      id: endereco.id,
      rua: endereco.rua,
      numero: endereco.numero,
      cep: endereco.cep,
      referencia: endereco.referencia,
      bairro: endereco.bairro,
      municipio: endereco.municipio
    });
  }

  public continuarComprando(): void {
    this.router.navigate(["loja/produtos"], { queryParamsHandling: 'preserve'});
  }

  public verMinhaConta(): void {
    this.router.navigate(["cliente/conta"], { queryParamsHandling: 'preserve'});
  }

  ngOnInit(): void {
    this.form = this._fb.group({
      id: [null],
      rua: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(255)]],
      numero: ['', Validators.required],
      cep: ['', [Validators.required, Validators.maxLength(8)]],
      referencia: ['', Validators.maxLength(255)],
      bairro: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(60)]],
      municipio: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(60)]]
    });
    this.clienteId = parseInt(this.route.snapshot.queryParams['client_id']);
    this.buscarEnderecoDoUsuario(this.clienteId);
  }
}