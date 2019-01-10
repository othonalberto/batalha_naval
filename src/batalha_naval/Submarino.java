package batalha_naval;

import javax.swing.JOptionPane;

/**
 *
 * @author othonalberto
 */
public class Submarino extends Navio {
    public Submarino() {
        super("Submarino", 1, 3, "Ofensivo em áreas específicas e Inteligência");   
    }
    
    @Override
    public Navio atirar(Jogador inimigo) {
        if (this.getHabilidades().isEmpty())
            return super.atirar(inimigo);
        
        if (this.getHabilidades().get(0).getNome().equals("Sonar") || this.getHabilidades().get(0).getNome().equals("Torpedo") ) {
            int pX = -1, pY = -1, i;
            String entrada, h;
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

            if (h.equals("Sonar")) {
                    // mostra navios que estão em uma area 3
                    if (pX+2 < 14) {
                        JOptionPane.showMessageDialog(
                            null,   "Graças ao uso da habilidade Sonar você poderá visualizar o que há em algumas posições do seu inimigo:\n"+
                                    "Em ["+pX+"]["+pY+"] :" + inimigo.getTabuleiro()[pX][pY].getOcupante()+"\n"+
                                    "Em ["+(pX+1)+"]["+pY+"] :" + inimigo.getTabuleiro()[pX+1][pY].getOcupante()+"\n"+
                                    "Em ["+(pX+2)+"]["+pY+"] :" + inimigo.getTabuleiro()[pX+2][pY].getOcupante()+"\n");
                    } else if (pX-2 < 14) {
                        JOptionPane.showMessageDialog(
                            null,   "Graças ao uso da habilidade Sonar você poderá visualizar o que há em algumas posições do seu inimigo:\n"+
                                    "Em ["+pX+"]["+pY+"] :" + inimigo.getTabuleiro()[pX][pY].getOcupante()+"\n"+
                                    "Em ["+(pX-1)+"]["+pY+"] :" + inimigo.getTabuleiro()[pX-1][pY].getOcupante()+"\n"+
                                    "Em ["+(pX-2)+"]["+pY+"] :" + inimigo.getTabuleiro()[pX-2][pY].getOcupante()+"\n");
                    } else {
                        JOptionPane.showMessageDialog(
                            null,   "Graças ao uso da habilidade Sonar você poderá visualizar o que há em algumas posições do seu inimigo:\n"+
                                    "Em ["+pX+"]["+pY+"] :" + inimigo.getTabuleiro()[pX][pY].getOcupante()+"\n"+
                                    "Em ["+pY+"]["+pX+"] :" + inimigo.getTabuleiro()[pY][pX].getOcupante()+"\n");
                    }
            } else {
                for (i = pX; i < inimigo.getTabuleiro()[0].length-2; i++) {
                    if (inimigo.getTabuleiro()[i][pY].getSituacao() != 0) {
                        inimigo.getTabuleiro()[i][pY].setSituacao('0');
                        inimigo.getTabuleiro()[i+1][pY].setOcupante("");
                        inimigo.getTabuleiro()[i+1][pY].setSituacao('0');
                        inimigo.getTabuleiro()[i+1][pY].setOcupante("");
                        inimigo.getTabuleiro()[i+2][pY].setSituacao('0');
                        inimigo.getTabuleiro()[i+2][pY].setOcupante("");
                        this.ganharEnergia(100);
                        i = 16;
                    } else {
                        for (i = 0; i < inimigo.getNaviosJogador().size(); i++)
                            inimigo.getNaviosJogador().get(i).ganharEnergia(30);
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

