package hipermercado;
import java.util.ArrayList;
public class Cola {
    private int tamCola;
    private int maximoCola;
    ArrayList<Cliente> Cola;
    private boolean cerrada;

    public Cola() {
        Cola=new ArrayList<>();
        this.maximoCola=0;
        this.tamCola=0;
    }

    public synchronized ArrayList<Cliente> getCola() {
        return Cola;
    }

    public void setTamCola(int tamCola) {
        this.tamCola = tamCola;
    }
    
    public void añadirFinal(){
        if(cerrada==true)return;
        if(Cola.size()<tamCola){
            Cola.add(new Cliente());
            if(Cola.size()>maximoCola)
                maximoCola=Cola.size();
        }
    }

    public void añadirPrincipio(Cliente cliente){
        if(tamCola<Cola.size()){
            Cola.add(0, cliente);
            if(Cola.size()>maximoCola)
                maximoCola=Cola.size();
        }
    }
    
    public Cliente sacar(){
        return Cola.remove(0);
    }
    
    public void cerrar(){
        cerrada=true;
    }
    
    public int tamañoMáximo(){
        return maximoCola;
    }
}