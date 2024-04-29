public class Fila {
    /* Na Fila devemos inserir no final e remover do inicio */

    private Node head; //neesa implementação, head é o primeiro elemento da fila
    private int size; //tamanho da fila -> opcional
    void setHead(Node head){
        this.head = head;
    }
    Node getHead(){
        return this.head;
    }
    void setSize(int size){
        this.size = size;
    }
    int getSize(){
        return this.size;
    }
    int incrementSize(){
        return this.size++;
    }
    int decrementSize(){
        return this.size--;
    }

    public Fila(){
        this.head = null;
    }

    public void inserir(int data){
        //checar a cabeça
        this.incrementSize(); //incrementa o tamanho da fila

        if(this.head == null){ //se a cabeça for nula, então a fila está vazia
            this.head = new Node(data);
            return; //retorna para não executar o resto do código
        }
        //a cabeça não é nula
        Node temp = this.head; //cria um nó temporário para percorrer a fila
        while (temp.next != null){ //enquanto o próximo nó não for nulo(até que temp seja o último nó)
            temp = temp.next; //temp recebe o próximo nó
        }
        temp.next = new Node(data); //quando o próximo nó for nulo, temp recebe um novo nó
    }

    public void remover(){
        if(this.head == null){ //se a cabeça for nula, então a fila está vazia
            System.out.println("Fila vazia");
            return; //retorna para não executar o resto do código
        }
        this.decrementSize(); //decrementa o tamanho da fila
        this.head = this.head.next; //a cabeça recebe o próximo nó
        //o nó que era a cabeça é removido pelo garbage collector
    }
}
