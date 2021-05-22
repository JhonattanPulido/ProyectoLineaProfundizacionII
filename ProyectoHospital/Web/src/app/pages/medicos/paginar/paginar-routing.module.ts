// Gestor de rutas
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// Componentes
import { PaginarComponent } from './paginar.component';

const routes: Routes = [
  {
    path: '',
    component: PaginarComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PaginarRoutingModule { }
