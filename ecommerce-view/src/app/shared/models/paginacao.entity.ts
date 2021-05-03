export class Paginacao {

    public filtro: string = '';
    public pagina: number = 0;
    public tamanho: number = 8;
    public ultima: boolean = false;
    public primeira: boolean = true;
    public totalElementos: number = 0;

    public constructor(values: Object = {}) { 
        Object.assign(this, values)
    }
}