// Gestor de rutas
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'medicos',  
    loadChildren: () => import('./pages/medicos/medicos.module').then(m => m.MedicosModule)
  },
  {
    path: 'examenes',
    loadChildren: () => import('./pages/examenes/examenes.module').then(m => m.ExamenesModule)
  },
  {
    path: 'consultas',
    loadChildren: () => import('./pages/consultas/consultas.module').then(m => m.ConsultasModule)
  },
  {
    path: '',
    redirectTo: 'medicos',
    pathMatch: 'full' 
  }  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
