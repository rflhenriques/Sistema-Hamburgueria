import main.atendimento.*;
import main.cardapio.*;
import main.carrinho.*;
import main.cozinha.*;
import main.desconto.*;
import main.interpreter.*;
import main.iterator.*;
import main.model.*;
import main.notificacao.*;
import main.pagamento.*;
import main.visitor.*;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        separador("BURGERGOF — 23 PADRÕES EM AÇÃO");

        separador("1. SINGLETON");
        SistemaHamburgueria sistema1 = SistemaHamburgueria.getInstancia();
        SistemaHamburgueria sistema2 = SistemaHamburgueria.getInstancia();
        System.out.println("Mesma instância? " + (sistema1 == sistema2));
        System.out.println("Restaurante: " + sistema1.getNomeRestaurante());

        separador("2. FLYWEIGHT");
        InfoNutricional info1 = FabricaInfoNutricional.getInfo("Burger Clássico");
        InfoNutricional info2 = FabricaInfoNutricional.getInfo("Burger Clássico");
        System.out.println("Mesma instância nutricional? " + (info1 == info2));
        System.out.println("Instâncias em cache: " + FabricaInfoNutricional.totalInstancias());

        separador("3. FACTORY METHOD");
        ICriadorLanche criadorClassico = new CriadorBurgerClassico();
        ICriadorLanche criadorVeggie   = new CriadorBurgerVeggie();
        System.out.println(criadorClassico.descreverLanche());
        System.out.println(criadorVeggie.descreverLanche());

        separador("4. ABSTRACT FACTORY");
        IFabricaCombo fabClassico = new FabricaComboClassico();
        Combo comboClassico = new Combo("Combo Clássico", 10.0);
        comboClassico.adicionar(fabClassico.criarBurger());
        comboClassico.adicionar(fabClassico.criarBebida());
        comboClassico.adicionar(fabClassico.criarAcompanhamento());
        comboClassico.adicionar(fabClassico.criarSobremesa());
        System.out.println(comboClassico);

        IFabricaCombo fabVeggie = new FabricaComboVeggie();
        Combo comboVeggie = new Combo("Combo Veggie", 10.0);
        comboVeggie.adicionar(fabVeggie.criarBurger());
        comboVeggie.adicionar(fabVeggie.criarBebida());
        comboVeggie.adicionar(fabVeggie.criarAcompanhamento());
        comboVeggie.adicionar(fabVeggie.criarSobremesa());
        System.out.println(comboVeggie);

        separador("5. BUILDER");
        IBuilderLanche builder   = new MontadorBurgerCustomizado();
        DiretorCozinha diretor   = new DiretorCozinha(builder);
        Produto duploBacon       = diretor.construirDuploBacon();
        Produto lancheVegano     = diretor.construirVegano();
        System.out.println("Duplo Bacon: " + duploBacon);
        System.out.println("Vegano: "      + lancheVegano);

        separador("6. COMPOSITE");
        Produto batata = new Produto("Batata Frita", 10.00, "Acompanhamento",
                FabricaInfoNutricional.getInfo("Batata Frita"));
        System.out.println("Preço combo clássico (10% OFF): R$ "
                + String.format("%.2f", comboClassico.getPreco()));
        System.out.println("Descrição: " + comboClassico.getDescricao());

        separador("7. DECORATOR");
        Produto burgerBase = criadorClassico.criarLanche();
        IItemCardapio comBacon   = new BaconDecorator(burgerBase);
        IItemCardapio comCheddar = new CheddarDecorator(comBacon);
        IItemCardapio comOvo     = new OvoDecorator(comCheddar);
        System.out.println("Base:          " + burgerBase.getDescricao() + " R$ " + burgerBase.getPreco());
        System.out.println("+ Bacon:       " + comBacon.getDescricao()   + " R$ " + comBacon.getPreco());
        System.out.println("+ Cheddar:     " + comCheddar.getDescricao() + " R$ " + comCheddar.getPreco());
        System.out.println("+ Ovo:         " + comOvo.getDescricao()     + " R$ " + comOvo.getPreco());

        separador("8. COMMAND + MEMENTO");
        Carrinho carrinho             = new Carrinho();
        GerenciadorComandos comandos  = new GerenciadorComandos();

        comandos.executar(new ComandoAdicionarItem(carrinho, comOvo));
        comandos.executar(new ComandoAdicionarItem(carrinho, batata));
        Produto refri = new Produto("Refrigerante", 7.00, "Bebida",
                FabricaInfoNutricional.getInfo("Refrigerante"));
        comandos.executar(new ComandoAdicionarItem(carrinho, refri));

        System.out.println(carrinho);
        System.out.println("\n-- Desfazendo último comando --");
        comandos.desfazerUltimo();
        System.out.println(carrinho);

        separador("9. CHAIN OF RESPONSIBILITY");
        Pedido pedidoDesc = new Pedido("Cliente Teste");
        pedidoDesc.adicionarItem(comOvo);
        pedidoDesc.adicionarItem(batata);

        ProcessadorDesconto cadeia = FabricaCadeiaDesconto.construir();
        System.out.println("Cupom inválido:   R$ " + String.format("%.2f",
                cadeia.calcular(pedidoDesc, "INVALIDO")));
        System.out.println("Cupom PROMO10:    R$ " + String.format("%.2f",
                cadeia.calcular(pedidoDesc, "PROMO10")));
        System.out.println("Cupom ANIVERSARIO:R$ " + String.format("%.2f",
                cadeia.calcular(pedidoDesc, "ANIVERSARIO")));
        System.out.println("Cupom GERENTE50:  R$ " + String.format("%.2f",
                cadeia.calcular(pedidoDesc, "GERENTE50")));

        separador("10. STRATEGY");
        System.out.println("-- PIX --");
        new PagamentoPix().processar(50.00);
        System.out.println("\n-- Cartão --");
        new PagamentoCartao().processar(50.00);
        System.out.println("\n-- Dinheiro (troco) --");
        new PagamentoDinheiro(60.00).processar(50.00);

        separador("11. ADAPTER");
        AdapterMercadoPago adapter = new AdapterMercadoPago();
        adapter.processar(47.50);

        separador("12. PROXY");
        ProxyAutorizacaoGerente proxy = new ProxyAutorizacaoGerente("admin123");
        Pedido pedidoProxy = new Pedido("Cliente Proxy");
        pedidoProxy.adicionarItem(batata);
        pedidoProxy.setTotalPago(10.00);
        proxy.cancelarPedido(pedidoProxy, "senhaErrada");
        proxy.cancelarPedido(pedidoProxy, "admin123");

        separador("13. BRIDGE");
        Notificacao notifWhats = new NotificacaoPedidoPronto(new EnvioWhatsApp());
        Notificacao notifSMS   = new NotificacaoPedidoRecebido(new EnvioSMS());
        Notificacao notifEmail = new NotificacaoPedidoEmPreparo(new EnvioEmail());
        notifWhats.enviar("João", "Pedido #1");
        notifSMS.enviar("Maria", "Pedido #2");
        notifEmail.enviar("Pedro", "Pedido #3");
        notifWhats.setPlataforma(new EnvioEmail());
        notifWhats.enviar("João", "Pedido #1 (reenviado por Email)");

        separador("14. FACADE + STATE + OBSERVER");
        Carrinho carrinhoFinal = new Carrinho();
        carrinhoFinal.adicionarItem(comboClassico);
        carrinhoFinal.adicionarItem(batata);

        PainelCozinha painel  = new PainelCozinha("Painel Principal");
        AppCliente    app     = new AppCliente("João",
                new NotificacaoPedidoPronto(new EnvioWhatsApp()));

        TotemFacade totem = new TotemFacade();
        Pedido pedidoFinal = totem.finalizarPedido(
                carrinhoFinal, "João",
                new PagamentoPix(),
                Arrays.asList(painel, app)
        );

        separador("15. PROTOTYPE");
        if (pedidoFinal != null) {
            Pedido pedidoClone = pedidoFinal.clone();
            System.out.println("Original: " + pedidoFinal);
            System.out.println("Clone:    " + pedidoClone);
            System.out.println("Mesma instância? " + (pedidoFinal == pedidoClone));
            System.out.println("Mesma lista?      " + (pedidoFinal.getItens() == pedidoClone.getItens()));
        }

        separador("16. TEMPLATE METHOD");
        PreparoLanche preparoClassico = new PreparoLancheClassico();
        PreparoLanche preparoVegano   = new PreparoLancheVegano();
        preparoClassico.prepararLanche(criadorClassico.criarLanche());
        preparoVegano.prepararLanche(criadorVeggie.criarLanche());

        separador("17. MEDIATOR");
        MediadorCozinha mediador = new MediadorCozinha();
        EstacaoChapa     chapa   = new EstacaoChapa();
        EstacaoFritadeira frit   = new EstacaoFritadeira();
        EstacaoMontagem   mont   = new EstacaoMontagem();
        mediador.registrarEstacao("Chapa",     chapa);
        mediador.registrarEstacao("Fritadeira", frit);
        mediador.registrarEstacao("Montagem",   mont);
        chapa.executar("Carne Bovina 160g");

        separador("18. VISITOR");
        VisitorCalculoCalorias visitCal = new VisitorCalculoCalorias();
        VisitorCalculoImpostos visitImp = new VisitorCalculoImpostos();
        comboClassico.aceitar(visitCal);
        visitCal.exibirResumo();
        comboClassico.aceitar(visitImp);
        visitImp.exibirResumo();

        separador("19. ITERATOR");
        List<IItemCardapio> cardapio = Arrays.asList(comboClassico, comboVeggie, batata, refri);
        IteradorCardapio iterador    = new IteradorCardapio(cardapio);
        System.out.println("Total de itens planificados: " + iterador.totalItens());
        while (iterador.temProximo()) {
            IItemCardapio item = iterador.proximo();
            System.out.println("  → " + item.getDescricao()
                    + " | R$ " + String.format("%.2f", item.getPreco()));
        }

        separador("20. INTERPRETER");
        InterpretadorPedido interpretador = new InterpretadorPedido();
        Carrinho carrinhoBot = new Carrinho();
        interpretador.interpretar("1 classico sem alface", carrinhoBot);
        interpretador.interpretar("2 batata", carrinhoBot);
        interpretador.interpretar("refri com bacon", carrinhoBot);
        System.out.println(carrinhoBot);

        separador("FIM — TODOS OS 23 PADRÕES DEMONSTRADOS");
    }

    private static void separador(String titulo) {
        System.out.println(" -> " + titulo);
    }
}
