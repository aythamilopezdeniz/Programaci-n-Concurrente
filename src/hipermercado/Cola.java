package hipermercado;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Cola {
    private int maximoCola;
    private final ArrayList<Cliente> cola;
    private boolean cerrada;

    public Cola() {
        cola=new ArrayList<>();
        this.maximoCola=0;
        this.cerrada=false;
    }

    public synchronized void añadirFinal(Cliente cliente){
        if(cerrada==true)return;
        int random=((int) (Math.random()*5)+1);
        try {
            wait(random*1000);
        } catch (InterruptedException ex) {}
        cola.add(cliente);
        System.out.print("Cliente "+cliente.dameNombre()+" añadido al final de la cola a las ");
        Main.dameHora(GregorianCalendar.getInstance().get(Calendar.HOUR_OF_DAY), 
                GregorianCalendar.getInstance().get(Calendar.MINUTE), 
                GregorianCalendar.getInstance().get(Calendar.SECOND));
        System.out.println(".");
        if(cola.size()>maximoCola)
            maximoCola=cola.size();
        notify();
    }

    public synchronized void añadirPrincipio(Cliente cliente){
        if(cerrada==true)return;
        cola.add(0, cliente);
        System.out.print("Cliente "+cliente.dameNombre()+" añadido al principio de la cola a las ");
        Main.dameHora(GregorianCalendar.getInstance().get(Calendar.HOUR_OF_DAY), 
                GregorianCalendar.getInstance().get(Calendar.MINUTE), 
                GregorianCalendar.getInstance().get(Calendar.SECOND));
        System.out.println(".");
        if(cola.size()>maximoCola)
            maximoCola=cola.size();
        notify();
    }
    
    public synchronized Cliente sacar(){
        while(!cerrada){
            try {
                wait(10000);
                if(cola.size()>0)break;
            } catch (InterruptedException ex) {}
        }
        if(cola.isEmpty())return null;
        System.out.print("Sacando a "+cola.get(0).dameNombre()+" de la cola a las ");
        Main.dameHora(GregorianCalendar.getInstance().get(Calendar.HOUR_OF_DAY), 
                GregorianCalendar.getInstance().get(Calendar.MINUTE), 
                GregorianCalendar.getInstance().get(Calendar.SECOND));
        System.out.println(".");
        return cola.remove(0);
    }
    
    public synchronized void cerrar(){
        cerrada=true;
    }
    
    public int tamañoMáximo(){
        return maximoCola;
    }
}