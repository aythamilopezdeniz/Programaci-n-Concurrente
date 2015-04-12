package hipermercado;

import java.util.Scanner;

public class Main {
    private static String Clientes;
    private static String Cajas;
    public static void main(String[] args) {
        Scanner leer=new Scanner(System.in);
        System.out.println("Establezca en número de cajas en funcionamiento: ");
        Cajas=leer.nextLine();
        System.out.println("Establezca el número de clientes: ");
        Clientes=leer.nextLine();
        System.out.println("Cajas: "+Cajas+" y Clientes: "+Clientes);
    }
}