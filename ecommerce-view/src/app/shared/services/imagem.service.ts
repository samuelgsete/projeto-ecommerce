import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

@Injectable()
export class ImagemService {

    private urlBase = "http://localhost:8080/carregamentos";

    public constructor(private readonly http: HttpClient) {}

    public carregarFotoProduto(fotoProduto: FormData) {
        return this.http.post<any>(this.urlBase.concat("/produtos"), fotoProduto);
    }
}