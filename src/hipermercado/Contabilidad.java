package hipermercado;
public class Contabilidad {
    private int saldo;

    public Contabilidad() {
        this.saldo=0;
    }
    
    public void añadeSaldo(int Saldo){
        this.saldo+=Saldo;
    }
    
    public int dameSaldo(){
        return saldo;
    }
}