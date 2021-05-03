import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { ToastrService } from 'ngx-toastr';

import { Cliente } from 'src/app/shared/models/cliente.entity';
import { Endereco } from 'src/app/shared/models/endereco.entity';
import { ClienteService } from 'src/app/shared/services/cliente.service';

@Component({
  selector: 'app-endereco',
  templateUrl: './endereco.component.html',
  styleUrls: ['./endereco.component.scss']
})
export class EnderecoComponent implements OnInit {

  public form: FormGroup;
  public endereco: Endereco = new Endereco();
  public cliente: Cliente = new Cliente();
  public carregamento: boolean = false;
  
  public constructor(
                        private readonly _fb: FormBuilder, 
                        private readonly router: Router,
                        private readonly toastr: ToastrService,
                        private servicoCliente: ClienteService
                    ) { }

  public buscarClientePorId(): void {
    const clienteId = 2;
    this.carregamento = true;
    this.servicoCliente.buscarClientePorId(clienteId).subscribe(response => {
      this.cliente = response;
      console.log(this.cliente);
      this.atualizarFormulario(this.cliente.endereco);
    }, err => {
      this.toastr.error('Não foi carregar o endereço', 'ERRO', { progressBar: true, positionClass: 'toast-bottom-center' });
    }).add(() => {
      this.carregamento = false;
    });
  }

  public atualizarConta(clienteId: number, endereco: Endereco): void {
    this.carregamento = true;
    const enderecoAtualizado = new Endereco({
      id: this.cliente.endereco.id,
      rua: endereco.rua,
      numero: endereco.numero,
      cep: endereco.cep,
      bairro: endereco.bairro,
      municipio: endereco.municipio
    });
    this.cliente.endereco = enderecoAtualizado;

    this.servicoCliente.atualizarCliente(clienteId, this.cliente).subscribe(response => {
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
      bairro: endereco.bairro,
      municipio: endereco.municipio
    });
  }

  public continuarComprando(): void {
    this.router.navigateByUrl("loja/produtos");
  }

  public verMinhaConta(): void {
    this.router.navigateByUrl("cliente/conta");
  }

  ngOnInit(): void {
    this.form = this._fb.group({
      id: [null],
      rua: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(255)]],
      numero: ['', Validators.required],
      cep: ['', [Validators.required, Validators.maxLength(8)]],
      bairro: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(60)]],
      municipio: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(60)]]
    });
    this.buscarClientePorId();
  }
}