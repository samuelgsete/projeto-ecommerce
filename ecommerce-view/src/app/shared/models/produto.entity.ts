import { EntidadeBase } from "./entidade-base.entity";

export class Produto extends EntidadeBase {

    public nome: string;
    public preco: number;
    public unidadesVendidas: number;
    public estoque: number;
    public detalhes: string;
    public urlImagem: string;
    public negocioId: number;

    public constructor(values: Object = {}) { 
        super();
        Object.assign(this, values)
    }
}