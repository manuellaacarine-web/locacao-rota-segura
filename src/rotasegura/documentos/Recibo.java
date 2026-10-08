package rotasegura.documentos;

import rotasegura.modelo.Cliente;
import rotasegura.modelo.Contrato;
import rotasegura.modelo.Veiculo;

public class Recibo implements Documento {
    //Inicio Atributo
    private Contrato contrato;
    //Fim Atributo

    //Inicio Metodo Recebido
    public Recibo(Contrato contrato) {
        if (contrato == null) {
            throw new IllegalArgumentException("Contrato não confirmado");
        }

        this.contrato = contrato;
    }
    //Fim Metodo Recebido

    //Inicio Sobreescrita do Metodo Gerar Texto Recebido da Interface
    @Override
    public String gerar() {
        return "RECIBO DE LOCAÇÃO \n"
        + "Cliente: " + contrato.getClienteResponsavel().getNome() + "\n"
        + "Veiculo: " + contrato.getVeiculoAlugado().getModelo() + "\n"
        + "Dias: " + contrato.calcularDias() + "\n"
        + "Valor das diarias: R$" + contrato.calcularValorDiarias() + "\n"
        + "Seguro: R$" + contrato.calcularValorSeguro() + "\n"
        + "Manutenção: R$" + contrato.calcularValorManutencao() + "\n"
        + "Total: R$" + contrato.calcularValorTotal();
    }
    //Fim Sobreescrita do Metodo Gerar Texto Recebido da Interface

    //Inicio Sobreescrita do Metodo Imprimir Texto Recebido da Interface
    @Override
    public void imprimir() {
        System.out.println(gerar());
    }
    //Fim Sobreescrita do Metodo Imprimir Texto Recebido da Interface

}
