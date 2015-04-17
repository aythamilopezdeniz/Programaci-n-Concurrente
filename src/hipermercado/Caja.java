package hipermercado;

import java.util.Date;

public class Caja extends Thread{
    private final Contabilidad contabilidad;
    private static int idUnique=0;
    private final Cola cola;
    private Date date;
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
            date=new Date();
            System.out.println("Hora "+date.getHours()+":"+date.getMinutes()+":"+
                    date.getSeconds()+" Sacar cliente "+cliente.toString());
            contabilidadCaja+=cliente.damePrecioCarro();
            try {
                sleep((long) ((contabilidadCaja/10)*1000));
                System.out.println(" Cliente cobrado");
            } catch (InterruptedException ex) {}
        }
        contabilidad.añadeSaldo(contabilidadCaja);
        System.out.println("Cerrando caja y registrando contabilidad.");
    }
}