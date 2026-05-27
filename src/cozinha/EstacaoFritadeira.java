package cozinha;

public class EstacaoFritadeira extends EstacaoCozinha {

    public EstacaoFritadeira() {
        super("Fritadeira");
    }

    @Override
    public void executar(String tarefa) {
        System.out.println("[Mediator/Fritadeira] Fritando: " + tarefa);
        System.out.println("[Mediator/Fritadeira] ✅ Acompanhamento pronto!");
        avisar("ACOMPANHAMENTO_PRONTO");
    }
}