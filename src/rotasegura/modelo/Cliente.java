package rotasegura.modelo;


public class Cliente {
    //Inicio Atributos
    private String nome;
    private String cpf;
    private String telefone;
    //Fim Atributos

    //Inicio Construtor
    public Cliente(String nome, String cpf, String telefone) {
        setNome(nome);
        setCpf(cpf);
        setTelefone(telefone);
    }
    //Fim Construtor
    
    //Inico Getters
    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }
    //Fim Getters

    //Inicio Setters
    public void setNome(String nome) {
        if (nome == null) {
            throw new IllegalArgumentException("Nome invalido");
        }
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        if (cpf == null) {
            throw new IllegalArgumentException("Cpf invalido");
        }
        this.cpf = cpf;
    }

    public void setTelefone(String telefone) {
        if (telefone == null) {
            throw new IllegalArgumentException("Telefone invalido");
        }
        this.telefone = telefone;
    }
    //Fim Setters

}
