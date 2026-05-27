package cardapio;

import visitor.IVisitor;

public class Produto implements IItemCardapio {

    private final String nome;
    private final double preco;
    private final String categoria;
    private final InfoNutricional infoNutricional;

    public Produto(String nome, double preco, String categoria, InfoNutricional info) {
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
        this.infoNutricional = info;
    }

    @Override
    public double getPreco() {
        return preco;
    }

    @Override
    public String getDescricao() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public InfoNutricional getInfo() {
        return infoNutricional;
    }

    @Override
    public void aceitar(IVisitor visitor) {
        visitor.visitarProduto(this);
    }

    @Override
    public String toString() {
        return nome + " - R$ " + String.format("%.2f", preco);
    }
}