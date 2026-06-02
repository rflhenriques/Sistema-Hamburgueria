package main.notificacao;

public abstract class Notificacao {

    protected IPlataformaEnvio plataforma;

    public Notificacao(IPlataformaEnvio plataforma) {
        this.plataforma = plataforma;
    }

    public void setPlataforma(IPlataformaEnvio plataforma) {
        this.plataforma = plataforma;
    }

    public abstract void enviar (String destinatario, String detalhe);
}
