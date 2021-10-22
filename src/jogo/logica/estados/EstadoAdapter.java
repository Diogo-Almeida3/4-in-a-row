package jogo.logica.estados;

import jogo.logica.dados.JogoQuatroLinhaDados;

import java.io.Serializable;

public abstract class EstadoAdapter implements IEstado, Serializable {

    protected JogoQuatroLinhaDados jogo;

    public EstadoAdapter(JogoQuatroLinhaDados jogo){
        this.jogo = jogo;
    }

    @Override
    public IEstado comecar() {
        return this;
    }

    @Override
    public IEstado insereNomes(String[] i) {
        return this;
    }

    @Override
    public IEstado jogar() {
        return this;
    }

    @Override
    public IEstado escolheTipoJogador(int i) {
        return this;
    }

    @Override
    public IEstado reiniciaJogo() {
        return this;
    }

    @Override
    public IEstado voltarMenu() {
        return this;
    }

    @Override
    public IEstado jogaPeca(int i) {
        return this;
    }

    @Override
    public IEstado jogaPecaEspecial(int i) {
        return this;
    }

    @Override
    public IEstado jogaMinijogo() {
        return this;
    }

    @Override
    public IEstado iniciaJogo() {
        return this;
    }

    @Override
    public IEstado verificaResposta(int i) {
        return this;
    }

    @Override
    public IEstado verificaResposta(String i) {
        return this;
    }

    @Override
    public IEstado atualiza(int i, int atual, int cred, int cred2, int nJogadas){return this;}

    @Override
    public IEstado replays() {
        return this;
    }

    @Override
    public IEstado voltarJogo() {
        return this;
    }
}
