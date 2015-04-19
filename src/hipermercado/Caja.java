package hipermercado;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Caja extends Thread{
    private final Contabilidad contabilidad;
    private static int idUnique=0;
    private final Cola cola;
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
        System.out.println("Caja "+id+" Abierta");
        while(true){
            Cliente cliente=cola.sacar();
            if(cliente==null)break;
            contabilidadCaja+=cliente.damePrecioCarro();
            try {
                sleep((long) ((contabilidadCaja/10)*1000));
                System.out.print("Atendiendo a "+cliente.toString()+" Hora "+
                        GregorianCalendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+
                        GregorianCalendar.getInstance().get(Calendar.MINUTE)+":"+
                        GregorianCalendar.getInstance().get(Calendar.SECOND)+".");
                System.out.println(" Cliente cobrado");
            } catch (InterruptedException ex) {
                System.out.println("Caja "+id+" no operativa, disculpe las molestias.");
                cola.añadirPrincipio(cliente);
            }
        }
        if(contabilidadCaja==0)
            System.out.println("Caja Cerrada.");
        else
            System.out.println("Cerrando caja y registrando contabilidad."); 
        contabilidad.añadeSaldo(contabilidadCaja);
    }
}