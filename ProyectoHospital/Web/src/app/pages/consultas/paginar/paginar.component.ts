// Librerías
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import {animate, state, style, transition, trigger} from '@angular/animations';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';

// Entidades
import { Consulta } from 'src/app/entidades/consulta.entity';
import { ConsultaPaginador } from 'src/app/entidades/consulta-paginador.entity';

// Servicios
import { ConsultaService } from 'src/app/services/consulta.service';

@Component({
  selector: 'app-paginar',
  templateUrl: './paginar.component.html',
  styleUrls: ['./paginar.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class PaginarComponent implements OnInit {

  // Variables
  public inicio?: number;
  public cantidad?: number;
  public nombreMedico?: string;
  public dataSource?: Consulta[];
  public paginas: number[];
  public consultaPaginador?: ConsultaPaginador;
  public displayedColumns: string[] = ['id', 'fecha', 'detallesConsultas', 'examenes', 'acciones'];
  private horizontalPosition: MatSnackBarHorizontalPosition = 'end';
  private verticalPosition: MatSnackBarVerticalPosition = 'bottom';
  public expandedElement?: Consulta | null;

  // Constructor
  constructor(
    private router: Router,
    private snackBar: MatSnackBar,
    private activatedRoute: ActivatedRoute,
    private consultaService: ConsultaService
  ) { 
    this.paginas = [];
  }

  ngOnInit(): void {

    this.obtenerParametros('inicio', 'cantidad')
      .then((res: number[] | null) => {

        this.inicio = res![0];
        this.cantidad = res![1];
        this.nombreMedico = localStorage.getItem('medico-nombre')!;

        this.consultaService.paginar(this.inicio!, this.cantidad!, Number.parseInt(localStorage.getItem('medico-id')!))
        .then((res: ConsultaPaginador | null) => {
          if (res != null) {
            this.consultaPaginador = res;           
            this.dataSource = this.consultaPaginador.lista;     
            for (let i = 0; i < this.consultaPaginador!.cantidadPaginas; i++)
              this.paginas?.push(i);                    
          }
        });  
      });

  }

  // Métodos

  // Paginar
  public paginar(pagina: number) : void {
    this.router.navigate(['consultas', 'pag', pagina, '5'])
      .then(_ => {
        this.obtenerParametros('inicio', 'cantidad')
          .then((res: number[] | null) => {

            this.paginas = [];
            this.inicio = res![0];
            this.cantidad = res![1];
            this.nombreMedico = localStorage.getItem('medico-nombre')!;

            this.consultaService.paginar(this.inicio!, this.cantidad!, Number.parseInt(localStorage.getItem('medico-id')!))
            .then((res: ConsultaPaginador | null) => {
              if (res != null) {
                this.consultaPaginador = res;           
                this.dataSource = this.consultaPaginador.lista;     
                for (let i = 0; i < this.consultaPaginador!.cantidadPaginas; i++)
                  this.paginas?.push(i);                    
              }
            });  
          });
      });    
  }

  // Actualizar consulta
  public async actualizar(id: number) : Promise<void> {
    localStorage.setItem('consulta-id', id.toString());
    this.router.navigateByUrl('/consultas/actualizar');
  }

  // Eliminar consulta
  public async eliminar(id: number) : Promise<void> {
    await this.consultaService.eliminar(id)
      .then((res: string) => {
        if (res == '0')
          window.location.reload();
        else {
          this.snackBar.open(res, 'Cerrar', {
            horizontalPosition: this.horizontalPosition,
            verticalPosition: this.verticalPosition,
          });
        }
      });
  }

  // Obtener parámetros de Url
  private obtenerParametros(...param: string[]) : Promise<number[] | null> {    
    return new Promise(resolve => {
      this.activatedRoute.paramMap
      .subscribe(res => {
        var parametros: number[] = [];
        param.forEach(e => {
          parametros.push(Number.parseInt(res.get(e)!));
        });
        resolve(parametros);
      });
    });        
  }

}
