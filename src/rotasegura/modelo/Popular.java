package rotasegura.modelo;


public class Popular extends Veiculo{
    //Inico Contrutor da classe-pai
    public Popular(String marca, String modelo, int ano, String cor,
    String placa) {
        super(marca, modelo, ano, cor, placa);
    }
        //Fim Contrutor da classe-pai
        
        //Inicio Sobrescrevendo a Abstração
    @Override
    public double calcularDiaria() {
        return 100.00;
    }
    
    @Override
    public double calcularSeguro() {
        return 20.00;
    }
    
    @Override
    public double calcularManutencao() {
        return 30.00;
    }
    //Fim Sobrescrevendo a Abstração
}
