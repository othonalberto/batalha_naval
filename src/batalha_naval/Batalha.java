package batalha_naval;

import javax.swing.JOptionPane;

/**
 *
 * @author othonalberto
 */
public class Batalha {
    String executar(Jogador jogador1, Jogador jogador2) {
        int ordem, opcao, selecionado, atual, habilitado;
        String entrada;
        Jogador jogadores[];
        String navios[] = {"Corveta", "Cruzador" , "Destroyer" , "Fragata", "Porta Aviões" , "Submarino"};
        
        jogadores = new Jogador[2];
        ordem = (int) (Math.random() * 2);
        selecionado = 0;
        opcao = 0;
        habilitado = 0;

        if (ordem == 1) {
            jogadores[0] = jogador1;
            jogadores[1] = jogador2;
        } else {
            jogadores[0] = jogador2;
            jogadores[1] = jogador1;
        }
        
        ordem = 0; // a partir daqui a variavel "ordem" será utilizada como flag, para nao criar mais variáveis
        atual = 0; // flag que mostra qual o navio atual
        
        while(jogadores[0].somaNavio() != 0 && jogadores[1].somaNavio() != 0) {
        
            // seleciona a ação a ser tomada pelo jogador
            do {
                entrada = JOptionPane.showInputDialog("Olá, " + jogadores[atual].getApelido() + "("+jogadores[atual].somaNavio()*100/31 + "%). " + "O que deseja fazer nesta rodada?\n" +
                                                "1 - Atirar\n" + "2 - Não atirar\n" + "3 - Desistir\n");

                try {
                    opcao = Integer.parseInt(entrada);
                }

                catch(NumberFormatException FormatException){
                    JOptionPane.showMessageDialog(null, "Formato de número inválido.");         
                }


                catch(Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
                
                if (opcao == 3) {
                    if (atual == 1)
                        ordem = 0;
                    else
                        ordem = 1;
                    
                    return jogadores[ordem].getNome();
                }

            } while (opcao < 1 || opcao > 3);

            // seleciona o navio a ser utilizado
            do{
                entrada = JOptionPane.showInputDialog("Selecione o navio que deseja utilizar nesta rodada:\n" +
                                                "1 - Corveta\n" + "2 - Cruzador\n" + "3 - Destroyer\n" + "4 - Fragata\n"
                                                + "5 - Porta Aviões\n" + "6 - Submarino\n");

                try {
                    selecionado = Integer.parseInt(entrada);

                    ordem = jogadores[atual].existeNavio(
                            navios[selecionado-1]
                    );

                    if (ordem == 0)
                        JOptionPane.showMessageDialog(null, "Esse navio já foi destruído.");
                    
                    habilitado = jogadores[atual].getNaviosJogador().get(0).getDesabilitado();
                    

                }

                catch(NumberFormatException FormatException) {
                    JOptionPane.showMessageDialog(null, "Formato de número inválido.");         
                }

                catch(ArrayIndexOutOfBoundsException exception) {
                    JOptionPane.showMessageDialog(null, "O número digito deve ser válido.");
                }

                catch(Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }

            } while (ordem == 0 || selecionado < 1 || selecionado > 6);

            if (atual == 1)
                ordem = 0;
            else
                ordem = 1;

            if (habilitado != 0){
                for (ordem = 0; ordem < jogadores[atual].getNaviosJogador().size(); ordem++) {
                    jogadores[atual].getNaviosJogador().get(ordem).setDesabilitado(
                         (jogadores[atual].getNaviosJogador().get(ordem).getDesabilitado()) - 1
                    );
                }
                
                JOptionPane.showMessageDialog(null, "Você está desabilitado!");
                
            } else{
                if (opcao == 1) {
                    jogadores[atual].getNaviosJogador().get(selecionado-1).atirar(jogadores[ordem]);
                }
                else
                    jogadores[atual].getNaviosJogador().get(selecionado-1).ganharEnergia(75);
            }

            if (atual == 1)
                atual--;
            else
                atual++;
        }
    
        // retorna o nome do jogador vencedor
        if (jogadores[0].somaNavio() != 0)
            return jogadores[0].getNome();
        else
            return jogadores[1].getNome();
    }
}