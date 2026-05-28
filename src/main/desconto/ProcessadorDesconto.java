package main.desconto;

import main.model.Pedido;

public abstract class ProcessadorDesconto {

    protected ProcessadorDesconto proximo;

    public ProcessadorDesconto setProximo(ProcessadorDesconto proximo) {
        this.proximo = proximo;
        return proximo;
    }

    public abstract double calcular(Pedido pedido, String codigoCupom);

    protected double passarAdiante(Pedido pedido, String codigoCupom) {
        if (proximo != null) {
            return proximo.calcular(pedido, codigoCupom);
        }
        return pedido.calcularTotal();
    }
}
