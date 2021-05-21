import { Endereco } from "./endereco.entity";
import { EntidadeBase } from "./entidade-base.entity";
import { Perfil } from "./perfil.entity";

export class Usuario extends EntidadeBase {
    
    public nome: string;
    public sobrenome: string;
    public telefone: string;
    public email: string;
    public senha: string;
    public endereco: Endereco = new Endereco();
    public estaAtivo: boolean;
    public perfis: Array<Perfil> = [];

    public constructor(values: Object = {}) { 
        super();
        Object.assign(this, values); 
    }
}