package main.cozinha;

import main.cardapio.Produto;

public abstract class PreparoLanche {

    public final void prepararLanche(Produto lanche) {
        System.out.println("\n[Template] Iniciando preparo: "
                + lanche.getDescricao());
        tostarPao();
        prepararProteina();
        montar();
        embalar();
        System.out.println("[Template] ✅ Lanche pronto: "
                + lanche.getDescricao());
    }

    private void tostarPao() {
        System.out.println("[Template] 1. Tostando o pão...");
    }

    private void montar() {
        System.out.println("[Template] 3. Montando o lanche...");
    }

    private void embalar() {
        System.out.println("[Template] 4. Embalando o pedido...");
    }

    protected abstract void prepararProteina();
}
