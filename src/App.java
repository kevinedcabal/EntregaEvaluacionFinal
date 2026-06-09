import Controlador.PredioControlador;
import Modelos.PredioCSV;
import Vistas.PredioVista;

public class App {
    public static void main(String[] args) throws Exception {
        PredioCSV modelo = new PredioCSV();
        PredioVista vista = new PredioVista();
        PredioControlador controlador = new PredioControlador(modelo, vista);
        
        String rutaArchivo = "src/predios.csv";
        controlador.iniciar(rutaArchivo);
    }
}
