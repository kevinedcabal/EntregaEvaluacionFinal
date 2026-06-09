package Utilitarios;
import Modelos.Predio;
import java.util.ArrayList;
import java.util.List;

public class Ordenadores {

    private final static int STATATICO  = 0;
    private final static int ASCENDENTE = -1;
    private final static int DESCENDENTE = 1;
    private final static int DIVISOR = 2;

    public static void quickSort(List<Predio> lista, String columna) {
        if (lista == null || lista.size() <= DESCENDENTE) return;
        quickSortRecursivo(lista, STATATICO, lista.size() - DESCENDENTE, columna);
    }

    private static void quickSortRecursivo(List<Predio> lista, int bajo, int alto, String columna) {
        if (bajo < alto) {
            int indiceParticion = particionHoare(lista, bajo, alto, columna);
            quickSortRecursivo(lista, bajo, indiceParticion, columna);
            quickSortRecursivo(lista, indiceParticion + DESCENDENTE, alto, columna);
        }
    }

    private static int particionHoare(List<Predio> lista, int bajo, int alto, String columna) {
        String pivote = lista.get(bajo + (alto - bajo) / DIVISOR).getValorPorColumna(columna).toLowerCase();
        int i = bajo - DESCENDENTE;
        int j = alto + DESCENDENTE;

        while (true) {
            do {
                i++;
            } while (lista.get(i).getValorPorColumna(columna).toLowerCase().compareTo(pivote) < STATATICO);

            do {
                j--;
            } while (lista.get(j).getValorPorColumna(columna).toLowerCase().compareTo(pivote) > STATATICO);

            if (i >= j) return j;


            Predio temp = lista.get(i);
            lista.set(i, lista.get(j));
            lista.set(j, temp);
        }
    }


    public static List<Predio> busquedaBinariaParcial(List<Predio> listaOrdenada, String criterio, String columna) {
        List<Predio> resultados = new ArrayList<>();
        if (listaOrdenada.isEmpty() || criterio.isEmpty()) return resultados;

        String criterioLower = criterio.toLowerCase();
        int inicio = STATATICO;
        int fin = listaOrdenada.size() - DESCENDENTE;
        int indiceEncontrado = ASCENDENTE;


        while (inicio <= fin) {
            int medio = inicio + (fin - inicio) / DIVISOR;
            String valorMedio = listaOrdenada.get(medio).getValorPorColumna(columna).toLowerCase();

            if (valorMedio.startsWith(criterioLower)) {
                indiceEncontrado = medio;
                break; 
            } else if (valorMedio.compareTo(criterioLower) < STATATICO) {
                inicio = medio + DESCENDENTE;
            } else {
                fin = medio - DESCENDENTE;
            }
        }


        if (indiceEncontrado != ASCENDENTE) {
            int izq = indiceEncontrado;
            while (izq >= STATATICO && listaOrdenada.get(izq).getValorPorColumna(columna).toLowerCase().startsWith(criterioLower)) {
                resultados.add(STATATICO, listaOrdenada.get(izq)); 
                izq--;
            }

            int der = indiceEncontrado + DESCENDENTE;
            while (der < listaOrdenada.size() && listaOrdenada.get(der).getValorPorColumna(columna).toLowerCase().startsWith(criterioLower)) {
                resultados.add(listaOrdenada.get(der));
                der++;
            }
        }
        return resultados;
    }
}