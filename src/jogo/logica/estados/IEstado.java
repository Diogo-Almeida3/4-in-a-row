package jogo.logica.estados;

import jogo.logica.Enum.Situacao;

public interface IEstado {
    /* Menu */
    IEstado comecar();
    IEstado voltarMenu();
    IEstado jogar();

    /* Configuração do jogo */
    IEstado escolheTipoJogador(int i);
    IEstado insereNomes(String [] i);

    /* Jogo */
    IEstado jogaPeca(int i);
    IEstado jogaPecaEspecial(int i);
    IEstado jogaMinijogo();
    IEstado iniciaJogo();
    IEstado reiniciaJogo();

    /* MiniJogos */
    IEstado verificaResposta(int i);
    IEstado verificaResposta(String i);

    /* Replays */
    IEstado replays();
    IEstado atualiza(int i, int atual, int cred, int cred2, int nJogadas);

    IEstado voltarJogo();
    Situacao getSituacaoAtual();
}
