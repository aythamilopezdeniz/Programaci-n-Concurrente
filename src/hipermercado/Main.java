package hipermercado;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;


public class Main {
    private static int numeroClientes;
    private static int numeroCajas;
    private static ArrayList<Caja> cajas;
    public static void main(String[] args) {
        cajas=new ArrayList<>();
        numeroCajas=numeroDeCajas();
        numeroClientes=numeroClientes();
        final Cola cola=new Cola();
        Contabilidad contabilidad=new Contabilidad();
        execute(numeroCajas, numeroClientes, cola, contabilidad);
        
    }

    private static void execute(int numeroCajas, int numeroClientes, 
            Cola cola, Contabilidad contabilidad) {
        for (int i=0;i<numeroCajas; i++) {
            cajas.add(new Caja(cola, contabilidad));
        }
        for(int i=0;i<numeroCajas;i++){
            cajas.get(i).start();
            System.out.println("Abriendo Caja "+(i+1));
        }
        for(int i=0;i<numeroClientes;i++){
            cola.añadirFinal(new Cliente());
        }
        cola.cerrar();
        for(int i=0;i<cajas.size(); i++){
            try {
                cajas.get(i).join();
            } catch (InterruptedException ex) {
            }
        }
        System.out.println("Contabilidad del Hipermercado "+contabilidad.dameSaldo()+"€");
    }
    
    /*private static void execute(int Cajas, int Clientes, Cola cola, Contabilidad contabilidad) {
        /*long t1=System.currentTimeMillis();
        long t2=System.currentTimeMillis();
        int j=0;
        while((t2-t1)/0.001<1){
            t2=System.currentTimeMillis();
            if(j==Clientes)break;
            j++;
            nuevoCliente(cola);
        }
        cola.cerrar();
        //Esperar un tiempo para añadir nuevo cliente a la cola        
        //esperar hasta los 60s para cerrar la cola.
    }*/
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
            Thread.sleep((long) (time/0.001));
            System.out.println("tiempo de espera "+time);
        } catch (InterruptedException ex) {
        }
        //System.out.println("Añadiendo Cliente");
        cola.añadirFinal(new Cliente());
    }
}/*public synchronized void añadirFinal(Cliente c) {
        if (cerrada == false){
            try {
                int rnd = ((int)(Math.random()*5)+1);
                System.out.println("Esperando "+"("+rnd+")"+" segundos para agregar al siguiente cliente");
                wait(rnd*1000);
            }catch(InterruptedException e){}
            miCola.add(c);
            System.out.println(dameHora()+" Cliente "+c.toString()+" añadido al final de la cola.");
            notifyAll();
        }
        private static void generaClientes (int c){
        for (int i=1;i<=c;i++){
            //colaClientes.start();
            colaClientes.añadirFinal(new Cliente());;
        }
    }*/