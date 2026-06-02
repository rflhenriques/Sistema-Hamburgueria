package main.pagamento;

public interface IEstrategiaPagamento {
    boolean processar (double valor);
    String getDescricao();
}
