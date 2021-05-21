import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";

import { Observable } from "rxjs";

@Injectable()
export class AuthService {

    private urlBase = "http://localhost:8080/oauth/token";

    public constructor(private readonly http: HttpClient) {}

    public fazerLogin(email: string, senha: string): Observable<any> {
        let user = new FormData();
        user.append('username', email);
        user.append('password', senha);
        user.append('grant_type', 'password');

        const clientOAuth2 = btoa('client:123');
        
        return this.http.post<any>(this.urlBase, user, { headers: { Authorization: `Basic ${clientOAuth2}`} });
    }
    
    public userIsAutenticate(): boolean {
        const accessToken = localStorage.getItem("access_token");
        if(accessToken) {
          return true;
        }
        return false;
      }
}