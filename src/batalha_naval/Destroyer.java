package batalha_naval;

import javax.swing.JOptionPane;

/**
 *
 * @author othonalberto
 */
public class Destroyer extends Navio {
    public Destroyer() {
        super("Destroyer", 1, 4, "Inteligência e Anti-Submarino");
    }
    
    @Override
    public Navio atirar(Jogador inimigo) {
        if (this.getHabilidades().isEmpty())
            return super.atirar(inimigo);
        
        if (this.getHabilidades().get(0).getNome().equals("Cargas de Profundidade") || this.getHabilidades().get(0).getNome().equals("Radar") ) {
            int pX, pY, i, j, aux;
            String entrada, h;
            
            pX = pY = -1;
            aux = 0;
            h = this.getHabilidades().get(0).getNome();
            
            this.consumirEnergia();

            do {
                entrada = JOptionPane.showInputDialog("Digite a coordenada X que deseja atirar: (0 a 13)");

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

            if (h.equals("Cargas de Profundidade")) {
                    // atinge submarinos
                    for (j = pY; j < inimigo.getTabuleiro()[0].length; j++) {
                        for (i = pX; i < inimigo.getTabuleiro()[0].length; i++) {
                            if (inimigo.getTabuleiro()[i][j].getOcupante().equals("Submarino")) {
                                inimigo.getTabuleiro()[i][j].setOcupante("");
                                inimigo.getTabuleiro()[i][j].setSituacao('0');
                                this.ganharEnergia(100);
                            } else {
                                for (aux = 0; aux < inimigo.getNaviosJogador().size(); aux++)
                                    inimigo.getNaviosJogador().get(aux).ganharEnergia(30);
                            }
                        }
                    }
  
            } else {
                // Radar encontra navios numa area mas nao encontra submarinos
                for (j = pY; j < inimigo.getTabuleiro()[0].length-3; j++) {
                        for (i = pY; i < inimigo.getTabuleiro()[0].length-5; i++) {
                            if (!inimigo.getTabuleiro()[i][j].getOcupante().equals("Submarino")) {
                                if (inimigo.getTabuleiro()[i][j].getSituacao() != '0') {
                                    JOptionPane.showMessageDialog(null, inimigo.getTabuleiro()[i][j].getOcupante() + " inimigo em: ["+i+"]["+j+"].");
                                }
                            }           
                        }
                }
                
            }
            
            this.habilidades.remove(0);
        }
        
        else
            return (Submarino) super.atirar(inimigo);
       
        return this;
        
    }
}
