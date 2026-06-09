package model;
import java.util.List;

public class PerfilUsuario {
    private String genero;
    private String objetivo;
    private String nivel;
    private List<Integer> restricoes;

    public PerfilUsuario(String genero, String objetivo, String nivel, List<Integer> restricoes) {
        this.genero = genero;
        this.objetivo = objetivo;
        this.nivel = nivel;
        this.restricoes = restricoes;
    }

    public String getGenero() { return genero; }
    public String getObjetivo() { return objetivo; }
    public String getNivel() { return nivel; }
    public List<Integer> getRestricoes() { return restricoes; }

    public boolean temRestricao(int codigo) {
        return restricoes.contains(codigo);
    }
}