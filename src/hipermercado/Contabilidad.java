package hipermercado;
public class Contabilidad {
    private double saldo;

    public Contabilidad() {
        this.saldo=0;
    }
    
    public synchronized void añadeSaldo(double Saldo){
        this.saldo+=Saldo;
    }
    
    public synchronized double dameSaldo(){
        return saldo;
    }
}