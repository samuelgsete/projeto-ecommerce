import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { ReactiveFormsModule } from '@angular/forms';
import { NgxCurrencyModule } from "ngx-currency";
import { ToastrModule } from 'ngx-toastr';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { NgxMaskModule } from 'ngx-mask'

import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatSelectModule } from '@angular/material/select';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatListModule } from '@angular/material/list';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatBadgeModule } from '@angular/material/badge';
import { MatStepperModule } from '@angular/material/stepper';
import { MatTabsModule } from '@angular/material/tabs';
import { TelefonePipe } from './pipes/telefone.pipe';
import { CepPipe } from './pipes/cep.pipe';

@NgModule({
  declarations: [
    TelefonePipe,
    CepPipe
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MDBBootstrapModule.forRoot(),
    NgxDatatableModule ,
    MatFormFieldModule,
    MatInputModule,
    ToastrModule.forRoot(),
    NgxMaskModule.forRoot(),
    MatButtonModule,
    MatIconModule,
    MatMenuModule,
    MatSelectModule,
    MatExpansionModule,
    MatProgressBarModule,
    MatListModule,
    MatTooltipModule,
    MatToolbarModule,
    MatBadgeModule,
    MatStepperModule,
    MatTabsModule
  ],
  exports: [
    ReactiveFormsModule,
    MDBBootstrapModule,
    NgxDatatableModule,
    MatFormFieldModule,
    MatInputModule,
    NgxCurrencyModule,
    ToastrModule,
    NgxMaskModule,
    MatButtonModule,
    MatIconModule,
    MatMenuModule,
    MatSelectModule,
    MatExpansionModule,
    MatProgressBarModule,
    MatListModule,
    MatTooltipModule,
    MatToolbarModule,
    MatBadgeModule,
    MatStepperModule,
    MatTabsModule,
    TelefonePipe,
    CepPipe
  ]
})
export class SharedModule { }