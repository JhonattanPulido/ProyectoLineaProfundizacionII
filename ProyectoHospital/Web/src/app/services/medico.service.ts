// Librerías
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http';

// Entidades
import { Medico } from '../entidades/medico.entity';

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
  public crear(medico: Medico) : Promise<string> {
    return new Promise(resolve => {
      this.http.post(`${ webAPI }/medicos`, medico, { observe: 'response' })
        .subscribe((res: HttpResponse<any>) => {
          resolve("0"); // Médico creado correctamente          
        }, (err: HttpErrorResponse) => {
          console.log(err);
          switch (err.status) {            
            case 409:
              resolve(err.error.mensaje); // Error 409
              break;
            default:
              resolve("Ha ocurrido un error inesperado, intentelo nuevamente!"); // Error 500
          }
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
