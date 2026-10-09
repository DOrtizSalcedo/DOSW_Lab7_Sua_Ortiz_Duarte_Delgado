# DOSW - Laboratorio 7

## Entidades Seleccionadas

| Entidad           | Justificación                                                                                                                                                    | Relación                              |
|-------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------|
| Usuario           | Son los datos básicos de la persona que se va a autenticar en la plataforma; contando con las credenciales para identificar en el login el rol al que pertenece. | Usuario - Trabajador: 1 - 0..1        |
| Trabajador        | Extiende al usuario con las características de un trabajador como la calificación, zona de cobertura y demás.                                                    | Trabajador - Oficio: N - M            |
| Oficio            | Tiene relación con lo que hace un trabajador con oficios principales y secundarios.                                                                              | Trabajador-Oficio - Oficio: N -1      |
| Trabajador Oficio | Sirve para implementar la relación muchos a muchos entre Trabajador y Oficio                                                                                     | Trabajador - Trabajador-Oficio: 1 - N |                                                                                                                    |