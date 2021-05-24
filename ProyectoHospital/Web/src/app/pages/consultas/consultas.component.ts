// Librerías
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';

// Entidades
import { Examen } from 'src/app/entidades/examen.entity';

// Servicios
import { ExamenService } from 'src/app/services/examen.service';
import { ConsultaService } from 'src/app/services/consulta.service';
import { DetalleConsulta } from 'src/app/entidades/detalle-consulta.entity';
import { Consulta } from 'src/app/entidades/consulta.entity';


@Component({
  selector: 'app-consultas',
  templateUrl: './consultas.component.html',
  styleUrls: ['./consultas.component.css']
})
export class ConsultasComponent implements OnInit {

  // Variables
  private medicoId?: number;
  private consulta?: Consulta;
  public formGroup?: FormGroup;
  public toppings = new FormControl();  
  public listaExamenes?: Examen[] | null;
  public listaDetallesConsultas: DetalleConsulta[];
  private horizontalPosition: MatSnackBarHorizontalPosition = 'end';
  private verticalPosition: MatSnackBarVerticalPosition = 'bottom';
  public displayedColumns: string[] = ['diagnostico', 'tratamiento', 'acciones'];

  // Constructor
  constructor(
    private router: Router,
    private snackBar: MatSnackBar,
    private formBuilder: FormBuilder,
    private examenService: ExamenService,
    private consultaService: ConsultaService
  ) { 
    this.listaDetallesConsultas = [];
  }

  async ngOnInit(): Promise<void> {
    await this.examenService.leerTodo()
      .then((res: Examen[] | null) => {
        this.listaExamenes = res;
        if (this.listaExamenes != null) {
          this.medicoId = Number.parseInt(localStorage.getItem('medico-id')!);
          // Generando los campos del formulario
          this.formGroup = this.formBuilder.group({
            diagnostico: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(120), Validators.pattern("[a-zA-záéíóúÁÉÍÓÚñÑ0-9 ]*")]],
            tratamiento: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(120), Validators.pattern("[a-zA-záéíóúÁÉÍÓÚñÑ0-9 ]*")]]
          });
        } else {
          this.snackBar.open('No hay exámenes registrados', 'Cerrar', {
            horizontalPosition: this.horizontalPosition,
            verticalPosition: this.verticalPosition,
          });
          this.router.navigateByUrl('/medicos/pag/0/5');
        }
      });
  }

  // Métodos

  // Crear consulta
  public async crearConsulta() : Promise<void> {    

    if (this.listaDetallesConsultas.length > 0) {

      this.consulta = {
        listaExamenes: this.toppings.value,
        listaDetallesConsultas: this.listaDetallesConsultas
      };

      if (this.consulta.listaExamenes?.length! > 0) {

        var consultas: Consulta[] = [];
        consultas.push(this.consulta);

        await this.consultaService.crear(consultas, Number.parseInt(localStorage.getItem('medico-id')!))
          .then((res: string) => {
            if (res == "0")
              this.router.navigateByUrl('/consultas/pag/0/5');
            else
              this.snackBar.open(res, 'Cerrar', {
                horizontalPosition: this.horizontalPosition,
                verticalPosition: this.verticalPosition,
              }); 
          });

      } else {
        this.snackBar.open('No hay exámenes asociados', 'Cerrar', {
          horizontalPosition: this.horizontalPosition,
          verticalPosition: this.verticalPosition,
        });  
      }      

    } else {
      this.snackBar.open('No hay detalles de consulta asociados', 'Cerrar', {
        horizontalPosition: this.horizontalPosition,
        verticalPosition: this.verticalPosition,
      });      
    }
    
  }

  // Añadir detalle consulta
  public addDetalleConsulta() : void {

    if (this.formGroup?.valid) {

      var detalleConsulta: DetalleConsulta = {
        diagnostico: this.formGroup.get('diagnostico')?.value,
        tratamiento: this.formGroup.get('tratamiento')?.value
      };

      this.listaDetallesConsultas.push(detalleConsulta);            
      this.formGroup.reset();
      this.formGroup.markAsUntouched();

    } else {
      this.snackBar.open('Información incorrecta!!', 'Cerrar', {
        horizontalPosition: this.horizontalPosition,
        verticalPosition: this.verticalPosition,
      });
    }

  }

  // Quitar detalle consulta
  public removeDetalleConsulta(detalleConsulta: DetalleConsulta) : void {
    this.listaDetallesConsultas.forEach((element, index) => {
      if (element.diagnostico == detalleConsulta.diagnostico) this.listaDetallesConsultas.splice(index, 1);
    });
  }

}
