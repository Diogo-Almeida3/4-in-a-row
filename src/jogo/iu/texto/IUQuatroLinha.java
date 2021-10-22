package jogo.iu.texto;

import jogo.logica.Utils;
import jogo.logica.Enum.Situacao;
import jogo.logica.memento.Gestor;

import java.io.IOException;

public class IUQuatroLinha {

    private Gestor jogoEstados;
    private boolean sair = false;

    public IUQuatroLinha(Gestor jogo) { this.jogoEstados = jogo; }

    public void corre() throws IOException {
        while(!sair){
            Situacao situacao = jogoEstados.getSituacaoAtual();
            System.out.println(jogoEstados.imprimeHistorico());
            jogoEstados.clearHistorico();
            switch (situacao){
                case INICIO:
                    uiInicio();
                    break;
                case ESCOLHEOPCAO:
                    uiEscolheOpcao();
                    break;
                case ASSISTEREPLAYS:
                    uiAssisteReplays();
                    break;
                case ESCOLHENUMEROJOGADORES:
                    uiEscolheNumeroJogadores();
                    break;
                case INSERENOMES:
                    uiInsereNomes();
                    break;
                case AGUARDAJOGADA:
                    System.out.println(jogoEstados.imprimeAtual() + jogoEstados.toString());
                    uiAguardaJogadaJogoSimples();
                    break;
                case AGUARDAJOGADAAVANCADA:

                    System.out.println(jogoEstados.imprimeAtual() + jogoEstados.toString());
                    uiAguardaJogadaJogoAvancada();
                    break;
                case TERMINAJOGO:
                    System.out.println("\n\nVencedor: " + jogoEstados.getVencedor() + "\n");
                    System.out.println(jogoEstados.toString());
                    uiTerminaJogo();
                    break;
                case JOGAMINIJOGO:
                    uiMiniJogo();
                    break;
                case JOGAMINIJOGOMATEMATICA:
                    System.out.println("Tempo Restante: " + jogoEstados.getTempoMat()+"\nRespostas Certas: " + jogoEstados.getRespostasCertas());
                    uiMiniJogoMatematica();
                    break;
                case JOGAMINIJOGOPALAVRAS:
                    uiMiniJogoPalavras();
                    break;
            }
        }
    }

    private void uiAssisteReplays() {
        switch (Utils.escolheOpcao("Avancar","Voltar ao menu")){
            case 0:
                jogoEstados.voltarMenu();
                break;
            case 1:
                System.out.println(jogoEstados.imprimeJogo());
                break;
        }
    }

    private void uiMiniJogo() {
        switch (Utils.escolheOpcao("Começar","Voltar Atrás")){
            case 0:
                jogoEstados.voltarJogo();
                break;
            case 1:
                jogoEstados.iniciaJogo();
                break;
        }
    }

    private void uiTerminaJogo() throws IOException {

        switch (Utils.escolheOpcao("Reiniciar Jogo","Sair")){
            case 0:
                jogoEstados.gravaJogo();
                sair = true;
                break;
            case 1:
                jogoEstados.reiniciaJogo();
                break;
        }
    }

    private void uiAguardaJogadaJogoAvancada() throws IOException {
        switch (Utils.escolheOpcao("Jogar Peça","Jogar Peça Especial", "Verificar Inventário","Voltar Atrás","Jogar MiniJogo","Sair")){
            case 0:
                jogoEstados.gravaContinuacao();
                sair = true;
                break;
            case 1:
                jogoEstados.jogaPeca(Utils.pedePosicaoPeca(jogoEstados.getTipoJogador(),jogoEstados.verificaColunaCheia()));
                break;
            case 2:
                jogoEstados.jogaPecaEspecial(Utils.pedePosicaoPecaEspecial(jogoEstados.getTipoJogador(),jogoEstados.verificaColunasVazias(),jogoEstados.getnPecasEspeciais()));
                break;
            case 3:
                System.out.println(Utils.verificaInventario(jogoEstados.getnCreditos(),jogoEstados.getnPecasEspeciais()));
                break;
            case 4:
                jogoEstados.voltaAtras(Utils.pedeRecuos(jogoEstados.getTipoJogador(), jogoEstados.getnCreditos()));
                break;
            case 5:
                jogoEstados.jogaMiniJogo();
                break;
        }
    }

    private void uiAguardaJogadaJogoSimples() throws IOException {
        switch (Utils.escolheOpcao("Jogar Peça","Jogar Peça Especial","Verificar Inventário","Voltar Atrás", "Sair")){
            case 0:
                jogoEstados.gravaContinuacao();
                sair = true;
                break;
            case 1:
                jogoEstados.jogaPeca(Utils.pedePosicaoPeca(jogoEstados.getTipoJogador(),jogoEstados.verificaColunaCheia()));
                break;
            case 2:
                jogoEstados.jogaPecaEspecial(Utils.pedePosicaoPecaEspecial(jogoEstados.getTipoJogador(),jogoEstados.verificaColunasVazias(),jogoEstados.getnPecasEspeciais()));
                break;
            case 3:
                System.out.println(Utils.verificaInventario(jogoEstados.getnCreditos(),jogoEstados.getnPecasEspeciais()));
                break;
            case 4:
                jogoEstados.voltaAtras(Utils.pedeRecuos(jogoEstados.getTipoJogador(), jogoEstados.getnCreditos()));
                break;
        }
    }

    private void uiInsereNomes() {
        switch (Utils.escolheOpcao("Inserir Nomes","Voltar ao Menu")){
            case 0:
                jogoEstados.voltarMenu();
                break;
            case 1:
                jogoEstados.insereNomes(Utils.pedeNomes(jogoEstados.getNHumanos()));
                break;
        }
    }

    private void uiEscolheNumeroJogadores() {
        switch (Utils.escolheOpcao("Humano vs Humano","Humano vs Computador","Computador vs Computador","Voltar ao Menu")){
            case 0:
                jogoEstados.voltarMenu();
                break;
            case 1:
                jogoEstados.escolheTipoJogador(1);
                break;
            case 2:
                jogoEstados.escolheTipoJogador(2);
                break;
            case 3:
                jogoEstados.escolheTipoJogador(3);
                break;
        }
    }

    private void uiEscolheOpcao() {
        switch (Utils.escolheOpcao("Jogar","Continuar Jogo","Replays","Sair")){
            case 0 :
                sair = true;
                break;
            case 1:
                jogoEstados.jogar();
                break;
            case 2:
                jogoEstados.carregaContinuacao("continuacaoJogoHist","continuacaoJogoReplay");
                break;
            case 3:
                jogoEstados.carregaJogo(Utils.escolheFicheiro());
                break;
        }
    }

    private void uiInicio() {
        System.out.println("\n-> Desenvolvedor: Diogo Dias Almeida\n-> Número: 2019126638\n");
        System.out.println("---- BEM VINDO AO QUATRO EM LINHA ----");
        jogoEstados.comecar();
    }

    private void uiMiniJogoPalavras() {
        jogoEstados.verificaResposta(Utils.pedeString("Tempo Restante: " + jogoEstados.getTempoPalavras()+"\nSequencia: " + jogoEstados.getSequencia()));
    }

    private void uiMiniJogoMatematica() {
        int res = Utils.pedeInteiro("Quanto é " + jogoEstados.getNmr1()+ " " + jogoEstados.getOperator() + " " + jogoEstados.getNmr2() + "?\n\nResposta:");
        System.out.println("Resposta Correta: " + jogoEstados.getConta() + "\n");
        jogoEstados.verificaResposta(res);
    }
}