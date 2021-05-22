// Librerías
import { Medico } from './medico.entity';
import { Paginador } from './paginador.entity';

// Paginador de médico
export interface MedicoPaginador extends Paginador {
    lista: Medico[]
}