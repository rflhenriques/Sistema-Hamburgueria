package main.pagamento;

public class MercadoPagoAPI {

    public MercadoPagoResponse sendPaymentRequest(MercadoPagoRequest request) {
        System.out.println("[MercadoPagoAPI] Processando pagamento externo...");
        System.out.println("[MercadoPagoAPI] Valor: " + request.getAmount());
        System.out.println("[MercadoPagoAPI] Método: " + request.getPaymentMethod());

        MercadoPagoResponse response = new MercadoPagoResponse();
        response.setStatusCode(200);
        response.setMessage("APPROVED");
        return response;
    }
}
