package rotasegura.servico;

import rotasegura.modelo.Contrato;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ArquivoLocacoes {
    
    public void salvar(Contrato contrato) {

        try (PrintWriter arquivo = new PrintWriter(new FileWriter("historico_locacoes.txt", true))) {

            arquivo.println(contrato.gerar());
            arquivo.println("-------------------------");
        } 
        catch (IOException erro) {
            throw new RuntimeException("Erro ao salvar o historico da locação");
        }

    }

}
