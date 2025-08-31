package br.com.teste;

import br.com.teste.model.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Principal {

    // Formatação pedida: data dd/MM/aaaa e números com "." milhar e "," decimal
    private static final DateTimeFormatter FMT_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DecimalFormatSymbols DFS = new DecimalFormatSymbols(new Locale("pt", "BR"));
    private static final DecimalFormat FMT_NUM;
    static {
        DFS.setGroupingSeparator('.');
        DFS.setDecimalSeparator(',');
        FMT_NUM = new DecimalFormat("#,##0.00", DFS);
    }

    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");

    public static void main(String[] args) {
        // 3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela
        List<Funcionario> funcionarios = carregarFuncionariosIniciais();

        // 3.2 – Remover o funcionário “João”
        for (int i = 0; i < funcionarios.size(); i++) {
            if ("João".equals(funcionarios.get(i).getNome())) {
                funcionarios.remove(i);
                break;
            }
        }

        // 3.3 – Imprimir todos os funcionários com as formatações pedidas
        System.out.println("3.3 – Funcionários (após remover João):");
        imprimirFuncionarios(funcionarios);

        // 3.4 – Aumento de 10% nos salários
        aplicarAumento(funcionarios, new BigDecimal("0.10"));

        // 3.5 – Agrupar por função em um MAP (chave = função, valor = lista de funcionários)
        Map<String, List<Funcionario>> porFuncao = agruparPorFuncao(funcionarios);

        // 3.6 – Imprimir os funcionários agrupados por função
        System.out.println("\n3.6 – Funcionários agrupados por função:");
        List<String> funcoes = new ArrayList<>(porFuncao.keySet());
        Collections.sort(funcoes, String.CASE_INSENSITIVE_ORDER);
        for (String funcao : funcoes) {
            System.out.println("- " + funcao + ":");
            imprimirFuncionarios(porFuncao.get(funcao));
        }

        // 3.8 – Aniversariantes dos meses 10 e 12
        System.out.println("\n3.8 – Aniversariantes dos meses 10 e 12:");
        for (Funcionario f : funcionarios) {
            int mes = f.getDataNascimento().getMonthValue();
            if (mes == 10 || mes == 12) {
                imprimirFuncionario(f);
            }
        }

        // 3.9 – Funcionário com maior idade (nome e idade)
        System.out.println("\n3.9 – Funcionário com maior idade:");
        if (!funcionarios.isEmpty()) {
            Funcionario maisVelho = funcionarios.get(0);
            for (Funcionario f : funcionarios) {
                if (f.getDataNascimento().isBefore(maisVelho.getDataNascimento())) {
                    maisVelho = f;
                }
            }
            int idade = Period.between(maisVelho.getDataNascimento(), LocalDate.now()).getYears();
            System.out.println("Nome: " + maisVelho.getNome() + " | Idade: " + idade + " anos");
        }

        // 3.10 – Imprimir a lista por ordem alfabética
        System.out.println("\n3.10 – Funcionários em ordem alfabética:");
        List<Funcionario> ordenados = new ArrayList<>(funcionarios);
        Collections.sort(ordenados, new Comparator<Funcionario>() {
            @Override
            public int compare(Funcionario a, Funcionario b) {
                return a.getNome().compareToIgnoreCase(b.getNome());
            }
        });
        imprimirFuncionarios(ordenados);

        // 3.11 – Total dos salários dos funcionários (após aumento)
        System.out.println("\n3.11 – Total dos salários (após aumento):");
        BigDecimal total = BigDecimal.ZERO;
        for (Funcionario f : funcionarios) {
            total = total.add(f.getSalario());
        }
        System.out.println("Total: " + FMT_NUM.format(total));

        // 3.12 – Quantos salários mínimos ganha cada funcionário (salário mínimo R$ 1212,00)
        System.out.println("\n3.12 – Salários mínimos por funcionário:");
        for (Funcionario f : funcionarios) {
            BigDecimal qtd = f.getSalario().divide(SALARIO_MINIMO, 2, RoundingMode.HALF_UP);
            System.out.println(f.getNome() + " -> " + FMT_NUM.format(qtd) + " salários mínimos");
        }
    }

    // ====== Métodos auxiliares ======

    private static List<Funcionario> carregarFuncionariosIniciais() {
        List<Funcionario> list = new ArrayList<>();
        // Tabela do enunciado (Heloisa sem acento)
        list.add(new Funcionario("Maria",   LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"),  "Operador"));
        list.add(new Funcionario("João",    LocalDate.of(1990, 5, 12),  new BigDecimal("2284.38"),  "Operador"));
        list.add(new Funcionario("Caio",    LocalDate.of(1961, 5, 2),   new BigDecimal("9836.14"),  "Coordenador"));
        list.add(new Funcionario("Miguel",  LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        list.add(new Funcionario("Alice",   LocalDate.of(1995, 1, 5),   new BigDecimal("2234.68"),  "Recepcionista"));
        list.add(new Funcionario("Heitor",  LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"),  "Operador"));
        list.add(new Funcionario("Arthur",  LocalDate.of(1993, 3, 31),  new BigDecimal("4071.84"),  "Contador"));
        list.add(new Funcionario("Laura",   LocalDate.of(1994, 7, 8),   new BigDecimal("3017.45"),  "Gerente"));
        list.add(new Funcionario("Heloisa", LocalDate.of(2003, 5, 24),  new BigDecimal("1606.85"),  "Eletricista"));
        list.add(new Funcionario("Helena",  LocalDate.of(1996, 9, 2),   new BigDecimal("2799.93"),  "Gerente"));
        return list;
    }

    private static void aplicarAumento(List<Funcionario> funcionarios, BigDecimal percentual) {
        BigDecimal fator = BigDecimal.ONE.add(percentual);
        for (Funcionario f : funcionarios) {
            BigDecimal novoSalario = f.getSalario().multiply(fator).setScale(2, RoundingMode.HALF_UP);
            f.setSalario(novoSalario);
        }
    }

    private static Map<String, List<Funcionario>> agruparPorFuncao(List<Funcionario> funcionarios) {
        Map<String, List<Funcionario>> mapa = new HashMap<>();
        for (Funcionario f : funcionarios) {
            String funcao = f.getFuncao();
            List<Funcionario> lista = mapa.get(funcao);
            if (lista == null) {
                lista = new ArrayList<>();
                mapa.put(funcao, lista);
            }
            lista.add(f);
        }
        return mapa;
    }

    private static void imprimirFuncionarios(List<Funcionario> funcionarios) {
        for (Funcionario f : funcionarios) {
            imprimirFuncionario(f);
        }
    }

    private static void imprimirFuncionario(Funcionario f) {
        String linha = "Nome: " + f.getNome()
                + " | Data Nasc.: " + FMT_DATA.format(f.getDataNascimento())
                + " | Salário: " + FMT_NUM.format(f.getSalario())
                + " | Função: " + f.getFuncao();
        System.out.println(linha);
    }
}
