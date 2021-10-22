package jogo.logica.estados.lista;

import jogo.logica.Enum.Situacao;
import jogo.logica.dados.JogoQuatroLinhaDados;
import jogo.logica.estados.EstadoAdapter;
import jogo.logica.estados.IEstado;

public class EscolheOpcao extends EstadoAdapter {
    public EscolheOpcao(JogoQuatroLinhaDados jogo)
    {
        super(jogo);
    }

    @Override
    public IEstado replays() {
        jogo.addHistorico("Replay");
        return new AssisteReplays(jogo);
    }

    @Override
    public IEstado iniciaJogo() {
        return new AguardaJogadaJogoSimples(jogo);
    }

    @Override
    public IEstado jogar() {
        jogo.addHistorico("Escolher Jogo");
        return new EscolheNumeroJogadores(jogo);
    }

    @Override
    public IEstado voltarMenu() {
        return this;
    }

    @Override
    public Situacao getSituacaoAtual() {
        return Situacao.ESCOLHEOPCAO;
    }
}
