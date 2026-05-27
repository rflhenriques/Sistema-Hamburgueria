package cozinha;

public abstract class EstacaoCozinha {

    protected final String nome;
    protected IMediadorCozinha mediador;

    public EstacaoCozinha(String nome) {
        this.nome = nome;
    }

    public void setMediador(IMediadorCozinha mediador) {
        this.mediador = mediador;
    }

    public String getNome() {
        return nome;
    }

    public abstract void executar(String tarefa);

    protected void avisar(String evento) {
        if (mediador != null) {
            mediador.notificar(this, evento);
        }
    }
}
