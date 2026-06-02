package main.visitor;

import main.cardapio.Produto;
import main.cardapio.Combo;

public interface IVisitor {
    void visitarProduto(Produto produto);
    void visitarCombo(Combo combo);
}