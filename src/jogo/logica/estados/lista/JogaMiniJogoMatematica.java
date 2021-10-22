package jogo.logica.estados.lista;

import jogo.logica.Enum.Situacao;
import jogo.logica.dados.JogoQuatroLinhaDados;
import jogo.logica.estados.EstadoAdapter;
import jogo.logica.estados.IEstado;

public class JogaMiniJogoMatematica extends EstadoAdapter {
    public JogaMiniJogoMatematica(JogoQuatroLinhaDados jogo) {
        super(jogo);
    }

    @Override
    public IEstado verificaResposta(int i) {
        if(jogo.getTempoRestante() < (30* 1000)){
            jogo.verificaResposta(i);
            if(jogo.getRespostasCertas() >= 5){
                jogo.addPecasEspeciais();
                jogo.setPermitidoJogarMiniJogo(1);
                jogo.resetJogadas(jogo.getNmrAtual());
                jogo.addHistorico("Venceu MiniJogo");
                return new AguardaJogadaJogoSimples(jogo);
            }
        }
        else{
            jogo.resetJogadas(jogo.getNmrAtual());
            jogo.passaJogada();
            jogo.addHistorico("Perdeu MiniJogo");
            if(jogo.getJogadas() > 4)
                return new AguardaJogadaJogoAvancado(jogo);
            return new AguardaJogadaJogoSimples(jogo);
        }
        jogo.geraConta();
        return new JogaMiniJogoMatematica(jogo);
    }

    @Override
    public Situacao getSituacaoAtual() {
        return Situacao.JOGAMINIJOGOMATEMATICA;
    }
}
