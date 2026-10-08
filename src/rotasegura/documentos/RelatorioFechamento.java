package rotasegura.documentos;

import rotasegura.modelo.Cliente;
import rotasegura.modelo.Contrato;
import rotasegura.modelo.Veiculo;

public class RelatorioFechamento implements Documento {
    //Inicio Atributo
    private Contrato contratoFinalizado;
    //Fim Atributo

    //Inicio Metodo Relatorio Fechamento
    public RelatorioFechamento(Contrato contratoFinalizado) {
        if (contratoFinalizado == null) {
            throw new IllegalArgumentException("Contrato não informado");
        }

        this.contratoFinalizado = contratoFinalizado;
    }
    //Fim Metodo Relatorio Fechamento

    //Inicio Sobrescrevendo Metodo Gerar Texto do Relatorio da Interface
    @Override
    public String gerar() {
        return  "RELATORIO DE FECHAMENTO \n"
        + "Cliente: " + contratoFinalizado.getClienteResponsavel().getNome() + "\n"
        + "Veiculo: " + contratoFinalizado.getVeiculoAlugado().getModelo() + "\n"
        + "Dias de locação: " + contratoFinalizado.calcularDias() + "\n"
        + "Valor das diarias: R$" + contratoFinalizado.calcularValorDiarias() + "\n"
        + "Valor do seguro: R$" + contratoFinalizado.calcularValorSeguro() + "\n"
        + "Valor da manutenção: R$" + contratoFinalizado.calcularValorManutencao() + "\n"
        + "Valor total: R$" + contratoFinalizado.calcularValorTotal();
    }
    //Fim Sobrescrevendo Metodo Gerar Texto do Relatorio da Interface

//Inicio Sobrescrevendo Metodo Imprimir Texto do Relatorio da Interface
    @Override
    public void imprimir() {
        System.out.println(gerar());
    }
    //Fim Sobrescrevendo Metodo Imprimir Texto do Relatorio da Interface
}
