package rotasegura.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Entrada {
    //Inicio Atributos
    private Scanner scanner;
    //Fim Atributos

    //Inicio Metodo de Entrada Para Texto, Numero Inteiro e Data
    public Entrada() {
        scanner = new Scanner(System.in);
    }

    public String lerTexto(String mensagem) {
        System.out.print(mensagem);
        String texto = scanner.nextLine();

        if (texto.isBlank()) {
            throw new IllegalArgumentException("O campo não pode ficar vazio");
        }

        return texto;
    }

    public int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } 
        catch (NumberFormatException erro) {
            throw new IllegalArgumentException("Digite um numero inteiro valido");
        }
    }

    public LocalDate lerData(String mensagem) {
        System.out.print(mensagem);

        try {
            return LocalDate.parse(scanner.nextLine().trim());
        } 
        catch (DateTimeParseException erro) {
            throw new IllegalArgumentException("Data invalida. Use o formato AAAA-MM-DD");
        }
    }
    //Fim Metodo de Entrada Para Texto, Numero Inteiro e Data
}
