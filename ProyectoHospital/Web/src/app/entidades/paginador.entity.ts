// Entidad paginador
export interface Paginador {
    cantidadPaginas: number,
    paginaActual: number,
    cantidadTotal: number, // Cantidad total de datos
    cantidadMostrar: number    
}