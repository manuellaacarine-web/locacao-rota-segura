package rotasegura.servico;

import rotasegura.documentos.Recibo;
import rotasegura.modelo.Cliente;
import rotasegura.modelo.Contrato;
import rotasegura.modelo.Veiculo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SistemaLocacao {
    //Inicio Atributos
    private List<Veiculo> frota;
    private List<Cliente> cadastroClientes;
    private List<Contrato> historicoLocacoes;
    private ArquivoLocacoes arquivoLocacoes;
    //Fim Atributos


    //Inicio Construtor
    public SistemaLocacao() {
        frota = new ArrayList<>();
        cadastroClientes = new ArrayList<>();
        historicoLocacoes = new ArrayList<>();
        arquivoLocacoes = new ArquivoLocacoes();
    }
    //Fim Construtor

    //Inicio do Metodo de Cadastro de Veiculo
    public void cadastrarVeiculo(Veiculo novoVeiculo) {
        if (novoVeiculo == null) {
            throw new IllegalArgumentException("Veiculo não informado");
        }

        for (Veiculo veiculo : frota) {
            if (veiculo.getPlaca().equalsIgnoreCase(novoVeiculo.getPlaca())) {
                throw new IllegalArgumentException("Ja existe um veiculo com essa placa");
            }
        }

        frota.add(novoVeiculo);
    }
    //Fim do Metodo de Cadastro de Veiculo

    //Inicio do Metodo de Cadastro de Cliente
    public void cadastroCliente(Cliente novoCliente) {
        if (novoCliente == null) {
            throw new IllegalArgumentException("Cliente não informado");
        }

        for (Cliente cliente : cadastroClientes) {
            if (cliente.getCpf().equals(novoCliente.getCpf())) {
                throw new IllegalArgumentException("Ja existe um cliente com esse CPF");
            }
        }

        cadastroClientes.add(novoCliente);
    }
    //Fim do Metodo de Cadastro de Cliente

    //Inicio do Metodo de Registro da Locação
    public void registrarLocacao(Contrato novaLocacao){
        if (novaLocacao == null) {
            throw new IllegalArgumentException("Locação não informada");
        }
        
        historicoLocacoes.add(novaLocacao);
        arquivoLocacoes.salvar(novaLocacao);
    }
    //Inicio do Metodo do Registro da Locação

    //Inicio do Metodo do Recibo
    public Recibo fecharLocacao(Contrato contrato) {
        if (contrato == null) {
            throw new IllegalArgumentException("Contrato não informado");
        }

        contrato.encerrarLocacao();

        Recibo recibo = new Recibo(contrato);

        return recibo;
    }
    //Fim do Metodo do Recibo

    //Inicio dos Metodos de Listagem e Busca
    public List<Veiculo> listarVeiculos() {
        return Collections.unmodifiableList(frota);
    }

    public List<Cliente> listarClientes() {
        return Collections.unmodifiableList(cadastroClientes);
    }

    public List<Contrato> listarLocacoesAbertas() {
        List<Contrato> abertas = new ArrayList<>();

        for (Contrato contrato : historicoLocacoes) {
            if (!contrato.isEncerrada()) {
                abertas.add(contrato);
            }
        }

        return abertas;
    }

    public Veiculo buscarVeiculoPorPlaca(String placa) {
        for (Veiculo veiculo : frota) {
            if (veiculo.getPlaca().equalsIgnoreCase(placa)) {
                return veiculo;
            }
        }

        throw new IllegalArgumentException("Veiculo nao encontrado: " + placa);
    }

    public Cliente buscarClientePorCpf(String cpf) {
        for (Cliente cliente : cadastroClientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }

        throw new IllegalArgumentException("Cliente nao encontrado: " + cpf);
    }
    //Fim dos Metodos de Listagem e Busca

}
