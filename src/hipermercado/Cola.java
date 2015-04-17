package hipermercado;
import java.util.ArrayList;
import java.util.Date;
public class Cola {
    private int maximoCola;
    private final ArrayList<Cliente> cola;
    private boolean cerrada;
    private Date date;
    

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
        date=new Date();
        System.out.println("Hora "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+
                " Cliente "+cliente.toString()+" añadido a la cola");
        if(cola.size()>maximoCola)
            maximoCola=cola.size();
        notifyAll();
    }

    public synchronized void añadirPrincipio(Cliente cliente){
        if(cerrada==true)return;
        cola.add(0, cliente);
        System.out.println("Hola "+date.getHours()+date.getMinutes()+date.getSeconds()+
                "Cliente "+cliente.toString());
        if(cola.size()>maximoCola)
            maximoCola=cola.size();
        notifyAll();
    }
    
    public synchronized Cliente sacar(){
        while(!cerrada){
            try {
                wait(10000);
                if(cola.size()>0)break;
            } catch (InterruptedException ex) {}
        }
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