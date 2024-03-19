import java.util.Arrays;
import java.util.Scanner;

/*
 * Code by fermariano
 */

 class Elfo {
    int idade;
    String nome;

    Elfo(int ii, String nn) {
        idade = ii;
        nome = nn;
    }

    public int getIdade() {
        return this.idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

 }

public class ElfTeam {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in); // abrindo o scanner
        int tam = teclado.nextInt();
        System.out.println(tam);
        Elfo[] elfos = new Elfo[tam];

        for (int i = 0; i < tam; i++) { // leitura da entrada
            String nome = teclado.next(); // le o nome
            int idade = teclado.nextInt(); // le a idade
            elfos[i] = new Elfo(idade, nome);
           // System.out.println(elfos[i].nome + " " + elfos[i].idade);
        }

        // ordenar o vetor (insertion)
        for (int i = 1; i < tam; i++) {
            Elfo key = elfos[i]; // salva o elfo que ta sendo comparado
            int j = i - 1;

            /* Move os elementos do arr[0..i-1] que são maiores que a chave
               para uma posição à frente de sua posição atual */
            while (j >= 0 && elfos[j].idade > key.idade) { // compara as idades dos elfos
                elfos[j + 1] = elfos[j];
                j = j - 1;
            }
            elfos[j + 1] = key;
        }

        // printar os times

        int times = tam/3;

        for (int i = 0; i < times; i++) {
            System.out.println("Time " + (i+1));
            System.out.println(elfos[i].nome + " " + elfos[i].idade);
            System.out.println(elfos[i + times].nome + " " + elfos[i + times].idade);
            System.out.println(elfos[i + 2 * times].nome + " " + elfos[i + 2 * times].idade);
        }


  }
}