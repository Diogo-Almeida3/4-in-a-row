package jogo.iu.gui;

import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import jogo.iu.gui.estados.*;
import jogo.iu.gui.resources.constantes.ConstantesGUI;
import jogo.iu.gui.resources.managers.CSSManager;
import jogo.iu.gui.resources.alertas.Alertas;
import jogo.logica.QuatroLinhaObservavel;


public class PrincipalPane extends BorderPane {
    private QuatroLinhaObservavel quatroLinhaObservavel;

    public PrincipalPane(QuatroLinhaObservavel quatroLinhaObservavel) {
        this.quatroLinhaObservavel = quatroLinhaObservavel;
        CSSManager.setCSS(this,"myStyles.css");
        organizaComponentes();
    }

    private void organizaComponentes(){
        setPrefSize(ConstantesGUI.DIMX_INICIAL, ConstantesGUI.DIMY_INICIAL);
        setMinSize(ConstantesGUI.DIMX_INICIAL, ConstantesGUI.DIMY_INICIAL);

        //Paineis dos estados
        InteracaoEscolheOpcao interacaoEscolheOpcao = new InteracaoEscolheOpcao(quatroLinhaObservavel);
        InteracaoInicio interacaoInicio = new InteracaoInicio(quatroLinhaObservavel);
        InteracaoEscolheNumeroJogadores interacaoEscolheNumeroJogadores = new InteracaoEscolheNumeroJogadores(quatroLinhaObservavel);
        InteracaoInsereNomes interacaoInsereNomes = new InteracaoInsereNomes(quatroLinhaObservavel);
        InteracaoAguardaJogadaJogoSimples interacaoAguardaJogadaJogoSimples = new InteracaoAguardaJogadaJogoSimples(quatroLinhaObservavel);
        InteracaoAguardaJogadaJogoAvancado interacaoAguardaJogadaJogoAvancado = new InteracaoAguardaJogadaJogoAvancado(quatroLinhaObservavel);
        InteracaoJogaMiniJogo interacaoJogaMiniJogo = new InteracaoJogaMiniJogo(quatroLinhaObservavel);
        InteracaoJogaMiniJogoMatematica interacaoJogaMiniJogoMatematica = new InteracaoJogaMiniJogoMatematica(quatroLinhaObservavel);
        InteracaoJogaMiniJogoPalavras interacaoJogaMiniJogoPalavras = new InteracaoJogaMiniJogoPalavras(quatroLinhaObservavel);
        InteracaoTerminaJogo interacaoTerminaJogo = new InteracaoTerminaJogo(quatroLinhaObservavel);
        InteracaoAssisteReplays interacaoAssisteReplays = new InteracaoAssisteReplays(quatroLinhaObservavel);

        //StackPane com paineis dos estados
        StackPane center = new StackPane(
                interacaoInicio,interacaoEscolheOpcao,interacaoEscolheNumeroJogadores,
                interacaoInsereNomes,interacaoAguardaJogadaJogoSimples,interacaoAguardaJogadaJogoAvancado,
                interacaoJogaMiniJogo,interacaoJogaMiniJogoMatematica,interacaoJogaMiniJogoPalavras,
                interacaoTerminaJogo, interacaoAssisteReplays);
        center.setPrefSize(ConstantesGUI.DIMX_INICIAL, ConstantesGUI.DIMY_COMECAR);
        center.setMinSize(ConstantesGUI.DIMX_INICIAL, ConstantesGUI.DIMY_COMECAR);

        setTop(createMenu());
        setCenter(center);
    }

    MenuBar createMenu() {
        MenuBar menuBar = new MenuBar();
        Menu info = new Menu("Informação");
        MenuItem infoDesenvolvedor = new MenuItem("Desenvolvedor");
        infoDesenvolvedor.setOnAction(e -> {
            Alertas.geraInformacao(0);
        });

        MenuItem infoQuatroLinha = new MenuItem("Como jogar");
        infoQuatroLinha.setOnAction(e -> {
           Alertas.geraInformacao(1);
        });

        MenuItem mnExit = new MenuItem("_Exit");
        mnExit.setOnAction(e -> {
            Platform.exit();
        });

        info.getItems().addAll(infoDesenvolvedor,infoQuatroLinha,new SeparatorMenuItem(),mnExit);

        menuBar.getMenus().addAll(info);
        return menuBar;
    }


}
