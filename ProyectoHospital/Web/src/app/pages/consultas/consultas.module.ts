// Librerías
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ConsultasRoutingModule } from './consultas-routing.module';

// Angular material
import { ComponentsModule } from '../../components/components.module';

// Componentes
import { ConsultasComponent } from './consultas.component';

@NgModule({
  declarations: [
    ConsultasComponent
  ],
  imports: [
    CommonModule,
    ComponentsModule,
    ConsultasRoutingModule
  ]
})
export class ConsultasModule { }
