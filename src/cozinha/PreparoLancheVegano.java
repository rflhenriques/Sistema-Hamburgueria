package cozinha;

public class PreparoLancheVegano extends PreparoLanche {

    @Override
    protected void prepararProteina() {
        System.out.println("[Template] 2. Aquecendo hambúrguer de grão-de-bico...");
    }
}