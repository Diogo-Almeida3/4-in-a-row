package jogo.logica;

import jogo.logica.Enum.Situacao;
import jogo.logica.estados.IEstado;
import jogo.logica.memento.Gestor;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;

import static jogo.logica.constantes.Constantes.*;

public class QuatroLinhaObservavel {

    private Gestor gestor;
    private final PropertyChangeSupport propertyChangeSupport;

    public QuatroLinhaObservavel(Gestor gestor) {
        this.gestor = gestor;
        propertyChangeSupport = new PropertyChangeSupport(gestor);
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener){
        propertyChangeSupport.addPropertyChangeListener(propertyName,listener);
    }

    public Situacao getSituacaoAtual(){
        return gestor.getSituacaoAtual();
    }

    public void undo(){
        gestor.undo();
        propertyChangeSupport.firePropertyChange(PROPRIEDADE_QUATROEMLINHA,null,null);
    }
    public void gravaJogo() throws IOException {
        gestor.gravaJogo();
        propertyChangeSupport.firePropertyChange(PROPRIEDADE_TERMINA,null,null);
    }
    public void voltaAtras(int quant) {
        gestor.voltaAtras(quant);
        propertyChangeSupport.firePropertyChange(PROPRIEDADE_JOGO,null,null);
    }
    public void carregaJogo(String s) {
        gestor.carregaJogo(s);
        propertyChangeSupport.firePropertyChange(PROPRIEDADE_MENU,null,null);
        propertyChangeSupport.firePropertyChange(PROPRIEDADE_REPLAY,null,null);
    }

    //Máquina de Estados
    public void comecar(){
        gestor.comecar();
        propertyChangeSupport.firePropertyChange(PROPRIEDADE_QUATROEMLINHA,null,null);
        propertyChangeSupport.firePropertyChange(PROPRIEDADE_MENU,null,null);
    }
    public void jogar() {
        gestor.jogar();
        propertyChangeSupport.firePropertyChange(PROPRIEDADE_MENU,null,null);
        propertyChangeSupport.firePropertyChange(PROPRIEDADE_PREPARAJOGO,null,null);
    }
    public void escolheTipoJogador(int i) {
        gestor.escolheTipoJogador(i);
        propertyChangeSupport.firePropertyChange(PROPRIEDADE_PREPARAJOGO,null,null);
    }
    public void insereNomes(String [] i){
        gestor.insereNomes(i);
        propertyChangeSupport.firePropertyChange(PROPRIEDADE_JOGO,null,null);
        propertyChangeSupport.firePropertyChange(PROPRIEDADE_PREPARAJOGO,null,null);
    }
    public void iniciaJogo() {
        gestor.iniciaJogo();
        propertyChangeSupport.firePropertyChange(PROPRIEDADE_MINIJOGOS,null,null);
    }
    public void verificaResposta(int i) {
        gestor.verificaResposta(i);
        propertyChangeSupport.firePropertyChange(PROPRIEDADE_MINIJOGOS,null,null);
        propertyChangeSupport.firePropertyChange(PROPRIEDADE_JOGO,null,null);
    }
    public void verificaResposta(String str){
        gestor.verificaResposta(str);
        propertyChangeSupport.firePropertyChange(PROPRIEDADE_MINIJOGOS,null,null);
        propertyChangeSupport.firePropertyChange(PROPRIEDADE_JOGO,null,null);
    }
    public void jogaMiniJogo() {
        gestor.jogaMiniJogo();
        propertyChangeSupport.firePropertyChange(PROPRIEDADE_JOGO,null,null);
        propertyChangeSupport.firePropertyChange(PROPRIEDADE_MINIJOGOS,null,null);
    }
    public void resetaJogo(){
        gestor.resetaJogo();
        propertyChangeSupport.firePropertyChange(PROPRIEDADE_QUATROEMLINHA,null,null);
    }
    public void replays() {
        gestor.replays();
        propertyChangeSupport.firePropertyChange(PROPRIEDADE_QUATROEMLINHA,null,null);
    }
    public void jogaPeca(int i) {
        gestor.jogaPeca(i);
        propertyChangeSupport.firePropertyChange(PROPRIEDADE_JOGO,null,null);
        propertyChangeSupport.firePropertyChange(PROPRIEDADE_TERMINA,null,null);
    }
    public void jogaPecaEspecial(int i) {
        gestor.jogaPecaEspecial(i);
        propertyChangeSupport.firePropertyChange(PROPRIEDADE_JOGO,null,null);
    }
    public void reiniciaJogo() throws IOException {
        gestor.reiniciaJogo();
        propertyChangeSupport.firePropertyChange(PROPRIEDADE_TERMINA,null,null);
        propertyChangeSupport.firePropertyChange(PROPRIEDADE_QUATROEMLINHA,null,null);
    }
    public void voltarMenu() {
        gestor.voltarMenu();
        propertyChangeSupport.firePropertyChange(PROPRIEDADE_PREPARAJOGO,null,null);
        propertyChangeSupport.firePropertyChange(PROPRIEDADE_MENU,null,null);
        propertyChangeSupport.firePropertyChange(PROPRIEDADE_REPLAY,null,null);
    }
    public void gravaContinuacao() throws IOException {
        gestor.gravaContinuacao();
        propertyChangeSupport.firePropertyChange(PROPRIEDADE_JOGO,null,null); //todo verificar se é necessário
    }
    public void carregaContinuacao(String continuacaoJogo,String replays) {
        gestor.carregaContinuacao(continuacaoJogo,replays);
        propertyChangeSupport.firePropertyChange(PROPRIEDADE_MENU,null,null);
        propertyChangeSupport.firePropertyChange(PROPRIEDADE_JOGO,null,null);
    }
    public void voltarJogo() {
        gestor.voltarJogo();
        propertyChangeSupport.firePropertyChange(PROPRIEDADE_MINIJOGOS,null,null);
        propertyChangeSupport.firePropertyChange(PROPRIEDADE_JOGO,null,null);
    }
    public void clearHistorico() {
        gestor.clearHistorico();
        propertyChangeSupport.firePropertyChange(PROPRIEDADE_QUATROEMLINHA,null,null);
    }

