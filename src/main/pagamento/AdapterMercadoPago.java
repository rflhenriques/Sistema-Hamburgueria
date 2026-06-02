package main.pagamento;

public class AdapterMercadoPago implements IEstrategiaPagamento {

    private final MercadoPagoAPI apiExterna;

    public AdapterMercadoPago() {
        this.apiExterna = new MercadoPagoAPI();
        System.out.println("[Adapter] AdapterMercadoPago inicializado.");
    }

    @Override
    public boolean processar(double valor) {
        System.out.println("[Adapter] Convertendo chamada interna → formato MercadoPago...");

        MercadoPagoRequest request = new MercadoPagoRequest(
                valor,
                "CREDIT_CARD",
                "BURGERGOF-" + System.currentTimeMillis()
        );

        MercadoPagoResponse response = apiExterna.sendPaymentRequest(request);

        System.out.println("[Adapter] Resposta recebida: " + response.getMessage());
        return response.isApproved();
    }

    @Override
    public String getDescricao() {
        return "Cartão via Mercado Pago";
    }
}
