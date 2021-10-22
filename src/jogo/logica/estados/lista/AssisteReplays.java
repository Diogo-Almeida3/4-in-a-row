package jogo.logica.estados.lista;

import jogo.logica.Enum.Situacao;
import jogo.logica.dados.JogoQuatroLinhaDados;
import jogo.logica.estados.EstadoAdapter;
import jogo.logica.estados.IEstado;

public class AssisteReplays extends EstadoAdapter {

    public AssisteReplays(JogoQuatroLinhaDados jogo) {
        super(jogo);
    }



    @Override
    public IEstado voltarMenu() {
        return new EscolheOpcao(jogo);
    }


    @Override
    public Situacao getSituacaoAtual() {
        return Situacao.ASSISTEREPLAYS;
    }
}
