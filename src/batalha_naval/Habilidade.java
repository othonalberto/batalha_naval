package batalha_naval;

/**
 *
 * @author othonalberto
 */
public class Habilidade {
    private final String nome;
    private final String descricao;
    
    public Habilidade(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
        
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
