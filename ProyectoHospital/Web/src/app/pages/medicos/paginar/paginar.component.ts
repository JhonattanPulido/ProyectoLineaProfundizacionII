// Librerías
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';

// Entidades
import { Medico } from 'src/app/entidades/medico.entity';
import { MedicoPaginador } from 'src/app/entidades/medico-paginador';

// Servicios
import { MedicoService } from 'src/app/services/medico.service';

@Component({
  selector: 'app-paginar',
  templateUrl: './paginar.component.html',
  styleUrls: ['./paginar.component.css']
})
export class PaginarComponent implements OnInit {

  // Variables
  public inicio?: number;
  public cantidad?: number;
  public medicoPaginador?: MedicoPaginador;
  public dataSource?: Medico[];
  public paginas: number[];
  public displayedColumns: string[] = ['id', 'nombre', 'apellido', 'correoElectronico', 'consultas', 'acciones'];
  private horizontalPosition: MatSnackBarHorizontalPosition = 'end';
  private verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  // Constructor
  constructor(
    private router: Router,
    private snackBar: MatSnackBar,
    private medicoService: MedicoService,
    private activatedRoute: ActivatedRoute
  ) { 
    this.paginas = [];
  }

  async ngOnInit(): Promise<void> {        

    this.obtenerParametros('inicio', 'cantidad')
      .then((res: number[] | null) => {

        this.inicio = res![0];
        this.cantidad = res![1];
        console.log(this.medicoPaginador);

        this.medicoService.paginar(this.inicio!, this.cantidad!)
        .then((res: MedicoPaginador | null) => {
          if (res != null) {
            this.medicoPaginador = res;
            this.dataSource = this.medicoPaginador.lista;
            for (let i = 0; i < this.medicoPaginador!.cantidadPaginas; i++)
              this.paginas?.push(i);
          }
        });  
      });
        
  }

  // Método

  // Paginar
  public paginar(pagina: number) : void {
    this.router.navigate(['medicos', 'pag', pagina, '5'])
      .then(_ => {
        this.obtenerParametros('inicio', 'cantidad')
          .then((res: number[] | null) => {

            this.paginas = [];
            this.inicio = res![0];
            this.cantidad = res![1];
            console.log(this.medicoPaginador);

            this.medicoService.paginar(this.inicio!, this.cantidad!)
            .then((res: MedicoPaginador | null) => {
              if (res != null) {
                this.medicoPaginador = res;
                this.dataSource = this.medicoPaginador.lista;
                for (let i = 0; i < this.medicoPaginador!.cantidadPaginas; i++)
                  this.paginas?.push(i);
              }
            });  
          });
      });    
  }

  // Crear consulta
  public crearConsulta(id: number) : void {
    localStorage.setItem('medico-id', id.toString());    
    this.router.navigateByUrl('/consultas');
  }

  // Leer consultas
  public leerConsultas(id: number, nombre: string) : void {
    localStorage.setItem('medico-id', id.toString()); 
    localStorage.setItem('medico-nombre', nombre); 
    this.router.navigateByUrl('/consultas/pag/0/5');
  }

  // Actualizar médico
  public actualizarMedico(id: number) : void {
    localStorage.setItem('usuario-id', id.toString());
    this.router.navigateByUrl('/medicos/actualizar');
  }

  // Eliminar médico
  public eliminarMedico(id: number) : void {
    this.medicoService.eliminar(id)
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
