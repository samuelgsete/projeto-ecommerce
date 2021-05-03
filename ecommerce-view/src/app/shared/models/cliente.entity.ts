import { Endereco } from "./endereco.entity";
import { EntidadeBase } from "./entidade-base.entity";

export class Cliente extends EntidadeBase {

    public nome: string;
    public sobrenome: string;
    public telefone: string;
    public email: string;
    public senha: string;
    public endereco: Endereco = new Endereco();
    public totalPedidos: number;
    public totalConsumido: number;
    public totalGasto: number;
    public negocioId: number;

    public constructor(values: Object = {}) { 
        super();
        Object.assign(this, values) 
    }
}