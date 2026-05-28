package main.iterator;

import main.cardapio.IItemCardapio;

public interface IIterador {
    boolean temProximo();
    IItemCardapio proximo();
    void reiniciar();
}