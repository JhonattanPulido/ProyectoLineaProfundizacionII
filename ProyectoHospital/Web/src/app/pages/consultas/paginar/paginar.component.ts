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
  ) { }

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
          }
        });  
      });

  }

  // Métodos

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
