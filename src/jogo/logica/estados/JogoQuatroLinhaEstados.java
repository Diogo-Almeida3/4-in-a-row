package jogo.logica.estados;

import jogo.logica.Enum.Situacao;
import jogo.logica.dados.JogoQuatroLinhaDados;
import jogo.logica.estados.lista.EscolheOpcao;
import jogo.logica.estados.lista.Inicio;
import jogo.logica.memento.Memento;

import java.io.IOException;
import java.io.Serializable;

public class JogoQuatroLinhaEstados implements Serializable {

    //Referencia para o estado atual
    private IEstado estado;
    private JogoQuatroLinhaDados jogo;

    public JogoQuatroLinhaEstados() {
        resetaJogo();
    }

    private void setEstado(IEstado estado){
        this.estado = estado;
    }

    public void comecar(){
        setEstado(estado.comecar());
    }
    public void jogar() {
        setEstado(estado.jogar());
    }
    public void escolheTipoJogador(int i) {
        setEstado(estado.escolheTipoJogador(i));
    }
    public void insereNomes(String [] i){setEstado(estado.insereNomes(i));}
    public void voltarMenu() {
        setEstado(estado.voltarMenu());
    }
    public void jogaPeca(int i) {
        setEstado(estado.jogaPeca(i));
    }
    public void jogaPecaEspecial(int i) {
        setEstado(estado.jogaPecaEspecial(i));
    }
    public void reiniciaJogo() {
        setEstado(estado.reiniciaJogo());
    }
    public void iniciaJogo() {
        setEstado(estado.iniciaJogo());
    }
    public void verificaResposta(int i) {
        setEstado(estado.verificaResposta(i));
    }
    public void verificaResposta(String str){setEstado(estado.verificaResposta(str));}
    public void jogaMiniJogo() {
        setEstado(estado.jogaMinijogo());
    }
    public void atualiza(int i, int atual, int cred, int cred2, int nJogadas){setEstado(estado.atualiza(i,atual,cred,cred2,nJogadas));}
    public void replays() {
        setEstado(estado.replays());
    }
    public void voltarJogo() {
        setEstado(estado.voltarJogo());
    }
    public void clearHistorico() {
        jogo.clearHistorico();
    }

    public int getnPecasEspeciais() {
        return jogo.getPecasEspeciais();
    }
    public int getnCreditos(){
        return jogo.getCreditos();
    }
    public int getNHumanos() {
        return jogo.getNHumanos();
    }
    public int getNmrAtual(){return jogo.getNmrAtual();}
    public int getCreditosAssist() {
        return jogo.getCreditosAssist();
    }
    public int getConta() {
        return jogo.getConta();
    }
    public int getNmr1() {
        return jogo.getNmr1();
    }
    public int getNmr2() {
        return jogo.getNmr2();
    }
    public int getRespostasCertas() {
        return jogo.getRespostasCertas();
    }
    public int getJogadasAssist(){return jogo.getJogadasAssist();}
    public int[] verificaColunaCheia() {
        return jogo.verificaColunaCheia();
    }
    public int [] verificaColunasVazias(){
        return jogo.verificaColunasVazias();
    }

    public String getVencedor(){
        return jogo.getVencedor();
    }
    public String getOperator() {
        return jogo.getOperator();
    }
    public String getSequencia() {
        return jogo.getSequencia();
    }
    public String imprimeJogo() {
        return jogo.toString();
    }
    public String imprimeHistorico(){return jogo.imprimeHistorico();}
    public String imprimeAtual() {
        return jogo.imprimeAtual();
    }

    public long getTempoPalavras() {
        return jogo.getTempoPalavras();
    }
    public long getTempoMat() {
        return jogo.getTempoMat();
    }

    public boolean getTipoJogador(){return jogo.tipoJogador();}

    /* Memento */
    public void setMementoJogo(Memento mem){
        try {
            jogo = (JogoQuatroLinhaDados) mem.getSnapshot();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Memento getMementoJogo() throws IOException {
            return new Memento(jogo);
    }
    public void resetaJogo(){
        jogo = new JogoQuatroLinhaDados();
        estado = new Inicio(jogo);
    }

    public void volta(){
        jogo = new JogoQuatroLinhaDados();
        estado = new EscolheOpcao(jogo);
    }

    public Situacao getSituacaoAtual(){
        return estado.getSituacaoAtual();
    }

    @Override
    public String toString() {
        return jogo.toString();
    }

    public int getLargura() {
        return jogo.getLargura();
    }
    public int getAltura() {
        return jogo.getAltura();
    }

    public int getAlturaPeca() {
        return jogo.getAlturaPeca();
    }

    public int[][] getPos() {
        return jogo.getPos();
    }
}