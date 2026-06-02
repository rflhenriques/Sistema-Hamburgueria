package test;

import main.cardapio.FabricaInfoNutricional;
import main.cardapio.Produto;
import main.model.Pedido;
import main.model.PedidoMemento;
import main.cozinha.EstadoCancelado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Model — CicloDeVidaPedido (Testes isolados da entidade central)")
class CicloDeVidaPedidoTest {

    private Pedido pedido;
    private Produto burger;
    private Produto batata;

    @BeforeEach
    void setUp() {
        Pedido.resetContador();
        pedido = new Pedido("Cliente Unitário");
        burger = new Produto("Burger Clássico", 22.90, "Hambúrguer", FabricaInfoNutricional.getInfo("Burger Clássico"));
        batata = new Produto("Batata Frita", 10.00, "Acompanhamento", FabricaInfoNutricional.getInfo("Batata Frita"));
    }

    @Test @DisplayName("adicionarItem() e removerItem() atualizam a lista corretamente")
    void testAdicionarERemoverItem() {
        pedido.adicionarItem(burger);
        pedido.adicionarItem(batata);
        assertEquals(2, pedido.getItens().size());

        pedido.removerItem(burger);
        assertEquals(1, pedido.getItens().size());
        assertEquals("Batata Frita", pedido.getItens().get(0).getDescricao());
    }

    @Test @DisplayName("calcularTotal() soma corretamente o preço de todos os itens")
    void testCalcularTotal() {
        pedido.adicionarItem(burger);
        pedido.adicionarItem(batata);
        assertEquals(32.90, pedido.calcularTotal(), 0.01);
    }

    @Test @DisplayName("salvarMemento() e restaurarMemento() preservam estado dos itens (Memento)")
    void testMementoPedido() {
        pedido.adicionarItem(burger);

        PedidoMemento snapshot = pedido.salvarMemento();

        pedido.adicionarItem(batata);
        assertEquals(2, pedido.getItens().size());

        pedido.restaurarMemento(snapshot);
        assertEquals(1, pedido.getItens().size(), "O pedido deveria ter voltado para 1 item");
        assertEquals("Burger Clássico", pedido.getItens().get(0).getDescricao());
    }

    @Test @DisplayName("setTotalPago() e getTotalPago() funcionam corretamente")
    void testTotalPago() {
        pedido.setTotalPago(50.0);
        assertEquals(50.0, pedido.getTotalPago());
    }

    @Test @DisplayName("setEstado() muda o estado e toString() reflete o estado atual")
    void testSetEstadoEToString() {
        pedido.setEstado(new EstadoCancelado());
        assertEquals("CANCELADO", pedido.getEstadoAtual().getNome());
        assertTrue(pedido.toString().contains("CANCELADO"));
    }
}
