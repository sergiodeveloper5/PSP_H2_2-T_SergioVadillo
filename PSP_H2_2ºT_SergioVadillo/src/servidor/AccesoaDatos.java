package servidor;

import java.io.*;
import java.util.*;

/**
 * Clase para manejar el acceso a datos desde un archivo de texto.
 */
public class AccesoaDatos {
    private final String rutaArchivo;

        /**
     * Constructor que inicializa la ruta del archivo.
     * 
     * @param rutaArchivo Ruta del archivo a procesar.
     */
    public AccesoaDatos(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }


        /**
     * Busca líneas en el archivo que contengan una clave específica.
     * 
     * @param clave Texto a buscar en el archivo.
     * @return Lista de líneas que contienen la clave.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    public List<String> buscarPorClave(String clave) throws IOException {
        List<String> resultados = new ArrayList<>(); // Lista para almacenar los resultados.
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            // Bucle para leer el archivo línea por línea.
            while ((linea = br.readLine()) != null) {
            	// Si la línea contiene la clave, añádela a los resultados.
                if (linea.toLowerCase().contains(clave.toLowerCase())) {
                    resultados.add(linea);
                }
            }
        }
        return resultados; // Devuelve todo lo que coincida con la palabra clave.
    }
}
