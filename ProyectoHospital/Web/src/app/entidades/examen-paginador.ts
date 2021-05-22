// Librerías
import { Examen } from "./examen.entity";
import { Paginador } from "./paginador.entity";

// Paginador de examen
export interface ExamenPaginador extends Paginador {
    lista: Examen[]
}