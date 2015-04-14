package hipermercado;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    private static int Clientes;
    private static int Cajas;
    public static void main(String[] args) {
        ArrayList<Caja> cajas=new ArrayList<>();
        Cajas=numeroDeCajas();
        Clientes=numeroClientes();
        Cola cola=new Cola();
        cola.setTamCola(Clientes);
        Contabilidad contabilidad=new Contabilidad();
        for(int i=0;i<Clientes;i++){
            cola.añadirFinal();
            System.out.println("Cliente "+cola.getCola().get(i).dameNombre()+" Carro "+
                    cola.getCola().get(i).damePrecioCarro());
        }
        execute(cajas, Cajas, Clientes, cola, contabilidad);
    }

    private static void execute(ArrayList<Caja> cajas, int Cajas, int Clientes, 
            Cola cola, Contabilidad contabilidad) {
        for (int i=0;i<Cajas; i++) {
            cajas.add(new Caja(cola, contabilidad));
        }
        for(int i=0;i<Cajas;i++){
            cajas.get(i).start();
        }
        for(int i=0;i<cajas.size();i++){
            contabilidad.añadeSaldo(cajas.get(i).getContabilidad().dameSaldo());
            //System.out.println(cajas.get(i).getContabilidad().dameSaldo());
            //System.out.println(cajas.get(i).getCola().getCola().get(0).dameNombre());
        }
        System.out.println("contabilidad "+contabilidad.dameSaldo());
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