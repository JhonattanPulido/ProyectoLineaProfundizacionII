// Librerías
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';

// Entidades
import { MedicoPaginador } from 'src/app/entidades/medico-paginador';
import { Medico } from 'src/app/entidades/medico.entity';

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
  public displayedColumns: string[] = ['id', 'nombre', 'apellido', 'correoElectronico', 'acciones'];

  // Constructor
  constructor(
    private medicoService: MedicoService,
    private activatedRoute: ActivatedRoute
  ) { }

  async ngOnInit(): Promise<void> {        

    this.obtenerParametros('inicio', 'cantidad')
      .then((res: number[] | null) => {

        this.inicio = res![0];
        this.cantidad = res![1];

        this.medicoService.paginar(this.inicio!, this.cantidad!)
        .then((res: MedicoPaginador | null) => {
          if (res != null) {
            this.medicoPaginador = res;
            this.dataSource = this.medicoPaginador.lista;
          }
        });  
      });
        
  }

  // Método

  // Eliminar médico
  public eliminarMedico(id: number) : void {
    alert(id);
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
