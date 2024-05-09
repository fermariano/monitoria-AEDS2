#ifndef PILHA
#define PILHA
#include "NODE.h"
#include <stdlib.h>

typedef struct Pilha  Pilha;
void inserirP(Pilha *pilha, int data); //push
void removerP(Pilha *pilha); //pop

struct Pilha {
    int size;
    struct Node *top;
    void (*push)(struct Pilha *pilha, int data);
    void (*pop)(struct Pilha *pilha);
};

Pilha* CriarPilha() {
    Pilha *pilha = (Pilha *)malloc(sizeof(Pilha));
    pilha->size = 0;
    pilha->top = NULL;
    pilha->push = inserirP;
    pilha->pop = removerP;
    return pilha;
}

void removerP(Pilha *pilha) {
    if (pilha->size == 0) {
        return;
    } else {
        pilha->size--;
        struct Node *temp = pilha->top;
        pilha->top = pilha->top->next;
        free(temp);
    }
}

void inserirP(Pilha *pilha, int data) {
    pilha->size++;
    if (pilha->top == NULL) {
        pilha->top = criarNode(data);
        return;
    }
    struct Node *temp = criarNode(data);
    temp->next = pilha->top;
    pilha->top = temp;
    // inserido!
}



#endif /* PILHA */