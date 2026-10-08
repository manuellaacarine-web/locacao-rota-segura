package rotasegura.modelo;


public class Sedan extends Veiculo{
    //Incio Contrutor da classe-pai
    public Sedan(String marca, String modelo, int ano, String cor,
    String placa){
        super(marca, modelo, ano, cor, placa);
    }
    //Fim Contrutor da classe-pai
    
    //Inicio Sobrescrevendo a Abstração
    @Override
    public double calcularDiaria() {
        return 150.00;
    }
    
    @Override
    public double calcularSeguro() {
        return 30.00;
    }
    
    @Override
    public double calcularManutencao() {
        return 45.00;
    }
    //Fim Sobrescrevendo a Abstração
}