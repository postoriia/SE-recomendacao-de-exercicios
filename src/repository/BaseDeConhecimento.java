package repository;
import java.util.ArrayList;
import java.util.List;

import model.Exercicio;
import model.PerfilUsuario;

public class BaseDeConhecimento {

    public static List<List<Exercicio>> gerarTreinoBase(PerfilUsuario usuario) {
        List<List<Exercicio>> cronograma = new ArrayList<>();
        for (int i = 0; i < 5; i++) cronograma.add(new ArrayList<>());

        String padraoSeries = (usuario.getNivel().equals("Avançado")) ? "4 séries" : "3 séries";
        String padraoReps = "10 a 12 reps";
        String padraoDescanso = "60s";

        if (usuario.getObjetivo().equals("Emagrecimento") || usuario.getObjetivo().equals("Condicionamento")) {
            padraoReps = "15 reps";
            padraoDescanso = "45s";
        }

        if (usuario.temRestricao(6) || usuario.temRestricao(8)) { 
            padraoDescanso = "1min e 40s (Recuperação Segura)";
            if (usuario.temRestricao(6)) {
                padraoReps = "12 a 14 reps (Evitar Carga Extrema)"; 
            }
        }

        if (usuario.getGenero().equals("Mulher")) {
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

        return cronograma;
    }
}