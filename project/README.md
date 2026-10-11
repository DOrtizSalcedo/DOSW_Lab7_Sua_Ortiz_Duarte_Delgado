# DOSW - Laboratorio 7

## Entidades Seleccionadas

| Entidad           | Justificación                                                                                                                                                    | Relación                              |
|-------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------|
| Usuario           | Son los datos básicos de la persona que se va a autenticar en la plataforma; contando con las credenciales para identificar en el login el rol al que pertenece. | Usuario - Trabajador: 1 - 0..1        |
| Trabajador        | Extiende al usuario con las características de un trabajador como la calificación, zona de cobertura y demás.                                                    | Trabajador - Oficio: N - M            |
| Oficio            | Tiene relación con lo que hace un trabajador con oficios principales y secundarios.                                                                              | Trabajador-Oficio - Oficio: N -1      |
| Trabajador Oficio | Sirve para implementar la relación muchos a muchos entre Trabajador y Oficio                                                                                     | Trabajador - Trabajador-Oficio: 1 - N |                                                                                                                    |

## Enlace a microservicio de imágenes

[Microservicio de imágenes](https://github.com/DOrtizSalcedo/DOSW_Lab7_image-service_Sua_Ortiz_Duarte_Delgado)

## Evidencia de tablas creadas en PostgreSQL

![Evidencia de las tablas creadas en PostgreSQL](docs\images\tables_evidence.png)

## Evidencia de pruebas ejecutadas con H2

![Pruebas H2](docs\images\h2_evidence.png)