package batalha_naval;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author othonalberto
 */
public class Jogo {
    public static void main(String args[]) {
        Jogador players[] = new Jogador[2];
        String vencedor;
        Jogador p1, p2;
        String nome, apelido, entrada;
        Date nasc = null;
        
        //jogador 1
        do {
            nome = JOptionPane.showInputDialog("Olá! Hora de fazer o cadastro! Digite seu nome: ");
        } while (nome.equals(""));
        
        apelido = JOptionPane.showInputDialog("Digite seu apelido (caso deixe em branco, usaremos seu nome): ");
        if (apelido.equals(""))
            apelido = nome;
        
        entrada = JOptionPane.showInputDialog("Digite sua data de nascimento: (dd/MM/yyyy)");
        try {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            nasc = (Date)sdf.parse(entrada);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao processar data de nascimento: " + ex);
        }
        p1 = new Jogador(nome, apelido, nasc);
        
        //jogador 2
        do {
            nome = JOptionPane.showInputDialog("Olá! Digite seu nome: ");
        } while (nome.equals(""));
        
        apelido = JOptionPane.showInputDialog("Digite seu apelido. Caso deixe em branco, usaremos seu nome.");
        if (apelido.equals(""))
            apelido = nome;
        
        entrada = JOptionPane.showInputDialog("Digite sua data de nascimento: (dd/MM/yyyy) ");
        try {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            nasc = (Date)sdf.parse(entrada);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao processar data de nascimento: " + ex);
        }
        
        p2 = new Jogador(nome, apelido, nasc);
       
        p1.preencheTabuleiro();
        p2.preencheTabuleiro();
        
        Batalha batalha = new Batalha();
        vencedor = batalha.executar(p1, p2);
       
        JOptionPane.showMessageDialog(null, "Parabéns pela vitória, " + vencedor + "! ");
        
    }
}
