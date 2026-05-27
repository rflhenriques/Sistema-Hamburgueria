package main.cardapio;

public class RemoverIngredienteDecorator extends AdicionalDecorator {

    private final String ingredienteRemovido;

    public RemoverIngredienteDecorator(IItemCardapio itemBase, String ingrediente) {
        super(itemBase);
        this.ingredienteRemovido = ingrediente;
    }

    @Override
    public String getDescricao() {
        return itemBase.getDescricao() + " (sem " + ingredienteRemovido + ")";
    }

    @Override
    public double getPreco() {
        return itemBase.getPreco();
    }
}