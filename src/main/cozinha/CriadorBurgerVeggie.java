package main.cozinha;

import main.cardapio.Produto;
import main.cardapio.FabricaInfoNutricional;

public class CriadorBurgerVeggie implements ICriadorLanche {

    @Override
    public Produto criarLanche() {
        return new Produto(
                "Burger Veggie",
                24.90,
                "Hambúrguer",
                FabricaInfoNutricional.getInfo("Burger Veggie"));
    }
}
