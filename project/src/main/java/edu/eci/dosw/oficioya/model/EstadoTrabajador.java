package edu.eci.dosw.oficioya.model;

/**
 * Estados que puede tomar un trabajador dentro de la plataforma. Se conserva el
 * conjunto definido en el laboratorio 6: PAUSADO corresponde a la regla de negocio
 * del trabajador con promedio inferior a 3 estrellas en sus ultimas 5 resenas.
 */
public enum EstadoTrabajador {
    ACTIVO,
    PAUSADO,
    INACTIVO
}
