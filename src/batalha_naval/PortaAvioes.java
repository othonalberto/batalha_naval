package batalha_naval;

import javax.swing.JOptionPane;

/**
 *
 * @author othonalberto
 */
public class PortaAvioes extends Navio {
    public PortaAvioes() {
        super("Porta Aviões", 2, 6, "Ofensivo e Estratégico");
    }
    
    @Override
    public Navio atirar(Jogador inimigo) {
        if (this.getHabilidades().isEmpty())
            return super.atirar(inimigo);
        
        if (this.getHabilidades().get(0).getNome().equals("Bombardeio Linear") || this.getHabilidades().get(0).getNome().equals("Pulso Eletromagnético") ) {
            int pX, pY, i, j, aux;
            String entrada, h;
            
            pX = pY = -1;
            aux = 0;
            h = this.getHabilidades().get(0).getNome();
            
            this.consumirEnergia();

            do {
                entrada = JOptionPane.showInputDialog("Digite a coordenada X que deseja atirar (0 a 13)");

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

            if (h.equals("Bombardeio Linear")) {
                j = 0;
                for (i = pX; i < inimigo.getTabuleiro()[0].length && j < 8; i++) {
                     if (inimigo.getTabuleiro()[i][pY].getSituacao() != '0') {
                        inimigo.getTabuleiro()[i][pY].setSituacao('0');
                        inimigo.getTabuleiro()[i][pY].setOcupante("");
                        j++;
                    } else {
                        for (aux = 0; aux < inimigo.getNaviosJogador().size(); aux++)
                            inimigo.getNaviosJogador().get(aux).ganharEnergia(30);
                        
                        j++;
                    }
                }
            } else {
                // Pulso Eletromagnético
                for (j = 0; j < inimigo.getNaviosJogador().size(); j++) {
                    for (i = 0; i < inimigo.getNaviosJogador().size(); i++) {
                       inimigo.getNaviosJogador().get(i).setDesabilitado(2);
                    }
                }
                
                
            }
            this.habilidades.remove(0);
        }
        
        else
            return super.atirar(inimigo);
       
        return this;
        
    }
}
