// Librerías
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MedicosRoutingModule } from './medicos-routing.module';

// Componentes
import { MedicosComponent } from './medicos.component';

@NgModule({
  declarations: [
    MedicosComponent
  ],
  imports: [
    CommonModule,
    MedicosRoutingModule
  ]
})
export class MedicosModule { }
