package main.notificacao;

public class EnvioSMS implements IPlataformaEnvio {

    @Override
    public void disparar(String destinatario, String mensagem) {
        System.out.print("[SMS] -> " + destinatario
                + ": " + mensagem);
    }

    @Override
    public String getNomePlataforma() {
        return "SMS";
    }
}
