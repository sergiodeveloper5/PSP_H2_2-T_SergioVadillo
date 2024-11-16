# PSP_H2_2-T_SergioVadillo

## 1.Introducción

Este proyecto consiste en una aplicación distribuida cliente-servidor que facilita la búsqueda en un archivo de datos. La arquitectura se compone de tres capas principales:

-Capa de acceso a datos: Se encarga de la lectura y búsqueda en un archivo local.
-Capa servidor: Procesa las solicitudes de los clientes y devuelve los resultados adecuados.
-Capa cliente: Permite a los usuarios interactuar con el servidor, enviando solicitudes y recibiendo respuestas.

## 2.Cómo ejecutar el proyecto:

- Primero ejecutamos:

Servidor.java: (Debería salir)

Servidor iniciado en el puerto 12345

Y después Cliente.java: (Debería salir)

Conectado al servidor en localhost:12345
Ingrese clave de búsqueda (o 'salir' para terminar):

Ahí podemos buscar lo que queramos del archivo datos.dat

Y por último escribir 'salir' y cerramos la conexión con el servidor.

- Si hay problemas al ejecutar el servidor puede ser porque el puerto está ocupado.

Por ejemplo puede salir esto por consola:
Error al iniciar el servidor: Address already in use: bind

Entonces tenemos que abrir cmd y ejecutar:
netstat -ano | findstr :12345

Esto te mostrará qué proceso está ocupando el puerto 55555. Deberías ver algo como:
TCP    0.0.0.0:12345      0.0.0.0:0           LISTENING    12345

El último número (12345 en este ejemplo) es el PID (Process ID) del proceso que está ocupando el puerto.

Finaliza el proceso con el PID identificado:
taskkill /PID 12345 /F


- Si no pudiesemos liberar el puerto, podemos cambiarlo:

En Servidor.java:
private static final int PUERTO = 55555; // Cambia a un puerto disponible.

En Cliente.java:
private static final int PUERTO = 55666; // Cambia a coincidir con el servidor.

Después de todo esto , el progrmaa se puede ejecutar sin problemas con las indicaciones que hay  al principio del documento.
