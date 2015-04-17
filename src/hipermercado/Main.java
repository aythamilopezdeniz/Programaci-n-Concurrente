package hipermercado;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;


public class Main {
    private static int clientes;
    private static int numeroCajas;
    private static ArrayList<Caja> cajas;
    public static void main(String[] args) {
        cajas=new ArrayList<>();
        numeroCajas=numeroDeCajas();
        clientes=numeroClientes();
        final Cola cola=new Cola();
        Contabilidad contabilidad=new Contabilidad();
        /*Timer timer=new Timer();
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                cola.cerrar();
                System.out.println("La cola se ha cerrado");
            }
        };
        timer.schedule(timerTask, 10000);*/
        execute(numeroCajas, clientes, cola, contabilidad);
        
    }
    private static void execute(int Cajas, int Clientes, Cola cola, Contabilidad contabilidad) {
        for (int i=0;i<Cajas; i++) {
            cajas.add(new Caja(cola, contabilidad));
        }
        for(int i=0;i<Cajas;i++){
            cajas.get(i).start();
            System.out.println("Abriendo Caja "+(i+1));
        }
        long t1=System.currentTimeMillis();
        long t2=System.currentTimeMillis();
        int j=1;
        while((t2-t1)*0.001<5){
            t2=System.currentTimeMillis();
            nuevoCliente(cola);
            if(j==Clientes)break;
            System.out.println("Añadiendo Cliente");
            j++;
        }
        cola.cerrar();
        //Esperar un tiempo para añadir nuevo cliente a la cola        
        //esperar hasta los 60s para cerrar la cola.
        for(int i=0;i<cajas.size(); i++){
            try {
                cajas.get(i).join();
            } catch (InterruptedException ex) {
            }
        }
        System.out.println("Contabilidad "+contabilidad.dameSaldo());
    }
    private static int numeroDeCajas() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int cajas = 0;
        boolean state=false;
        while(!state){
            System.out.print("Establezca en número de cajas en funcionamiento: ");
            try{
                cajas= Integer.parseInt(reader.readLine());
                state=true;
            }catch(Exception e){
            }
        }
        return cajas;
    }
    
    private static int numeroClientes(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int clientes = 0;
        boolean state=false;
        while(!state){
            System.out.print("Establezca el número de clientes en la cola: ");
            try{
                clientes= Integer.parseInt(reader.readLine());
                state=true;
            }catch(Exception e){
            }
        }
        return clientes;
    }

    private static void nuevoCliente(Cola cola) {
        int time=new Random().nextInt(5);
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
        }
        System.out.println("Añadiendo Cliente");
        cola.añadirFinal(new Cliente());
    }
}