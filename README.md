1. Classe `Pessoa` (nome, dataNascimento).
2. Classe `Funcionario` estende `Pessoa` (salario, funcao).
3. Classe `Principal` executa:
   - 3.1 Inserção dos funcionários (tabela do enunciado).
   - 3.2 Remoção do “João”.
   - 3.3 Impressão com data dd/MM/aaaa e números pt-BR.
   - 3.4 Aumento de 10% nos salários.
   - 3.5 Agrupamento por função em `Map<funcao, List<Funcionario>>`.
   - 3.6 Impressão agrupada por função.
   - 3.8 Aniversariantes dos meses 10 e 12.
   - 3.9 Funcionário com maior idade (nome e idade).
   - 3.10 Lista em ordem alfabética.
   - 3.11 Total dos salários.
   - 3.12 Salários mínimos por funcionário (R$ 1212,00).

## Requisitos
- Java 17+
- Maven 3.8+

## Como executar
```bash
mvn -q clean compile exec:java
