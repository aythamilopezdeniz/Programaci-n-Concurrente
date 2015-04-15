package hipermercado;
import java.util.ArrayList;
public class Cola {
    private int tamCola;
    private int maximoCola;
    ArrayList<Cliente> cola;
    private boolean cerrada;

    public Cola() {
        cola=new ArrayList<>();
        this.maximoCola=0;
        this.tamCola=0;
    }

    public synchronized void añadirFinal(){
        if(cerrada==true)return;
        cola.add(new Cliente());
        if(cola.size()>maximoCola)
            maximoCola=cola.size();
    }

    public synchronized void añadirPrincipio(Cliente cliente){
        cola.add(0, cliente);
        if(cola.size()>maximoCola)
            maximoCola=cola.size();
    }
    
    public synchronized Cliente sacar(){
        if(cola.isEmpty())return null;
        return cola.remove(0);
    }
    
    public synchronized void cerrar(){
        cerrada=true;
    }
    
    public int tamañoMáximo(){
        return maximoCola;
    }
}