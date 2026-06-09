package Modelos;
public class Predio {
    private String npn;
    private String municipio;
    private String direccion;
    private String ficha;

    public Predio(String npn, String municipio, String direccion, String ficha) {
        this.npn = npn != null ? npn.trim() : "";
        this.municipio = municipio != null ? municipio.trim() : "";
        this.direccion = direccion != null ? direccion.trim() : "";
        this.ficha = ficha != null ? ficha.trim() : "";
    }
    public String getNpn() { return npn; }
    public String getMunicipio() { return municipio; }
    public String getDireccion() { return direccion; }
    public String getFicha() { return ficha; }
    public String getValorPorColumna(String columna) {
        switch (columna.toLowerCase()) {
            case "npn": return npn;
            case "municipio": return municipio;
            case "direccion": return direccion;
            case "ficha": return ficha;
            default: return "";
        }
    }

    @Override
    public String toString() {
        return String.format("NPN: %s | Mun: %s | Dir: %s | Ficha: %s", npn, municipio, direccion, ficha);
    }
}