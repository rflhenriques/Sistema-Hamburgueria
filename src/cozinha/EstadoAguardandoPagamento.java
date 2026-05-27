package cozinha;

import model.Pedido;

public class EstadoAguardandoPagamento implements IEstadoPedido {

    @Override
    public void avancar(Pedido pedido) {
        System.out.println("[State] Pagamento confirmado. Pedido #"
                + pedido.getId() + " → PAGO.");
        pedido.setEstado(new EstadoPago());
    }

    @Override
    public void cancelar(Pedido pedido) {
        System.out.println("[State] Pedido #" + pedido.getId() + " cancelado.");
        pedido.setEstado(new EstadoCancelado());
    }

    @Override
    public String getNome() {
        return "AGUARDANDO_PAGAMENTO";
    }
}