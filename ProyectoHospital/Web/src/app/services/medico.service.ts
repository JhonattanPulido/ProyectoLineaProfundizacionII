// Librerías
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http';

// Entidades
import { Medico } from '../entidades/medico.entity';
import { MedicoPaginador } from 'src/app/entidades/medico-paginador';

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
  public paginar(inicio: number, cantidad: number) : Promise<MedicoPaginador | null> {
    return new Promise(resolve => {
      this.http.get(`${ webAPI }/medicos/pag/${ inicio }/${ cantidad }`, { observe: 'response' })
        .subscribe((res: HttpResponse<any>) => {          

          if (res.status == 200) {

            var medicoPaginado : MedicoPaginador = {
              cantidadTotal: res.body.cantidadTotal,
              cantidadMostrar: res.body.cantidadMostrar,
              cantidadPaginas: res.body.cantidadPaginas,
              paginaActual: res.body.paginaActual,
              lista: res.body.lista
            };

            resolve(medicoPaginado);

          } else
            resolve(null);

        }, (err: HttpErrorResponse) => {
          resolve(null);
        });
    });
  }

}
