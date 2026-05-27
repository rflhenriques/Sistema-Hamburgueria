package cozinha;

public class EstacaoChapa extends EstacaoCozinha {

    public EstacaoChapa() {
        super("Chapa");
    }

    @Override
    public void executar(String tarefa) {
        System.out.println("[Mediator/Chapa] Grelhando: " + tarefa);
        System.out.println("[Mediator/Chapa] ✅ Proteína pronta!");
        avisar("PROTEINA_PRONTA");
    }
}