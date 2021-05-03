import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'telefone'
})
export class TelefonePipe implements PipeTransform {

  public transform(value: string, ...args: any[]): string {
    let resultado = '';
    if(typeof value == 'string') {
      const telefone = value?.split('');
      resultado = `+55 (${telefone[0]}${telefone[1]}) ${telefone[2]}${telefone[3]}${telefone[4]}${telefone[5]}${telefone[6]}-${telefone[7]}${telefone[8]}${telefone[9]}${telefone[10]}`;
    }
   
    return resultado;
  }
}