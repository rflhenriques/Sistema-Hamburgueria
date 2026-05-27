package carrinho;

import cardapio.IItemCardapio;
import model.Carrinho;

public class ComandoAdicionarItem implements IComando {

    private final Carrinho carrinho;
    private final IItemCardapio item;

    public ComandoAdicionarItem(Carrinho carrinho, IItemCardapio item) {
        this.carrinho = carrinho;
        this.item = item;
    }

    @Override
    public void executar() {
        System.out.println("[Command] Executando: adicionar "
                + item.getDescricao());
        carrinho.adicionarItem(item);
    }

    @Override
    public void desfazer() {
        System.out.println("[Command] Desfazendo: adicionar "
                + item.getDescricao());
        carrinho.desfazer();
    }

    @Override
    public String getDescricao() {
        return "Adicionar " + item.getDescricao();
    }
}
