// Classe Node que representa um nó da BST
class Node {
    int key;  // Chave do nó
    Node left, right;  // Referências para os filhos esquerdo e direito

    public Node(int item) {
        key = item;
        left = right = null;
    }

    public void insert(int key) {
        insert(this, key);
    }
    private Node insert(Node root, int key) {
        if (root == null) {
            return new Node(key);
        }
        if (key < root.key) {
            root.left = insert(root.left, key);
        } else if (key > root.key) {
            root.right = insert(root.right, key);
        }
        return root;
    }
}

// Classe MatrixNode que representa um nó da matriz
class MatrixNode {
    Node root;  // Cada nó da matriz contém uma BST
    MatrixNode right, down, left, up;  // Referências para os nós adjacentes na matriz

    /**
     * Construtor para inicializar o nó da matriz com a raiz de uma BST
     * @param root raiz da BST associada ao nó
     */
    public MatrixNode(Node root) {
        this.root  =  root;
        this.right =  null;
        this.left  =  null;
        this.down  =  null;
        this.up    =  null;
    }

    /**
     * Método auxiliar para imprimir a BST em ordem
     * @param node raiz da BST a ser impressa
     */
    private void printBST(Node node) {
        if (node != null) {
            printBST(node.left);
            System.out.print("Key: " + node.key + " | ");
            printBST(node.right);
        }
    }

    /**
     * Método para imprimir a BST associada ao nó da matriz
     */
    public void printRoot() {
        printBST(root);
        System.out.println();
    }
}

// Classe Matrix que representa a matriz de nós
class Matrix {
    MatrixNode start; // M[0][0]

    public Matrix() {
        this.start = null;
    }

    /**
     * Método para adicionar um nó na matriz na posição especificada
     * @param root raiz da BST a ser adicionada
     * @param row linha onde o nó será adicionado
     * @param col coluna onde o nó será adicionado
     */
    public void addMatrixNode(Node root, int row, int col) {
        if (start == null) {
            start = new MatrixNode(root);
            return;
        }
        
        MatrixNode temp = start;
        for (int i = 0; i < row; i++) {
            if (temp.down == null) temp.down = new MatrixNode(null);
            temp.down.up = temp;
            temp = temp.down;
        }

        for (int j = 0; j < col; j++) {
            if (temp.right == null) temp.right = new MatrixNode(null);
            temp.right.left = temp;
            temp = temp.right;
        }

        temp.root = root;
    }

    /**
     * Método para navegar pela matriz e acessar as BSTs
     */
    public void traverseAndPrint() {
        MatrixNode tempLine = start;
        int i , j;   i = j = 0;

        while (tempLine != null) {
            MatrixNode tempColumn = tempLine; 
            while (tempColumn != null) {
                System.out.println("Matriz Pos [" + i + "][" + j + "]:");
                if (tempColumn.root != null) {
                    tempColumn.printRoot(); // Printa a BST associada ao nó
                }
                tempColumn = tempColumn.right; // Vai para a direita
                j++;
            }
            tempLine = tempLine.down; // Vai para baixo
            i++;
            j = 0;
        }
    }
}

// Classe principal para testar a navegação na matriz
public class MatrizBst {
    public static void main(String[] args) {
        
        Node root1 = new Node(1);
        root1.insert(2);
        root1.insert(3);

        Node root2 = new Node(4);
        root2.insert(5);
        root2.insert(6);

        Node root3 = new Node(7);
        root3.insert(8);
        root3.insert(9);

        Matrix matrix = new Matrix();
        matrix.addMatrixNode(root1, 0, 0);
        matrix.addMatrixNode(root2, 0, 1);
        matrix.addMatrixNode(root3, 1, 0);

        matrix.traverseAndPrint();
    }
}
