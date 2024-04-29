#include "PILHA.h"
#include <stdio.h>

int main(){
    
    Pilha *pilha = CriarPilha();
    pilha->push(pilha, 10);
    pilha->push(pilha, 20);
    pilha->push(pilha, 30);


    Node * temp = pilha->top;
    while (temp != NULL) {
        printf("%d\n", temp->data);
        temp = temp->next;
    }


    return 0;
}