import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Exercicio;
import model.PerfilUsuario;
import service.MotorInferencia;

public class main {
    public static void main(String[] args) {
        // fix do gemini pra erro de formatação UTF
        // Força o terminal do Windows (cmd) a usar a página de código UTF-8 (60001) antes de rodar
        try {
            new ProcessBuilder("cmd", "/c", "chcp 65001").inheritIO().start().waitFor();
        } catch (Exception e) {}

        // Configura as saídas e entradas do Java explicitamente para UTF-8
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

        System.out.println("=================================================================");
        System.out.println("   SISTEMA ESPECIALISTA CLÍNICO - ARQUITETURA DE REGRAS V3      ");
        System.out.println("=================================================================");

        int generoOpcao = 0;
        while (true) {
            System.out.println("\nSelecione o GÊNERO biológico:");
            System.out.println("1 - Mulher (Frequência 3 Inferiores / 2 Superiores)");
            System.out.println("2 - Homem  (Frequência 2 Inferiores / 3 Superiores)");
            System.out.print("Digite o número: ");
            String entrada = scanner.nextLine().trim();
            try {
                generoOpcao = Integer.parseInt(entrada);
                if (generoOpcao == 1 || generoOpcao == 2) break;
                System.out.println("[ERRO]: Opção inválida! Digite apenas 1 ou 2.");
            } catch (NumberFormatException e) {
                System.out.println("[ERRO]: Entrada inválida! Não digite letras ou símbolos. Use apenas números.");
            }
        }
        String genero = (generoOpcao == 1) ? "Mulher" : "Homem";

        int objOpcao = 0;
        while (true) {
            System.out.println("\nSelecione o seu OBJETIVO PRINCIPAL:");
            System.out.println("1 - Emagrecimento (Injeção de Cárdio obrigatória)");
            System.out.println("2 - Ganho de Massa");
            System.out.println("3 - Condicionamento (Injeção de Cárdio obrigatória)");
            System.out.println("4 - Flexibilidade");
            System.out.print("Digite o número: ");
            String entrada = scanner.nextLine().trim();
            try {
                objOpcao = Integer.parseInt(entrada);
                if (objOpcao >= 1 && objOpcao <= 4) break;
                System.out.println("[ERRO]: Opção inválida! Escolha de 1 a 4.");
            } catch (NumberFormatException e) {
                System.out.println("[ERRO]: Entrada inválida! Por favor, digite um número.");
            }
        }
        String objetivo = (objOpcao == 1) ? "Emagrecimento" : (objOpcao == 2) ? "Ganho de Massa" : (objOpcao == 3) ? "Condicionamento" : "Flexibilidade";

        int nivelOpcao = 0;
        while (true) {
            System.out.println("\nSelecione o seu NÍVEL DE EXPERIÊNCIA:");
            System.out.println("1 - Iniciante\n2 - Intermediário\n3 - Avançado");
            System.out.print("Digite o número: ");
            String entrada = scanner.nextLine().trim();
            try {
                nivelOpcao = Integer.parseInt(entrada);
                if (nivelOpcao >= 1 && nivelOpcao <= 3) break;
                System.out.println("[ERRO]: Opção inválida! Escolha de 1 a 3.");
            } catch (NumberFormatException e) {
                System.out.println("[ERRO]: Entrada inválida! Por favor, digite um número.");
            }
        }
        String nivel = (nivelOpcao == 1) ? "Iniciante" : (nivelOpcao == 3) ? "Avançado" : "Intermediário";

        List<Integer> restricoesEscolhidas = new ArrayList<>();
        while (true) {
            System.out.println("\nVocê possui alguma RESTRIÇÃO FÍSICA ou LESÃO? (Ex: problemas de joelho, coluna, etc.):");
            System.out.println("1 - Nenhuma (Sem restrições/Saudável)");
            System.out.println("2 - Hérnia de Disco / Dor Lombar Crônica");
            System.out.println("3 - Condromalácia Patelar / Dor no Joelho");
            System.out.println("4 - Lesão ou Dor no Ombro (Manguito Rotador)");
            System.out.println("5 - Lesão no Pulso / Cotovelo (Tendinite)");
            System.out.println("6 - Hipertensão (Pressão Alta)");
            System.out.println("7 - Diabetes");
            System.out.println("8 - Cardiopatias (Problemas no Coração)");
            System.out.println("9 - Obesidade Grau II ou III");
            System.out.println("10 - [CONCLUÍDO] Gerar treino adaptado");
            System.out.print("Escolha uma opção por vez: ");
            
            String entrada = scanner.nextLine().trim();
            int esc = 0;
            try {
                esc = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("[ERRO]: Entrada inválida! Digite apenas o número da opção desejada.");
                continue;
            }

            if (esc == 1) {
                restricoesEscolhidas.clear();
                restricoesEscolhidas.add(1);
                break;
            }
            if (esc == 10) {
                if (restricoesEscolhidas.isEmpty()) restricoesEscolhidas.add(1);
                break;
            }
            if (esc >= 2 && esc <= 9) {
                if (!restricoesEscolhidas.contains(esc)) {
                    restricoesEscolhidas.add(esc);
                    System.out.println("-> Restrição cadastrada!");
                } else {
                    System.out.println("-> Esta restrição já foi adicionada anteriormente.");
                }
            } else {
                System.out.println("[ERRO]: Opção fora do limite permitido (1 a 10).");
            }

        }

        PerfilUsuario usuario = new PerfilUsuario(genero, objetivo, nivel, restricoesEscolhidas);
        MotorInferencia motor = new MotorInferencia(usuario);

        System.out.println("======================================================================");
        System.out.println("   -> INDICAÇÃO DE EXERCÍCIOS / SISTEMA ESPECIALISTA - PRESCRIÇÃO     ");
        System.out.println("======================================================================");
        System.out.println("PACIENTE/ALUNO: " + usuario.getGenero() + "  |  NÍVEL: " + usuario.getNivel());
        System.out.println("OBJETIVO SISTÊMICO: " + usuario.getObjetivo());
        
        String[] diasSemana = {"SEGUNDA-FEIRA", "TERÇA-FEIRA", "QUARTA-FEIRA", "QUINTA-FEIRA", "SEXTA-FEIRA"};
        List<List<Exercicio>> cronogramaFinal = motor.getCronograma();

        for (int d = 0; d < 5; d++) {
            System.out.println("\n[ " + diasSemana[d] + " ]");
            List<Exercicio> exerciciosDoDia = cronogramaFinal.get(d);
            for (int e = 0; e < exerciciosDoDia.size(); e++) {
                Exercicio ex = exerciciosDoDia.get(e);
                System.out.println("   -> Ex " + (e + 1) + ": " + ex.getNome() + " | " + ex.getSeries() + " x " + ex.getReps() + " | Descanso: " + ex.getDescanso());
            }
        }

        System.out.println("\n======================================================================");
        System.out.println("-> ANÁLISE DE SEGURANÇA ARTICULAR E CLÍNICA:");
        List<String> justificativas = motor.getProntuarioJustificativas();

        if (justificativas.isEmpty()) {
            System.out.println("   Estrutura muscular limpa. Sem restrições motoras catalogadas.");
        } else {
            for (String justificativa : justificativas) {
                System.out.println("   [*] " + justificativa);
            }
        }
        System.out.println("======================================================================\n");

        scanner.close();
    }
}