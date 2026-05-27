package cozinha;

import model.Pedido;

public void interface IEstadoPedido {
    void avancar(Pedido pedido);
    void cancelar(Pedido pedido);
    String getNome();
}
