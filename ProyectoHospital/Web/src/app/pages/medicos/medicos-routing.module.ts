// Gestor de rutas de médico
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// Componentes
import { MedicosComponent } from './medicos.component';

const routes: Routes = [
  {
    path: '',
    component: MedicosComponent
  },
  {
    path: 'pag/:inicio/:cantidad',
    loadChildren: () => import('./paginar/paginar.module').then(m => m.PaginarModule)
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MedicosRoutingModule { }
