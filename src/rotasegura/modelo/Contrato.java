package rotasegura.modelo;

import rotasegura.documentos.Documento;
import java.time.LocalDate;

public class Contrato implements Documento {
    //Inicio Atributo
    private Cliente clienteResponsavel;
    private Veiculo veiculoAlugado;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private boolean encerrada;
    //Fim Atributo
    
    //Inicio Construtor do Contrato
    public Contrato(Cliente clienteResponsavel, Veiculo veiculoAlugado,
    LocalDate dataInicio, LocalDate dataFim){
        if (clienteResponsavel == null) {
            throw new IllegalArgumentException("Cliente não informado");
        }

        if (veiculoAlugado == null) {
            throw new IllegalArgumentException("Veiculo não informado");
        }

        if (dataInicio == null || dataFim == null) {
            throw new IllegalArgumentException("As datas são obrigatorias");
        }

        if (dataFim.isBefore(dataInicio)) {
            throw new IllegalArgumentException("A data final não pode ser anterior a data incial");
        }

        veiculoAlugado.alugar();

        this.clienteResponsavel = clienteResponsavel;
        this.veiculoAlugado = veiculoAlugado;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }
    //Fim Contrutor do Contrato

    //Incio Getters
    public Cliente getClienteResponsavel() {
        return clienteResponsavel;
    }

    public Veiculo getVeiculoAlugado() {
        return veiculoAlugado;
    }
    //Fim Getters

    //Incio Metodo de Calcular Dias
    public long calcularDias() {
        return java.time.temporal.ChronoUnit.DAYS.between(dataInicio, dataFim);
    }
    //Fim Metodo de Calcular Dias

    //Incio Metodo de Calcular a Diaria
    public double calcularValorDiarias() {
        return calcularDias() * veiculoAlugado.calcularDiaria();
    }
    //Fim Metodo de Calcular a Diaria
    
    //Inicio Metodo de Calcular o Seguro
    public double calcularValorSeguro() {
        return calcularDias() * veiculoAlugado.calcularSeguro();
    }
    //Fim Metodo de Calcular o Seguro
    
    //Inicio Metodo de Calcular a Manutenção
    public double calcularValorManutencao() {
        return calcularDias() * veiculoAlugado.calcularManutencao();
    }
    //Fim Metodo de Calcular a Manutenção
    
    //Inicio Metodo de Calcular Valor Total
    public double calcularValorTotal() {
        return calcularValorDiarias() + calcularValorSeguro() + calcularValorManutencao();
    }
    //Fim Metodo de Calcular Valor Total

    //Inicio Metodo de Encerrar a Locação
    public void encerrarLocacao() {
        if (encerrada) {
            throw new IllegalStateException("Esta locação já foi encerrada");
        }

        veiculoAlugado.devolver();
        encerrada = true;
    }

    public boolean isEncerrada() {
        return encerrada;
    }
    //Fim Metodo de Encerrar a Locação
    
    //Inicio Sobrescrevendo Metodo Gerar Texto do Contrato da Interface
    @Override
    public String gerar() {
        return "CONTRATO DE LOCAÇÃO \n" 
        + "Cliente: " + clienteResponsavel.getNome() + "\n"
        + "Veiculo: " + veiculoAlugado.getModelo() + "\n"
        + "Data de inicio: " + dataInicio + "\n"
        + "Data de fim: " + dataFim + "\n"
        + "Valor total: R$" + calcularValorTotal();
    }
    //Fim Sobrescrevendo Metodo Gerar Texto do Contrato da Interface

    //Inicio Sobrescrevendo Metodo Imprimir Contrato da Interface
    @Override
    public void imprimir() {
        System.out.println(gerar());
    }
    //Fim Sobrescrevendo Metodo Imprimir Contrato da Interface
}
