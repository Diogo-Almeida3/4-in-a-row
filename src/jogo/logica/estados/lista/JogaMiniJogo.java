package jogo.logica.estados.lista;

import jogo.logica.Enum.Situacao;
import jogo.logica.dados.JogoQuatroLinhaDados;
import jogo.logica.estados.EstadoAdapter;
import jogo.logica.estados.IEstado;

public class JogaMiniJogo extends EstadoAdapter {

    public JogaMiniJogo(JogoQuatroLinhaDados jogo) {
        super(jogo);
    }

    @Override
    public IEstado iniciaJogo() {
        if (jogo.getJogoAtivo() == 1) {
            jogo.addHistorico("MiniJogo Matematica");
            jogo.geraConta();
            jogo.setTempoStart();
            return new JogaMiniJogoMatematica(jogo);
        }
        jogo.addHistorico("MiniJogo Palavras");
        jogo.setSequencia();
        jogo.setTempoStart();
        return new JogoMiniJogoPalavras(jogo);
    }

    @Override
    public IEstado voltarJogo() {
        return new AguardaJogadaJogoAvancado(jogo);
    }

    @Override
    public Situacao getSituacaoAtual() {
        return  Situacao.JOGAMINIJOGO;
    }
}
