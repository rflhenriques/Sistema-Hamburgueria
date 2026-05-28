package main.interpreter;

import main.cardapio.IItemCardapio;
import main.cardapio.RemoverIngredienteDecorator;
import main.cardapio.BaconDecorator;
import main.cardapio.CheddarDecorator;
import main.cardapio.OvoDecorator;
import main.model.Carrinho;
import java.util.List;

public class ModificadorExpressao implements IExpressao {

    private final String modificador;
    private final String alvo;

    public ModificadorExpressao(String modificador, String alvo) {
        this.modificador = modificador.toLowerCase();
        this.alvo = alvo.toLowerCase();
    }

    @Override
    public void interpretar(Carrinho contexto) {
        List<IItemCardapio> itens = contexto.getItens();

        if(itens.isEmpty()) {
            System.out.println("[Interpreter/Modificador] Nenhum item no carrinho "
                    + "para modificar.");
            return;
        }

        IItemCardapio ultimo = itens.get(itens.size() - 1);
        IItemCardapio modificado = aplicarModificador(ultimo);

        if (modificado != ultimo) {
            contexto.removerItem(ultimo);
            contexto.adicionarItem(modificado);
            System.out.println("[Interpreter/Modificador] Aplicado \""
                    + modificador + " " + alvo + "\" em: "
                    + modificado.getDescricao());
        }
    }

    private IItemCardapio aplicarModificador(IItemCardapio item) {

        if (modificador.equals("sem")) {
            return new RemoverIngredienteDecorator(item,alvo);
        }
        if (modificador.equals("com") || modificador.equals ("adicionar")) {
            switch (alvo) {
                case "bacon": return new BaconDecorator(item);
                case "cheddar": return new CheddarDecorator(item);
                case "ovo": return new OvoDecorator(item);
                default:
                    System.out.println("[Interpreter/Modificador] Adicional \""
                            + alvo + "\" não reconhecido");
                    return item;
            }
        }
        return item;
    }
}