package main.cozinha;

import main.model.Pedido;

public interface IEstadoPedido {
    void avancar(Pedido pedido);
    void cancelar(Pedido pedido);
    String getNome();
}
