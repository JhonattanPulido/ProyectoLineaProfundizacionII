// Librerías
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';

// Entidades
import { Medico } from 'src/app/entidades/medico.entity';

// Servicios
import { MedicoService } from 'src/app/services/medico.service';

@Component({
  selector: 'app-actualizar',
  templateUrl: './actualizar.component.html',
  styleUrls: ['./actualizar.component.css']
})
export class ActualizarComponent implements OnInit {

  // Variables
  public medico?: Medico; 
  public formGroup?: FormGroup; 
  private horizontalPosition: MatSnackBarHorizontalPosition = 'end';
  private verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  // Constructor
  constructor(
    private router: Router,
    private snackBar: MatSnackBar,
    private formBuilder: FormBuilder,
    private medicoService: MedicoService
  ) { }

  async ngOnInit(): Promise<void> {

    await this.medicoService.leer(Number.parseInt(localStorage.getItem('usuario-id')!))
      .then((res: Medico | null) => {        

        this.medico = res!;

        if (this.medico != null) {          
          this.formGroup = this.formBuilder.group({
            nombre: [this.medico.nombre, [Validators.required, Validators.minLength(2), Validators.maxLength(26), Validators.pattern("[a-zA-záéíóúÁÉÍÓÚñÑ]*")]],
            apellido: [this.medico.apellido, [Validators.required, Validators.minLength(2), Validators.maxLength(26), Validators.pattern("[a-zA-záéíóúÁÉÍÓÚñÑ]*")]],
            email: [this.medico.correoElectronico, [Validators.required, Validators.email]],
            codigoPostal: [this.medico.direccion?.codigoPostal, [Validators.required, Validators.minLength(6), Validators.maxLength(6), Validators.pattern("[0-9]*")]],
            direccionDetallada: [this.medico.direccion?.direccionDetallada, [Validators.required, Validators.minLength(8), Validators.maxLength(80)]]
          });

        } else
          this.router.navigateByUrl('/not-found');

      });        

  }

  // Métodos

  // Actualizar médico
  public async actualizarMedico() : Promise<void> {

    if (this.formGroup?.valid) {

      var medicoAuxiliar: Medico = {      
        id: this.medico?.id,  
        nombre: this.formGroup.get('nombre')?.value,
        apellido: this.formGroup.get('apellido')?.value,
        correoElectronico: this.formGroup.get('email')?.value,
        direccion: {          
          codigoPostal: this.formGroup.get('codigoPostal')?.value,
          direccionDetallada: this.formGroup.get('direccionDetallada')?.value              
        }
      };

      await this.medicoService.actualizar(medicoAuxiliar)
      .then((res: string) => {
        if (res == "0") {
          this.snackBar.open("Información actualizada correctamente", 'Cerrar', {
            horizontalPosition: this.horizontalPosition,
            verticalPosition: this.verticalPosition,
          });
        } else {
          this.snackBar.open(res, 'Cerrar', {
            horizontalPosition: this.horizontalPosition,
            verticalPosition: this.verticalPosition,
          });
        }

      });

    } else {
      this.snackBar.open('Información incorrecta!!', 'Cerrar', {
        horizontalPosition: this.horizontalPosition,
        verticalPosition: this.verticalPosition,
      });
    }

  }

  // Obtener datos del formulario de registro de médico
  get f() { return this.formGroup!.controls; }

}
