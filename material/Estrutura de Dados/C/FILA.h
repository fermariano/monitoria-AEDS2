#ifndef FILA
#define FILA

#include "NODE.h"
#include <stdlib.h>

typedef struct Fila {
    int size;
    struct Node *head; // Qualificando o tipo corretamente como struct Node
    void (*inserir)(struct Fila *fila, int data);
    void (*remover)(struct Fila *fila);
} Fila;

void inserirF(Fila *fila, int data); //insere Final
void removerF(Fila *fila); //remove Inicio

Fila* CriarFila() {
    Fila *fila = (Fila *)malloc(sizeof(Fila));
    fila->size = 0;
    fila->head = NULL;
    fila->inserir = inserirF; // Atribuição direta do nome da função
    fila->remover = removerF; // Atribuição direta do nome da função
    return fila;
}


void removerF(Fila *fila) {
    if (fila->size == 0) {
        return;
    } else {
        fila->size--;
        struct Node *temp = fila->head; // Qualificando o tipo corretamente como struct Node
        fila->head = fila->head->next;
        free(temp);
    }
}

void inserirF(Fila *fila, int data) {
    fila->size++;
    if (fila->head == NULL) {
        fila->head = criarNode(data);
        return;
    }
    struct Node *temp = fila->head; // Qualificando o tipo corretamente como struct Node
    while (temp->next != NULL) {
        temp = temp->next;
    }
    temp->next = criarNode(data);
    // inserido!
}

#endif /* FILA */
