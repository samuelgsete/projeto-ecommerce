import { Injectable } from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";

import { Observable } from "rxjs";

import { Cliente } from "../models/cliente.entity";
import { Paginacao } from "../models/paginacao.entity";

@Injectable()
export class ClienteService {

    private urlBase = "http://localhost:8080/clientes";

    public constructor(private readonly http: HttpClient) {}

    public buscarClientePorId(clienteId: number): Observable<Cliente> {
        return this.http.get<Cliente>(this.urlBase.concat(`/${clienteId}`));
    }

    public buscarClientesPorIdNegocio(negocioId: number, paginacao: Paginacao): Observable<any> {
        const _params = new HttpParams()
                                .set('negocioId', `${negocioId}`)
                                .set('filtro', `${paginacao.filtro}`)
                                .set('page', `${paginacao.pagina}`)
                                .set('size', `${paginacao.tamanho}`);
        return this.http.get<any>(this.urlBase, { params: _params });
    }

    public listarProdutosFavoritos(clienteId: number, paginacao: Paginacao): Observable<any> {
        const _params = new HttpParams().set('page', `${paginacao.pagina}`).set('size', `${paginacao.tamanho}`);
        return this.http.get<any>(this.urlBase.concat(`/${clienteId}/produtos`), { params: _params });
    }

    public verHistorico(clienteId: number, paginacao: Paginacao): Observable<any> {
        const _params = new HttpParams().set('page', `${paginacao.pagina}`).set('size', `${paginacao.tamanho}`);
        return this.http.get<any>(this.urlBase.concat(`/${clienteId}/historico`), { params: _params });
    }

    public criarCliente(cliente: Cliente): Observable<any> {
        return this.http.post<any>(this.urlBase, cliente);
    }

    public atualizarCliente(clienteId: number, cliente:Cliente): Observable<any> {
        return this.http.put<any>(this.urlBase.concat(`/${clienteId}`), cliente);
    }
}