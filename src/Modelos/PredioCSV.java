package Modelos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PredioCSV {
    private static final String SEPARADOR = ",";
    private static final int NUMERO_CAMPOS = 4;
    
    public List<Predio> cargarPredios(String rutaArchivo) throws IOException {
        List<Predio> predios = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            br.readLine(); 
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(SEPARADOR);
                if (datos.length >= NUMERO_CAMPOS) {
                    predios.add(new Predio(datos[0], datos[1], datos[2], datos[3]));
                }
            }
        }
        return predios;
    }
}
