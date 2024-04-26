#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*
    Code by JoaoMineiro1990
*/

int main() {
    int vezes;
    scanf("%d", &vezes);

    char lingua[vezes][100];
    char traducao[vezes][100];

    // Lendo as frases e suas traduções
    for(int i = 0; i < vezes; i++) {
        scanf(" %[^\n]", lingua[i]);
        scanf(" %[^\n]", traducao[i]);
    }

    int numCri;
    scanf("%d", &numCri);

    for(int i = 0; i < numCri ; i++){
        char crianca[100];
        scanf(" %[^\n]", crianca); 
        printf("%s\n", crianca); 

        char idioma[100];
        scanf(" %[^\n]", idioma); 

        for(int j = 0; j < vezes; j++) {
            if(strcmp(idioma, lingua[j]) == 0) {
                if( i == (numCri-1))printf("%s", traducao[j]);
                else printf("%s\n", traducao[j]); 
                j = vezes;
                printf("\n")
            }
        }
    }

    return 0;
}