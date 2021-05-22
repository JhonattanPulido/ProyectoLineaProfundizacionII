// Librerías
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActualizarRoutingModule } from './actualizar-routing.module';

// Componentes
import { ActualizarComponent } from './actualizar.component';

// Angular material
import { ComponentsModule } from 'src/app/components/components.module';

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
