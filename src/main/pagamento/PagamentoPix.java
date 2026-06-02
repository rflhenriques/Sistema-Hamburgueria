package main.pagamento;

public class PagamentoPix implements IEstrategiaPagamento {

    @Override
    public boolean processar(double valor) {
        System.out.println("[Strategy/PIX] Gerando QR Code para R$ "
                + String.format("%.2f", valor) + "...");
        System.out.println("[Strategy/PIX] Pagamento PIX confirmado.");
        return true;
    }

    @Override
    public String getDescricao() {
        return "PIX (sem taxas)";
    }
}