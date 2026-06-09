package model;
public class Exercicio {
    private String nome;
    private String series;
    private String reps;
    private String descanso;

    public Exercicio(String nome, String series, String reps, String descanso) {
        this.nome = nome;
        this.series = series;
        this.reps = reps;
        this.descanso = descanso;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getSeries() { return series; }
    public void setSeries(String series) { this.series = series; }
    public String getReps() { return reps; }
    public void setReps(String reps) { this.reps = reps; }
    public String getDescanso() { return descanso; }
    public void setDescanso(String descanso) { this.descanso = descanso; }

    @Override
    public String toString() {
        return nome + " | " + series + " x " + reps + " | Descanso: " + descanso;
    }
}