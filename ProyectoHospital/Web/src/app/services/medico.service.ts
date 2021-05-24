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
          if (err.status == 500)
            resolve("Ha ocurrido un error inesperado, inténtelo nuevamente");            
          else
            resolve(err.error.mensaje);
        });
    });
  }

  // Leer médico
  public async leer(id: number) : Promise<Medico | null> {
    return new Promise(resolve => {
      this.http.get(`${ webAPI }/medicos/${ id }/s`, { observe: 'response' })
        .subscribe((res: HttpResponse<any>) => {
          
          var medico: Medico = {
            id: res.body.id,
            nombre: res.body.nombre,
            apellido: res.body.apellido,
            correoElectronico: res.body.correoElectronico,
            direccion: {
              id: res.body.direccion.id,
              codigoPostal: res.body.direccion.codigoPostal,
              direccionDetallada: res.body.direccion.direccionDetallada              
            }
          };

          resolve(medico);

        }, (err: HttpErrorResponse) => {
          resolve(null);
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

  // Actualizar médico
  public actualizar(medico: Medico) : Promise<string> {
    return new Promise(resolve => {
      this.http.put(`${ webAPI }/medicos`, medico, { observe: 'response' })
        .subscribe((res: HttpResponse<any>) => {
          resolve("0");
        }, ((err: HttpErrorResponse) => {
          if (err.status == 500)
            resolve("Ha ocurrido un error inesperado, inténtelo nuevamente");            
          else
            resolve(err.error.mensaje);
        }));
    });
  }

  // Eliminar médico
  public eliminar(id: number) : Promise<string> {
    return new Promise(resolve => {
      this.http.delete(`${ webAPI }/medicos/${ id }`, { observe: 'response' })
        .subscribe((res: HttpResponse<any>) => {
          resolve("0");
        }, (err: HttpErrorResponse) => {
          if (err.status == 500)
            resolve("Ha ocurrido un error inesperado, inténtelo nuevamente");            
          else
            resolve(err.error.mensaje);
        });
    });
  }

}
