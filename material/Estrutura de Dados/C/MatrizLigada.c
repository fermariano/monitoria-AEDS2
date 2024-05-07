#include <stdio.h>
#include <stdlib.h>

typedef struct Node
{
    int valor;
    struct Node *baixo;
    struct Node *cima;
    struct Node *dir;
    struct Node *esq;
} Node;

typedef struct Matriz
{
    Node *primeiro;
    int colunas;
    int linhas;
} Matriz;



void gerarColunas(Matriz *matriz, Node *C1, Node *C2)
{
    for (int i = 1; i < matriz->colunas; i++)
    {
        Node *nextC2 = malloc(sizeof(Node) * 1);
        Node *nextC1;
        if (C1->dir == NULL)
        {
            nextC1 = malloc(sizeof(Node) * 1);
        }
        else
        {
            nextC1 = C1->dir;
        }
        C1->dir = nextC1;
        C1->baixo = C2;
        C2->cima = C1;
        C2->dir = nextC2;
        nextC1->esq = C1;
        nextC1->baixo = nextC2;
        nextC2->cima = nextC1;
        nextC2->esq = C2;
        // atualizando ponteiros
        C1 = nextC1;
        C2 = nextC2;
    }
}

void gerarLinhas(Matriz *matriz)
{
    int linhas = matriz->linhas;
    Node *temp = matriz->primeiro;
    for (int i = 1; i < linhas; i++, temp = temp->baixo)
    { // criando linhas da matriz conectando cima e baixo
        Node *novo = malloc(sizeof(Node) * 1);
        novo->cima = temp;
        temp->baixo = novo;
    }
}
void gerarMatriz(Matriz *matriz)
{
    int linhas = matriz->linhas;
    int colunas = matriz->colunas;
    gerarLinhas(matriz);
    Node *C1 = matriz->primeiro;
    if(linhas >1){
        Node *C2 = matriz->primeiro->baixo;
      for (int i = 1; i < linhas; i++)
      { // criando colunas da matriz conectando esq e dir
          gerarColunas(matriz, C1, C2);
          C1 = C2;
          C2 = C2->baixo;
      }
    }else{
      printf("Matriz com apenas uma linha");
      for(int i = 1; i< colunas; i++){
        Node *novo = malloc(sizeof(Node) * 1);
        novo->esq = C1;
        C1->dir = novo;
        C1 = novo;
      }
    }
    
}

void iniciarMatriz(Matriz *matriz, int linhas, int colunas)
{
    if (linhas <= 0 || colunas <= 0)
    {
        printf("\nErro: linhas e colunas devem ser maiores que 0");
        return;
    }
    matriz->primeiro = malloc(sizeof(Node) * 1);
    matriz->linhas = linhas;
    matriz->colunas = colunas;
    gerarMatriz(matriz);
}




void setMatriz(Matriz *matriz, int linha, int coluna, int x)
{
    if (linha > matriz->linhas || coluna > matriz->colunas)
    {
        printf("\nErro: linha ou coluna fora do tamanho da matriz");
        return;
    }
    Node *temp = matriz->primeiro;
    for (int i = 1; i <= linha; i++)
    {
        temp = temp->baixo;
    }

    for (int i = 1; i <= coluna; i++)
    {
        temp = temp->dir;
    }
    temp->valor = x;
}

void printMatriz(Matriz *matriz)
{
    int linha = matriz->linhas;
    int coluna = matriz->colunas;
    Node* tempLinha = matriz->primeiro;

    while(tempLinha!=NULL){
      Node* tempColuna = tempLinha;
      while(tempColuna!=NULL){
        printf("%d ", tempColuna->valor);
        tempColuna = tempColuna->dir;
      }
      puts("");
      tempLinha = tempLinha->baixo;
    }
}

int main()
{
    Matriz *matriz = malloc(sizeof(Matriz) * 1);
    iniciarMatriz(matriz, 5, 1);
    int linha = matriz->linhas;
    int coluna = matriz->colunas;
    for (int i = 0; i < linha; i++)
    {
        for (int j = 0; j < coluna; j++)
        {
            setMatriz(matriz, i, j, i+j);
        }
        printf("\n");
    }
    printMatriz(matriz);
    return 0;
}
