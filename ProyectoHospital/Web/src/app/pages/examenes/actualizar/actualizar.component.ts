// Librerías
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';

// Entidades
import { Examen } from 'src/app/entidades/examen.entity';

// Servicios
import { ExamenService } from 'src/app/services/examen.service';

@Component({
  selector: 'app-actualizar',
  templateUrl: './actualizar.component.html',
  styleUrls: ['./actualizar.component.css']
})
export class ActualizarComponent implements OnInit {

  // Variables
  private examen?: Examen;
  public formGroup?: FormGroup;
  private horizontalPosition: MatSnackBarHorizontalPosition = 'end';
  private verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  // Constructor
  constructor(
    private router: Router,
    private snackBar: MatSnackBar,
    private formBuilder: FormBuilder,
    private examenService: ExamenService
  ) { }

  async ngOnInit(): Promise<void> {

    await this.examenService.leer(Number.parseInt(localStorage.getItem('examen-id')!))
      .then((res: Examen | null) => {

        this.examen = res!;

        if (this.examen != null) {         

          this.formGroup = this.formBuilder.group({
            nombre: [this.examen.nombre, [Validators.required, Validators.minLength(2), Validators.maxLength(26), Validators.pattern("[a-zA-záéíóúÁÉÍÓÚñÑ0-9 ]*")]],
            descripcion: [this.examen.descripcion, [Validators.required, Validators.minLength(2), Validators.maxLength(120)]]
          });

        } else
          this.router.navigateByUrl('/not-found');

      });

  }

  // Métodos

  // Actualizar examen
  public async actualizarExamen() : Promise<void> {

    if (this.formGroup?.valid) {

      var examenAuxiliar: Examen = {      
        id: this.examen?.id,  
        nombre: this.formGroup.get('nombre')?.value,        
        descripcion: this.formGroup.get('descripcion')?.value,        
      };

      await this.examenService.actualizar(examenAuxiliar)
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
