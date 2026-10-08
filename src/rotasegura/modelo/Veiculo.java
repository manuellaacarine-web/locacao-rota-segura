package rotasegura.modelo;



public abstract class Veiculo {
    //Inicio Atributos
    private String marca;
    private String modelo;
    private int ano;
    private String cor;
    private String placa;
    private boolean disponivel;
    //Fim Atributos

    //Inicio Contrutor
    public Veiculo (String marca, String modelo, int ano, String cor,
    String placa) {
        setMarca(marca);
        setModelo(modelo);
        setAno(ano);
        setCor(cor);
        setPlaca(placa);
        this.disponivel = true;
    }
    //Fim Construtor

    //Inicio Getter
    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAno() {
        return ano;
    }

    public String getCor() {
        return cor;
    }

    public String getPlaca() {
        return placa;
    }

    public boolean isDisponivel() {
        return disponivel;
    }
    //Fim Getter

    //Inicio Setter
    public void setMarca(String marca) {
        if (marca == null) {
            throw new IllegalArgumentException("Marca invalida");
        }
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        if (modelo == null) {
            throw new IllegalArgumentException("Modelo invalido");
        }
        this.modelo = modelo;
    }

    public void setAno(int ano) {
        if (ano < 1900) {
            throw new IllegalArgumentException("Ano invalido");
        }
        this.ano = ano;
    }

    public void setCor(String cor) {
        if (cor == null) {
            throw new IllegalArgumentException("Cor invalida");
        }
        this.cor = cor;
    }

    public void setPlaca(String placa) {
        if (placa == null) {
            throw new IllegalArgumentException("Placa invalida");
        }
        this.placa = placa;
    }
    //Fim Setter
    
    //Inicio Abstração
    public abstract double calcularDiaria();

    public abstract double calcularSeguro();

    public abstract double calcularManutencao();
    //Fim Abstração

    //Inicio Metodo de alugar
    public void alugar() {
        if(disponivel) {
            this.disponivel = false;
        } 
        else {
            throw new IllegalStateException("O veivulo esta indisponivel");
        }
    }
    //Fim Metodo de alugar

    //Inicio Metodo de devolver
    public void devolver() {
        if (!disponivel) {
            this.disponivel = true;
        }
        else {
            throw new IllegalStateException("O veiculo ja esta disponivel");
        }
    }
    //Fim Metodo de devolver
}
