package hipermercado;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
        tiempoEspera(cola);
        execute(numeroCajas, numeroClientes, cola, contabilidad);
    }

    private static void execute(int numeroCajas, int numeroClientes, 
            Cola cola, Contabilidad contabilidad) {
        for (int i=0;i<numeroCajas; i++) {
            cajas.add(new Caja(cola, contabilidad));
        }
        for(int i=0;i<numeroCajas;i++){
            cajas.get(i).start();
        }
        //DuendeAveria duende=new DuendeAveria(cajas);
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
        System.out.println("Contabilidad total del Hipermercado:"+contabilidad.dameSaldo()+"€");
        System.out.println("Tamaño máximo de la cola:"+cola.tamañoMáximo());
    }

    private static int numeroDeCajas() {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        int cajas=0;
        boolean state=false;
        while(!state){
            System.out.print("Establezca en número de cajas en funcionamiento: ");
            try{
                cajas=Integer.parseInt(reader.readLine());
                state=true;
            }catch(Exception e){
            }
        }
        return cajas;
    }
    
    private static int numeroClientes(){
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        int clientes=0;
        boolean state=false;
        while(!state){
            System.out.print("Establezca el número de clientes en la cola: ");
            try{
                clientes=Integer.parseInt(reader.readLine());
                state=true;
            }catch(Exception e){
            }
        }
        return clientes;
    }

    private static void tiempoEspera(final Cola cola) {
        new Thread(){

            @Override
            public void run() {
                try {
                    sleep(60000);
                } catch (InterruptedException ex) {
                }
                cola.cerrar();
                System.out.println("La cola para pagar ha cerrada. Vuelva en otro momento.");
            }
        }.start();
    }

    public static void dameHora(int hora, int minutos, int segundos) {
        if(minutos<10&&segundos<10)
            System.out.print(hora+":0"+minutos+":0"+segundos);
        else if(minutos>=10&&segundos<10)
            System.out.print(hora+":"+minutos+":0"+segundos);
        else if(minutos<10&&segundos>=10)
            System.out.print(hora+":0"+minutos+":"+segundos);
        else System.out.print(hora+":"+minutos+":"+segundos);;
    }
}