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
## Exemplo de Saída

3.3 – Funcionários (após remover João):
Nome: Maria | Data Nasc.: 18/10/2000 | Salário: 2.009,44 | Função: Operador
Nome: Caio | Data Nasc.: 02/05/1961 | Salário: 9.836,14 | Função: Coordenador
Nome: Miguel | Data Nasc.: 14/10/1988 | Salário: 19.119,88 | Função: Diretor
Nome: Alice | Data Nasc.: 05/01/1995 | Salário: 2.234,68 | Função: Recepcionista
Nome: Heitor | Data Nasc.: 19/11/1999 | Salário: 1.582,72 | Função: Operador
Nome: Arthur | Data Nasc.: 31/03/1993 | Salário: 4.071,84 | Função: Contador
Nome: Laura | Data Nasc.: 08/07/1994 | Salário: 3.017,45 | Função: Gerente
Nome: Heloisa | Data Nasc.: 24/05/2003 | Salário: 1.606,85 | Função: Eletricista
Nome: Helena | Data Nasc.: 02/09/1996 | Salário: 2.799,93 | Função: Gerente

... (demais seções seguem, com total 50.906,82 após aumento)
