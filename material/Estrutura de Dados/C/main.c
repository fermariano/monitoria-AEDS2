#include "FILA.h"
#include <stdio.h>




int main(){
    Fila * myQueue = CriarFila();
    myQueue->inserir(myQueue, 1);
    myQueue->inserir(myQueue, 2);
    myQueue->inserir(myQueue, 3);

    myQueue->remover(myQueue);
    printf("Tamanho da fila: %d\n", myQueue->size);



    return 0;
}