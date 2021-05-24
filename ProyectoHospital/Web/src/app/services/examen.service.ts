// Librerías
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http';

// Entidades
import { Examen } from 'src/app/entidades/examen.entity';
import { ExamenPaginador } from 'src/app/entidades/examen-paginador';

// Environment
import { environment } from 'src/environments/environment';

const webAPI = environment.webAPI;

@Injectable({
  providedIn: 'root'
})
export class ExamenService {

  // Constructor
  constructor(
    private http: HttpClient
  ) { }

  // Métodos

  // Crear examen
  public async crear(examen: Examen) : Promise<string> {
    return new Promise(resolve => {
      this.http.post(`${ webAPI }/examenes`, examen, { observe: 'response' })
        .subscribe((res: HttpResponse<any>) => {
          resolve("0"); // Examen creado correctamente
        }, (err: HttpErrorResponse) => {
          if (err.status == 500)
            resolve("Ha ocurrido un error inesperado, inténtelo nuevamente");
          else
            resolve(err.error.mensaje);
        });
    });
  }

  // Leer examen
  public async leer(id: number) : Promise<Examen | null> {
    return new Promise(resolve => {
      this.http.get(`${ webAPI }/examenes/${ id }`, { observe: 'response' })
        .subscribe((res: HttpResponse<any>) => {
          var examen: Examen = {
            id: res.body.id,
            nombre: res.body.nombre,
            descripcion: res.body.descripcion
          };

          resolve(examen);

        }, (err: HttpErrorResponse) => {
          resolve(null);
        });
    });
  }

  // Leer exámenes
  public async leerTodo() : Promise<Examen[] | null> {
    return new Promise(resolve => {
      this.http.get(`${ webAPI }/examenes`, { observe: 'response' })
        .subscribe((res: HttpResponse<any>) => {
          resolve(res.body);
        }, (err: HttpErrorResponse) => {
          resolve(null);
        });
    });
  }

  // Paginar exámenes
  public async paginar(inicio: number, cantidad: number) : Promise<ExamenPaginador | null> {
    return new Promise(resolve => {
      this.http.get(`${ webAPI }/examenes/${ inicio }/${ cantidad }`, { observe: 'response' })
        .subscribe((res: HttpResponse<any>) => {
          
          if (res.status == 200) {

            var examenPaginado: ExamenPaginador = {
              cantidadTotal: res.body.cantidadTotal,
              cantidadMostrar: res.body.cantidadMostrar,
              cantidadPaginas: res.body.cantidadPaginas,
              paginaActual: res.body.paginaActual,
              lista: res.body.lista
            };

            resolve(examenPaginado);

          } else  
            resolve(null);

        }, (err: HttpErrorResponse) => {
          resolve(null);
        });
    })
  }

  // Actualizar examen
  public async actualizar(examen: Examen) : Promise<string> {
    return new Promise(resolve => {
      this.http.put(`${ webAPI }/examenes`, examen, { observe: 'response' })
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

  // Eliminar examen
  public async eliminar(id: number) : Promise<string> {
    return new Promise(resolve => {
      this.http.delete(`${ webAPI }/examenes/${ id }`, { observe: 'response' })
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
