import { EntidadeBase } from "./entidade-base.entity";

export class Endereco extends EntidadeBase {

    public rua: string;
    public numero: number;
    public cep: string;
    public bairro: string;
    public municipio: string;

    public constructor(values: Object = {}) { 
        super();
        Object.assign(this, values) 
    }
}