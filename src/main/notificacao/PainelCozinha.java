package main.notificacao;

import main.model.Pedido;

public class PainelCozinha implements IObserver {

    private final String nomePainel;

    public PainelCozinha(String nomePainel) {
        this.nomePainel = nomePainel;
    }

    @Override
    public void atualizar(Pedido pedido) {
        System.out.println("[Observer/PainelCozinha:" + nomePainel + "] "
                + "Pedido #" + pedido.getId()
                +" (" + pedido.getNomeCliente() + ") -> "
                + pedido.getEstadoAtual().getNome());
    }
}
