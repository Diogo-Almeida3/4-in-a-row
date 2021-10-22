package jogo.logica.estados.lista;

import jogo.logica.Enum.Situacao;
import jogo.logica.dados.JogoQuatroLinhaDados;
import jogo.logica.estados.EstadoAdapter;
import jogo.logica.estados.IEstado;

public class AguardaJogadaJogoAvancado extends EstadoAdapter {

    public AguardaJogadaJogoAvancado(JogoQuatroLinhaDados jogo) {
        super(jogo);
    }

    @Override
    public IEstado jogaPeca(int i) {
        /* Joga a peça */
        if(jogo.jogaPeca(i))
            jogo.addHistorico("Peça Jogada");
        else{
            jogo.addHistorico("Erro ao jogar a peça");
            return this;
        }

        /* Caso um dos jogadores vença*/
        if(!jogo.verificaVitoria()){
            /* Caso o tabuleiro não esteja completo mantem se no mesmo estado */
            if(!jogo.verificaTabuleiro()){
                jogo.setPermitidoJogarMiniJogo(0);
                if(jogo.getJogadas() > 4) {

                    return new AguardaJogadaJogoAvancado(jogo);
                } else{
                    return new AguardaJogadaJogoSimples(jogo);
                }
            } else
                jogo.addHistorico("Tabuleiro cheio");
        } else
            jogo.addHistorico("Um dos jogadores venceu o jogo");
        return new TerminaJogo(jogo);
    }

    @Override
    public IEstado jogaPecaEspecial(int i) {
        if(jogo.getPecasEspeciais() > 0 && jogo.tipoJogador()) {
            jogo.setPermitidoJogarMiniJogo(0);
            jogo.jogaPecaEspecial(i);
            jogo.addHistorico("Peça especial jogada");
        }
        else
            jogo.addHistorico("Erro ao jogar peça especial");
        if(jogo.getJogadas() > 4)
            return new AguardaJogadaJogoAvancado(jogo);
        return new AguardaJogadaJogoSimples(jogo);
    }

    @Override
    public IEstado jogaMinijogo() {
        if(jogo.tipoJogador() && jogo.getPermitidoJogarMiniJogo() == 0) {
            jogo.setMiniJogo(jogo.getMinijogoAtual());
            jogo.alteraMinijogoAtual();
            jogo.addHistorico("A entrar nos minijogos");
            return new JogaMiniJogo(jogo);
        } else{
            jogo.addHistorico("Erro ao entrar nos minijogos");
        }
        return new AguardaJogadaJogoAvancado(jogo);
    }

    @Override
    public IEstado atualiza(int i, int atual, int cred, int cred2, int nJogadas) {
        jogo.undoUpdate(i,atual,cred,cred2, nJogadas);
        jogo.resetJogadas(atual);
        jogo.addHistorico("Undo");
        if(jogo.getJogadas() > 4)
            return new AguardaJogadaJogoAvancado(jogo);
        return new AguardaJogadaJogoSimples(jogo);
    }

    @Override
    public Situacao getSituacaoAtual() {
        return Situacao.AGUARDAJOGADAAVANCADA;
    }
}
