package jogo.logica.memento;

import jogo.logica.Enum.Situacao;
import jogo.logica.estados.JogoQuatroLinhaEstados;

import java.io.IOException;

public class JogoQuatroLinhaEstadosOriginator implements  IMementoOriginator{

    private JogoQuatroLinhaEstados jogoQuatroLinhaEstados;

    public JogoQuatroLinhaEstadosOriginator() {
        this.jogoQuatroLinhaEstados = new JogoQuatroLinhaEstados();
    }

    @Override
    public Memento getMemento() throws IOException {
        return new Memento(jogoQuatroLinhaEstados);
    }

    @Override
    public void setMemento(Memento m) throws IOException, ClassNotFoundException {
        jogoQuatroLinhaEstados = (JogoQuatroLinhaEstados) m.getSnapshot();
    }

    @Override
    public void setMementoJogo(Memento mem) {
        jogoQuatroLinhaEstados.setMementoJogo(mem);
    }

    @Override
    public Memento getMementoJogo() throws IOException{
        return jogoQuatroLinhaEstados.getMementoJogo();
    }

    public void resetaJogo(){
        jogoQuatroLinhaEstados.resetaJogo();
    }
    public void volta(){
        jogoQuatroLinhaEstados.volta();
    }

    //Funções da máquina de estados
    public void comecar(){
        jogoQuatroLinhaEstados.comecar();
    }
    public void jogar() {
        jogoQuatroLinhaEstados.jogar();
    }
    public void escolheTipoJogador(int i) {
        jogoQuatroLinhaEstados.escolheTipoJogador(i);
    }
    public void insereNomes(String [] i){jogoQuatroLinhaEstados.insereNomes(i);}
    public void voltarMenu() {
        jogoQuatroLinhaEstados.voltarMenu();
    }
    public void jogaPeca(int i) {
        jogoQuatroLinhaEstados.jogaPeca(i);
    }
    public void jogaPecaEspecial(int i) {
        jogoQuatroLinhaEstados.jogaPecaEspecial(i);
    }
    public void reiniciaJogo() {
        jogoQuatroLinhaEstados.reiniciaJogo();
    }
    public void iniciaJogo() {
        jogoQuatroLinhaEstados.iniciaJogo();
    }
    public void verificaResposta(int i) {
        jogoQuatroLinhaEstados.verificaResposta(i);
    }
    public void verificaResposta(String str){
        jogoQuatroLinhaEstados.verificaResposta(str);
    }
    public void jogaMiniJogo() {
        jogoQuatroLinhaEstados.jogaMiniJogo();
    }
    public void replays() {
        jogoQuatroLinhaEstados.replays();
    }
    public void voltarJogo() {
        jogoQuatroLinhaEstados.voltarJogo();
    }
    public void clearHistorico() {
        jogoQuatroLinhaEstados.clearHistorico();
    }
    public void atualiza(int i, int atual, int cred, int cred2, int nJogadas) {
        jogoQuatroLinhaEstados.atualiza(i,atual,cred,cred2,nJogadas);
    }

    public int getnPecasEspeciais() {
        return jogoQuatroLinhaEstados.getnPecasEspeciais();
    }
    public int getnCreditos(){
        return jogoQuatroLinhaEstados.getnCreditos();
    }
    public int getNHumanos() {
        return jogoQuatroLinhaEstados.getNHumanos();
    }
    public int getConta() {
        return jogoQuatroLinhaEstados.getConta();
    }
    public int getNmr1() {
        return jogoQuatroLinhaEstados.getNmr1();
    }
    public int getNmr2() {
        return jogoQuatroLinhaEstados.getNmr2();
    }
    public int getRespostasCertas() {
        return jogoQuatroLinhaEstados.getRespostasCertas();
    }
    public int getNmrAtual(){
        return jogoQuatroLinhaEstados.getNmrAtual();
    }
    public int getCreditosAssist() {
        return jogoQuatroLinhaEstados.getCreditosAssist();
    }
    public int getJogadasAssist() {
        return jogoQuatroLinhaEstados.getJogadasAssist();
    }
    public int[] verificaColunaCheia() {
        return jogoQuatroLinhaEstados.verificaColunaCheia();
    }
    public int [] verificaColunasVazias(){
        return jogoQuatroLinhaEstados.verificaColunasVazias();
    }

    public String imprimeJogo() {
        return jogoQuatroLinhaEstados.imprimeJogo();
    }
    public String getVencedor(){
        return jogoQuatroLinhaEstados.getVencedor();
    }
    public String getOperator() {
        return jogoQuatroLinhaEstados.getOperator();
    }
    public String getSequencia() {
        return jogoQuatroLinhaEstados.getSequencia();
    }
    public String imprimeHistorico(){return jogoQuatroLinhaEstados.imprimeHistorico();}
    public String imprimeAtual() {
        return jogoQuatroLinhaEstados.imprimeAtual();
    }

    public long getTempoMat() {
        return jogoQuatroLinhaEstados.getTempoMat();
    }
    public long getTempoPalavras() {
        return jogoQuatroLinhaEstados.getTempoPalavras();
    }

    public boolean getTipoJogador(){
        return jogoQuatroLinhaEstados.getTipoJogador();
    }

    public Situacao getSituacaoAtual(){
        return jogoQuatroLinhaEstados.getSituacaoAtual();
    }

    @Override
    public String toString() {
        return jogoQuatroLinhaEstados.toString();
    }


    public int getLargura() {
        return jogoQuatroLinhaEstados.getLargura();
    }
    public int getAltura() {
        return jogoQuatroLinhaEstados.getAltura();
    }

    public int getAlturaPeca() {
        return jogoQuatroLinhaEstados.getAlturaPeca();
    }

    public int[][] getPos() {
        return jogoQuatroLinhaEstados.getPos();
    }
}
