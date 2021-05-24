// Librerías
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PaginarRoutingModule } from './paginar-routing.module';

// Angular material
import { ComponentsModule } from 'src/app/components/components.module';

// Componentes
import { PaginarComponent } from './paginar.component';


@NgModule({
  declarations: [
    PaginarComponent
  ],
  imports: [
    CommonModule,
    ComponentsModule,
    PaginarRoutingModule
  ]
})
export class PaginarModule { }
