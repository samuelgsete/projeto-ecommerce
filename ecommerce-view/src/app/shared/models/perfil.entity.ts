import { EntidadeBase } from "./entidade-base.entity";

export class Perfil extends EntidadeBase {

    public nome: string;

    public constructor(values: Object = {}) { 
        super();
        Object.assign(this, values); 
    }
}