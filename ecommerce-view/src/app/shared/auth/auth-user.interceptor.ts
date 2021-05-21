import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';

import { Observable } from 'rxjs';

@Injectable()
export class AuthUserInterceptor implements HttpInterceptor {

  public intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const accessToken = localStorage.getItem("access_token");

    if(accessToken) {
      const cloned = req.clone({
        headers: req.headers.set("Authorization", `Bearer ${accessToken}`)
      });
      return next.handle(cloned);
    }

    return next.handle(req);
  }
}