export abstract class EntidadeBase {

    public id: number;

    public constructor(values: Object = {}) { Object.assign(this, values) }
}