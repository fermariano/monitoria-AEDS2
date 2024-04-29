public class Lista {
    private Node head; //cabeça da lista
    private Node tail; //cauda da lista
    private int size; //tamanho da lista -> opcional
    void setHead(Node head){
        this.head = head;
    }
    Node getHead(){
        return this.head;
    }
    void setTail(Node tail){
        this.tail = tail;
    }
    Node getTail(){
        return this.tail;
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

    public Lista(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    //inserçoes
    public void inserirInicio(int data){ //metodo igual da pilha
        this.incrementSize(); //incrementa o tamanho da lista

        if(this.head == null){ //se a cabeça for nula, então a lista está vazia
            this.head = this.tail = new Node(data);
            return; //retorna para não executar o resto do código
        }
        //a cabeça não é nula -> não mexe na cauda
        Node temp = new Node(data); //cria um nó temporário
        temp.next = this.head; //o próximo nó do temp é a cabeça
        this.head = temp; //a cabeça recebe o temp
    }

    public void inserirFinal(int data){//metodo igual da fila
        this.incrementSize(); //incrementa o tamanho da lista

        if(this.head == null){ //se a cabeça for nula, então a lista está vazia
            this.head = this.tail = new Node(data);
            return; //retorna para não executar o resto do código
        }
        //a cabeça não é nula
        this.tail.next = new Node(data); //adicione um novo nó no final
        this.tail = this.tail.next; //a cauda recebe o novo nó
    }

    public void inserir(int index,int data){ // index similar ao array em que  0 = pos1
        if(this.head == null){ //se a cabeça for nula, então a lista está vazia
            this.head = this.tail = new Node(data);
            return;
        }
        if(index == 0){ //se o index for 0, insira no inicio
            this.inserirInicio(data);
            return;
        }
        Node temp = this.head;
        for(int i = 0; i<index - 1;i++){//neste loop, pretendemos chegar ao nó anterior ao index
            if(temp.next == null){ 
                System.out.println("Index invalido");
                return;
            }
            temp = temp.next;
        }
        //o temp é o nó anterior ao index
        Node newNode = new Node(data);
        newNode.next = temp.next; //faça essa operação primeiro para não desconectar a lista
        //agora podemos apontar o temp para o novo nó
        temp.next = newNode;
        //inserido!
    }

    //remoções
    public void removerInicio(){ //metodo igual da pilha
        if(this.head == null){ //se a cabeça for nula, então a lista está vazia
            System.out.println("Lista vazia");
            return; //retorna para não executar o resto do código
        }
        this.decrementSize(); //decrementa o tamanho da lista
        this.head = this.head.next; //a cabeça recebe o próximo nó
    }

    public void removerFinal(){ //metodo igual da fila
        if(this.head == null){ //se a cabeça for nula, então a lista está vazia
            System.out.println("Lista vazia");
            return; //retorna para não executar o resto do código
        }
        this.decrementSize(); //decrementa o tamanho da lista
        Node temp = this.head;
        while(temp.next != this.tail){ //enquanto o próximo nó não for a cauda
            temp = temp.next; //temp recebe o próximo nó
        }
        this.tail = temp; //a cauda recebe o temp
        this.tail.next = null; //o próximo nó da cauda é nulo
    }

    public void remover(int index){
        if(this.head == null){ //se a cabeça for nula, então a lista está vazia
            System.out.println("Lista vazia");
            return;
        }
        if(index == 0){ //se o index for 0, remova o inicio
            this.removerInicio();
            return;
        }
        Node temp = this.head;
        for(int i = 0; i<index - 1;i++){//neste loop, pretendemos chegar ao nó anterior ao index
            if(temp.next == null){ 
                System.out.println("Index invalido");
                return;
            }
            temp = temp.next;
        }
        //o temp é o nó anterior ao index
        temp.next = temp.next.next; //o próximo nó do temp é o próximo nó do próximo nó
        //removido!
    }

}
