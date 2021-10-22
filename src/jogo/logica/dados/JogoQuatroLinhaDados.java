package jogo.logica.dados;

import jogo.logica.dados.jogadores.Computador;
import jogo.logica.dados.jogadores.Humano;
import jogo.logica.dados.jogadores.Jogador;
import jogo.logica.dados.minijogos.MiniJogoMatematica;
import jogo.logica.dados.minijogos.MiniJogoPalavras;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class JogoQuatroLinhaDados implements Serializable {

    ArrayList<String> historico;
    private static final int LARGURA = 7;
    private static final int ALTURA = 6;

    private int [][] tabuleiro;
    private Jogador [] jogadores = new Jogador[2];

    private Random rand = new Random();
    private MiniJogoMatematica matematica;
    private MiniJogoPalavras palavras;

    private long tempoStart;
    private int jogoAtivo,alturaPeca, atual,nHumanos,minijogoAtual;

    public int getLargura(){
        return LARGURA;
    }

    public int getAltura(){
        return ALTURA;
    }

    public int getMinijogoAtual(){
        return minijogoAtual;
    }

    public void alteraMinijogoAtual(){
        if(minijogoAtual == 1)
            minijogoAtual = 2;
        else
            minijogoAtual = 1;
    }

    public void addHistorico(String acao){
        historico.add(acao);
    }

    public String imprimeHistorico(){
        return historico.toString();
    }

    public JogoQuatroLinhaDados() {
        preparaJogo();
    }

    public void preparaJogo(){
        this.historico = new ArrayList<>();
        this.tabuleiro = new int[ALTURA][LARGURA];
        this.atual = rand.nextInt(2) + 1;
        this.jogoAtivo = -1;
        this.minijogoAtual = 1;
        this.nHumanos = 0;
        for (int i = 0; i < ALTURA; i++) {
            for (int j = 0; j < LARGURA; j++) {
                this.tabuleiro[i][j] = 0;
            }
        }
    }

    public void adicionaJogadores(int i){
        if(i == 1){
          this.jogadores[0] = new Humano();
          this.jogadores[1] = new Humano();
          this.nHumanos = 2;
        } else if(i == 2) {
            this.jogadores[0] = new Humano();
            this.jogadores[1] = new Computador();
            this.nHumanos = 1;
        } else{
            this.jogadores[0] = new Computador();
            this.jogadores[1] = new Computador();
        }
    }

    public void defineNomes(String [] nomes){
        for (int j = 0; j < 2; j++) {
                jogadores[j].setNome(nomes[j]);
        }
    }

    public void jogaPecaEspecial(int i) {
        for (int j = 0; j < ALTURA; j++) {
            this.tabuleiro[j][i - 1] = 0;
        }
        this.jogadores[this.atual - 1].addnJogadas();
        this.jogadores[this.atual - 1].subPecasEspeciais();
        if(this.atual == 1)
            this.atual = 2;
        else
            this.atual = 1;
    }

    public void setMiniJogo(int i) {
        if(i == 1){
            this.matematica = new MiniJogoMatematica();
            jogoAtivo = 1;
            return;
        }
        this.palavras = new MiniJogoPalavras();
        jogoAtivo = 2;
    }

    public void geraConta(){
        matematica.geraConta();
    }

    public void setTempoStart(){
        this.tempoStart = System.currentTimeMillis();
    }

    public void verificaResposta(int i){
        matematica.verificaResposta(i);
    }

    public void addPecasEspeciais(){
        jogadores[atual - 1].addPecasEspeciais();
    }

    public void passaJogada() {
        if (atual == 1)
            atual = 2;
        else
            atual = 1;
    }

    public void setPermitidoJogarMiniJogo(int i){
        jogadores[atual - 1].setPermitidoJogarMiniJogo(i);
    }

    public void setSequencia() {
        palavras.leFicheiro();
        palavras.setSequencia();
    }


    public void resetJogadas(int jogador) {
        jogadores[jogador - 1].resetJogadas();
    }


    public void undoUpdate(int i, int j, int cred, int cred2, int nJogadas) {
        jogadores[j - 1].setCreditos(cred - i);
        if(j == 2){
            jogadores[0].setCreditos(cred2);
            jogadores[0].setJogadas(nJogadas);
            return;
        }
        jogadores[1].setCreditos(cred2);
        jogadores[1].setJogadas(nJogadas);
    }

    public int [][] getPos(){
        int [][] pos = new int [ALTURA] [LARGURA];
        for (int i = 0; i < ALTURA; i++) {
            for (int j = 0; j < LARGURA; j++) {
                pos[i][j] = tabuleiro[i][j];
            }
        }
        return pos;
    }

    public int[] verificaColunaCheia(){
        int [] livres = new int[LARGURA];
        for (int i = 0,j = 0; i < LARGURA; i++) {
            if (tabuleiro[0][i] == 0){
                livres[j] = i + 1;
                j++;
            }
        }
        return livres;
    }

    public int[] verificaColunasVazias(){
        int [] vazias = new int[LARGURA];
        for (int i = 0,j = 0; i < LARGURA; i++) {
            if (tabuleiro[ALTURA - 1][i] == 0){
                vazias[j] = i + 1;
                j++;
            }
        }
        return vazias;
    }

    public int getAlturaPeca(){
        return alturaPeca;
    }

    public boolean jogaPeca(int i){
        for (int j = ALTURA - 1; j >= 0; j--) {
            if(this.tabuleiro[j][i - 1] == 0){
                if(this.atual == 1){
                    this.tabuleiro[j][i - 1] = 1;
                    alturaPeca = j;
                    this.jogadores[this.atual - 1].addnJogadas();
                    this.atual = 2;
                }
                else{
                    this.tabuleiro[j][i - 1] = 2;
                    alturaPeca = j;
                    this.jogadores[this.atual - 1].addnJogadas();
                    this.atual = 1;
                }
                return true;
            }
        }
        return false;
    }

    public boolean verificaVitoria(){
        /* Verifica [\] */
        for (int i = 0; i < ALTURA - 3; i++) {
            for (int j = 0; j < LARGURA - 3; j++) {
                if((this.tabuleiro[i][j] == 1 && this.tabuleiro[i + 1][j + 1] == 1 && this.tabuleiro[i + 2][j + 2] == 1 && this.tabuleiro[i + 3][j + 3] == 1) ||
                        (this.tabuleiro[i][j] == 2 && this.tabuleiro[i + 1][j + 1] == 2 && this.tabuleiro[i + 2][j + 2] == 2 && this.tabuleiro[i + 3][j + 3] == 2))
                    return true;
            }
        }

        /* Verifica [/] */
        for (int i = 0; i < ALTURA - 3; i++) {
            for (int j = LARGURA - 1; j > 2; j--) {
                if((this.tabuleiro[i][j] == 1 && this.tabuleiro[i + 1][j - 1] == 1 && this.tabuleiro[i + 2][j - 2] == 1 && this.tabuleiro[i + 3][j - 3] == 1) ||
                        (this.tabuleiro[i][j] == 2 && this.tabuleiro[i + 1][j - 1] == 2 && this.tabuleiro[i + 2][j - 2] == 2 && this.tabuleiro[i + 3][j - 3] == 2))
                    return true;
            }
        }
        /* Verifica [|] */
        for (int i = 0; i < ALTURA - 3; i++) {
            for (int j = 0; j < LARGURA; j++) {
                if((this.tabuleiro[i][j] == 1 && this.tabuleiro[i + 1][j] == 1 && this.tabuleiro[i + 2][j] == 1 && this.tabuleiro[i + 3][j] == 1) ||
                        (this.tabuleiro[i][j] == 2 && this.tabuleiro[i + 1][j] == 2 && this.tabuleiro[i + 2][j] == 2 && this.tabuleiro[i + 3][j] == 2))
                    return true;
            }
        }
        /* Verifica [-] */
        for (int i = 0; i < ALTURA; i++) {
            for (int j = 0; j < LARGURA - 3; j++) {
                if((this.tabuleiro[i][j] == 1 && this.tabuleiro[i][j + 1] == 1 && this.tabuleiro[i][j + 2] == 1 && this.tabuleiro[i][j + 3] == 1) ||
                        (this.tabuleiro[i][j] == 2 && this.tabuleiro[i][j + 1] == 2 && this.tabuleiro[i][j + 2] == 2 && this.tabuleiro[i][j + 3] == 2))
                    return true;
            }
        }
        return false;
    }

    public boolean verificaTabuleiro() {
        for (int i = 0; i < LARGURA; i++) {
            if(this.tabuleiro[0][i] == 0)
                return false;
        }
        return true;
    }

    public boolean tipoJogador(){
        if(jogadores[atual - 1].getClass().getSimpleName().equals("Humano"))
            return true;
        return false;
    }

    public boolean verificaResposta(String str){
        if(str.equalsIgnoreCase(palavras.getSequencia()))
            return true;
        return false;
    }

    public String getVencedor(){
        if(!verificaTabuleiro()) {
            if (this.atual == 1)
                return jogadores[1].getNome();
            return jogadores[0].getNome();
        } else
            return "Empate";
    }

    public String getOperator(){
        return matematica.getOperator();
    }

    public String getSequencia() {
        return palavras.getSequencia();
    }

    public String getAtual(){
        return this.jogadores[this.atual - 1].getNome();
    } //Obtém o nome

    public int getNmrAtual(){
        return this.atual;
    } //Obtém o número

    public int getJogadas(){
        return jogadores[atual - 1].getnJogadas() + 1;
    }

    public int getJogadasAssist(){
        if(atual == 1)
            return jogadores[1].getnJogadas();
        return jogadores[0].getnJogadas();
    }

    public int getCreditos(){
        return jogadores[atual - 1].getCreditos();
    }

    public int getPecasEspeciais(){
        return jogadores[atual - 1].getPecasEspeciais();
    }

    public int getNHumanos(){
        return nHumanos;
    }

    public int getConta(){
        return matematica.getCalculoAtual();
    }

    public int getJogoAtivo(){
        return jogoAtivo;
    }

    public int getNmr1() {
        return matematica.getNmr1();
    }

    public int getNmr2(){
        return matematica.getNmr2();
    }

    public int getRespostasCertas() {
        return matematica.getRespostasCertas();
    }

    public int getPermitidoJogarMiniJogo(){
        return jogadores[atual - 1].getPermitidoJogarMiniJogo();
    }

    public int getCreditosAssist() {
        if(atual == 1)
            return jogadores[1].getCreditos();
        return jogadores[0].getCreditos();
    }

    public long getTempoRestante(){
        return System.currentTimeMillis() - tempoStart;
    }

    public long getTempoMat() {
        return 30 - (getTempoRestante()/1000);
    }


    public long getTempoPalavras(){
        return palavras.getTempo();
    }

    @Override
    public String toString() {
        String tabuleiro = "Estado do tabuleiro: \n\n";
        for (int i = 0; i < ALTURA; i++) {
            tabuleiro += " | ";
            for (int j = 0; j < LARGURA; j++) {
                tabuleiro += (this.tabuleiro[i][j] == 0 ? " " : this.tabuleiro[i][j]) + " | ";
            }
            tabuleiro += "\n";
        }
        return tabuleiro;
    }



    public String imprimeAtual(){
        if(this.jogadores[atual - 1] == null)
            return "Inicio";
        return ("Vez de " + jogadores[atual - 1].getNome() + " jogar...\n");
    }

    public void clearHistorico() {
        historico.clear();
    }
}