package main.notificacao;

import main.model.Pedido;

public interface IObserver {

    void atualizar(Pedido pedido);

}
