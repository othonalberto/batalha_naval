package batalha_naval;

import javax.swing.JOptionPane;

/**
 *
 * @author othonalberto
 */
public class Cruzador extends Navio {
    public Cruzador() {
        super("Cruzador",1, 5, "Ofensivo concentrado");
    }
    
    @Override
    public Navio atirar(Jogador inimigo) {
        if (this.getHabilidades().isEmpty())
            return super.atirar(inimigo);
        
        if (this.getHabilidades().get(0).getNome().equals("Artilharia") || this.getHabilidades().get(0).getNome().equals("Artilharia Pesada") ) {
            int pX, pY, i, j;
            String entrada, h;
            
            h = this.getHabilidades().get(0).getNome();
            pX = pY = -1;
            
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

            if (h.equals("Artilharia")) {
                    for (j = pY; j < pY+2 && j+2 < inimigo.getTabuleiro()[0].length; j++) {
                        for (i = pX; i < pX+2 && i+2 < inimigo.getTabuleiro()[0].length; i++) {
                             if (inimigo.getTabuleiro()[i][j].getSituacao() != '0') {
                                inimigo.getTabuleiro()[i][pY].setOcupante("");
                                inimigo.getTabuleiro()[i][pY].setSituacao('0');
                                this.ganharEnergia(100);
                            } else {
                                for (i = 0; i < inimigo.getNaviosJogador().size(); i++)
                                    inimigo.getNaviosJogador().get(i).ganharEnergia(30);
                            }
                        }
                    }
  
            } else {
                // Artilharia Pesada
                for (j = pY; j < pY+3 && pY+3 < inimigo.getTabuleiro()[0].length; j++) {
                        for (i = pX; i < pX+3 && pX+3 < inimigo.getTabuleiro()[0].length; i++) {
                             if (inimigo.getTabuleiro()[i][j].getSituacao() != '0') {
                                inimigo.getTabuleiro()[i][pY].setOcupante("");
                                inimigo.getTabuleiro()[i][pY].setSituacao('0');
                                this.ganharEnergia(100);
                            } else {
                                for (i = 0; i < inimigo.getNaviosJogador().size(); i++)
                                    inimigo.getNaviosJogador().get(i).ganharEnergia(30);
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
