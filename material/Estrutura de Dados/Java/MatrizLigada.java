import javax.swing.*;
import java.awt.*;

public class MatrizLigada extends JPanel {

    class Node {
        protected int valor;
        protected Node cima;
        protected Node baixo;
        protected Node esq;
        protected Node dir;

        Node() {

        }

        Node(int valor) {
            this.valor = valor;
        }

        // getters
        public Node getCima() {
            return cima;
        }

        public Node getBaixo() {
            return baixo;
        }

        public Node getEsq() {
            return esq;
        }

        public Node getDir() {
            return dir;
        }

        public int getValor() {
            return valor;
        }

        // setters
        public void setCima(Node cima) {
            this.cima = cima;
        }

        public void setBaixo(Node baixo) {
            this.baixo = baixo;
        }

        public void setEsq(Node esq) {
            this.esq = esq;
        }

        public void setDir(Node dir) {
            this.dir = dir;
        }

        public void setValor(int valor) {
            this.valor = valor;
        }
    }

    int linhas;
    int colunas;
    Node inicio; // posicao 0x0 na matriz

    MatrizLigada() {

    }

    public void gerarMatriz(int linhas, int colunas) {
        preencher();
    }

    MatrizLigada(int linhas, int colunas) {
        this.linhas = linhas;
        this.colunas = colunas;
        preencher();
    }

    MatrizLigada(int linhas, int colunas, int valor) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.inicio = new Node(valor);
        preencher();
    }

    protected void criarLinhas() {
        Node temp = inicio;
        for (int i = 1; i < linhas; i++) {
            Node novo = new Node(1);
            temp.setBaixo(novo);
            novo.setCima(temp);
            temp = novo;
        }
    }

    public void preencher() {
        if (inicio == null) {
            inicio = new Node(3);
        }
        criarLinhas();
        Node C1 = inicio;
        Node C2 = inicio.getBaixo();
        for (int i = 1; i < linhas; i++) {
            C1.setBaixo(C2);
            C2.setCima(C1);
            criarColunas(C1, C2);
            C1 = C2;
            C2 = C2.getBaixo();
        }
    }

    protected void criarColunas(Node C1, Node C2) { // C1 é acima de C2
        for (int i = 1; i < colunas; i++) {
            Node nextC1;
            Node nextC2 = new Node(1);
            if (C1.getDir() != null) { // já fez direcionamento nessa linha
                nextC1 = C1.getDir();
            } else { // linha precisa de direcionamento
                nextC1 = new Node(1);
                C1.setDir(nextC1);
                nextC1.setEsq(C1);
            }
            nextC1.setBaixo(nextC2);
            C2.setDir(nextC2);
            nextC2.setEsq(C2);
            nextC2.setCima(nextC1);
            C1 = nextC1;
            C2 = nextC2;
        }
    }

    public void print() {
        Node linhaTemp = inicio;
        Node ColunaTemp;
        for (; linhaTemp != null; linhaTemp = linhaTemp.getBaixo()) {
            ColunaTemp = linhaTemp;
            for (; ColunaTemp != null; ColunaTemp = ColunaTemp.getDir()) {
                System.out.print(ColunaTemp.getValor() + " ");
            }
            System.out.println();
        }
    }

    public void printarDiagonal() {
        Node temp = inicio;
        while (true) {
            System.out.print(temp.valor + " ");

            temp = temp.baixo;
            if (temp == null) {
                break;
            }
            temp = temp.dir;
        }
        System.out.println();
    }

    public void printarDiagonalInversa() {
        Node temp = inicio;
        while (temp.getDir() != null) {
            temp = temp.getDir();
        }
        while (true) {
            System.out.print(temp.getValor() + " ");
            if (temp.getBaixo() == null) {
                break;
            }
            temp = temp.getBaixo().getEsq();
        }   
        System.out.println();
    }

    public void setPos(int linhax, int colunax, int x) { // indice de vetor
        Node elemento = inicio;
        for (int i = 0; i < colunax; i++) {
            elemento = elemento.getDir();
        }

        for (int i = 0; i < linhax; i++) {
            elemento = elemento.getBaixo();
        }
        elemento.setValor(x);
       
    }

      public int getPos(int linhax, int colunax) { // indice de vetor
        Node elemento = inicio;
        for (int i = 0; i < colunax; i++) {
            elemento = elemento.getDir();
        }

        for (int i = 0; i < linhax; i++) {
            elemento = elemento.getBaixo();
        }
        return elemento.getValor();
        
    }

    public static MatrizLigada LerMatriz(MatrizLigada m){
        int linhas1,colunas1;
        linhas1 = MyIO.readInt();
        colunas1 = MyIO.readInt();
        m = new MatrizLigada(linhas1, colunas1);
        for(int i = 0; i<linhas1; i++){
            for(int j = 0; j<colunas1; j++){
            m.setPos(i,j,MyIO.readInt());
            }
        }
        return m;
    }

    public static void PrintarSoma(MatrizLigada m1,MatrizLigada m2){
        MatrizLigada m3 = new MatrizLigada(m1.linhas,m1.colunas);
        for(int i = 0; i< m1.linhas; i++){
            for(int j =0; j<m1.colunas; j++){
            m3.setPos(i,j,m1.getPos(i,j)+m2.getPos(i,j));
            }
        }
        m3.print();
    }

    public static void PrintarMultiplic(MatrizLigada m1, MatrizLigada m2){
        MatrizLigada m3 = new MatrizLigada(m1.linhas,m2.colunas);
        for(int i = 0; i< m1.linhas; i++){
            for(int j = 0; j<m2.colunas; j++){
                int soma = 0;
                for(int k = 0; k<m1.colunas;k++){
                    soma += m1.getPos(i,k)*m2.getPos(k,j);
                }
                m3.setPos(i,j,soma);
            }
        }
        m3.print();
    }


    public static void main(String args[]) {
        int casos = MyIO.readInt();
        for(; casos>0;casos--){
            MatrizLigada matrizes[] = new MatrizLigada[2];
            for(int i = 0; i<2; i++){
               matrizes[i] = LerMatriz(matrizes[i]);
            }   
            matrizes[0].printarDiagonal();      
            matrizes[0].printarDiagonalInversa();
            PrintarSoma(matrizes[0],matrizes[1]);
            PrintarMultiplic(matrizes[0],matrizes[1]);
        }   
    }
}
