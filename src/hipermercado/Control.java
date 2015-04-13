package hipermercado;

public class Control {
    private final Supermercado supermercado;

    public Control(Supermercado supermercado) {
        this.supermercado=supermercado;
    }

    public void execute() {
        supermercado.showCaja();
        supermercado.showClientes();
        supermercado.show();
    }
}