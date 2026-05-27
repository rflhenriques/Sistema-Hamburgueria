package main.cozinha;

public class EstacaoMontagem extends EstacaoCozinha {

    public EstacaoMontagem() {
        super("Montagem");
    }

    @Override
    public void executar(String tarefa) {
        System.out.println("[Mediator/Montagem] Montando pedido: " + tarefa);
        System.out.println("[Mediator/Montagem] ✅ Pedido montado e embalado!");
        avisar("PEDIDO_PRONTO");
    }
}