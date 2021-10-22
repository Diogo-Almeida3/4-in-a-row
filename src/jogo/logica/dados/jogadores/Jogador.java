package jogo.logica.dados.jogadores;

import java.io.Serializable;

public abstract class Jogador implements Serializable{

    private String nome;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    abstract public int getCreditos();

    abstract public int getPecasEspeciais();

    abstract public int getnJogadas();

    abstract public void setCreditos(int i);

    abstract public void addPecasEspeciais();

    abstract public void addnJogadas();

    abstract public void setJogadas(int i);

    abstract public void subPecasEspeciais();

    abstract public void setPermitidoJogarMiniJogo(int permitidoJogarMiniJogo);

    abstract public int getPermitidoJogarMiniJogo();

    abstract public void resetJogadas();
}
