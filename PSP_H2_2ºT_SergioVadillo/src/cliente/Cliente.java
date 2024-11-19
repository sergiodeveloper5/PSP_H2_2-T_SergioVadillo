package cliente;

import java.io.*;
import java.net.*;

/**
 * Clase principal para conectar y enviar solicitudes al servidor.
 */
public class Cliente {
    private static final String HOST = "localhost"; // Dirección del servidor.
    private static final int PUERTO = 12345; // Puerto del servidor.

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PUERTO);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader consola = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Conectado al servidor en " + HOST + ":" + PUERTO);
            String mensaje;

            // Bucle para enviar solicitudes hasta que el usuario escriba "salir".
            do {
                System.out.print("Ingrese clave de búsqueda (o 'salir' para terminar): ");
                mensaje = consola.readLine(); // Leer mensaje del usuario.

                if (mensaje.equalsIgnoreCase("salir")) {
                    salida.println(mensaje); // Enviar "salir" al servidor.
                    break;
                }

                salida.println("BUSCAR:" + mensaje); // Enviar comando de búsqueda.

             // Leer las respuestas del servidor.
                String respuesta;
                while ((respuesta = entrada.readLine()) != null && !respuesta.isEmpty()) {
                    System.out.println("Servidor: " + respuesta);
                }
                System.out.println("Fin de la respuesta del servidor.");
            } while (!mensaje.equalsIgnoreCase("salir"));

            System.out.println("Desconectado del servidor.");
        } catch (IOException e) {
            System.err.println("Error en la comunicación con el servidor: " + e.getMessage());
        }
    }
}
