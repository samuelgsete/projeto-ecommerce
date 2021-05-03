import { EntidadeBase } from "./entidade-base.entity";
import { Pedido } from "./pedido.entity";
import { Produto } from "./produto.entity";

export class ItemPedido extends EntidadeBase {

    public produto: Produto;
    public quantidade: number;
    public pedido: Pedido;

    public constructor(values: Object = {}) { 
        super();
        Object.assign(this, values)
    }
}