package hipermercado;

import java.util.Random;

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
    
    @Override
    public void run() {
        double contabilidadCaja=0;
        int i=0;
        while(true){
            Cliente cliente=cola.sacar();
            if(cliente==null)break;
            nuevoCliente();
            System.out.print("Sacar cliente "+cliente.dameNombre()+
                    " Precio Carro "+cliente.damePrecioCarro());
            contabilidadCaja+=cliente.damePrecioCarro();
            try {
                sleep((long) (contabilidadCaja/10000));
                System.out.println(" Cliente cobrado");
            } catch (InterruptedException ex) {
            }
            if(i==3){
                cola.cerrar();
            }
            i++;
        }
        contabilidad.añadeSaldo(contabilidadCaja);
    }

    private void nuevoCliente() {
        int time = new Random().nextInt(5);
        long t1=System.currentTimeMillis();
        long t2=System.currentTimeMillis();
        while((t2-t1)*0.001<time){
            t2=System.currentTimeMillis();
            if((t2-t1)*0.001==time)break;
        }
        System.out.println("Añade Cliente");
        cola.añadirFinal();
        /*try {
            sleep(time*1000);
        } catch (InterruptedException ex) {
        }*/
    }
}