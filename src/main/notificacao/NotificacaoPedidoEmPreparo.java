package main.notificacao;

public class NotificacaoPedidoEmPreparo extends Notificacao {

    public NotificacaoPedidoEmPreparo(IPlataformaEnvio plataforma) {
        super(plataforma);
    }

    @Override
    public void enviar(String destinatario, String detalhe) {
        String mensagem = "Seu lanche está na chapa!" + detalhe;
        plataforma.disparar(destinatario, mensagem);
    }
}
