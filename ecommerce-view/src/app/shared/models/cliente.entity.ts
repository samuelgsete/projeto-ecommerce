import { EntidadeBase } from "./entidade-base.entity";
import { Usuario } from "./usuario.entity";

export class Cliente extends EntidadeBase {

    public usuario = new Usuario();
    public totalPedidos: number;
    public totalConsumido: number;
    public totalGasto: number;
    public adminId: number;

    public constructor(values: Object = {}) { 
        super();
        Object.assign(this, values) 
    }
}