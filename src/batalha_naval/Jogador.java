package batalha_naval;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author othonalberto
 */
public class Jogador {
    private String nome;
    private String apelido;
    private Date dataDeNascimento;
    private ArrayList <Navio> naviosJogador; 
    private Tabuleiro tabuleiro[][];
    
    public Jogador(String nome, String apelido, Date dataDeNascimento){
        this.nome = nome;
        this.apelido = apelido;
        this.dataDeNascimento = dataDeNascimento;
        this.tabuleiro = new Tabuleiro[14][14];
        this.naviosJogador = new ArrayList();
        
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 14; j++) {
                this.tabuleiro[i][j] = new Tabuleiro();
            }
        }   
    }
    
    public void preencheTabuleiro() { 
        
        Corveta corveta = new Corveta();
        Habilidade escudo;
        escudo = new Habilidade("Escudo", "Cria um escudo 3x3 sobre o seu próprio campo, capaz de absorver até 5 tiros antes de ser destruído. Não pode ser lançado sobre a própria corveta.");
        corveta.adicionarHabilidade(escudo);
        
        Cruzador cruzador = new Cruzador();
        Habilidade artilharia, artilhariaPesada;
        artilharia = new Habilidade("Artilharia", "A habilidade mais fraca do jogo. Ela simplesmente atinge uma área 2x2.");
        artilhariaPesada = new Habilidade("Artilharia Pesada", "O mesmo que a artilharia, porém atinge uma área 3x3");
        cruzador.adicionarHabilidade(artilharia);
        cruzador.adicionarHabilidade(artilhariaPesada);
        
        Destroyer destroyer = new Destroyer();
        Habilidade cargasDeProfundidade, radar;
        cargasDeProfundidade = new Habilidade("Cargas de Profundidade", "Esta habilidade atinge uma área enorme, porém só atinge submarinos. Todos os outros navios e quadrados serão ignorados.");
        radar = new Habilidade("Radar", "Esta habilidade encontra navios na superfície em uma grande área, porém não pode encontrar submarinos.");
        destroyer.adicionarHabilidade(cargasDeProfundidade);
        destroyer.adicionarHabilidade(radar);
        
        Fragata fragata = new Fragata();
        Habilidade foguetes, misselBalistico;
        foguetes = new Habilidade("Foguetes", "Lança foguetes aleatórios em uma grande área. Cada quadrado na área tem uma chance de 50% de ser atingido por um foguete. Altamente eficaz contra navios grandes, como o Porta-Aviões, o Cruzador, e o Destroyer.");
        misselBalistico = new Habilidade("Míssel Balístico", "Destrói todos os quadrados na área afetada, porém faz com que todos os jogadores descubram onde está a sua fragata.");
        fragata.adicionarHabilidade(foguetes);
        fragata.adicionarHabilidade(misselBalistico);
        
        PortaAvioes portaAvioes = new PortaAvioes();
        Habilidade bombardeioLinear, pulsoEletromagnetico;
        bombardeioLinear = new Habilidade("Bombardeio Linear", "Atinge 8 quadrados ao longo de uma linha.");
        pulsoEletromagnetico = new Habilidade("Pulso Eletromagnético", "Desabilita todos os navios de um jogador por duas rodadas. É a habilidade mais poderosa do jogo.");
        portaAvioes.adicionarHabilidade(bombardeioLinear);
        portaAvioes.adicionarHabilidade(pulsoEletromagnetico);
        
        Submarino submarino = new Submarino();
        Habilidade torpedo, sonar;
        torpedo = new Habilidade("Torpedo", "Torpedo: Lança um torpedo que se move lentamente em uma linha reta através de um dos dois tabuleiros adjacentes (não é possível lançar um torpedo no tabuleiro oposto). Se ele encontrar um navio no caminho, ele irá explodir, atingindo uma área 3x3, que ignora escudos." );
        sonar = new Habilidade("Sonar", "Revela a posição de qualquer navio (incluindo submarinos) que estejam na área afetada.");
        submarino.adicionarHabilidade(torpedo);
        submarino.adicionarHabilidade(sonar);
        
        this.insereNavio(corveta);
        this.insereNavio(cruzador);
        this.insereNavio(destroyer);
        this.insereNavio(fragata);
        this.insereNavio(portaAvioes);
        this.insereNavio(submarino);
        
    }
    
    public Navio insereNavio(Navio navio) {
        int s, x, y;
    
        do {
            x = (int) (Math.random() * 14);
            y = (int) (Math.random() * 14);
            s = verificaDisponibilidade(x, y, navio);
        } while (s != 1);
        
        for (int j = 0; j<navio.getJ(); j++) {
            for (int i = 0; i<navio.getI(); i++){
               this.tabuleiro[x+i][y+j].setSituacao('1');
               this.tabuleiro[x+i][y+j].setOcupante(navio.getNome());
            }
        }
        
        this.naviosJogador.add(navio);
        navio.setI(x);
        navio.setJ(y);
        return navio;
    }
    
    public Corveta insereNavio(Corveta navio) {
        int s, x, y, j, i;
        int adicionalX = 0, adicionalY = 0;
        

        if (navio.getHabilidades().isEmpty()) {
            return (Corveta) insereNavio(navio);
        } else {
            do {
                x = (int) (Math.random() * 14);
                y = (int) (Math.random() * 14);
            
                s = verificaDisponibilidade(x + 3, y + 3, navio);
            } while (s != 1);
        
            System.out.println(navio.getNome() + " de " + this.getApelido() + " foi inserido em: " + x + ", " + y);
        
            for (j = 0; j<navio.getJ(); j++) {
                for (i = 0; i<navio.getI(); i++){
                    this.tabuleiro[x+i][y+j].setSituacao('1');
                    this.tabuleiro[x+i+3][y+j+3].setSituacao('2');
                    this.tabuleiro[x+i][y+j].setOcupante(navio.getNome());
                    this.tabuleiro[x+i+3][y+j+3].setOcupante("Habilidade");
                }
            }
            
           
            this.naviosJogador.add(navio);
            navio.setI(x);
            navio.setJ(y);
        }
        
        return navio;
    }
    
    public int verificaDisponibilidade(int x, int y, Navio navio) {
        int i, j;
        
        if (navio.getI()+x > 13 || navio.getJ()+y > 13) return 0;
        
        for (i = 0; i<navio.getI(); i++) {
            for (j = 0; j<navio.getJ(); j++){
                if (this.tabuleiro[x+i][y+j].getSituacao() != '0') return 0;
            }
        }
        return 1;  
    }   
    
    public int somaNavio() {
        int i, j, soma = 0;
        
        for (i = 0; i < this.tabuleiro[0].length; i++) {
            for (j = 0; j < this.tabuleiro[0].length; j++) {
                if (this.tabuleiro[j][i].getSituacao() == '1' || this.tabuleiro[j][i].getSituacao() == '2')
                    soma++;
            }
        }
        
        return soma;
    }
    
    public int existeNavio(String navio) {
        int i, j;
        
        for (i = 0; i < this.tabuleiro[0].length; i++) {
            for (j = 0; j < this.tabuleiro[0].length; j++) {
                if (this.tabuleiro[j][i].getOcupante().equals(navio))
                    return 1; // assim que acha o navio com o nome, retorna 
            }
        }
        
        return 0;
    }
    
    public void removeNavio(String navio) {
        int i;
        
        for (i = 0; i < this.naviosJogador.size(); i++) {
            if (naviosJogador.get(i).getNome().equals(navio))
                naviosJogador.remove(i);
        }
    }
    
    public void checaNaviosAtivos() {
        int retorno, i;
        String nomes[] = {"Corveta", "Cruzador", "Destroyer", "Fragata", "Porta Aviões", "Submarino"};
        
        for (i = 0; i < nomes[0].length(); i++) {
            retorno = this.existeNavio(nomes[i]);
            if (retorno == 0)
                this.removeNavio(nomes[i]);
        }
    }
      
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(Date dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public Tabuleiro[][] getTabuleiro() {
        return tabuleiro;
    }

    public void setTabuleiro(Tabuleiro[][] tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public ArrayList<Navio> getNaviosJogador() {
        return naviosJogador;
    }

    public void setNaviosJogador(ArrayList<Navio> NaviosJogador) {
        this.naviosJogador = NaviosJogador;
    }

 
}

