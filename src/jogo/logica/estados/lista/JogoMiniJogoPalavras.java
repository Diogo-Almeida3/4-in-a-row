package jogo.logica.estados.lista;

import jogo.logica.Enum.Situacao;
import jogo.logica.dados.JogoQuatroLinhaDados;
import jogo.logica.estados.EstadoAdapter;
import jogo.logica.estados.IEstado;

public class JogoMiniJogoPalavras extends EstadoAdapter {
    public JogoMiniJogoPalavras(JogoQuatroLinhaDados jogo) {
        super(jogo);
    }

    @Override
    public IEstado verificaResposta(String i) {

        if(jogo.getTempoRestante() < (jogo.getTempoPalavras() * 1000)){
            if(jogo.verificaResposta(i)){
                jogo.addPecasEspeciais();
                jogo.setPermitidoJogarMiniJogo(1);
                jogo.resetJogadas(jogo.getNmrAtual());
                jogo.addHistorico("Venceu Minijogo");
                return new AguardaJogadaJogoSimples(jogo);
            }
        }
        jogo.resetJogadas(jogo.getNmrAtual());
        jogo.passaJogada();
        jogo.addHistorico("Perdeu MiniJogo");
        if(jogo.getJogadas() > 4)
            return new AguardaJogadaJogoAvancado(jogo);
        return new AguardaJogadaJogoSimples(jogo);
    }

    @Override
    public Situacao getSituacaoAtual() {
        return Situacao.JOGAMINIJOGOPALAVRAS;
    }
}
