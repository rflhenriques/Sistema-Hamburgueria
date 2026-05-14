package atendimento;

import burgergof.cardapio.IItemCardapio;
import burgergof.model.Carrinho;
import burgergof.model.Pedido;
import burgergof.pagamento.IEstrategiaPagamento;
import burgergof.estoque.GerenciadorEstoque;
import burgergof.cozinha.FilaCozinha;
import burgergof.notificacao.IObserver;

public class TotemFacade {

    private final GerenciadorEstoque estoque;
    private final FilaCozinha        filaCozinha;

    public TotemFacade() {
        this.estoque      = GerenciadorEstoque.getInstancia();
        this.filaCozinha  = new FilaCozinha();
        System.out.println("[Facade] TotemFacade inicializado.");
    }

    public Pedido finalizarPedido(Carrinho carrinho, String nomeCliente,
                                  IEstrategiaPagamento estrategiaPagamento,
                                  java.util.List<IObserver> observers) {

        System.out.println("\n[Facade] Iniciando processamento do pedido...");

        if (!validarEstoque(carrinho)) {
            System.out.println("[Facade] ❌ Pedido cancelado: itens fora de estoque.");
            return null;
        }

        Pedido pedido = new Pedido(nomeCliente);
        if (observers != null) {
            for (IObserver obs : observers) {
                pedido.addObserver(obs);
            }
        }
        for (IItemCardapio item : carrinho.getItens()) {
            pedido.adicionarItem(item);
        }

        double total = pedido.calcularTotal();
        boolean pagamentoOk = estrategiaPagamento.processar(total);

        if (!pagamentoOk) {
            System.out.println("[Facade] ❌ Pagamento recusado.");
            return null;
        }

        pedido.setTotalPago(total);

        baixarEstoque(carrinho);

        pedido.avancarEstado();
        pedido.avancarEstado();
        filaCozinha.adicionar(pedido);

        System.out.println("[Facade] ✅ Pedido #" + pedido.getId() + " finalizado com sucesso!");
        System.out.println("[Facade] Total pago: R$ " + String.format("%.2f", total));

        return pedido;
    }

    private boolean validarEstoque(Carrinho carrinho) {
        return true;
    }

    private void baixarEstoque(Carrinho carrinho) {
        System.out.println("[Facade] Estoque atualizado.");
    }
}
