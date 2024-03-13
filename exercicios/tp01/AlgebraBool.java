public class AlgebraBool {

    static boolean[] GetBoolMap(String frase) {
        int tamanho = Integer.parseInt(String.valueOf(frase.charAt(0))); // primerio caracter é o numero de variaveis
        
        boolean[] arraybool = new boolean[tamanho];
        int count = 0;
        for (int i = 0; i < tamanho * 2 + 1; i++) {
            if (frase.charAt(i) == '1') {
                arraybool[count] = true;
                count++;
            } else if (frase.charAt(i) == '0') {
                arraybool[count] = false;
                count++;
            }
        }
        return arraybool;
    }

    public static void main(String[] args) {
        boolean arraybool[];
        while (true) {
            String frase = MyIO.readLine();
            if (igual_0(frase)) {
                break;
            }
            arraybool = GetBoolMap(frase); // retorna um array bool com quantos elementos logicos terão e o valor
                                           // deles em bool ex(a,b,c) se é true ou false;
            frase = GetOperators(frase, arraybool); // retorna nova String com os operadores substituidos por simbolos
            // para facilitar leitura and = ^, or = V, not = !

            frase = CalculateBooleanExpresion(frase); // retorna a frase com as operaçoes logicas resolvidas
            MyIO.println(frase);

        }

    }

    // intermediario das funçoes logicas
    public static String CalculateBooleanExpresion(String frase) {
        // preparando para chamar a funçao And
        int operadores = 0;

        for (int i = 0; i < frase.length(); i++) { // loop para contar quantos operadores tem na frase
            if (frase.charAt(i) == '^' || frase.charAt(i) == 'V' || frase.charAt(i) == '!') {
                operadores++;
            }
        }

        int[] OperatorsIndex = new int[operadores];// criando array para guardar as posiçoes dos operadores
        int index = 0; // criando Index para guardar a posiçao dos operadores

        for (int i = frase.length() - 1; i >= 0; i--) { // loop para guardar a posisao dos operadores em index de tras
                                                        // para frente para a ordem de resoluçao de operaçoes
            if (frase.charAt(i) == '^' || frase.charAt(i) == 'V' || frase.charAt(i) == '!') {
                OperatorsIndex[index] = i;
                index++;
            }
        }

        for (int i : OperatorsIndex) { // loop para chamar as funçoes logicas de acordo com a ordem de resoluçao de
                                       // operaçoes (tras para frente)
            if (frase.charAt(i) == '^') {
                frase = andElement(frase, i);
            } else if (frase.charAt(i) == 'V') {
                frase = OrElement(frase, i);
            } else if (frase.charAt(i) == '!') {

                frase = notElementSingle(frase, i);
            }
        }

        return frase;
    }

    // faz a operação lógica not, and, or;

    public static String andElement(String frase, int index) {
        StringBuilder newFrase = new StringBuilder();
        int i = 0;
        int length = frase.length();
    
        while (i < length) {
            if (i == index) {
                int count = 0;
                int positivo = 0;
                int currentIndex = index; // Criando uma variável para controlar a posição atual
    
                // Conta o número de dígitos dentro dos parênteses até o fim dos parênteses
                while (frase.charAt(currentIndex) != ')') {
                    char c = frase.charAt(currentIndex);
                    if (Character.isDigit(c)) {
                        count++;
                        if (c == '1') {
                            positivo++;
                        }
                    }
                    currentIndex++;
                }
    
                // Verifica se todos os dígitos dentro dos parênteses são '1'
                newFrase.append((positivo == count) ? '1' : '0');
    
                i = currentIndex + 1; // Atualiza a posição externa para pular os dígitos e os parênteses
            } else {
                newFrase.append(frase.charAt(i));
                i++;
            }
        }
    
        return newFrase.toString();
    }
    

    public static String notElementSingle(String frase, int index) {
        StringBuilder newFrase = new StringBuilder();
        int i = 0;
        int length = frase.length();
    
        while (i < length) {
            if (i == index) {
                int count = 0;
    
                // Conta o número de dígitos dentro dos parênteses
                while (frase.charAt(index) != ')') {
                    char c = frase.charAt(index);
                    if (Character.isDigit(c)) {
                        count++;
                        newFrase.append(c == '1' ? '0' : '1'); // troca as entradas invertendo-as
                    }
                    index++; // atualiza o índice
                }
    
                i += count + 3; // Pula os dígitos e os parênteses
            } else {
                newFrase.append(frase.charAt(i));
                i++;
            }
        }
    
        return newFrase.toString();
    }
    

    public static String OrElement(String frase, int index) {
        StringBuilder newFrase = new StringBuilder();
        int i = 0;
        int length = frase.length();
    
        while (i < length) {
            if (i == index) {
                int count = 0;
                int positivo = 0;
                int currentIndex = index; // Criando uma variável para controlar a posição atual
    
                // Conta o número de dígitos dentro dos parênteses
                while (frase.charAt(currentIndex) != ')') {
                    char c = frase.charAt(currentIndex);
                    if (Character.isDigit(c)) {
                        count++;
                        if (c == '1') {
                            positivo++;
                        }
                    }
                    currentIndex++;
                }
    
                // Verifica se há dígitos nos parênteses que são positivos
                newFrase.append((positivo > 0) ? '1' : '0');
    
                i += count + 3; // Pula os dígitos e os parênteses
            } else {
                newFrase.append(frase.charAt(i));
                i++;
            }
        }
    
        return newFrase.toString();
    }
    

    // preparando a String para leitura logica abaixo desse comentário, Que Deus
    // perdoe essas funções malevolentes

    public static String CleanString(String frase) { // um codigo que não me orgulho mas ele apenas deixa a String
                                                     // com os caracteres essenciais tirando as informação da entrada
                                                     // como espaços ou os numeros iniciais
        String newfrase = "";// String resultante
        int newindex = 0; // index da primeira Operação (inicio da String utilizavel para a logica)

        // frase antes de limpar: 2 1 0 ^(!(1) , !(0))
        for (int i = 0; i < frase.length(); i++) { // caminhando e apagando numeros até chegar na operação
            if (frase.charAt(i) == 'V' || frase.charAt(i) == '!' || frase.charAt(i) == '^') {
                newindex = i;
                break; // saí do loop
            }
            if (!clear(frase.charAt(i))) { // identifica se é um numero ou um espaço ou uma virgula
                newfrase += frase.charAt(i); // adicionando caracteres a nova frase uteis
            }
        }

        for (int i = newindex; i < frase.length(); i++) {
            if (frase.charAt(i) == ' ' || frase.charAt(i) == ',')
                continue; // ignora espaços e virgulas
            else
                newfrase += frase.charAt(i);
        }
        // frase depois de limpar: ^(!(1)!(0))
        return newfrase;
    }

    public static String getBoolValue(String frase, boolean[] arraybool) {
        String newfrase = "";
        int qtd = arraybool.length;

        for (char element : frase.toCharArray()) {
            if (element == 'A') {
                if (arraybool[0]) {
                    newfrase += '1';
                } else {
                    newfrase += '0';
                }
            } else if (element == 'B' && qtd > 1) {
                if (arraybool[1]) {
                    newfrase += '1';
                } else {
                    newfrase += '0';
                }
            } else if (element == 'C' && qtd > 2) {
                if (arraybool[2]) {
                    newfrase += '1';
                } else {
                    newfrase += '0';
                }
            } else {
                newfrase += element;
            }
        }
        return newfrase;
    }

    public static String GetOperators(String frase, boolean[] arraybool) { // funçõa intermediária que chama as outras
                                                                           // funções para substituir os operadores
                                                                           // logicos por simbolos

        frase = equalsAnd(frase); // substitui and por ^
        frase = equalsNot(frase); // substitui not por !
        frase = equalsOr(frase);// substitui or por V
        frase = (getBoolValue(frase, arraybool)); // retorna String com os termos A,B,C substituidos pelos seus
                                                  // valores logicos.
        frase = CleanString(frase); // remove os numeros da entrada inicial e

        return frase;
    }

    // retorna true se for igual a not
    public static String equalsNot(String frase) {
        return frase.replace("not", "!");
    }
    
    public static String equalsOr(String frase) {
        return frase.replace("or", "V");
    }
    
    public static String equalsAnd(String frase) {
        return frase.replace("and", "^");
    }
    
    static boolean igual_0(String frase) {
        return frase.equals("0");
    }
    
    static boolean clear(char c) {
        return Character.isDigit(c) || c == ' ' || c == ',' || c == '.';
    }

    static boolean isnumber(char c) {
        return Character.isDigit(c);
    }
    
}
