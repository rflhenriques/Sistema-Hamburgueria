package main.notificacao;

public class EnvioWhatsApp implements IPlataformaEnvio{

    @Override
    public void disparar(String destinatario, String mensagem) {
        System.out.println("[WhatsApp] -> " + destinatario
                + ": " + mensagem);
    }

    @Override
    public String getNomePlataforma() {
        return "WhatsApp";
    }
}
