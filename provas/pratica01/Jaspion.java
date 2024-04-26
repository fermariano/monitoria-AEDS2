import java.util.Scanner;
import java.util.Arrays;

/*
code by fermariano
*/

public class Jaspion {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        int n = teclado.nextInt();

        for (int i = 0; i < n; i++) {

            int word = teclado.nextInt();
            int music = teclado.nextInt();

            teclado.nextLine();

            String japao[] = new String[word+2];
            String brasil[] = new String[word+2];

            
            for (int j = 0; j < word; j++) {
                japao[j] = teclado.nextLine();
                brasil[j] = teclado.nextLine();
            }

            japao[word] = "o";
            brasil[word] = "o"; 
            
            japao[word+1] = ".";
            brasil[word+1] = "\n";

           
            String musica = "";
            

            for (int j = 0; j < music; j++) {
                musica += teclado.nextLine();
                musica += " ";
                musica+= ".";
                musica += " ";
            }

            String[] splitmusica = musica.split("\\s+");

            String resp = "";
        
            for (int j = 0; j < splitmusica.length; j++) {
                for (int k = 0; k < japao.length; k++) {
                    if (splitmusica[j].equals(japao[k])) {
                        resp += brasil[k];
                        resp += " ";
                    }
                }
            }
            
            System.out.println(resp);
            

        }
        teclado.close();

    }

}
