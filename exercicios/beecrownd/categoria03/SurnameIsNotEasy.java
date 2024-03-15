import java.util.Scanner;

/**
 * Surname is not Easy
 * 
 *  link -> https://judge.beecrowd.com/en/problems/view/3358
 * 
 * 
 *  Análise assintótica:
 * 
 *     N -> tamanho do sobrenome
 *     k -> numero de consoantes consecutivas
 * 
 *     melhor caso -> O(1)
 * 
 *     pior caso -> O((N-K)K) -> O(N-K^2)
*/
public class SurnameIsNotEasy {

    static boolean isVowel(char ch){
        final char[] vowels = {'a', 'e', 'i', 'o', 'u'};
        for(char vowel : vowels){
            if(ch == vowel){
                return true;
            }
        }
        return false;
    }

    static boolean isSurnameEasy(String surname, int k){
        for(int i = 0; i < surname.length()-k; i++){
            int count = 0;

            for(int j = i; j < i+k; j++){
                if(!isVowel(surname.charAt(j))){
                    count++;
                }
            }

            if(count == k){
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {

        final int consecutiveConsonants = 3;

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();sc.nextLine();
        for(int i = 0; i < N; i++){
            String currentSurname = sc.nextLine();

            System.out.print(currentSurname);
            if(isSurnameEasy(currentSurname, consecutiveConsonants)){
                System.out.println(" eh facil");
            }else{
                System.out.println(" nao eh facil");
            }

        }
        sc.close();
    }

    
}
