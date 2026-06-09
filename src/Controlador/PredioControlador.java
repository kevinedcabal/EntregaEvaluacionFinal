package Controlador;

import Modelos.*;
import Utilitarios.Ordenadores;
import Vistas.PredioVista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PredioControlador {
    private PredioCSV modelo;
    private PredioVista vista;
    private List<Predio> listaGlobal;
    private static final int MILISEGUNDOS_POR_HORA = 3600000;
    private static final int MILISEGUNDOS_POR_MINUTO = 60000;
    private static final int MILISEGUNDOS_POR_SEGUNDO = 1000;
    
    public PredioControlador(PredioCSV modelo, PredioVista vista) {
        this.modelo = modelo;
        this.vista = vista;
        
        this.vista.agregarListenerBotonBuscar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutarOrdenamiento();
            }
        });
    }

    public void iniciar(String rutaArchivo) {
        try {

            listaGlobal = modelo.cargarPredios(rutaArchivo);
            
            vista.actualizarTabla(listaGlobal);
            vista.setVisible(true);

        } catch (Exception e) {
            vista.mostrarError("Error al cargar el archivo CSV: " + e.getMessage());
        }
    }

    private void ejecutarOrdenamiento() {
        if (listaGlobal == null || listaGlobal.isEmpty()) return;

        String columna = vista.getColumnaSeleccionada();
        String criterio = vista.getCriterioBusqueda();


        if (!criterio.matches("\\d+")) {
            if (!criterio.isEmpty() && (columna.equals("NPN") || columna.equals("Ficha"))) {
            vista.mostrarError("Para la columna " + columna + ", el criterio de búsqueda debe ser estrictamente numérico.");
            return; 
            }
        }
        long inicio = System.currentTimeMillis();


        Ordenadores.quickSort(listaGlobal, columna);

        List<Predio> resultadosAMostrar;
        if (criterio.isEmpty()) {
            resultadosAMostrar = listaGlobal;
        } else {
            resultadosAMostrar = Ordenadores.busquedaBinariaParcial(listaGlobal, criterio, columna);
        }

        long fin = System.currentTimeMillis();
        long tiempoTotalMs = fin - inicio;

        vista.actualizarTabla(resultadosAMostrar);
        vista.setCronometro(formatearTiempo(tiempoTotalMs));
    }

    private String formatearTiempo(long milisegundos) {
        long horas = milisegundos / MILISEGUNDOS_POR_HORA;
        long minutos = (milisegundos % MILISEGUNDOS_POR_HORA) / MILISEGUNDOS_POR_MINUTO;
        long segundos = (milisegundos % MILISEGUNDOS_POR_MINUTO) / MILISEGUNDOS_POR_SEGUNDO;
        long ms = milisegundos % MILISEGUNDOS_POR_SEGUNDO;
        
        return String.format("%02d:%02d:%02d.%03d", horas, minutos, segundos, ms);
    }
}