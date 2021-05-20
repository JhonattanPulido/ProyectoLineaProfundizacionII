// Librerías
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http';

// Enviroment
import { environment } from '../../environments/environment';

// Ruta del API de servicios
const webAPI = environment.webAPI;

@Injectable({
  providedIn: 'root'
})
export class MedicoService {

  // Constructor
  constructor(
    private http: HttpClient
  ) { }

  // Métodos

  // Crear médico
  public crear(medico: any) : Promise<any> {
    return new Promise(resolve => {
      this.http.post(`${ webAPI }/medicos`, medico, { observe: 'response' })
        .subscribe((res: HttpResponse<any>) => {
          // Si todo sale bien
        }, (err: HttpErrorResponse) => {
          // Controlo los errores
        });
    });
  }

  // Paginar médicos
  public paginar(inicio: number, cantidad: number) : Promise<any> {
    return new Promise(resolve => {
      this.http.get(`${ webAPI }/medicos/pag/${ inicio }/${ cantidad }`, { observe: 'response' })
        .subscribe((res: HttpResponse<any>) => {
          console.log(res);
        }, (err: HttpErrorResponse) => {
          console.log(err);
        });
    });
  }

}
