// Librerías
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';

// Entidades
import { Examen } from 'src/app/entidades/examen.entity';

// Servicios
import { ExamenService } from '../../services/examen.service';

@Component({
  selector: 'app-examenes',
  templateUrl: './examenes.component.html',
  styleUrls: ['./examenes.component.css']
})
export class ExamenesComponent implements OnInit {

  // Variables
  private examen?: Examen;
  public formGroup: FormGroup;
  private horizontalPosition: MatSnackBarHorizontalPosition = 'end';
  private verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  // Constructor
  constructor(
    private router: Router,
    private snackBar: MatSnackBar,
    private formBuilder: FormBuilder,
    private examenService: ExamenService
  ) { 
    // Generando los campos del formulario
    this.formGroup = this.formBuilder.group({
      nombre: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(26), Validators.pattern("[a-zA-záéíóúÁÉÍÓÚñÑ0-9 ]*")]],
      descripcion: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(120)]]
    });
  }

  ngOnInit(): void {

  }

  // Métodos

  // Crear examen
  public async crearExamen() : Promise<void> {
    
    if (this.formGroup.valid) {

      this.examen = {
        nombre: this.formGroup.get('nombre')?.value,
        descripcion: this.formGroup.get('descripcion')?.value
      };

      this.examenService.crear(this.examen)
        .then((res: string) => {
          if (res == '0') {
            this.router.navigateByUrl('/examenes/pag/0/5');
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
