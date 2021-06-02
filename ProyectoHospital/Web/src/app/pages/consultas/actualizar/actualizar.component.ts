// Librerías
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';

// Entidades
import { Examen } from 'src/app/entidades/examen.entity';
import { Consulta } from 'src/app/entidades/consulta.entity';

// Servicios
import { ExamenService } from 'src/app/services/examen.service';
import { ConsultaService } from 'src/app/services/consulta.service';
import { DetalleConsulta } from 'src/app/entidades/detalle-consulta.entity';

@Component({
  selector: 'app-actualizar',
  templateUrl: './actualizar.component.html',
  styleUrls: ['./actualizar.component.css']
})
export class ActualizarComponent implements OnInit {

  // Variables
  private medicoId?: number;
  public formGroup?: FormGroup;
  public consulta?: Consulta | null;
  public toppings = new FormControl();  
  public listaExamenes?: Examen[] | null;
  public listaDetallesConsultas?: DetalleConsulta[] | null;
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
  ) { }

  async ngOnInit(): Promise<void> {
    await this.consultaService.leer(Number.parseInt(localStorage.getItem('consulta-id')!))
      .then((res: Consulta | null) => {
        this.consulta = res;                
        this.listaDetallesConsultas = this.consulta?.listaDetallesConsultas;                
        this.medicoId = Number.parseInt(localStorage.getItem('medico-id')!);
        this.formGroup = this.formBuilder.group({
          diagnostico: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(120), Validators.pattern("[a-zA-záéíóúÁÉÍÓÚñÑ0-9 ]*")]],
          tratamiento: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(120), Validators.pattern("[a-zA-záéíóúÁÉÍÓÚñÑ0-9 ]*")]]
        });        
      });
    await this.examenService.leerTodo()
    .then((res: Examen[] | null) => {
      this.listaExamenes = res;
    });
  }
  
  // Métodos

  // Actualizar consulta
  public async actualizar() : Promise<void> {

    if (this.listaDetallesConsultas!.length > 0) {

      this.consulta!.listaExamenes = this.toppings.value!;
      this.consulta!.listaDetallesConsultas = this.listaDetallesConsultas!;  
      this.consulta!.medico = {
        id: this.medicoId
      }; 
      
      if (this.consulta!.listaExamenes?.length! > 0) {         

        await this.consultaService.actualizar(this.consulta!)
          .then((res: string) => {
            if (res == "0")
              window.location.reload();
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

      this.listaDetallesConsultas!.push(detalleConsulta);            
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
    this.listaDetallesConsultas!.forEach((element, index) => {
      if (element.diagnostico == detalleConsulta.diagnostico) this.listaDetallesConsultas!.splice(index, 1);
    });
  }

}
