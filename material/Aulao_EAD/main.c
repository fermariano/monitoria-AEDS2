
ChecarArvore(No * NoArvore, Celula head ,int pesquisado){
    if(NoArvore == null){
        return;
    }
    if(pesquisado < NoArvore->numero){
        ChecarArvore(NoArvore->esq, head, pesquisado);
    }else if(pesquisado > NoArvore->numero){
        ChecarArvore(NoArvore->dir, head, pesquisado);
    }else{//elemento igual
        inserirCelula(head,pesquisado);
    }
}

Celula * encontrarRepetidos(No * raiz, CelulaMatriz * inicio){
    Celula * head = malloc(sizeof(Celula)); //celula vazia

    CelulaMatriz * tempLinha = inicio;
    while(tempLinha != null){
        CelulaMatriz * tempColuna = tempLinha;
        while(tempColuna != null){
            ChecarArvore(raiz, head, tempColuna->numero);
            tempColuna = tempColuna->prox;
        }
        tempLinha = tempLinha->inf;
    }
    return head;

}

void inserirCelula(Celula head, int chave){ //inserir decrescente
    Celula * temp = head;

    Celula * ins = malloc(sizeof(Celula));
    ins->numero = chave;
    // 5 4 3  1
    //2
    while(temp->prox != null){
        if(chave > temp->prox.numero){
            Celula * op = temp->prox;
            temp->ins;
            ins->prox = op;
            return ;
        }
        temp = temp->prox;
    }
    temp->next = ins;
}