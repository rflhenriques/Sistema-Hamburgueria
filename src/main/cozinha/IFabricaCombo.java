package main.cozinha;

import main.cardapio.Produto;

public interface IFabricaCombo {

    Produto criarBurguer();

    Produto criarBebida();

    Produto criarAcompanhamento();

    Produto criarSobremesa();
}