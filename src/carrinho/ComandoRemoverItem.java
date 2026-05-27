package carrinho;

import cardapio.IItemCardapio;
import model.Carrinho;

public class ComandoRemoverItem implements IComando {

    private final Carrinho carrinho;
    private final IItemCardapio item;

    public ComandoRemoverItem(Carrinho carrinho, IItemCardapio item) {
        this.carrinho = carrinho;
        this.item = item;
    }

    @Override
    public void executar() {
        System.out.println("[Command] Executando: remover "
                + item.getDescricao());
        carrinho.removerItem(item);
    }

    @Override
    public void desfazer() {
        System.out.println("[Command] Desfazendo: remover "
                + item.getDescricao());
        carrinho.desfazer();
    }

    @Override
    public String getDescricao() {
        return "Remover " + item.getDescricao();
    }
}
