package main.cardapio;

import main.visitor.IVisitor;

public interface IItemCardapio {

    double getPreco();

    String getDescricao();

    void aceitar(IVisitor visitor);
}