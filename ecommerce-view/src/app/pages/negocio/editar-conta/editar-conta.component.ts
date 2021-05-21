import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Negocio } from 'src/app/shared/models/negocio.entity';
import { Usuario } from 'src/app/shared/models/usuario.entity';
import { NegocioService } from 'src/app/shared/services/negocio.service';

@Component({
  selector: 'app-editar-conta',
  templateUrl: './editar-conta.component.html',
  styleUrls: ['./editar-conta.component.scss']
})
export class EditarContaComponent implements OnInit {

  public form: FormGroup;
  public negocio: Negocio = new Negocio();
  public carregamento: boolean = false;

  public constructor(
                        private readonly _fb: FormBuilder, 
                        private readonly router: Router,
                        private readonly route: ActivatedRoute,
                        private readonly toastr: ToastrService,
                        private readonly servicoNegocio: NegocioService
                    ) {}
  
  public carregarConta(adminId: number): void {
    this.carregamento = true;
    this.servicoNegocio.buscarNegocioPorId(adminId).subscribe(response =>{
      this.negocio = response;
      this.preencherFormulario(this.negocio);
    },
    err => {
      this.toastr.error('Não foi carregar os dados', 'ERRO', { progressBar: true, positionClass: 'toast-bottom-center' });
    }).add(() => {
      this.carregamento = false;
    });
  }  

  public atualizarNegocio(adminId: number, formConta: any): void {
    this.carregamento = true;
    const negocioAtualizado = new Negocio({
      id: formConta.id,
      usuario: new Usuario({
        id: this.negocio.usuario.id,
        nome: formConta.nome,
        sobrenome: formConta.sobrenome,
        telefone: formConta.telefone,
        email: this.negocio.usuario.email,
        senha: this.negocio.usuario.senha,
        endereco: this.negocio.usuario.endereco,
        estaAtivo: this.negocio.usuario.estaAtivo,
        perfis: this.negocio.usuario.perfis

      }),
      nomeFantasia: formConta.nomeFantasia,
      descricao: formConta.descricao,
      receita: this.negocio.receita,
      pedidosConcluidos: this.negocio.pedidosConcluidos,
      pedidosCancelados: this.negocio.pedidosCancelados,
      totalClientes: this.negocio.totalClientes,
      totalProdutosVendidos: this.negocio.totalProdutosVendidos
    });

    this.servicoNegocio.atualizarNegocio(adminId, negocioAtualizado).subscribe(response =>{
      this.toastr.success('Atualizado com sucesso', 'Tudo ok!', { progressBar: true, positionClass: 'toast-bottom-center' });
    },
    err => {
      this.toastr.error('Não foi carregar os dados', 'ERRO', { progressBar: true, positionClass: 'toast-bottom-center' });
    }).add(() => {
      this.carregamento = false;
    });
  }
  
  public preencherFormulario(negocio: Negocio): void {
    this.form.patchValue({
      id: negocio.id,
      nome: negocio.usuario.nome,
      sobrenome: negocio.usuario.sobrenome,
      telefone: negocio.usuario.telefone,
      nomeFantasia: negocio.nomeFantasia,
      descricao: negocio.descricao,
    });
  }

  public verMeusProdutos(): void {
    this.router.navigate(["/negocio/admin/produtos"], { queryParamsHandling: 'preserve'});
  }

  public verMinhaConta(): void {
    this.router.navigate(["negocio/admin/endereco"], { queryParamsHandling: 'preserve'});
  }

  ngOnInit(): void {
    this.form = this._fb.group({
      id: [null],
      nome: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(60)]],
      sobrenome: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(60)]],
      telefone: ['', [Validators.required, Validators.maxLength(11)]],
      nomeFantasia: ['', [Validators.required, Validators.maxLength(255)]],
      descricao: ['', [Validators.required, Validators.maxLength(512)]]
    });
    const adminId = parseInt(this.route.snapshot.queryParams['admin_id']);
    this.carregarConta(adminId);
  }
}