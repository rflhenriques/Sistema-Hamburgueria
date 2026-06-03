# 🍔 BurgerGoF - 23 Design Patterns em Java

[![Java](https://img.shields.io/badge/Java-11%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com/)
[![JUnit5](https://img.shields.io/badge/JUnit5-Testing-25A162?style=for-the-badge&logo=junit5&logoColor=white)](https://junit.org/junit5/)
[![Arquitetura](https://img.shields.io/badge/Architecture-GoF_Patterns-blue?style=for-the-badge)](https://en.wikipedia.org/wiki/Design_Patterns)

Um projeto acadêmico robusto e 100% testado que demonstra a implementação completa de **todos os 23 Padrões de Projeto (Design Patterns) do GoF (Gang of Four)** em uma aplicação Java orientada a objetos do mundo real.

O domínio escolhido foi um sistema completo para uma **Hamburgueria**, abrangendo desde o atendimento no totem, customização do cardápio, processamento de pagamentos, até a esteira de produção na cozinha e painel de notificações.

---

## 🎯 Objetivo do Projeto

O principal objetivo deste repositório é servir como um material de estudo avançado e um portfólio de engenharia de software. Demonstrando como separar responsabilidades, usar interfaces, criar arquiteturas escaláveis e evitar o anti-pattern *"God Class"* usando os padrões consagrados da engenharia de software.

## 🏗️ Estrutura e Padrões Utilizados

O projeto está modularizado e organizado por contextos da Hamburgueria. Abaixo está o mapeamento exato de qual padrão resolveu qual problema de domínio:

### 🛠️ Padrões Criacionais (Creational)
Lidam com a inicialização e configuração segura dos objetos.

| Padrão | Onde foi usado? | Por que foi usado? |
|--------|-----------------|--------------------|
| **Singleton** | `SistemaHamburgueria` / `GerenciadorEstoque` | Garante que toda a aplicação acesse a mesma instância central de estoque e configurações da loja. |
| **Factory Method** | `CriadorBurgerClassico` / `Veggie` | Delega às fábricas específicas a criação correta de suas respectivas versões de lanches. |
| **Abstract Factory**| `IFabricaCombo` | Cria famílias inteiras de produtos coerentes (Combo Clássico vs Combo Veggie) sem misturar itens. |
| **Builder** | `MontadorBurgerCustomizado` | Permite montar um hambúrguer super customizado passo-a-passo evitando um construtor gigante (telescoping constructor). |
| **Prototype** | `Pedido.clone()` | Implementa a função "Repetir Pedido Anterior", clonando um carrinho existente instantaneamente. |

### 🧱 Padrões Estruturais (Structural)
Lidam com a composição de classes e objetos formando estruturas maiores e mais complexas.

| Padrão | Onde foi usado? | Por que foi usado? |
|--------|-----------------|--------------------|
| **Adapter** | `AdapterMercadoPago` | Adapta uma API de pagamentos externa incompatível com a nossa interface de pagamentos interna. |
| **Bridge** | `Notificacao` x `IPlataformaEnvio` | Separa a Regra (Qual momento notificar?) da Plataforma (WhatsApp, SMS, Email). |
| **Composite** | `Combo` | Permite que o sistema trate produtos individuais (Batata) e Combos como a mesma coisa dentro do carrinho. |
| **Decorator** | `AdicionalDecorator` | Adiciona e remove ingredientes dinamicamente (Ex: `+ Bacon`, `+ Ovo`) somando seus valores em tempo de execução. |
| **Facade** | `TotemFacade` | Oferece uma porta de entrada unificada para fechar o pedido, escondendo a complexidade do estoque, caixa, e notificações. |
| **Flyweight** | `FabricaInfoNutricional` | Economiza memória compartilhando objetos de "Tabela Nutricional" entre os milhares de lanches vendidos. |
| **Proxy** | `ProxyAutorizacaoGerente` | Adiciona uma barreira de proteção. Só permite cancelar/estornar pedido mediante senha de administrador. |

### 🧠 Padrões Comportamentais (Behavioral)
Lidam com a comunicação, responsabilidades e algoritmos entre os objetos.

| Padrão | Onde foi usado? | Por que foi usado? |
|--------|-----------------|--------------------|
| **Chain of Responsibility**| `ProcessadorDesconto` | Cadeia encadeada de tentativas para validar cupons (Aniversário -> Promocional -> Gerente). |
| **Command** | `GerenciadorComandos` | Transforma cada "Adicionar ao carrinho" em um objeto executável, possibilitando o botão de "Desfazer" (Undo). |
| **Interpreter**| `InterpretadorPedido` | Mini-compilador que traduz sentenças de texto do totem (ex: *"2 batatas e 1 refri"*) em comandos do sistema. |
| **Iterator** | `IteradorCardapio` | Navega linearmente pela árvore complexa do cardápio sem expor como as listas e combos estão estruturados internamente. |
| **Mediator** | `MediadorCozinha` | Coordena as estações da cozinha (Chapa, Fritadeira). A chapa não avisa a montagem diretamente, o Mediador orquestra. |
| **Memento** | `PedidoMemento` | Salva um estado instantâneo (snapshot) do carrinho e permite restauração segura. |
| **Observer** | `AppCliente` / `PainelCozinha` | Ficam "ouvindo" o Pedido e são notificados em tempo real quando o status avança. |
| **State** | `IEstadoPedido` | Remove os `if-elses` gigantes. O pedido muda suas regras se está "Na Cozinha" ou "Aguardando Pagamento". |
| **Strategy** | `IEstrategiaPagamento` | Permite trocar o algoritmo de pagamento (Cartão, Dinheiro com troco, Pix) dinamicamente. |
| **Template Method**| `PreparoLanche` | Define os passos rígidos da cozinha na superclasse (Embalar, Montar), mas deixa o "Preparo da Proteína" para as subclasses. |
| **Visitor** | `VisitorCalculoImpostos` | Adiciona novas lógicas sobre os produtos do cardápio (calcular taxas tributárias ou somar calorias totais) sem mexer no código dos produtos em si. |

## 📊 Diagramas UML
Na raiz do projeto você encontrará diagramas documentando a arquitetura do projeto. 
* *Ver arquivo `diagrama_uml_completo.png` para a visão holística das classes interagindo.*

## 🚀 Como Executar o Projeto

**Pré-requisitos:**
* JDK 11, 17 ou superior.
* IDE (IntelliJ IDEA, Eclipse ou VS Code).

**Execução:**
1. Clone este repositório.
2. Navegue até o diretório do projeto.
3. Execute o arquivo principal: `src/main/Main.java`.
4. O console exibirá uma simulação sequencial e muito visual de todos os 23 padrões funcionando na prática.

## 🧪 Testes Automatizados (JUnit 5)

Este projeto não foi validado apenas "no olho". Foram implementados testes de unidade para validar o comportamento esperado de todos os padrões aplicados. Para rodar a suíte de testes:

Executar todos os testes no pacote `src/test/`:
- `BurgerCustomizadoTest`
- `CardapioAdicionaisTest`
- `CarrinhoComprasTest`
- `CicloDeVidaPedidoTest`
- `SistemaHamburgueriaTest`
- *e muitos outros...*

---
*Projeto acadêmico desenvolvido para a disciplina de Arquitetura de Software e Padrões de Projetos.*
