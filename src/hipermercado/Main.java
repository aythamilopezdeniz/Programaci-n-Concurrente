package hipermercado;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    private static int Clientes;
    private static int numeroCajas;
    private static ArrayList<Caja> cajas;
    public static void main(String[] args) {
        cajas=new ArrayList<>();
        numeroCajas=numeroDeCajas();
        Clientes=numeroClientes();
        Cola cola=new Cola();
        Contabilidad contabilidad=new Contabilidad();
        for(int i=0;i<Clientes;i++){
            cola.añadirFinal();
            System.out.println("Añadiendo cliente "+(i+1));
        }
        execute(numeroCajas, Clientes, cola, contabilidad);
    }

    private static void execute(int Cajas, int Clientes, Cola cola, Contabilidad contabilidad) {
        long t1 = System.currentTimeMillis();
        long t2 = System.currentTimeMillis();
        for (int i=0;i<Cajas; i++) {
            cajas.add(new Caja(cola, contabilidad));
        }
        for(int i=0;i<Cajas;i++){
            cajas.get(i).start();
            System.out.println("Abriendo Caja "+(i+1));
        }
        for(int i=0;i<cajas.size(); i++){
            try {
                cajas.get(i).join();
            } catch (InterruptedException ex) {
            }
        }
        int cliente = new Random().nextInt(5);
        //Esperar un tiempo para añadir nuevo cliente a la cola        
        //esperar hasta los 60s para cerrar la cola.
        t2 = System.currentTimeMillis();
        if((t2-t1)*0.001==3){
            System.out.println("hola");
            //cola.cerrar();
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
}