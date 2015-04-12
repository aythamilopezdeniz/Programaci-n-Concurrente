package hipermercado;
import java.util.ArrayList;
public class Cola {
    private int tamCola;
    private int maximoCola;
    ArrayList<Cliente> Cola;
    //private Cliente[] Cola;

    public Cola() {
        Cola=new ArrayList<>();
        //Cola=new Cliente[20];
        this.maximoCola=0;
        this.tamCola=0;
    }
    
    public void añadirFinal(){
        if(tamCola<Cola.size()){
            Cola.add(new Cliente());
            if(Cola.size()>maximoCola)
                maximoCola=Cola.size();
            /*Cola[tamCola]=new Cliente();
            tamCola++;*/
        }
    }

    public void añadirPrincipio(){
        if(tamCola<Cola.size()){
            Cola.add(0, new Cliente());
            if(Cola.size()>maximoCola)
                maximoCola=Cola.size();
            /*Cliente[] aux=new Cliente[tamCola];
            for(int i=0;i<Cola.length;i++){
                aux[i]=Cola[i];
            }
            Cola[0]=new Cliente();
            tamCola++;
            for(int i=0;i<aux.length;i++){
                Cola[i+1]=aux[i];
            }*/
        }
    }
    
    public Cliente sacar(){
        return Cola.remove(0);
        /*for(int i=0;i<Cola.size()-1;i++) {
            Cola[i]=Cola[i+1];
        }
        tamCola--;
        return Cola;*/
    }
    
    public void cerrar(){
    }
    
    public int tamañoMáximo(){
        return maximoCola;
    }
}