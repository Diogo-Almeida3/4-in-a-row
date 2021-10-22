package jogo.logica.estados.lista;

import jogo.logica.Enum.Situacao;
import jogo.logica.dados.JogoQuatroLinhaDados;
import jogo.logica.estados.EstadoAdapter;
import jogo.logica.estados.IEstado;

public class TerminaJogo extends EstadoAdapter {


    public TerminaJogo(JogoQuatroLinhaDados jogo) {
        super(jogo);
    }

    @Override
    public IEstado reiniciaJogo() {
        jogo.preparaJogo();
        return new Inicio(jogo);
    }


    @Override
    public Situacao getSituacaoAtual() {
        return Situacao.TERMINAJOGO;
    }
}
