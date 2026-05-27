package main.cozinha;

import main.model.Pedido;

public class EstadoNaCozinha implements IEstadoPedido {

    @Override
    public void avancar(Pedido pedido) {
        System.out.println("[State] Pedido #" + pedido.getId()
                + " → PRONTO.");
        pedido.setEstado(new EstadoPronto());
    }

    @Override
    public void cancelar(Pedido pedido) {
        throw new IllegalStateException(
                "[State] Operação inválida: pedido em preparo não pode ser cancelado."
        );
    }

    @Override
    public String getNome() {
        return "NA_COZINHA";
    }
}