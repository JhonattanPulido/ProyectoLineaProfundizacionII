// Entidades
import { Consulta } from "./consulta.entity";
import { Paginador } from "./paginador.entity";

// Entidad consulta paginador
export interface ConsultaPaginador extends Paginador {
    lista: Consulta[]
}