package main.notificacao;

public class EnvioEmail implements IPlataformaEnvio {

    @Override
    public void disparar(String destinatario, String mensagem) {
        System.out.print("[Email] -> " + destinatario
                + ": " + mensagem);
    }

    @Override
    public String getNomePlataforma() {
        return "Email";
    }
}