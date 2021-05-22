// Librerías
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';

// Entidades
import { Medico } from 'src/app/entidades/medico.entity';

// Servicios
import { MedicoService } from '../../services/medico.service';

@Component({
  selector: 'app-medicos',
  templateUrl: './medicos.component.html',
  styleUrls: ['./medicos.component.css']
})
export class MedicosComponent implements OnInit {

  // Variables
  private medico?: Medico;
  public formGroup: FormGroup;
  private horizontalPosition: MatSnackBarHorizontalPosition = 'end';
  private verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  // Constructor
  constructor(
    private router: Router,
    private snackBar: MatSnackBar,
    private formBuilder: FormBuilder,
    private medicoService: MedicoService
  ) { 

    // Generando los campos del formulario
    this.formGroup = this.formBuilder.group({
      nombre: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(26), Validators.pattern("[a-zA-záéíóúÁÉÍÓÚñÑ]*")]],
      apellido: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(26), Validators.pattern("[a-zA-záéíóúÁÉÍÓÚñÑ]*")]],
      email: ['', [Validators.required, Validators.email]],
      codigoPostal: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(6), Validators.pattern("[0-9]*")]],
      direccionDetallada: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(80)]]
    });

  }

  ngOnInit(): void {
  }

  // Métodos

  // Crear médico
  public crearMedico() : void {

    // ¿El formulario esta diligenciado correctamente?
    if (this.formGroup.valid) {

      // Cargamos los datos del médico a la variable
      this.medico = {
        nombre: this.formGroup.get('nombre')?.value,
        apellido: this.formGroup.get('apellido')?.value,
        correoElectronico: this.formGroup.get('email')?.value,
        direccion: {
          codigoPostal: this.formGroup.get('codigoPostal')?.value,
          direccionDetallada: this.formGroup.get('direccionDetallada')?.value
        }        
      };

      // Consumimos el servicio de crear médico
      this.medicoService.crear(this.medico)
        .then((res: string) => {
          if (res == '0') {
            this.router.navigateByUrl('/medicos/pag/0/5');
          } else { // Encontró un error
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
  get f() { return this.formGroup.controls; }

}
