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
}