package test;

import main.cardapio.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Composite + Decorator")

class CardapioAdicionaisTest {

    @Test @DisplayName("Preço do combo é a soma dos filhos com desconto")
    void testPrecoCombo() {
        Combo combo = new Combo("Teste", 10.0);
        combo.adicionar(new Produto("Item1", 10.0, "Hamburguer", FabricaInfoNutricional.getInfo("Burguer Clássico")));
        combo.adicionar(new Produto("Item2", 10.0, "Bebida", FabricaInfoNutricional.getInfo("Refrigerante")));
        assertEquals(18.00, combo.getPreco(), 0.01);
    }

    @Test @DisplayName("Decorator BaconDecorator acumula preço corretamente")
    void testBaconDecorator() {
        Produto base = new Produto("Burguer", 20.0, "Hamburguer", FabricaInfoNutricional.getInfo("Burguer Clássico"));
        IItemCardapio comBacon = new BaconDecorator(base);
        assertEquals(23.00, comBacon.getPreco(),0.01);
        assertTrue(comBacon.getDescricao().contains("+ Bacon"));
    }

    @Test @DisplayName("Múltiplos decoratos acumulam preços e descrições")
    void testMultiplosDecorators() {
        Produto base = new Produto("Burguer", 20.0, "Hamburguer", FabricaInfoNutricional.getInfo("Burguer Clássico"));
        IItemCardapio dec = new OvoDecorator(new CheddarDecorator(new BaconDecorator(base)));
        assertEquals(27.50, dec.getPreco(), 0.01);
        assertTrue(dec.getDescricao().contains(" + Bacon"));
        assertTrue(dec.getDescricao().contains(" + Cheddar"));
        assertTrue(dec.getDescricao().contains(" + Ovo"));
    }

    @Test @DisplayName("RemoverIngredienteDecorator não altera preço")
    void testRemoverIngrediente() {
        Produto base = new Produto("Burger", 20.00, "Hambúrguer", FabricaInfoNutricional.getInfo("Burger Clássico"));
        IItemCardapio semAlface = new RemoverIngredienteDecorator(base, "Alface");
        assertEquals(20.00, semAlface.getPreco(), 0.01);
        assertTrue(semAlface.getDescricao().contains("sem Alface"));
    }
}
