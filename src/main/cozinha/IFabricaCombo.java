package main.cozinha;

import main.cardapio.Produto;

public interface IFabricaCombo {

    Produto criarBurger();

    Produto criarBebida();

    Produto criarAcompanhamento();

    Produto criarSobremesa();

}