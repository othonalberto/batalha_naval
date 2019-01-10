package batalha_naval;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author othonalberto
 */
public class Navio {
    protected String nome;
    protected int energia;
    protected int i, j;
    protected String papel;
    protected ArrayList <Habilidade> habilidades;
    protected int desabilitado;
    
    public Navio(String nome, int i, int j, String papel) {
        this.nome = nome;
        this.energia = 100; //todos iniciam com energia 100
        this.i = i;
        this.j = j;
        this.papel = papel;
        this.habilidades = new ArrayList();
        this.desabilitado = 0;
        
    }
    
    public void adicionarHabilidade(Habilidade habilidade) {
        this.habilidades.add(habilidade);
    }

    public void consumirEnergia() {
        this.energia -= 5;
    };
    
    public void ganharEnergia(int quantidade) {
        if ((this.energia += quantidade) < 1001)
            this.energia += quantidade;
    }
    
    public Navio atirar(Jogador inimigo) {
        int pX = -1, pY = -1;
        String entrada;
        
        this.consumirEnergia();
        
        do {
            entrada = JOptionPane.showInputDialog("Digite a coordenada X que deseja atirar: ");
        
            try {
                pX = Integer.parseInt(entrada);
                
                if (pX == -1 || pX < 0 || pX > 13)
                    JOptionPane.showMessageDialog(null, "A coordenada deve ser um valor do intervalo de 0 a 13.");
            }
        
            catch(NumberFormatException FormatException){
                JOptionPane.showMessageDialog(null, "Formato de número inválido.");         
            }
            
        
            catch(Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        
        } while (pX == -1 || pX < 0 || pX > 13);
        
        do {
            entrada = JOptionPane.showInputDialog("Digite a coordenada Y que deseja atirar: (0 a 13) ");  
            try {
                pY = Integer.parseInt(entrada);
                
                if(pY == -1 || pY < 0 || pY > 13)
                    JOptionPane.showMessageDialog(null, "A coordenada deve ser um valor do intervalo de 0 a 13.");
            }
        
            catch(NumberFormatException FormatException){
                JOptionPane.showMessageDialog(null, "Formato de número inválido.");         
            }
            
        
            catch(Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        } while (pY == -1 || pY < 0 || pY > 13);
                
        switch (inimigo.getTabuleiro()[pX][pY].getSituacao()) {
            case '0':
                //todos navios inimigos ganham +30 de energia
                int x;
                for (x = 0; x < inimigo.getNaviosJogador().size(); x++)
                    inimigo.getNaviosJogador().get(x).ganharEnergia(30);
                break;
                
            default:
                inimigo.getTabuleiro()[pX][pY].setSituacao('0');
                inimigo.getTabuleiro()[pX][pY].setOcupante("");
                this.ganharEnergia(100);
                break;
        }
        
        return this;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }

    public ArrayList <Habilidade> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(ArrayList <Habilidade> habilidades) {
        this.habilidades = habilidades;
    }

    public int getDesabilitado() {
        return desabilitado;
    }

    public void setDesabilitado(int desabilitado) {
        this.desabilitado = desabilitado;
    }
  
}
