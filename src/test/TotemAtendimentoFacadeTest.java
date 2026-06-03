package test;

import main.atendimento.TotemFacade;
import main.cardapio.Produto;
import main.cardapio.FabricaInfoNutricional;
import main.model.Carrinho;
import main.model.Pedido;
import main.pagamento.IEstrategiaPagamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Facade — TotemAtendimentoFacade (Simulando o Totem da Hamburgueria)")
class TotemAtendimentoFacadeTest {

    private TotemFacade facade;
    private Carrinho carrinho;
    private Produto burger;

    @BeforeEach
    void setUp() {
        facade = new TotemFacade();
        carrinho = new Carrinho();
        burger = new Produto("Burger Clássico", 22.90, "Hambúrguer",
                FabricaInfoNutricional.getInfo("Burger Clássico"));
        carrinho.adicionarItem(burger);
    }

    @Test @DisplayName("finalizarPedido() com pagamento aprovado retorna pedido na cozinha")
    void testFinalizarPedidoComSucesso() {
        IEstrategiaPagamento pagamentoOk = new IEstrategiaPagamento() {
            @Override
            public boolean processar(double valor) {
                return true; // Pagamento simulado com sucesso
            }

            @Override
            public String getDescricao() {
                return "Pagamento Mock Aprovado";
            }
        };

        Pedido pedido = facade.finalizarPedido(carrinho, "Cliente Teste", pagamentoOk, Collections.emptyList());

        assertNotNull(pedido, "Pedido não deveria ser nulo com pagamento aprovado");
        assertEquals("Cliente Teste", pedido.getNomeCliente());
        assertEquals("NA_COZINHA", pedido.getEstadoAtual().getNome(), "Pedido deveria ter avançado para NA_COZINHA");
        assertEquals(22.90, pedido.getTotalPago(), 0.01);
    }

    @Test @DisplayName("finalizarPedido() com pagamento recusado retorna null")
    void testFinalizarPedidoComPagamentoRecusado() {
        IEstrategiaPagamento pagamentoRecusado = new IEstrategiaPagamento() {
            @Override
            public boolean processar(double valor) {
                return false;
            }

            @Override
            public String getDescricao() {
                return "Pagamento Mock Recusado";
            }
        };

        Pedido pedido = facade.finalizarPedido(carrinho, "Cliente Sem Saldo", pagamentoRecusado, null);

        assertNull(pedido, "Pedido deveria ser nulo quando o pagamento é recusado");
    }

    @Test @DisplayName("finalizarPedido() associa observers corretamente")
    void testFinalizarPedidoComObservers() {
        IEstrategiaPagamento pagamentoOk = new IEstrategiaPagamento() {
            @Override
            public boolean processar(double valor) {
                return true;
            }

            @Override
            public String getDescricao() {
                return "Pagamento Mock Aprovado";
            }
        };

        boolean[] notificado = {false};

        Pedido pedido = facade.finalizarPedido(carrinho, "Cliente Notificado", pagamentoOk,
                Collections.singletonList(p -> notificado[0] = true));

        assertNotNull(pedido);

        pedido.avancarEstado();

        assertTrue(notificado[0], "Observer deveria ter sido notificado da mudança de estado");
    }
}