package jogo.logica.estados.lista;

import jogo.logica.Enum.Situacao;
import jogo.logica.dados.JogoQuatroLinhaDados;
import jogo.logica.estados.EstadoAdapter;
import jogo.logica.estados.IEstado;

public class EscolheNumeroJogadores extends EstadoAdapter {

    public EscolheNumeroJogadores(JogoQuatroLinhaDados jogo) {
        super(jogo);
    }

    @Override
    public IEstado escolheTipoJogador(int i) {
        jogo.addHistorico("Insere Nomes");
        jogo.adicionaJogadores(i);
        return new InsereNomes(jogo);
    }

    @Override
    public IEstado voltarMenu() {
        return new EscolheOpcao(jogo);
    }

    @Override
    public Situacao getSituacaoAtual() {
        return Situacao.ESCOLHENUMEROJOGADORES;
    }
}
