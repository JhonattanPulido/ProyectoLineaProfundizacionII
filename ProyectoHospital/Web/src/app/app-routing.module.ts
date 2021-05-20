// Gestor de rutas
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'medicos',  
    loadChildren: () => import('./pages/medicos/medicos.module').then(m => m.MedicosModule)
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
