package jogo.logica.estados.lista;

import jogo.logica.Enum.Situacao;
import jogo.logica.dados.JogoQuatroLinhaDados;
import jogo.logica.estados.EstadoAdapter;
import jogo.logica.estados.IEstado;
import jogo.logica.memento.Memento;

import java.util.ArrayList;
import java.util.Stack;

public class Inicio extends EstadoAdapter {

    public Inicio(JogoQuatroLinhaDados jogo){
        super(jogo);
    }

    @Override
    public IEstado comecar() {
        jogo.addHistorico("Inicio");
        return new EscolheOpcao(jogo);
    }

    @Override
    public Situacao getSituacaoAtual() {
        return Situacao.INICIO;
    }
}
