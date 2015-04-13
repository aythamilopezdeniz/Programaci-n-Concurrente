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
        Contabilidad contabilidad=new Contabilidad();
        for(int i=0;i<Clientes;i++){
            cola.añadirFinal();
        }
        /*Scanner leer=new Scanner(System.in);
        System.out.println("Establezca en número de cajas en funcionamiento: ");
        Cajas=Integer.parseInt(leer.nextInt());
        System.out.println("Establezca el número de clientes: ");
        Clientes=leer.nextLine();
        System.out.println("Cajas: "+Cajas+" y Clientes: "+Clientes);*/
        /*Console();*/
        execute(cajas, Cajas, Clientes, cola, contabilidad);
    }

    private static void Console() {
        Supermercado supermercado=new Supermercado();
        Control control=new Control(supermercado);
        control.execute();
    }

    private static void execute(ArrayList<Caja> cajas, int Cajas, int Clientes, 
            Cola cola, Contabilidad contabilidad) {
        for (int i=0;i<Cajas; i++) {
            cajas.add(new Caja(cola, contabilidad));
        }
        for(int i=0;i<Cajas;i++){
            cajas.get(i).start();
        }
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