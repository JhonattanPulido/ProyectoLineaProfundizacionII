// Entidades
import { Medico } from "./medico.entity";
import { Examen } from "./examen.entity";
import { DetalleConsulta } from "./detalle-consulta.entity";

// Entidad consulta
export interface Consulta {
    id?: number,
    fecha?: Date,
    medico?: Medico,
    listaDetallesConsultas?: DetalleConsulta[],
    listaExamenes?: any[]
}