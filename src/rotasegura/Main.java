package rotasegura;

import rotasegura.documentos.Recibo;
import rotasegura.documentos.RelatorioFechamento;
import rotasegura.modelo.Cliente;
import rotasegura.modelo.Contrato;
import rotasegura.modelo.Popular;
import rotasegura.modelo.Sedan;
import rotasegura.modelo.Suv;
import rotasegura.modelo.Veiculo;
import rotasegura.servico.SistemaLocacao;
import rotasegura.util.Entrada;
import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        SistemaLocacao sistema = new SistemaLocacao();
        Entrada entrada = new Entrada();
        int opcao = -1;

        while (opcao != 0) {
            exibirMenu();

            try {
                opcao = entrada.lerInteiro("Escolha uma opção: ");

                switch (opcao) {
                    case 1 -> cadastrarVeiculo(sistema, entrada);
                    case 2 -> listarVeiculos(sistema);
                    case 3 -> cadastrarCliente(sistema, entrada);
                    case 4 -> listarClientes(sistema);
                    case 5 -> abrirLocacao(sistema, entrada);
                    case 6 -> fecharLocacao(sistema, entrada);
                    case 7 -> listarLocacoesAbertas(sistema);
                    case 8 -> carregarExemplo(sistema);
                    case 0 -> System.out.println("Encerrando o sistema...");
                    default -> System.out.println("Opção inválida.");
                }
            }
            catch (IllegalArgumentException | IllegalStateException erro) {
                System.out.println("\n[ERRO TRATADO] " + erro.getMessage());
            }
            catch (RuntimeException erro) {
                System.out.println("\n[ERRO INESPERADO] " + erro.getMessage());
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\n===== ROTA SEGURA - LOCAÇÃO DE VEÍCULOS =====");
        System.out.println("1 - Cadastrar veículo");
        System.out.println("2 - Listar veículos");
        System.out.println("3 - Cadastrar cliente");
        System.out.println("4 - Listar clientes");
        System.out.println("5 - Abrir nova locação");
        System.out.println("6 - Devolver veículo e fechar locação (recibo)");
        System.out.println("7 - Listar locações abertas");
        System.out.println("8 - Carregar dados de exemplo");
        System.out.println("0 - Sair");
    }

    private static void cadastrarVeiculo(SistemaLocacao sistema, Entrada entrada) {
        int tipo = entrada.lerInteiro("Tipo (1-Popular, 2-Sedan, 3-SUV): ");
        String marca = entrada.lerTexto("Marca: ");
        String modelo = entrada.lerTexto("Modelo: ");
        int ano = entrada.lerInteiro("Ano: ");
        String cor = entrada.lerTexto("Cor: ");
        String placa = entrada.lerTexto("Placa: ");

        Veiculo veiculo;

        switch (tipo) {
            case 1 -> veiculo = new Popular(marca, modelo, ano, cor, placa);
            case 2 -> veiculo = new Sedan(marca, modelo, ano, cor, placa);
            case 3 -> veiculo = new Suv(marca, modelo, ano, cor, placa);
            default -> throw new IllegalArgumentException("Tipo de veículo inválido");
        }

        sistema.cadastrarVeiculo(veiculo);
        System.out.println("Veículo cadastrado com sucesso!");
    }

    private static void listarVeiculos(SistemaLocacao sistema) {
        List<Veiculo> frota = sistema.listarVeiculos();

        if (frota.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado.");
            return;
        }

        System.out.println("\n--- FROTA ---");
        for (Veiculo v : frota) {
            System.out.println(v.getClass().getSimpleName() + " | " + v.getMarca() + " " + v.getModelo()
                    + " | " + v.getAno() + " | " + v.getCor() + " | " + v.getPlaca()
                    + " | " + (v.isDisponivel() ? "DISPONÍVEL" : "ALUGADO")
                    + " | diária R$" + v.calcularDiaria());
        }
    }

    private static void cadastrarCliente(SistemaLocacao sistema, Entrada entrada) {
        String nome = entrada.lerTexto("Nome: ");
        String cpf = entrada.lerTexto("CPF: ");
        String telefone = entrada.lerTexto("Telefone: ");

        sistema.cadastroCliente(new Cliente(nome, cpf, telefone));
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private static void listarClientes(SistemaLocacao sistema) {
        List<Cliente> clientes = sistema.listarClientes();

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        System.out.println("\n--- CLIENTES ---");
        for (Cliente c : clientes) {
            System.out.println(c.getNome() + " | CPF " + c.getCpf() + " | Tel " + c.getTelefone());
        }
    }

    private static void abrirLocacao(SistemaLocacao sistema, Entrada entrada) {
        String cpf = entrada.lerTexto("CPF do cliente: ");
        String placa = entrada.lerTexto("Placa do veículo: ");
        LocalDate inicio = entrada.lerData("Data de início (AAAA-MM-DD): ");
        LocalDate fim = entrada.lerData("Data de fim (AAAA-MM-DD): ");

        Cliente cliente = sistema.buscarClientePorCpf(cpf);
        Veiculo veiculo = sistema.buscarVeiculoPorPlaca(placa);

        Contrato contrato = new Contrato(cliente, veiculo, inicio, fim);
        sistema.registrarLocacao(contrato);

        System.out.println("\nLocação aberta!\n");
        contrato.imprimir();
    }

    private static void fecharLocacao(SistemaLocacao sistema, Entrada entrada) {
        List<Contrato> abertas = sistema.listarLocacoesAbertas();

        if (abertas.isEmpty()) {
            throw new IllegalStateException("Não há locações abertas");
        }

        listarLocacoesAbertas(sistema);
        int numero = entrada.lerInteiro("Número da locação a fechar: ");

        if (numero < 1 || numero > abertas.size()) {
            throw new IllegalArgumentException("Número de locação inválido");
        }

        Contrato contrato = abertas.get(numero - 1);
        Recibo recibo = sistema.fecharLocacao(contrato);

        System.out.println("\nVeículo devolvido. Locação fechada!\n");
        recibo.imprimir();
        System.out.println();
        new RelatorioFechamento(contrato).imprimir();
    }

    private static void listarLocacoesAbertas(SistemaLocacao sistema) {
        List<Contrato> abertas = sistema.listarLocacoesAbertas();

        if (abertas.isEmpty()) {
            System.out.println("Nenhuma locação aberta.");
            return;
        }

        System.out.println("\n--- LOCAÇÕES ABERTAS ---");
        for (int i = 0; i < abertas.size(); i++) {
            Contrato c = abertas.get(i);
            System.out.println((i + 1) + " - " + c.getClienteResponsavel().getNome()
                    + " | " + c.getVeiculoAlugado().getModelo()
                    + " (" + c.getVeiculoAlugado().getPlaca() + ")");
        }
    }

    private static void carregarExemplo(SistemaLocacao sistema) {
        sistema.cadastrarVeiculo(new Popular("Chevrolet", "Onix", 2024, "Prata", "ABC1D23"));
        sistema.cadastrarVeiculo(new Sedan("Toyota", "Corolla", 2023, "Preto", "DEF4G56"));
        sistema.cadastrarVeiculo(new Suv("Jeep", "Compass", 2025, "Branco", "HIJ7K89"));
        sistema.cadastroCliente(new Cliente("Gustavo da Silva", "12345678900", "14999999999"));
        System.out.println("Dados de exemplo carregados (3 veículos e 1 cliente).");
    }
}
