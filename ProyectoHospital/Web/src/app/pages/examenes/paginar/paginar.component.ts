// Librerías
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';

// Entidades
import { Examen } from 'src/app/entidades/examen.entity';
import { ExamenPaginador } from 'src/app/entidades/examen-paginador';

// Servicios
import { ExamenService } from 'src/app/services/examen.service';

@Component({
  selector: 'app-paginar',
  templateUrl: './paginar.component.html',
  styleUrls: ['./paginar.component.css']
})
export class PaginarComponent implements OnInit {

  // Variables
  public inicio?: number;
  public cantidad?: number;
  public examenPaginador?: ExamenPaginador;
  public dataSource?: Examen[];
  public paginas: number[];
  public displayedColumns: string[] = ['id', 'nombre', 'descripcion', 'acciones'];
  private horizontalPosition: MatSnackBarHorizontalPosition = 'end';
  private verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  // Constructor
  constructor(
    private router: Router,
    private snackBar: MatSnackBar,
    private examenService: ExamenService,
    private activatedRoute: ActivatedRoute
  ) { 
    this.paginas = [];
  }

  ngOnInit(): void {

    this.obtenerParametros('inicio', 'cantidad')
      .then((res: number[] | null) => {

        this.inicio = res![0];
        this.cantidad = res![1];

        this.examenService.paginar(this.inicio!, this.cantidad!)
        .then((res: ExamenPaginador | null) => {
          if (res != null) {
            this.examenPaginador = res;
            this.dataSource = this.examenPaginador.lista;
            for (let i = 0; i < this.examenPaginador!.cantidadPaginas; i++)
              this.paginas?.push(i);
          }
        });  
      });

  }

  // Métodos

  // Paginar
  public paginar(pagina: number) : void {
    this.router.navigate(['examenes', 'pag', pagina, '5'])
      .then(_ => {
        this.obtenerParametros('inicio', 'cantidad')
          .then((res: number[] | null) => {

            this.paginas = [];
            this.inicio = res![0];
            this.cantidad = res![1];

            this.examenService.paginar(this.inicio!, this.cantidad!)
            .then((res: ExamenPaginador | null) => {
              if (res != null) {
                this.examenPaginador = res;
                this.dataSource = this.examenPaginador.lista;
                for (let i = 0; i < this.examenPaginador!.cantidadPaginas; i++)
                  this.paginas?.push(i);
              }
            });  
          });
      });    
  }

  // Actualizar examen
  public actualizarExamen(id: number) : void {
    localStorage.setItem('examen-id', id.toString());
    this.router.navigateByUrl('/examenes/actualizar');
  }

  // Eliminar examen
  public eliminarExamen(id: number) : void {
    this.examenService.eliminar(id)
      .then(res => {
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
