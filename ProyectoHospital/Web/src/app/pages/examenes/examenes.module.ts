// Librerías
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ExamenesRoutingModule } from './examenes-routing.module';

// Angular material
import { ComponentsModule } from '../../components/components.module';

// Componentes
import { ExamenesComponent } from './examenes.component';

@NgModule({
  declarations: [
    ExamenesComponent
  ],
  imports: [
    CommonModule,
    ComponentsModule,
    ExamenesRoutingModule
  ]
})
export class ExamenesModule { }
