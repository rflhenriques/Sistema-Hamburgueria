package main.interpreter;

import main.model.Carrinho;

public class QuantidadeExpressao implements IExpressao {

    private final int quantidade;

    private final IExpressao itemExpressao;

    public QuantidadeExpressao(int quantidade, IExpressao itemExpressao) {
        this.quantidade = quantidade;
        this.itemExpressao = itemExpressao;
    }

    @Override
    public void interpretar(Carrinho contexto) {
        System.out.println("[Interpreter/Quantidade] Adicionando " + quantidade + "x ...");
        for (int i = 0; i < quantidade; i++) {
            itemExpressao.interpretar(contexto);
        }
    }
}