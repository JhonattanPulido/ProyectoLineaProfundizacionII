// Librerías
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotFoundRoutingModule } from './not-found-routing.module';

// Angular material
import { ComponentsModule } from '../../components/components.module';

// Componentes
import { NotFoundComponent } from './not-found.component';

@NgModule({
  declarations: [
    NotFoundComponent
  ],
  imports: [
    CommonModule,
    ComponentsModule,
    NotFoundRoutingModule
  ]
})
export class NotFoundModule { }
