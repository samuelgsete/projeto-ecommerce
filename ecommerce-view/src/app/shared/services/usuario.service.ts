import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

import { Observable } from "rxjs";
import { Endereco } from "../models/endereco.entity";

import { Usuario } from "../models/usuario.entity";

@Injectable()
export class UsuarioService {

    private urlBase = 'http://localhost:8080/usuarios';

    public constructor(private readonly http: HttpClient) {}

    public buscarUsuarioPorId(usuarioId: number): Observable<Usuario> {
        return this.http.get<Usuario>(this.urlBase.concat(`/${usuarioId}`));
    }

    public criarUsuario(novoUsuario: Usuario): Observable<Usuario> {
        return this.http.post<Usuario>(this.urlBase, novoUsuario);
    }

    public buscarEnderecoDoUsuario(id: number): Observable<Endereco> {
        return this.http.get<Endereco>(this.urlBase.concat(`/${id}/endereco`));
    }

    public atualizarEnderecoDoUsuario(id: number, endereco: Endereco): Observable<Endereco> {
        return this.http.put<Endereco>(this.urlBase.concat(`/${id}/endereco`), endereco);
    }

    public atualizarUsuario(usuarioId: number, usuario: Usuario): Observable<Usuario> {
        return this.http.put<Usuario>(this.urlBase.concat(`/${usuarioId}`), usuario);
    }
}