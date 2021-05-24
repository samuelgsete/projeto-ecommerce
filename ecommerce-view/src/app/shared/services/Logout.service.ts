import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

import { Observable } from "rxjs";

@Injectable()
export class LogoutService {

    private readonly urlBase = "http://localhost:8080/user/logout";

    public constructor(private readonly http: HttpClient) {}

    public fazerLogout(): Observable<any> {
        return this.http.post<any>(this.urlBase, {});
    }
}