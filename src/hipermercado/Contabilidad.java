package hipermercado;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Contabilidad {
    private double saldo;

    public Contabilidad() {
        this.saldo=0;
    }
    
    public synchronized void añadeSaldo(double Saldo){
        this.saldo+=Saldo;
        System.out.print("Añadiendo saldo a la contabilidad "+Saldo+"€ a las ");
        Main.dameHora(GregorianCalendar.getInstance().get(Calendar.HOUR_OF_DAY), 
                GregorianCalendar.getInstance().get(Calendar.MINUTE), 
                GregorianCalendar.getInstance().get(Calendar.SECOND));
        System.out.println(".");
    }
    
    public synchronized double dameSaldo(){
        return saldo;
    }
}