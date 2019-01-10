package batalha_naval;

/**
 *
 * @author othonalberto
 */
public class Tabuleiro {
    private char situacao; // 0 = livre; 1 = ocupado; 2 = bloqueado
    private String ocupante;

    public Tabuleiro (){
        this.situacao = '0';
        this.ocupante = "";
    }
    
    public char getSituacao() {
        return situacao;
    }

    public void setSituacao(char situacao) {
        this.situacao = situacao;
    }

    public String getOcupante() {
        return ocupante;
    }

    public void setOcupante(String ocupante) {
        this.ocupante = ocupante;
    }
}
