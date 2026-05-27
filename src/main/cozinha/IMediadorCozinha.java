package main.cozinha;

public interface IMediadorCozinha {
    void notificar(EstacaoCozinha remetente, String evento);
    void registrarEstacao(String nome, EstacaoCozinha estacao);
}