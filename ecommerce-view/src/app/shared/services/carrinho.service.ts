import { Injectable, EventEmitter } from "@angular/core";

import { ItemPedido } from "../models/item-pedido";

@Injectable()
export class CarrinhoService {

    public adicionarItem = new EventEmitter<ItemPedido[]>();
    public items: Array<ItemPedido> = [];
    public custo: number = 0.0

    public constructor() {
        this.adicionarItem.emit();
    }

    public adicionar(item: ItemPedido): void {
        this.items.push(item);
        this.custo += item.produto.preco * item.quantidade;
        this.adicionarItem.emit(this.items);
    }

    public removerItem(indice: number): void {
        const item = this.items.splice(indice, 1);
        this.adicionarItem.emit();
        this.custo -= item[0].produto.preco * item[0].quantidade;
    }

    public esvaziarCarrinho(): void {
        this.items = [];
        this.adicionarItem.emit();
    }
}