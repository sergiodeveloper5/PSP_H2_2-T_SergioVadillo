package servidor;

import java.io.*;
import java.util.*;

// Clase para acceder al archivo y buscar líneas que contengan una clave específica.
public class AccesoaDatos {
    private final String rutaArchivo;

    // Constructor de la clase.
    public AccesoaDatos(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    // Busca todas las líneas del archivo que contengan la clave que has proporcionado.
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
