package cozinha;

import model.Pedido;

public class EstadoPago implements IEstadoPedido {

    @Override
    public void avancar(Pedido pedido) {
        System.out.println("[State] Pedido #" + pedido.getId()
                + " -> NA_COZINHA.");
        pedido.setEstado(new EstadoNaCozinha());
    }

    @Override
    public void cancelar(Pedido pedido) {
        System.out.println("[State] Pedido pago não pode ser cancelado sem estorno."
                + "Acione o gerente.");
    }

    @Override
    public String getNome() {
        return "PAGO";
    }
}