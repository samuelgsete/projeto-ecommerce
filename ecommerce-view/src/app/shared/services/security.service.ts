import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

import { Observable } from "rxjs";

@Injectable()
export class SecurityService {

    public urlBase = 'http://localhost:8080/user/auth';

    public constructor(private readonly http: HttpClient) {}

    public obterUsuarioLogado(): Observable<any> {
        return this.http.get<any>(this.urlBase);
    }
}