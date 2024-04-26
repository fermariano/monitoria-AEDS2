# Álgebra Booleana em Java

Este é um programa em Java que realiza operações de álgebra booleana em expressões lógicas fornecidas pelo usuário. Ele suporta operadores lógicos AND (^), OR (V) e NOT (!), além de parênteses para definir a precedência das operações.

## Funcionalidades

- Leitura de expressões lógicas fornecidas pelo usuário.
- Cálculo das operações lógicas (AND, OR, NOT) na expressão.
- Substituição dos operadores por símbolos para facilitar a leitura (AND: ^, OR: V, NOT: !).
- Aceitação de variáveis A, B e C para representar valores booleanos.
- Flexibilidade para adicionar mais variáveis e operadores conforme necessário.

## Como Utilizar

1. Clone ou faça o download deste repositório para o seu computador.
2. Compile o arquivo `AlgebraBool.java` utilizando o Java Compiler (`javac AlgebraBool.java`).
3. Execute o programa compilado com o comando `java AlgebraBool`.
4. Digite uma expressão lógica quando solicitado e pressione Enter para ver o resultado.

## Exemplos de Entradas

1. `2 0 1 not(and(A , B))`
2. `2 1 0 not(and(A , B))`
3. `2 1 1 not(and(A , B))`
4. `3 0 0 0 and(or(A , B) , not(and(B , C)))`
5. `3 0 0 1 and(or(A , B) , not(and(B , C)))`
6. `3 0 1 0 and(or(A , B) , not(and(B , C)))`
7. `3 0 1 1 and(or(A , B) , not(and(B , C)))`
8. `3 1 0 0 and(or(A , B) , not(and(B , C)))`
9. `3 1 0 1 and(or(A , B) , not(and(B , C)))`
10. `3 1 1 0 and(or(A , B) , not(and(B , C)))`
11. `3 1 1 1 and(or(A , B) , not(and(B , C)))`

## Contribuindo

Sinta-se à vontade para contribuir com este projeto! Se encontrar algum bug ou tiver sugestões de melhorias, por favor, abra uma issue ou envie um pull request.

## Autores

Este programa foi desenvolvido por Luis Phillip

