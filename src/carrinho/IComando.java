package carrinho;

public interface IComando {

    void executar();
    void desfazer();
    String getDescricao();
}