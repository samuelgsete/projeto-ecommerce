import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'cep'
})
export class CepPipe implements PipeTransform {

  public transform(value: string, ...args: any[]): string {
    let resultado = '';
    if(typeof value == 'string') {
      const cep = value?.split('');
      resultado = `${cep[0]}${cep[1]}.${cep[2]}${cep[3]}${cep[4]}-${cep[5]}${cep[6]}${cep[7]}`;
    }
    return resultado;
  }
}