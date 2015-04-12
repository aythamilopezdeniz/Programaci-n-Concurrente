package hipermercado;
public class Caja extends Thread{
    private Contabilidad contabilidad;
    private static int idUnique=0;
    private Cola cola;
    private int id=0;

    public Caja(Cola Cola, Contabilidad Contabilidad) {
        this.contabilidad=Contabilidad;
        this.cola=Cola;
        this.id=idUnique++;
    }

    public Contabilidad getContabilidad() {
        return contabilidad;
    }

    public void setContabilidad(Contabilidad contabilidad) {
        this.contabilidad = contabilidad;
    }

    public static int getIdUnique() {
        return idUnique;
    }

    public static void setIdUnique(int idUnique) {
        Caja.idUnique = idUnique;
    }

    public Cola getCola() {
        return cola;
    }

    public void setCola(Cola cola) {
        this.cola = cola;
    }

    @Override
    public void run() {
    }
}