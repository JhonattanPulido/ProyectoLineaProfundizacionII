// Librerías
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MedicosRoutingModule } from './medicos-routing.module';

// Angular material
import { ComponentsModule } from '../../components/components.module';

// Componentes
import { MedicosComponent } from './medicos.component';

@NgModule({
  declarations: [
    MedicosComponent
  ],
  imports: [
    CommonModule,
    ComponentsModule,
    MedicosRoutingModule
  ]
})
export class MedicosModule { }
