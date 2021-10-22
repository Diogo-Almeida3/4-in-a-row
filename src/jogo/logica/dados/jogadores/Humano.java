package jogo.logica.dados.jogadores;

public class Humano extends Jogador{


    private int creditos;
    private int pecasEspeciais;
    private int nJogadas;
    private int permitidoJogarMiniJogo;
    public Humano(){
        this.creditos = 5;
        this.pecasEspeciais = 0;
        this.nJogadas = 0;
        this.permitidoJogarMiniJogo = 0;
    }

    @Override
    public int getCreditos() {
        return creditos;
    }
    @Override
    public int getPecasEspeciais() {
        return pecasEspeciais;
    }
    @Override
    public int getnJogadas() {
        return nJogadas;
    }
    @Override
    public void setCreditos(int i) {
        this.creditos = i;
    }
    @Override
    public void addPecasEspeciais() {
        this.pecasEspeciais += 1;
    }
    @Override
    public void addnJogadas() {
        this.nJogadas += 1;
    }
    @Override
    public void subPecasEspeciais() {
        this.pecasEspeciais -= 1;
    }

    @Override
    public void setJogadas(int i) {
        this.nJogadas = i;
    }

    @Override
    public int getPermitidoJogarMiniJogo() {
        return permitidoJogarMiniJogo;
    }
    @Override
    public void setPermitidoJogarMiniJogo(int permitidoJogarMiniJogo) {
        this.permitidoJogarMiniJogo = permitidoJogarMiniJogo;
    }

    @Override
    public void resetJogadas() {
        this.nJogadas = 0;
    }

    @Override
    public String toString() {
        return "Inventário: " +
                "creditos=" + creditos +
                ", pecasEspeciais=" + pecasEspeciais;
    }
}
