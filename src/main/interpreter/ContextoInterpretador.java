package main.interpreter;

public class ContextoInterpretador {

    private String textoOriginal;
    private String[] tokens;
    private int posicao = 0;

    public ContextoInterpretador(String texto) {
        this.textoOriginal = texto.trim().toLowerCase();
        this.tokens = textoOriginal.split("\\s+");
    }

    public String getTextoOriginal() {
        return textoOriginal;
    }

    public String[] getTokens() {
        return tokens;
    }

    public boolean temProximo() {
        return posicao < tokens.length;
    }

    public String proximoToken() {
        if(!temProximo()) return "";
        return tokens[posicao++];
    }

    public String tokenAtual() {
        if(posicao >= tokens.length) return "";
        return tokens[posicao];
    }

    public void reiniciar() {
        posicao = 0;
    }
}