import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";

import { Produto } from "../models/produto.entity";
import { Paginacao } from "../models/paginacao.entity";
import { Observable } from "rxjs";

@Injectable()
export class ProdutoService {
    
    private urlBase = "http://localhost:8080/produtos";

    public constructor(private readonly http: HttpClient) {}

    public buscarProdutoPorId(produtoId: number) {
        return this.http.get<Produto>(this.urlBase.concat(`/${produtoId}`));
    }

    public buscarProdutosPorIdNegocio(negocioId: number, paginacao: Paginacao): Observable<any> {
        const _params = new HttpParams()
                                    .set('negocioId', `${negocioId}`)
                                    .set('filtro', `${paginacao.filtro}`)
                                    .set('page', `${paginacao.pagina}`)
                                    .set('size', `${paginacao.tamanho}`);
        return this.http.get<any>(this.urlBase, { params: _params });
    }

    public criarProduto(produto: Produto) {
        return this.http.post<any>(this.urlBase, produto);
    }

    public atualizarProduto(produtoId: number, produto: Produto): Observable<any> {
        return this.http.put<any>(this.urlBase.concat(`/${produtoId}`), produto);
    }

    public atualizarEstoque(produtoId: number, estoque: number): Observable<any> {
        return this.http.patch(this.urlBase.concat(`/${produtoId}`), estoque);
    }

    public removerProduto(produtoId: number): Observable<any> {
        return this.http.delete(this.urlBase.concat(`/${produtoId}`));
    }
}