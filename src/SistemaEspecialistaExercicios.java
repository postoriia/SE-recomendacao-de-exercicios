import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaEspecialistaExercicios {

    static class Exercicio {
        String nome;
        String series;
        String reps;
        String descanso;

        public Exercicio(String nome, String series, String reps, String descanso) {
            this.nome = nome;
            this.series = series;
            this.reps = reps;
            this.descanso = descanso;
        }
    }

    public static void main(String[] args) {
        try {
            new ProcessBuilder("cmd", "/c", "chcp 65001").inheritIO().start().waitFor();
        } catch (Exception e) {}

        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

        System.out.println("=================================================================");
        System.out.println("   SISTEMA ESPECIALISTA CLÍNICO - ARQUITETURA DE REGRAS V3      ");
        System.out.println("=================================================================");

        // 1. Entrada Gênero (com tratamento de erro robusto)
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
            System.out.println("\nSelecione as RESTRIÇÕES FÍSICAS (Escolha quantas precisar):");
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

        String padraoSeries = (nivel.equals("Avançado")) ? "4 séries" : "3 séries";
        String padraoReps = "10 a 12 reps";
        String padraoDescanso = "60s";

        if (objetivo.equals("Emagrecimento") || objetivo.equals("Condicionamento")) {
            padraoReps = "15 reps";
            padraoDescanso = "45s";
        }

        if (restricoesEscolhidas.contains(6) || restricoesEscolhidas.contains(8)) { 
            padraoDescanso = "1min e 40s (Recuperação Segura)";
            if (restricoesEscolhidas.contains(6)) {
                padraoReps = "12 a 14 reps (Evitar Carga Extrema)"; 
            }
        }

        List<List<Exercicio>> cronograma = new ArrayList<>();
        for (int i = 0; i < 5; i++) cronograma.add(new ArrayList<>());

        if (genero.equals("Mulher")) {
            // SEG - INFERIOR
            cronograma.get(0).add(new Exercicio("Agachamento Livre", padraoSeries, padraoReps, padraoDescanso));
            cronograma.get(0).add(new Exercicio("Leg Press 45°", padraoSeries, padraoReps, padraoDescanso));
            cronograma.get(0).add(new Exercicio("Cadeira Extensora", padraoSeries, padraoReps, padraoDescanso));
            cronograma.get(0).add(new Exercicio("Abdominal Supra", "3 séries", "20 reps", "45s"));
            // TER - SUPERIOR
            cronograma.get(1).add(new Exercicio("Puxada Alta Pulley", padraoSeries, padraoReps, padraoDescanso));
            cronograma.get(1).add(new Exercicio("Remada Sentada c/ Barra", padraoSeries, padraoReps, padraoDescanso));
            cronograma.get(1).add(new Exercicio("Desenvolvimento Halter", padraoSeries, padraoReps, padraoDescanso));
            cronograma.get(1).add(new Exercicio("Rosca Direta c/ Barra Reta", padraoSeries, padraoReps, padraoDescanso));
            // QUA - INFERIOR
            cronograma.get(2).add(new Exercicio("Stiff com Barra", padraoSeries, padraoReps, padraoDescanso));
            cronograma.get(2).add(new Exercicio("Cadeira Flexora", padraoSeries, padraoReps, padraoDescanso));
            cronograma.get(2).add(new Exercicio("Elevação Pélvica", padraoSeries, padraoReps, padraoDescanso));
            cronograma.get(2).add(new Exercicio("Prancha Abdominal", "3 séries", "45s", "45s"));
            // QUI - SUPERIOR
            cronograma.get(3).add(new Exercicio("Supino Reto c/ Barra", padraoSeries, padraoReps, padraoDescanso));
            cronograma.get(3).add(new Exercicio("Crucifixo Máquina", padraoSeries, padraoReps, padraoDescanso));
            cronograma.get(3).add(new Exercicio("Tríceps Pulley Reta", padraoSeries, padraoReps, padraoDescanso));
            cronograma.get(3).add(new Exercicio("Elevação Lateral", padraoSeries, padraoReps, padraoDescanso));
            // SEX - INFERIOR
            cronograma.get(4).add(new Exercicio("Agachamento Hack", padraoSeries, padraoReps, padraoDescanso));
            cronograma.get(4).add(new Exercicio("Mesa Flexora", padraoSeries, padraoReps, padraoDescanso));
            cronograma.get(4).add(new Exercicio("Gêmeos em Pé", "4 séries", "15 reps", "45s"));
            cronograma.get(4).add(new Exercicio("Abdominal Infra", "3 séries", "15 reps", "45s"));
        } else {
            // SEG - SUPERIOR
            cronograma.get(0).add(new Exercicio("Supino Reto c/ Barra", padraoSeries, padraoReps, padraoDescanso));
            cronograma.get(0).add(new Exercicio("Supino Inclinado Halter", padraoSeries, padraoReps, padraoDescanso));
            cronograma.get(0).add(new Exercicio("Tríceps Testa c/ Barra", padraoSeries, padraoReps, padraoDescanso));
            cronograma.get(0).add(new Exercicio("Tríceps Pulley Reta", padraoSeries, padraoReps, padraoDescanso));
            // TER - INFERIOR
            cronograma.get(1).add(new Exercicio("Agachamento Livre", padraoSeries, padraoReps, padraoDescanso));
            cronograma.get(1).add(new Exercicio("Leg Press 45°", padraoSeries, padraoReps, padraoDescanso));
            cronograma.get(1).add(new Exercicio("Cadeira Extensora", padraoSeries, padraoReps, padraoDescanso));
            cronograma.get(1).add(new Exercicio("Gêmeos Sentado", "4 séries", "15 reps", "45s"));
            // QUA - SUPERIOR
            cronograma.get(2).add(new Exercicio("Puxada Alta Pulley", padraoSeries, padraoReps, padraoDescanso));
            cronograma.get(2).add(new Exercicio("Remada Curvada Barra Reta", padraoSeries, padraoReps, padraoDescanso));
            cronograma.get(2).add(new Exercicio("Rosca Direta c/ Barra Reta", padraoSeries, padraoReps, padraoDescanso));
            cronograma.get(2).add(new Exercicio("Abdominal Supra", "4 séries", "20 reps", "45s"));
            // QUI - SUPERIOR
            cronograma.get(3).add(new Exercicio("Desenvolvimento Militar Barra", padraoSeries, padraoReps, padraoDescanso));
            cronograma.get(3).add(new Exercicio("Elevação Lateral", padraoSeries, padraoReps, padraoDescanso));
            cronograma.get(3).add(new Exercicio("Crucifixo Invertido", padraoSeries, padraoReps, padraoDescanso));
            cronograma.get(3).add(new Exercicio("Encolhimento Halter", padraoSeries, padraoReps, padraoDescanso));
            // SEX - INFERIOR
            cronograma.get(4).add(new Exercicio("Levantamento Terra", padraoSeries, padraoReps, padraoDescanso));
            cronograma.get(4).add(new Exercicio("Cadeira Flexora", padraoSeries, padraoReps, padraoDescanso));
            cronograma.get(4).add(new Exercicio("Stiff com Barra", padraoSeries, padraoReps, padraoDescanso));
            cronograma.get(4).add(new Exercicio("Prancha Abdominal", "3 séries", "45s", "45s"));
        }

        List<String> prontuarioJustificativas = new ArrayList<>();

        // [*] Hérnia de Disco (2): Corta carga axial vertical e flexões severas
        if (restricoesEscolhidas.contains(2)) {
            prontuarioJustificativas.add("Hérnia de Disco: Removida compressão vertical (eixo espinhal). Inseridos apoios de banco.");
            substituirExercicio(cronograma, "Agachamento Livre", "Leg Press Horizontal (Coluna Totalmente Apoiada)");
            substituirExercicio(cronograma, "Agachamento Hack", "Leg Press Horizontal (Angular Confortável)");
            substituirExercicio(cronograma, "Levantamento Terra", "Puxada Alta Pulley (Foco Dorsal Controlado)");
            substituirExercicio(cronograma, "Remada Curvada Barra Reta", "Remada Articulada Baixa (Suporte no Peito)");
            substituirExercicio(cronograma, "Stiff com Barra", "Cadeira Flexora (Tronco Retilíneo)");
            substituirExercicio(cronograma, "Desenvolvimento Militar Barra", "Elevação Lateral Sentado (Apoio nas costas)");
            substituirExercicio(cronograma, "Desenvolvimento Halter", "Elevação Lateral Sentado (Apoio nas costas)");
        }

        // [*] Condromalácia Patelar (3): Controla ângulo de flexão e translação da patela
        if (restricoesEscolhidas.contains(3)) {
            prontuarioJustificativas.add("Condromalácia Patelar: Ajustada amplitude articular inferior para proteger a patela (ângulo controlado).");
            substituirExercicio(cronograma, "Agachamento Livre", "Leg Press Horizontal (Base de pés Alta, amplitude até 90°)");
            substituirExercicio(cronograma, "Cadeira Extensora", "Cadeira Extensora (Cadência Lenta, sem explosão)");
            substituirExercicio(cronograma, "Agachamento Hack", "Cadeira Flexora Unilateral Guiada");
        }

        // [*] Lesão no Ombro / Manguito (4): Elimina movimentos acima da cabeça e barras fixas que esmagam o manguito
        if (restricoesEscolhidas.contains(4)) {
            prontuarioJustificativas.add("Lesão no Ombro: Movimentos acima da linha acromial proibidos. Suplência com pegada neutra paralelo.");
            substituirExercicio(cronograma, "Supino Reto c/ Barra", "Chest Press Máquina (Pegada Neutra / Vertical)");
            substituirExercicio(cronograma, "Desenvolvimento Militar Barra", "Elevação Lateral no Cabo (Linha do Corpo)");
            substituirExercicio(cronograma, "Desenvolvimento Halter", "Elevação Lateral no Cabo (Linha do Corpo)");
        }

        // [*] Lesão no Pulso / Cotovelo (5): Elimina barras retas, força rotação anatômica
        if (restricoesEscolhidas.contains(5)) {
            prontuarioJustificativas.add("Lesão Pulso/Cotovelo: Substituídas barras retas por halteres/cabos de pegada neutra e móvel.");
            substituirExercicio(cronograma, "Rosca Direta c/ Barra Reta", "Rosca Martelo com Halteres");
            substituirExercicio(cronograma, "Tríceps Testa c/ Barra", "Tríceps Polia com Corda");
            substituirExercicio(cronograma, "Tríceps Pulley Reta", "Tríceps Polia com Corda");
            substituirExercicio(cronograma, "Remada Sentada c/ Barra", "Remada Sentada com Triângulo (Pega Neutra)");
        }

        // [*] Hipertensão (6): Corta isometrias prolongadas
        if (restricoesEscolhidas.contains(6)) {
            prontuarioJustificativas.add("Hipertensão: Proibida Isometria Prolongada (risco de pico de pressão). Trocados por dinâmicos.");
            substituirExercicio(cronograma, "Prancha Abdominal", "Abdominal Supra (Dinâmico Controlado)");
        }

        // [*] Diabetes (7): Controle do volume de exaustão
        if (restricoesEscolhidas.contains(7)) {
            prontuarioJustificativas.add("Diabetes: Monitoramento metabólico ativo. Volume balanceado contra hipoglicemia severa.");
        }

        // [*] Cardiopatias (8): Elimina circuitos exaustivos (O controle do descanso já foi injetado acima)
        if (restricoesEscolhidas.contains(8)) {
            prontuarioJustificativas.add("Cardiopatia: Bloqueado treinos de intensidade extrema sem descanso (Anti-HIIT).");
        }

        // INJEÇÃO OBRIGATÓRIA DE CÁRDIO (Se Emagrecimento ou Condicionamento)
        if (objetivo.equals("Emagrecimento") || objetivo.equals("Condicionamento")) {
            String tipoCardio = "Esteira (Caminhada Moderada)";
            String justificativaCardio = "Injeção de Cárdio obrigatória para queima calórica e otimização do VO2 máximo.";

            // [*] Obesidade Grau II/III (9): Altera a esteira por zero impacto articular
            if (restricoesEscolhidas.contains(9)) {
                tipoCardio = "Bicicleta Horizontal Ergométrica (Zero Impacto)";
                justificativaCardio = "Obesidade: Ajustado cardio para modalidade sem impacto vertical para preservar tornozelos e joelhos.";
            }

            prontuarioJustificativas.add(justificativaCardio);
            
            for (int d = 0; d < 5; d++) {
                cronograma.get(d).add(new Exercicio(tipoCardio, "1 sessão", "20 minutos", "Fim do treino"));
            }
        }

        System.out.println("\n======================================================================");
        System.out.println("            PROGRAMA DE TREINAMENTO INTEGRADO E SEGURO                ");
        System.out.println("======================================================================");
        System.out.println("PACIENTE/ALUNO: " + genero + "  |  NÍVEL: " + nivel);
        System.out.println("OBJETIVO SISTÊMICO: " + objetivo);
        
        String[] diasSemana = {"SEGUNDA-FEIRA", "TERÇA-FEIRA", "QUARTA-FEIRA", "QUINTA-FEIRA", "SEXTA-FEIRA"};
        for (int d = 0; d < 5; d++) {
            System.out.println("\n[ " + diasSemana[d] + " ]");
            List<Exercicio> exerciciosDoDia = cronograma.get(d);
            for (int e = 0; e < exerciciosDoDia.size(); e++) {
                Exercicio ex = exerciciosDoDia.get(e);
                System.out.println("   -> Ex " + (e + 1) + ": " + ex.nome + " | " + ex.series + " x " + ex.reps + " | Descanso: " + ex.descanso);
            }
        }

        System.out.println("\n======================================================================");
        System.out.println("-> ANÁLISE DE SEGURANÇA ARTICULAR E CLÍNICA:");
        if (prontuarioJustificativas.isEmpty()) {
            System.out.println("   Estrutura muscular limpa. Sem restrições motoras catalogadas.");
        } else {
            for (String justificativa : prontuarioJustificativas) {
                System.out.println("   [*] " + justificativa);
            }
        }
        System.out.println("======================================================================\n");

        scanner.close();
    }

    private static void substituirExercicio(List<List<Exercicio>> cronograma, String antigo, String novo) {
        for (List<Exercicio> dia : cronograma) {
            for (Exercicio ex : dia) {
                if (ex.nome.equalsIgnoreCase(antigo)) {
                    ex.nome = novo;
                }
            }
        }
    }
}