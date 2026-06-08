package service;
import java.util.ArrayList;
import java.util.List;

import model.Exercicio;
import model.PerfilUsuario;
import repository.BaseDeConhecimento;

public class MotorInferencia {
    private List<List<Exercicio>> cronograma;
    private List<String> prontuarioJustificativas;

    public MotorInferencia(PerfilUsuario usuario) {
        this.cronograma = BaseDeConhecimento.gerarTreinoBase(usuario);
        this.prontuarioJustificativas = new ArrayList<>();
        processarRegrasAdaptacao(usuario);
    }

    private void processarRegrasAdaptacao(PerfilUsuario usuario) {
        // [*] Hérnia de Disco (2)
        if (usuario.temRestricao(2)) {
            prontuarioJustificativas.add("Hérnia de Disco: Removida compressão vertical (eixo espinhal). Inseridos apoios de banco.");
            substituirExercicio("Agachamento Livre", "Leg Press Horizontal (Coluna Totalmente Apoiada)");
            substituirExercicio("Agachamento Hack", "Leg Press Horizontal (Angular Confortável)");
            substituirExercicio("Levantamento Terra", "Puxada Alta Pulley (Foco Dorsal Controlado)");
            substituirExercicio("Remada Curvada Barra Reta", "Remada Articulada Baixa (Suporte no Peito)");
            substituirExercicio("Stiff com Barra", "Cadeira Flexora (Tronco Retilíneo)");
            substituirExercicio("Desenvolvimento Militar Barra", "Elevação Lateral Sentado (Apoio nas costas)");
            substituirExercicio("Desenvolvimento Halter", "Elevação Lateral Sentado (Apoio nas costas)");
        }

        // [*] Condromalácia Patelar (3)
        if (usuario.temRestricao(3)) {
            prontuarioJustificativas.add("Condromalácia Patelar: Ajustada amplitude articular inferior para proteger a patela (ângulo controlado).");
            substituirExercicio("Agachamento Livre", "Leg Press Horizontal (Base de pés Alta, amplitude até 90°)");
            substituirExercicio("Cadeira Extensora", "Cadeira Extensora (Cadência Lenta, sem explosão)");
            substituirExercicio("Agachamento Hack", "Cadeira Flexora Unilateral Guiada");
        }

        // [*] Lesão no Ombro / Manguito (4)
        if (usuario.temRestricao(4)) {
            prontuarioJustificativas.add("Lesão no Ombro: Movimentos acima da linha acromial proibidos. Suplência com pegada neutra paralelo.");
            substituirExercicio("Supino Reto c/ Barra", "Chest Press Máquina (Pegada Neutra / Vertical)");
            substituirExercicio("Desenvolvimento Militar Barra", "Elevação Lateral no Cabo (Linha do Corpo)");
            substituirExercicio("Desenvolvimento Halter", "Elevação Lateral no Cabo (Linha do Corpo)");
        }

        // [*] Lesão no Pulso / Cotovelo (5)
        if (usuario.temRestricao(5)) {
            prontuarioJustificativas.add("Lesão Pulso/Cotovelo: Substituídas barras retas por halteres/cabos de pegada neutra e móvel.");
            substituirExercicio("Rosca Direta c/ Barra Reta", "Rosca Martelo com Halteres");
            substituirExercicio("Tríceps Testa c/ Barra", "Tríceps Polia com Corda");
            substituirExercicio("Tríceps Pulley Reta", "Tríceps Polia com Corda");
            substituirExercicio("Remada Sentada c/ Barra", "Remada Sentada com Triângulo (Pega Neutra)");
        }

        // [*] Hipertensão (6)
        if (usuario.temRestricao(6)) {
            prontuarioJustificativas.add("Hipertensão: Proibida Isometria Prolongada (risco de pico de pressão). Trocados por dinâmicos.");
            substituirExercicio("Prancha Abdominal", "Abdominal Supra (Dinâmico Controlado)");
        }

        // [*] Diabetes (7)
        if (usuario.temRestricao(7)) {
            prontuarioJustificativas.add("Diabetes: Monitoramento metabólico ativo. Volume balanceado contra hipoglicemia severa.");
        }

        // [*] Cardiopatias (8)
        if (usuario.temRestricao(8)) {
            prontuarioJustificativas.add("Cardiopatia: Bloqueado treinos de intensidade extrema sem descanso (Anti-HIIT).");
        }

        // INJECAO OBRIGATORIA DE CARDIO
        if (usuario.getObjetivo().equals("Emagrecimento") || usuario.getObjetivo().equals("Condicionamento")) {
            String tipoCardio = "Esteira (Caminhada Moderada)";
            String justificativaCardio = "Injeção de Cárdio obrigatória para queima calórica e otimização do VO2 máximo.";

            // [*] Obesidade Grau II/III (9)
            if (usuario.temRestricao(9)) {
                tipoCardio = "Bicicleta Horizontal Ergométrica (Zero Impacto)";
                justificativaCardio = "Obesidade: Ajustado cardio para modalidade sem impacto vertical para preservar tornozelos e joelhos.";
            }

            prontuarioJustificativas.add(justificativaCardio);
            
            for (int d = 0; d < 5; d++) {
                cronograma.get(d).add(new Exercicio(tipoCardio, "1 sessão", "20 minutos", "Fim do treino"));
            }
        }
    }

    private void substituirExercicio(String antigo, String novo) {
        for (List<Exercicio> dia : cronograma) {
            for (Exercicio ex : dia) {
                if (ex.getNome().equalsIgnoreCase(antigo)) {
                    ex.setNome(novo);
                }
            }
        }
    }

    public List<List<Exercicio>> getCronograma() { return cronograma; }
    public List<String> getProntuarioJustificativas() { return prontuarioJustificativas; }
}