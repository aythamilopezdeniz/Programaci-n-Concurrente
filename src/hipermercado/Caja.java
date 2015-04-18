package hipermercado;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Caja extends Thread{
    private final Contabilidad contabilidad;
    private static int idUnique=0;
    private final Cola cola;
    private GregorianCalendar date;
    private int id=0;

    public Caja(Cola Cola, Contabilidad Contabilidad) {
        this.contabilidad=Contabilidad;
        this.cola=Cola;
        this.id=idUnique++;
    }
    
    @Override
    public void run() {
        double contabilidadCaja=0;
        int i=0;
        while(true){
            Cliente cliente=cola.sacar();
            if(cliente==null)break;
            date=new GregorianCalendar();
            contabilidadCaja+=cliente.damePrecioCarro();
            try {
                sleep((long) ((contabilidadCaja/10)*1000));
                System.out.print("Hora "+date.getTime().getHours()+":"+date.getTime().getMinutes()+":"+
                    date.getTime().getSeconds()+" Sacar cliente "+cliente.toString());
                System.out.println(" Cliente cobrado");
            } catch (InterruptedException ex) {}
        }
        if(contabilidadCaja==0)
            System.out.println("Cerrando caja.");
        else
            System.out.println("Cerrando caja y registrando contabilidad."); 
        contabilidad.añadeSaldo(contabilidadCaja);
    }
}