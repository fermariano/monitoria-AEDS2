public class Pilha { //LIFO -> Last In First Out
    private Node top; //topo da pilha
    private int size; //tamanho da pilha -> opcional
    void setTop(Node top){
        this.top = top;
    }
    Node getTop(){
        return this.top;
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


    public Pilha(){
        this.top = null;
    }

    public void push(int data){
        this.incrementSize(); //incrementa o tamanho da pilha
        
        if(this.top == null){ //se o topo for nulo, então a pilha está vazia
            this.top = new Node(data);
            return; //retorna para não executar o resto do código
        }
        //o topo não é nulo
        Node temp = new Node(data); //cria um nó temporário
        temp.next = this.top;
        this.top = temp;
    }

    public void pop(){
        if(this.top == null){ //se o topo for nulo, então a pilha está vazia
            System.out.println("Pilha vazia");
            return; //retorna para não executar o resto do código
        }
        this.decrementSize(); //decrementa o tamanho da pilha
        this.top = this.top.next; //o topo recebe o próximo nó
    }
}
