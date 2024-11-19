package servidor;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

/**
 * Clase principal para iniciar el servidor.
 * Permite atender múltiples clientes simultáneamente.
 */
public class Servidor {
    private static final int PUERTO = 12345; // Puerto en el que el servidor estará a la espera de conexiones.

    public static void main(String[] args) {
    	// Este pool de hilos está esperando para manejar múltiples clientes.
        ExecutorService pool = Executors.newCachedThreadPool();
        // Instancia de la capa de acceso a datos, incluyendo la ruta al archivo.
        AccesoaDatos accesoDatos = new AccesoaDatos("data/datos.dat");

        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor iniciado en el puerto " + PUERTO);
            
            // // Bucle para aceptar conexiones de clientes.
            while (true) {
                Socket cliente = servidor.accept(); // Espera la conexión de un cliente.
                pool.execute(() -> atenderCliente(cliente, accesoDatos));// Cada cliente se procesa en un hlo independiente.
            }
        } catch (IOException e) {
            System.err.println("Error al iniciar el servidor: " + e.getMessage());
        } finally {
            pool.shutdown();// Al detener el servidor apagar el pool de hilos.
        }
    }
    
    /**
     * Atiende las peticiones de un cliente.
     * 
     * @param cliente      Socket del cliente.
     * @param accesoDatos  Instancia para acceso a los datos.
     */
    private static void atenderCliente(Socket cliente, AccesoaDatos accesoDatos) {
        try (
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true)
        ) {
            String mensajeCliente;

            // Lee solicitudes del cliente.
            while ((mensajeCliente = entrada.readLine()) != null) {
                if (mensajeCliente.equalsIgnoreCase("salir")) {
                    salida.println("Conexión cerrada. Adiós!");
                    break; // Cerrar conexión.
                }

                if (mensajeCliente.startsWith("BUSCAR:")) {
                	// Hace la busqueda.
                    String clave = mensajeCliente.substring(7).trim(); // Extrae la clave.
                    salida.println("Buscando por: " + clave);

                 // Busca resultados en el archivo.
                    try {
                        var resultados = accesoDatos.buscarPorClave(clave);
                        if (resultados.isEmpty()) {
                            salida.println("No se encontraron resultados para: " + clave);
                        } else {
                        	// Envia cada resultado al cliente.
                            for (String resultado : resultados) {
                                salida.println(resultado);
                            }
                        }
                    } catch (IOException e) {
                        salida.println("Error al acceder a los datos.");
                    }
                } else {
                    salida.println("Comando no reconocido.");
                }
                salida.println("");
            }
        } catch (IOException e) {
            System.err.println("Error en la comunicación con el cliente: " + e.getMessage());
        } finally {
            try {
                cliente.close();
            } catch (IOException e) {
                System.err.println("Error al cerrar el cliente: " + e.getMessage());
            }
        }
    }
}