    public void disparaReplay(){
        propertyChangeSupport.firePropertyChange(PROPRIEDADE_REPLAY,null,null);
    }

    public int getnPecasEspeciais() {
        return gestor.getnPecasEspeciais();
    }
    public int getnCreditos(){
        return gestor.getnCreditos();
    }
    public int getConta() {
        return gestor.getConta();
    }
    public int getNmr1() {
        return gestor.getNmr1();
    }
    public int getNmr2() {
        return gestor.getNmr2();
    }
    public int getNHumanos() {
        return gestor.getNHumanos();
    }
    public int getRespostasCertas() {
        return gestor.getRespostasCertas();
    }
    public int getNmrAtual(){ return gestor.getNmrAtual();}
    public int getCreditosAssist() {
        return gestor.getCreditosAssist();
    }
    public int getJogadasAssist(){return gestor.getJogadasAssist();}
    public int[] verificaColunaCheia() {
        return gestor.verificaColunaCheia();
    }
    public int [] verificaColunasVazias(){
        return gestor.verificaColunasVazias();
    }
    public int getLargura(){return gestor.getLargura();}
    public int getAltura(){return gestor.getAltura();}


    public String imprimeJogo() {
        return gestor.imprimeJogo();
    }

    public String getVencedor(){
        return gestor.getVencedor();
    }
    public String getOperator() {
        return gestor.getOperator();
    }
    public String getSequencia() {
        return gestor.getSequencia();
    }
    public String imprimeHistorico(){return gestor.imprimeHistorico();}
    public String imprimeAtual(){return gestor.imprimeAtual();}

    public long getTempoMat() {
        return gestor.getTempoMat();
    }
    public long getTempoPalavras() {
        return gestor.getTempoPalavras();
    }

    public boolean getTipoJogador(){
        return gestor.getTipoJogador();
    }

    @Override
    public String toString() {
        return  gestor.toString();
    }

    public int getAlturaPeca() {
        return gestor.getAlturaPeca();
    }

    public int[][] getPos() {
        return gestor.getPos();
    }
}
