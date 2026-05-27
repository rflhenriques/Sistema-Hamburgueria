package main.cozinha;

import main.cardapio.Produto;
import main.cardapio.FabricaInfoNutricional;

public class FabricaComboClassico implements IFabricaCombo {

    @Override
    public Produto criarBurguer() {
        return new Produto("Burguer Clássico", 22.90, "Hamburguer",
                FabricaInfoNutricional.getInfo("Burguer Clássico"));
    }

    @Override
    public Produto CriarBebida() {
        return new Produto("Refrigerante", 7.00, "Bebida",
                FabricaInfoNutricional.getInfo("Refrigerante"));
    }

    @Override
    public Produto criarAcompanhamento() {
        return new Produto("Batata Frita", 10.00, "Acompanhamento",
                FabricaInfoNutricional.getInfo("Batata Frita"));
    }

    @Override
    public Produto criarSobremesa() {
        return new Produto("Sorvete", 8.00, "Sobremesa",
                FabricaInfoNutricional.getInfo("Sorvete"));
    }
}
