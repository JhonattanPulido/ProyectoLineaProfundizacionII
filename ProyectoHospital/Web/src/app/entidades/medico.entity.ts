// Librerías
import { Direccion } from "./direccion.entity";

// Entidad médico
export interface Medico {
    id?: number,
    nombre?: string,
    apellido?: string,
    correoElectronico?: string,
    direccion?: Direccion
}