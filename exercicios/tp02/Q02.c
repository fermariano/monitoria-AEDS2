#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include <stdbool.h>
#include <stdint.h>
#include <time.h>

typedef struct {
    char *id;
    char *name;
    char **alternateNames;
    int alternateNamesCount;
    char *house;
    char *ancestry;
    char *species;
    char *patronus;
    bool isHogwartsStaff;
    bool isHogwartsStudent;
    char *actorName;
    bool isAlive;
    char **alternateActors;
    int alternateActorsCount;
    char *dateOfBirth;
    int yearOfBirth;
    char *eyeColour;
    char *gender;
    char *hairColour;
    bool isWizard;
} Character;



char *read_string(int *i, const char *csvLine) {
    int maxSize = strlen(csvLine);
    if (*i >= maxSize) 
        return NULL; 
    //TODO fzer logica de #define com buffer_size e se passar expande
    char *str = (char *)malloc(maxSize * sizeof(char));
    int pos = 0; 
    while (*i < maxSize && csvLine[*i] != ';') 
        str[pos++] = csvLine[(*i)++];

    str[pos] = '\0'; 
    (*i)++;
    return str;
}

char **read_multivalued(int *i, const char *csvLine, int *arrayCount) {
    int maxSize = strlen(csvLine);
    if (*i >= maxSize-1 || csvLine[*i] != '[') 
        return NULL;
    (*i)++; // jump '['
    char **array = (char **)malloc(10 * sizeof(char *)); 
    if (array == NULL) {
        perror("Memory allocation error in string array");
        return NULL;
    }

    int pos = 0;
    while (*i < maxSize && csvLine[*i] != ']') {
        char *tempStr = (char *)malloc((maxSize + 1) * sizeof(char));
        if (tempStr == NULL) {
            perror("Memory allocation error in string");
            return NULL;
        }

        int j = 0;
        while (*i < maxSize && csvLine[*i] != ',' && csvLine[*i] != ']') {
            if (csvLine[*i] != '\'') 
                tempStr[j++] = csvLine[*i];
            
            (*i)++;
        }

        tempStr[j] = '\0'; 
        array[pos++] = tempStr;

        if (csvLine[*i] == ',') {
            (*i)++; 
        }
    }
    (*i) += 2; // jump ']' and ';'
    *arrayCount = pos; 
    return array;
}

bool read_boolean(int *i, const char *csvLine) {
    bool predicate = false;
    char* wordRead = read_string(i, csvLine);

    if (wordRead != NULL) {
        if (strcmp(wordRead, "VERDADEIRO") == 0) {
            predicate = true;
        }
        free(wordRead);
    }

    return predicate; 
}

int read_integer(int *i, const char *csvLine){
    char* wordRead = read_string(i, csvLine);
    if(wordRead == NULL)
        return -1;
    return atoi(wordRead);
}

Character* csvLine_mapper(char* csvLine) {
    Character* character = (Character*)malloc(sizeof(Character));
    if (character == NULL) {
        perror("Memory allocation error in Character struct");
        return NULL;
    }

    int i = 0;

    character->id = read_string(&i, csvLine);
    character->name = read_string(&i, csvLine);

    int alternateNamesCount = 0;
    character->alternateNames = read_multivalued(&i, csvLine, &alternateNamesCount);
    character->alternateNamesCount = alternateNamesCount;

    character->house = read_string(&i, csvLine);
    character->ancestry = read_string(&i, csvLine);
    character->species = read_string(&i, csvLine);
    character->patronus = read_string(&i, csvLine);
    character->isHogwartsStaff = read_boolean(&i, csvLine);
    character->isHogwartsStudent = read_boolean(&i, csvLine);
    character->actorName = read_string(&i, csvLine);
    character->isAlive = read_boolean(&i, csvLine);

    int alternateActorsCount = 0;
    character->alternateActors = read_multivalued(&i, csvLine, &alternateActorsCount);
    character->alternateActorsCount = alternateActorsCount;

    character->dateOfBirth = read_string(&i, csvLine);
    character->yearOfBirth = read_integer(&i, csvLine);
    character->eyeColour = read_string(&i, csvLine);
    character->gender = read_string(&i, csvLine);
    character->hairColour = read_string(&i, csvLine);
    character->isWizard = read_boolean(&i, csvLine);

    return character; 
}

void print_string_array(char** array, int count) {
    printf("{");
    for (int i = 0; i < count; i++) {
        printf("%s", array[i]);
        if (i < count - 1) {
            printf(", ");
        }
    }
    printf("}");
}

void print_character(Character* character) {
    if (character == NULL) {
        printf("Character struct is NULL.\n");
        return;
    }

    printf("[");
    printf("%s", character->id ? character->id : " "); 
    printf(" ## %s", character->name ? character->name : " ");

    printf(" ## ");
    if (character->alternateNames) {
        print_string_array(character->alternateNames, character->alternateNamesCount);
    } else {
        printf(" {}");
    }

    printf(" ## %s", character->house ? character->house : " "); 
    printf(" ## %s", character->ancestry ? character->ancestry : " "); 
    printf(" ## %s", character->species ? character->species : " "); 
    printf(" ## %s", character->patronus ? character->patronus : " "); 
    printf(" ## %s", character->isHogwartsStaff ? "true" : "false"); 
    printf(" ## %s", character->isHogwartsStudent ? "true" : "false"); 
    printf(" ## %s", character->actorName ? character->actorName : " ");
    printf(" ## %s", character->isAlive ? "true" : "false"); 
    printf(" ## %s", character->dateOfBirth ? character->dateOfBirth : " "); 
    printf(" ## %d", character->yearOfBirth); 
    printf(" ## %s", character->eyeColour ? character->eyeColour : " ");
    printf(" ## %s", character->gender ? character->gender : " ");
    printf(" ## %s", character->hairColour ? character->hairColour : " "); 
    printf(" ## %s", character->isWizard ? "true" : "false");

    printf("]\n");
}


int main(){
    FILE *file = fopen("C:\\Users\\thomn\\OneDrive\\Desktop\\monitoria-AEDS2\\exercicios\\tp02\\characters.csv", "r");  
    if (file == NULL) { 
        perror("File not found exception.");
        return 1;
    }

    char line[256];
    fgets(line, sizeof(line), file);
    while (fgets(line, sizeof(line), file)) { 
        print_character(csvLine_mapper(line));
    }

    fclose(file);


    return 0;
}