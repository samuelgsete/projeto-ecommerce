import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { ToastrService } from 'ngx-toastr';

import { Cliente } from 'src/app/shared/models/cliente.entity';
import { ClienteService } from 'src/app/shared/services/cliente.service';

@Component({
  selector: 'app-conta',
  templateUrl: './conta.component.html',
  styleUrls: ['./conta.component.scss']
})
export class ContaComponent implements OnInit {

  public form: FormGroup;
  public cliente: Cliente = new Cliente();
  public carregamento: boolean = false;

  public constructor(
                        private readonly _fb: FormBuilder, 
                        private readonly router: Router,
                        private readonly toastr: ToastrService,
                        private servicoCliente: ClienteService
                      ) { }

  public buscarClientePorId() {
    const clienteId = 2;
    this.carregamento = true;
    this.servicoCliente.buscarClientePorId(clienteId).subscribe(response => {
      this.cliente = response;
      this.atualizarFormulario(this.cliente);
    }, err => {
      this.toastr.error('Não foi carregar o cliente', 'ERRO', { progressBar: true, positionClass: 'toast-bottom-center' });
    }).add(() => {
      this.carregamento = false;
    });
  }

  public atualizarConta(clienteId: number, cliente: Cliente) {
    this.carregamento = true;
    const clienteAtualizado = new Cliente({
      id: cliente.id,
      nome: cliente.nome,
      sobrenome: cliente.sobrenome,
      telefone: cliente.telefone,
      email: cliente.email,
      senha: cliente.senha,
      endereco: this.cliente.endereco,
      negocioId: this.cliente.negocioId,
      totalPedido: this.cliente.totalPedidos,
      totalConsumido: this.cliente.totalConsumido,
      totalGasto: this.cliente.totalGasto,
    });
    this.servicoCliente.atualizarCliente(clienteId, clienteAtualizado).subscribe(response => {
      this.toastr.success('Atualizado com sucesso', 'Tudo ok!', { progressBar: true, positionClass: 'toast-bottom-center' });
    },
    err => {
      this.toastr.error('Não foi possível atualizar', 'ERRO', { progressBar: true, positionClass: 'toast-bottom-center' });
    }).add(() => {
      this.carregamento = false;
    });

  }

  public atualizarFormulario(cliente: Cliente): void {
    this.form.patchValue({
      id: cliente.id,
      nome: cliente.nome,
      sobrenome: cliente.sobrenome,
      telefone: cliente.telefone,
      email: cliente.email,
      senha: cliente.senha
    });
  }

  public continuarComprando(): void {
    this.router.navigateByUrl("loja/produtos");
  }

  public verMeuEndereco(): void {
    this.router.navigateByUrl("cliente/endereco")
  }

  ngOnInit(): void {
    this.buscarClientePorId();
    this.form = this._fb.group({
      id: [null],
      nome: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(60)]],
      sobrenome: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(60)]],
      telefone: ['', [Validators.required, Validators.maxLength(14)]],
      email: ['', [Validators.required, Validators.email, Validators.maxLength(255)]],
      senha: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(15)]],
      confirmacaoSenha: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(15)]]
    });
  }
}