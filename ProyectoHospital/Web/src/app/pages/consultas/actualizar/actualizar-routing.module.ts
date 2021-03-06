// Librerías
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// Componentes
import { ActualizarComponent } from './actualizar.component';

const routes: Routes = [
  {
    path: '',
    component: ActualizarComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ActualizarRoutingModule { }
