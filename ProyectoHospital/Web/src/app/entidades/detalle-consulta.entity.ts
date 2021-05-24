// Entidades
import { Consulta } from "./consulta.entity";

// Entidad detalle consulta
export interface DetalleConsulta {
    id?: number,
    diagnostico: string,
    tratamiento: string,
    consulta?: Consulta
}