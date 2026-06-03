package main.model;

import main.cardapio.IItemCardapio;
import main.cozinha.IEstadoPedido;
import main.cozinha.EstadoAguardandoPagamento;
import main.notificacao.IObserver;
import java.util.ArrayList;
import java.util.List;

public class Pedido implements Cloneable {

    private static int contadorId = 1;

    public static void resetContador() {
        contadorId = 1;
    }

    private final int id;

    private List<IItemCardapio> itens;

    private IEstadoPedido estadoAtual;

    private final List<IObserver> observers;

    private String nomeCliente;

    private double totalPago;

    public Pedido(String nomeCliente) {
        this.id = contadorId++;
        this.nomeCliente = nomeCliente;
        this.itens = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.estadoAtual = new EstadoAguardandoPagamento();
    }

    @Override
    public Pedido clone() {
        try {
            Pedido clone = (Pedido) super.clone();
            clone.itens = new ArrayList<>(this.itens);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Erro ao clonar pedido", e);
        }
    }

    public void avancarEstado() {
        estadoAtual.avancar(this);
    }

    public void cancelar() {
        estadoAtual.cancelar(this);
    }

    public void setEstado(IEstadoPedido novoEstado) {
        this.estadoAtual = novoEstado;
        notificarObservers();
    }

    public IEstadoPedido getEstadoAtual() { return estadoAtual; }

    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    public void notificarObservers() {
        for (IObserver obs : observers) {
            obs.atualizar(this);
        }
    }

    public PedidoMemento salvarMemento() {
        return new PedidoMemento(new ArrayList<>(itens));
    }

    public void restaurarMemento(PedidoMemento memento) {
        this.itens = new ArrayList<>(memento.getItensSalvos());
        System.out.println("[Memento] Pedido #" + id + " restaurado para " + itens.size() + " itens.");
    }

    public void adicionarItem(IItemCardapio item) {
        itens.add(item);
    }

    public void removerItem(IItemCardapio item) {
        itens.remove(item);
    }

    public List<IItemCardapio> getItens() {
        return new ArrayList<>(itens);
    }

    public double calcularTotal() {
        return itens.stream().mapToDouble(IItemCardapio::getPreco).sum();
    }

    public int getId() {
        return id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public double getTotalPago() {
        return totalPago;
    }

    public void setTotalPago(double v) {
        this.totalPago = v;
    }

    @Override
    public String toString() {
        return "Pedido #" + id + " [" + nomeCliente + "] — " + estadoAtual.getNome()
                + " | Total: R$ " + String.format("%.2f", calcularTotal());
    }
}
