package rotasegura.modelo;


public class Suv extends Veiculo{
    //Inicio Contrutor da classe-pai
    public Suv(String marca, String modelo, int ano, String cor,
    String placa) {
        super(marca, modelo, ano, cor, placa);
    }
    //Fim Contrutor da classe-pai
    
    //Inicio Sobrescrevendo a Abstração
    @Override
    public double calcularDiaria() {
        return 200.00;
    }
    
    @Override
    public double calcularSeguro() {
        return 40.00;
    }

    @Override
    public double calcularManutencao() {
        return 60.00;
    }
    //Fim Sobrescrevendo a Abstração
}
