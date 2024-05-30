// Classe Node que representa um nó da BST
class BstNode {
    int key;  // Chave do nó
    LinkedList values;  // Lista ligada para armazenar múltiplos valores associados à chave
    BstNode left, right;  // Referências para os filhos esquerdo e direito

    /**
     * Construtor para inicializar o nó com uma chave
     * @param item chave do nó
     */
    public BstNode(int item) {
        key = item;
        values = new LinkedList();
        left = right = null;
    }
    public void printRoot() { //metodo que printa apenas raizes
      printRoot(this); // manda o proprio No para o metodo recursivo
    }
    private void printRoot(BstNode root) {
        if (root != null) {
            printRoot(root.left);
            System.out.print("Key: " + root.key + " Values: " + root.values + " | ");
            printRoot(root.right);
        }
    }
}

// Classe LinkedListNode que representa um nó da lista ligada
class LinkedListNode {
    int data;  // Dados armazenados no nó
    LinkedListNode next;  // Referência para o próximo nó na lista

    /**
     * Construtor para inicializar o nó da lista ligada com dados
     * @param data valor a ser armazenado no nó
     */
    public LinkedListNode(int data) {
        this.data = data;
        this.next = null;
    }
}

// Classe LinkedList que representa a lista ligada
class LinkedList {
    LinkedListNode head;  // Cabeça da lista ligada

    /**
     * Construtor para inicializar a lista ligada
     */
    public LinkedList() {
        head = null;
    }

    /**
     * Método para adicionar um valor na lista ligada
     * @param data valor a ser adicionado na lista
     */
    public void add(int data) {
        LinkedListNode newNode = new LinkedListNode(data);  // Cria um novo nó com o valor fornecido
        if (head == null) {  // Se a lista estiver vazia, o novo nó se torna a cabeça
            head = newNode;
        } else {
            LinkedListNode temp = head;  // Caso contrário, percorre a lista até o final
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;  // Adiciona o novo nó no final da lista
        }
    }

    /**
     * Método para converter a lista ligada em uma string
     * @return representação em string da lista ligada
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        LinkedListNode temp = head;
        while (temp != null) {
            result.append(temp.data).append(" ");  // Adiciona o valor de cada nó na string
            temp = temp.next;
        }
        return result.toString();
    }
}

// Classe BST que representa a árvore binária de busca
class BST {
    BstNode root;  // Raiz da árvore

    /**
     * Construtor para inicializar a árvore
     */
    public BST() {
        root = null;
    }

    /**
     * Método público para inserir uma chave e valor na árvore
     * @param key chave a ser inserida
     * @param value valor associado à chave a ser inserido
     */
    public void insert(int key, int value) {
        root = insertRec(root, key, value);
    }

    /**
     * Método recursivo para inserir uma chave e valor na árvore
     * @param root nó atual da árvore
     * @param key chave a ser inserida
     * @param value valor associado à chave a ser inserido
     * @return o nó atualizado da árvore
     */
    private BstNode insertRec(BstNode root, int key, int value) {
        if (root == null) {
            root = new BstNode(key);
            root.values.add(value);
            return root;
        }
        if (key < root.key)
            root.left = insertRec(root.left, key, value);  // Insere no filho esquerdo se a chave for menor
        else if (key > root.key)
            root.right = insertRec(root.right, key, value);  // Insere no filho direito se a chave for maior
        else
            root.values.add(value);  // Adiciona o valor à lista ligada se a chave já existir

        return root;
    }

    /**
     * Método público para pesquisar uma chave na árvore
     * @param key chave a ser pesquisada
     * @return lista ligada de valores associados à chave ou null se não encontrada
     */
    public LinkedList search(int key) {
        return searchRec(root, key);
    }

    /**
     * Método recursivo para pesquisar uma chave na árvore
     * @param root nó atual da árvore
     * @param key chave a ser pesquisada
     * @return lista ligada de valores associados à chave ou null se não encontrada
     */
    private LinkedList searchRec(BstNode root, int key) {
        if (root == null || root.key == key)
            return (root != null) ? root.values : null;  // Retorna a lista de valores se a chave for encontrada
        if (root.key > key)
            return searchRec(root.left, key);  // Pesquisa no filho esquerdo se a chave for menor
        return searchRec(root.right, key);  // Pesquisa no filho direito se a chave for maior
    }

    /**
     * Método principal para testar a inserção e a pesquisa na árvore
     * @param args argumentos da linha de comando (não utilizados)
     */
}


public class BstList {

    public static void main(String[] args) {
        BST tree = new BST();  // Cria uma nova árvore
        for (int i = 1; i <= 5; i++) {
            for (int j = i * 100; j <= (i*100) + 5; j++) {
                tree.insert(i, j);  // Insere chaves e valores na árvore
            }
        }
        for (int i = 1; i <= 5; i++) {
            LinkedList values = tree.search(i);  // Pesquisa valores associados à chave
            System.out.println("Chave " + i + ": " + values);  // Exibe os valores associados à chave
        }
    }
}
