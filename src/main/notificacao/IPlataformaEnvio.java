package main.notificacao;

public interface IPlataformaEnvio {
    void disparar(String destinatario, String mensagem);
    String getNomePlataforma();
}