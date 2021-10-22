package jogo.logica.memento;

import jogo.logica.Enum.Situacao;

import java.io.IOException;

public class Gestor {
    private JogoQuatroLinhaEstadosOriginator jogoQuatroLinhaEstadosOriginator;
    private CareTaker careTaker;

    public Gestor() {
        this.jogoQuatroLinhaEstadosOriginator = new JogoQuatroLinhaEstadosOriginator();
        this.careTaker = new CareTaker(jogoQuatroLinhaEstadosOriginator);
    }

    //Situação
    public Situacao getSituacaoAtual(){
        return jogoQuatroLinhaEstadosOriginator.getSituacaoAtual();
    }

    //CareTaker
    public void undo(){
        careTaker.undo();
    }
    public void veReplay(){
        careTaker.veReplay();
    }

    public void gravaJogo() throws IOException {
        careTaker.gravaMementoJogo();
        careTaker.gravaJogo();
    }
    public void voltaAtras(int quant) {
        if(careTaker.stackSize() != 0  && quant > 0) {
            if (quant > careTaker.stackSize())
                quant = careTaker.stackSize();
            int atual = getNmrAtual(); //Jogador atual para retirar créditos à pessoa certa
            int cred = getnCreditos(); //Créditos do jogador atual
            int cred2 = getCreditosAssist(); //Créditos do outro jogador

            int nJogadas = getJogadasAssist();
            careTaker.gravaMementoJogo();
            for (int i = 0; i < quant; i++)
                undo(); //Aciona o undo
            if(nJogadas > getJogadasAssist())
                nJogadas = getJogadasAssist();
            jogoQuatroLinhaEstadosOriginator.atualiza(quant, atual, cred, cred2,nJogadas); //Atualiza os créditos
        }
    }
    public void carregaJogo(String s) {
        if(!s.equals("")){
            careTaker.carregaJogo(s);
            jogoQuatroLinhaEstadosOriginator.replays();
        }
        else
            jogoQuatroLinhaEstadosOriginator.voltarMenu();
    }

    //Máquina de Estados
    public void comecar(){
        jogoQuatroLinhaEstadosOriginator.comecar();
    }
    public void jogar() {
        jogoQuatroLinhaEstadosOriginator.jogar();
    }
    public void escolheTipoJogador(int i) {
        jogoQuatroLinhaEstadosOriginator.escolheTipoJogador(i);
    }
    public void insereNomes(String [] i){
        jogoQuatroLinhaEstadosOriginator.insereNomes(i);
    }
    public void iniciaJogo() {
        jogoQuatroLinhaEstadosOriginator.iniciaJogo();
    }
    public void verificaResposta(int i) {
        jogoQuatroLinhaEstadosOriginator.verificaResposta(i);
    }
    public void verificaResposta(String str){
        jogoQuatroLinhaEstadosOriginator.verificaResposta(str);
    }
    public void jogaMiniJogo() {
        jogoQuatroLinhaEstadosOriginator.jogaMiniJogo();
    }
    public void resetaJogo(){
        jogoQuatroLinhaEstadosOriginator.resetaJogo();
    }
    public void replays() {
        jogoQuatroLinhaEstadosOriginator.replays();
    }
    public void jogaPeca(int i) {

        careTaker.gravaMemento();
        careTaker.gravaMementoJogo();
        jogoQuatroLinhaEstadosOriginator.jogaPeca(i);
    }
    public void jogaPecaEspecial(int i) {
        careTaker.gravaMemento();
        careTaker.gravaMementoJogo();
        jogoQuatroLinhaEstadosOriginator.jogaPecaEspecial(i);
    }
    public void reiniciaJogo() throws IOException {
        careTaker.gravaMementoJogo();
        gravaJogo();
        careTaker.reiniciaHistoricoEstados();
        careTaker.reiniciaHistoricoReplay();
        jogoQuatroLinhaEstadosOriginator.reiniciaJogo();
    }
    public void voltarMenu() {
        careTaker.reiniciaHistoricoReplay();
        jogoQuatroLinhaEstadosOriginator.volta();
        jogoQuatroLinhaEstadosOriginator.voltarMenu();
    }
    public void gravaContinuacao() throws IOException {
        careTaker.gravaMemento();
        careTaker.gravaContinuacao();
    }
    public void carregaContinuacao(String continuacaoJogo,String replays) {
        careTaker.carregaContinuacao(continuacaoJogo,replays);
        careTaker.redoJogo();
    }
    public void voltarJogo() {
        jogoQuatroLinhaEstadosOriginator.voltarJogo();
    }
    public void clearHistorico() {
        jogoQuatroLinhaEstadosOriginator.clearHistorico();
    }

    public int getnPecasEspeciais() {
        return jogoQuatroLinhaEstadosOriginator.getnPecasEspeciais();
    }
    public int getnCreditos(){
        return jogoQuatroLinhaEstadosOriginator.getnCreditos();
    }
    public int getConta() {
        return jogoQuatroLinhaEstadosOriginator.getConta();
    }
    public int getNmr1() {
        return jogoQuatroLinhaEstadosOriginator.getNmr1();
    }
    public int getNmr2() {
        return jogoQuatroLinhaEstadosOriginator.getNmr2();
    }
    public int getNHumanos() {
        return jogoQuatroLinhaEstadosOriginator.getNHumanos();
    }
    public int getRespostasCertas() {
        return jogoQuatroLinhaEstadosOriginator.getRespostasCertas();
    }
    public int getNmrAtual(){ return jogoQuatroLinhaEstadosOriginator.getNmrAtual();}
    public int getCreditosAssist() {
        return jogoQuatroLinhaEstadosOriginator.getCreditosAssist();
    }
    public int getJogadasAssist(){return jogoQuatroLinhaEstadosOriginator.getJogadasAssist();}
    public int[] verificaColunaCheia() {
        return jogoQuatroLinhaEstadosOriginator.verificaColunaCheia();
    }
    public int [] verificaColunasVazias(){
        return jogoQuatroLinhaEstadosOriginator.verificaColunasVazias();
    }
    public int getLargura(){return jogoQuatroLinhaEstadosOriginator.getLargura();}
    public int getAltura(){return jogoQuatroLinhaEstadosOriginator.getAltura();}

    public String imprimeJogo() {
        careTaker.veReplay();
        return jogoQuatroLinhaEstadosOriginator.imprimeJogo();
    }
    public String getVencedor(){
        return jogoQuatroLinhaEstadosOriginator.getVencedor();
    }
    public String getOperator() {
        return jogoQuatroLinhaEstadosOriginator.getOperator();
    }
    public String getSequencia() {
        return jogoQuatroLinhaEstadosOriginator.getSequencia();
    }
    public String imprimeHistorico(){return jogoQuatroLinhaEstadosOriginator.imprimeHistorico();}
    public String imprimeAtual(){return jogoQuatroLinhaEstadosOriginator.imprimeAtual();}

    public long getTempoMat() {
        return jogoQuatroLinhaEstadosOriginator.getTempoMat();
    }
    public long getTempoPalavras() {
        return jogoQuatroLinhaEstadosOriginator.getTempoPalavras();
    }

    public boolean getTipoJogador(){
        return jogoQuatroLinhaEstadosOriginator.getTipoJogador();
    }

    @Override
    public String toString() {
        return  jogoQuatroLinhaEstadosOriginator.toString();
    }

    public int getAlturaPeca() {
        return jogoQuatroLinhaEstadosOriginator.getAlturaPeca();
    }

    public int[][] getPos() {
        return jogoQuatroLinhaEstadosOriginator.getPos();
    }
}
