package hipermercado;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Supermercado {
    private int cajas;
    private int clientes;

    public int getCajas() {
        return cajas;
    }

    public int getClientes() {
        return clientes;
    }

    public void setCajas(int cajas) {
        this.cajas = cajas;
    }

    public void setClientes(int clientes) {
        this.clientes = clientes;
    }
    
    public void showCaja(){
        this.cajas=numeroDeCajas();
    }
    
    public void showClientes(){
        this.cajas=numeroClientes();
    }
    
    public void show(){
        System.out.println("hay "+cajas+" cajas disponibles y "+clientes+" clientes.");
    }

    private int numeroDeCajas() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        cajas=0;
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
    
    private int numeroClientes(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        clientes=0;
        boolean state=false;
        while(!state){
            System.out.print("Establezca el número de clientes: ");
            try{
                clientes= Integer.parseInt(reader.readLine());
                state=true;
            }catch(Exception e){
            }
        }
        return clientes;
    }
}