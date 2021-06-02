// Librerías
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActualizarRoutingModule } from './actualizar-routing.module';

// Angular material
import { ComponentsModule } from 'src/app/components/components.module';

// Componentes
import { ActualizarComponent } from './actualizar.component';

@NgModule({
  declarations: [
    ActualizarComponent
  ],
  imports: [
    CommonModule,
    ComponentsModule,
    ActualizarRoutingModule
  ]
})
export class ActualizarModule { }
