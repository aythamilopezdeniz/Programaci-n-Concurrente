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
    
    @Override
    public void run() {
        double contabilidadCaja=0;
        while(true){
            Cliente cliente=cola.sacar();
            if(cliente==null)break;
            System.out.println("Sacar cliente "+cliente.dameNombre()+
                    " Precio Carro "+cliente.damePrecioCarro());
            contabilidadCaja+=cliente.damePrecioCarro();
            try {
                sleep((long) (contabilidadCaja/10000));
            } catch (InterruptedException ex) {
            }
        }
        contabilidad.añadeSaldo(contabilidadCaja);
    }
}