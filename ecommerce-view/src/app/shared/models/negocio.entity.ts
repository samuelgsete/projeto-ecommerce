import { EntidadeBase } from "./entidade-base.entity";
import { Usuario } from "./usuario.entity";

export class Negocio extends EntidadeBase {

    public usuario = new Usuario();
    public nomeFantasia: string;
    public descricao: string;
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