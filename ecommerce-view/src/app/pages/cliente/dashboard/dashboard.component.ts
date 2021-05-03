import { Component, OnInit } from '@angular/core';

import { Cliente } from 'src/app/shared/models/cliente.entity';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  public cliente: Cliente = new Cliente();

  public constructor() { }

  ngOnInit(): void {}
}