import { Cliente } from "./cliente.entity";
import { EntidadeBase } from "./entidade-base.entity";
import { ItemPedido } from "./item-pedido";

export class Pedido extends EntidadeBase {

    public cliente: Cliente = new Cliente();
    public custo: number;
    public feitoEm: Date;
    public itens: Array<ItemPedido> = [];
    public situacao: string;
    public adminId: number;
    public eNovo: boolean;

    public constructor(values: Object = {}) { 
        super();
        Object.assign(this, values)
    }
}