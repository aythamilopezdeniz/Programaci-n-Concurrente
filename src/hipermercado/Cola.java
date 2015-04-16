package hipermercado;
import java.util.ArrayList;
public class Cola {
    private int maximoCola;
    private ArrayList<Cliente> cola;
    private boolean cerrada;

    public Cola() {
        cola=new ArrayList<>();
        this.maximoCola=0;
        this.cerrada=false;
    }

    public synchronized void añadirFinal(){
        if(cerrada==true)return;
        cola.add(new Cliente());
        if(cola.size()>maximoCola)
            maximoCola=cola.size();
        notify();
    }

    public synchronized void añadirPrincipio(Cliente cliente){
        if(cerrada==true)return;
        cola.add(0, cliente);
        if(cola.size()>maximoCola)
            maximoCola=cola.size();
        notify();
    }
    
    public synchronized Cliente sacar(){
        if(cola.isEmpty())return null;
        /*while(!cerrada){
            try {
                wait(10000);
            } catch (InterruptedException ex) {
            }
        }*/
        return cola.remove(0);
    }
    
    public synchronized void cerrar(){
        cerrada=true;
    }
    
    public int tamañoMáximo(){
        return maximoCola;
    }
}