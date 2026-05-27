package main.atendimento;

import main.model.Pedido;

public interface IOperacaoCaixa {
    boolean cancelarPedido(Pedido pedido, String senhaOperador);
    boolean estornarPagamento(Pedido pedido, String senhaOperador);
    void abrirCaixa(String operador);
    void fecharCaixa();
}
