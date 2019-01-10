package batalha_naval;

import javax.swing.JOptionPane;

/**
 *
 * @author othonalberto
 */
public class Fragata extends Navio {
    public Fragata() {
        super("Fragata", 1, 3, "Ofensivo em larga escala");
    }
    
    @Override
    public Navio atirar(Jogador inimigo) {
        if (this.getHabilidades().isEmpty()) {
            return super.atirar(inimigo);
        }
        
        if (this.getHabilidades().get(0).getNome().equals("Foguetes") || this.getHabilidades().get(0).getNome().equals("Míssel Balístico") ) {
            int pX, pY, i, j, aux;
            String entrada, h;
            
            pX = pY = -1;
            aux = 0;
            h = this.getHabilidades().get(0).getNome();
            
            this.consumirEnergia();

            do {
                entrada = JOptionPane.showInputDialog("Digite a coordenada X que deseja atirar (0 a 13): ");

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

            if (h.equals("Foguetes")) {
                    // "foguetes" atirará em 2 pontos aleatórios e no ponto escolhido pelo jogador
                    if (inimigo.getTabuleiro()[pX][pY].getSituacao() != '0') {
                        inimigo.getTabuleiro()[pX][pY].setSituacao('0');
                        inimigo.getTabuleiro()[pX][pY].setOcupante("");
                    } else {
                        for (i = 0; i < inimigo.getNaviosJogador().size(); i++)
                                inimigo.getNaviosJogador().get(i).ganharEnergia(30);
                    }
                    
                    for (i = 0; i < 2; i++) {
                        pX = (int) (Math.random() * 14);
                        pY = (int) (Math.random() * 14);
                        
                        if (inimigo.getTabuleiro()[pX][pY].getSituacao() != '0') {
                            inimigo.getTabuleiro()[pX][pY].setSituacao('0');
                            inimigo.getTabuleiro()[pX][pY].setOcupante("");
                        } else {
                            for (aux = 0; aux < inimigo.getNaviosJogador().size(); aux++)
                                inimigo.getNaviosJogador().get(aux).ganharEnergia(30);
                        }
                    }
               
            } else {
                // Míssel Balístico destruirá mas mostrará a fragata
                if (inimigo.getTabuleiro()[pX][pY].getSituacao() != '0') {
                            inimigo.getTabuleiro()[pX][pY].setSituacao('0');
                            inimigo.getTabuleiro()[pX][pY].setOcupante("");
                            this.ganharEnergia(100);
                } else {
                        for (i = 0; i < inimigo.getNaviosJogador().size(); i++)
                            inimigo.getNaviosJogador().get(i).ganharEnergia(30);
                }
                
                JOptionPane.showMessageDialog(null, "A fragata do seu inimigo está em ["+this.i+"]["+this.j+"], " + inimigo.getApelido());
                
                
                
            }
            
            this.habilidades.remove(0);
            return this;
        }
        
        else
            return super.atirar(inimigo);
       
    }
}
