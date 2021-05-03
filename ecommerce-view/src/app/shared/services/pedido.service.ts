import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";

import { Observable } from "rxjs";

import { Paginacao } from "../models/paginacao.entity";
import { Pedido } from "../models/pedido.entity";

@Injectable()
export class PedidoService {

    private urlBase = 'http://localhost:8080/pedidos';

    public constructor(private readonly http: HttpClient) {}

    public buscarPedidoPorId(pedidoId: number): Observable<Pedido> {
        return this.http.get<Pedido>(this.urlBase.concat(`/${pedidoId}`));
    }

    public buscarPedidosPorIdCliente(clienteId: number, paginacao: Paginacao): Observable<any> {
        const _params = new HttpParams()
            .set('page', `${paginacao.pagina}`)
            .set('size', `${paginacao.tamanho}`);
        return this.http.get<any>(this.urlBase.concat(`/cliente/${clienteId}`), { params: _params });
    }

    public buscarPedidosPorIdNegocio(negocioId: number, paginacao: Paginacao): Observable<any> {
        const _params = new HttpParams()
                                .set('negocioId', `${negocioId}`)
                                .set('filtro', `${paginacao.filtro}`)
                                .set('page', `${paginacao.pagina}`)
                                .set('size', `${paginacao.tamanho}`);
        return this.http.get<any>(this.urlBase, { params: _params });
    }

    public atualizarSituacaoPedido(pedidoId: number, situacao: String): Observable<any> {
        return this.http.patch(this.urlBase.concat(`/${pedidoId}?situacao=${situacao}`), {});
    }

    public cancelarPedido(pedidoId: number): Observable<any> {
        return this.http.delete<any>(this.urlBase.concat(`/${pedidoId}`));
    }

    public fazerPedido(pedido: Pedido): Observable<Pedido> {
        return this.http.post<Pedido>(this.urlBase, pedido);
    }
}