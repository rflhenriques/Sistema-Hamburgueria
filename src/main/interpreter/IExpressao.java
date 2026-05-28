package main.interpreter;

import main.model.Carrinho;

public interface IExpressao {
    void interpretar(Carrinho contexto);
}