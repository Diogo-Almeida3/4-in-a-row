package jogo.logica.estados.lista;

import jogo.logica.Enum.Situacao;
import jogo.logica.dados.JogoQuatroLinhaDados;
import jogo.logica.estados.EstadoAdapter;
import jogo.logica.estados.IEstado;

public class InsereNomes extends EstadoAdapter {
    public InsereNomes(JogoQuatroLinhaDados jogo) {
        super(jogo);
    }

    @Override
    public IEstado insereNomes(String[] nomes) {
        if(nomes[0].equalsIgnoreCase(nomes[1])){
            jogo.addHistorico("Nomes Iguais");
            return this;
        }

        jogo.addHistorico("Inicio Jogo");
        jogo.defineNomes(nomes);
        return new AguardaJogadaJogoSimples(jogo);
    }

    @Override
    public IEstado voltarMenu() {
        return new EscolheOpcao(jogo);
    }

    @Override
    public Situacao getSituacaoAtual() {
        return Situacao.INSERENOMES;
    }
}
