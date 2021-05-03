import { Endereco } from "./endereco.entity";
import { EntidadeBase } from "./entidade-base.entity";

export class Negocio extends EntidadeBase {

    public proprietario: string;
    public nomeFantasia: string;
    public telefone: string;
    public email: string;
    public senha: string;
    public descricao: string;
    public endereco: Endereco = new Endereco();
    public receita: number;
    public pedidosConcluidos: number;
    public pedidosCancelados: number;
    public totalClientes: number;
    public totalProdutosVendidos: number;

    public constructor(values: Object = {}) { 
        super();
        Object.assign(this, values);
    }
}