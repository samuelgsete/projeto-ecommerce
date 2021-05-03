import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Negocio } from "../models/negocio.entity";

@Injectable()
export class NegocioService {

    private urlBase = 'http://localhost:8080/negocios';

    public constructor(private readonly http: HttpClient) {}

    public buscarNegocioPorId(negocioId: number): Observable<Negocio> {
        return this.http.get<Negocio>(this.urlBase.concat(`/${negocioId}`));
    }

    public criarNegocio(negocio: Negocio): Observable<any> {
        return this.http.post<any>(this.urlBase, negocio);
    }
}