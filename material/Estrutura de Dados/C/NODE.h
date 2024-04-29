#ifndef NODE
#define NODE

#include <stdlib.h>

// Definição da estrutura Node
typedef struct Node {
    int data;
    struct Node *next;
} Node;

Node *criarNode(int data);

Node *criarNode(int data) {
    Node *node = (Node *)malloc(sizeof(Node));
    node->data = data;
    node->next = NULL;
    return node;
}

#endif /* NODE_H */

