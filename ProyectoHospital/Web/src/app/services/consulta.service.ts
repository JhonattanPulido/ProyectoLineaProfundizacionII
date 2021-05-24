// Librerías
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http';

// Entidades
import { Consulta } from 'src/app/entidades/consulta.entity';
import { ConsultaPaginador } from 'src/app/entidades/consulta-paginador.entity';

// Environment
import { environment } from 'src/environments/environment';

const webAPI = environment.webAPI;

@Injectable({
  providedIn: 'root'
})
export class ConsultaService {

  // Constructor
  constructor(
    private http: HttpClient
  ) { }

  // Métodos

  // Crear consulta
  public async crear(consulta: Consulta[], medicoId: number) : Promise<string> {
    return new Promise(resolve => {
     this.http.post(`${ webAPI }/consultas/${ medicoId }`, consulta, { observe: 'response' }) 
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

  // Paginar consultas
  public async paginar(inicio: number, cantidad: number, medicoId: number) : Promise<ConsultaPaginador | null> {
    return new Promise(resolve => {
      this.http.get(`${ webAPI }/consultas/${ inicio }/${ cantidad }/${ medicoId }`, { observe: 'response' })
        .subscribe((res: HttpResponse<any>) => {

          var consultaPaginador: ConsultaPaginador = {
            cantidadTotal: res.body.cantidadTotal,
              cantidadMostrar: res.body.cantidadMostrar,
              cantidadPaginas: res.body.cantidadPaginas,
              paginaActual: res.body.paginaActual,
              lista: res.body.lista
          };

          resolve(consultaPaginador);          

        }, (err: HttpErrorResponse) => {
          resolve(null);
        });
    });
  }

}