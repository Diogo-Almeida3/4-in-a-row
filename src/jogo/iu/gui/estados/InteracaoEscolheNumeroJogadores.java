package jogo.iu.gui.estados;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import jogo.iu.gui.resources.customObjects.CustomButton;
import jogo.iu.gui.resources.customObjects.CustomLabel;
import jogo.logica.Enum.Situacao;
import jogo.logica.QuatroLinhaObservavel;

import static jogo.logica.constantes.Constantes.*;
import static jogo.iu.gui.resources.constantes.ConstantesGUI.*;

public class InteracaoEscolheNumeroJogadores extends BorderPane {

    private QuatroLinhaObservavel quatroLinhaObservavel;
    private CustomButton btnMenu,btnCom, btnPla,btnVs;

    public InteracaoEscolheNumeroJogadores(QuatroLinhaObservavel quatroLinhaObservavel){
        this.quatroLinhaObservavel = quatroLinhaObservavel;
        organizaComponentes();
        registaObservador();
        registaListeners();
        atualiza();
    }

    private void organizaComponentes() {
        CustomLabel lblTitulo = new CustomLabel("ESCOLHA DE JOGADORES",DIM_LABEL_ESCOLHENUMEROJOGADORES);
        VBox titulo = new VBox(10);
        titulo.getChildren().addAll(lblTitulo);
        titulo.setAlignment(Pos.BASELINE_CENTER);
        setTop(titulo);

        btnPla = new CustomButton("Humano vs Humano", DIM_BUTTON_ESCOLHENUMEROJOGADORES);
        btnVs = new CustomButton("Humano vs Computador",DIM_BUTTON_ESCOLHENUMEROJOGADORES);
        btnCom = new CustomButton("Computador vs Computador",DIM_BUTTON_ESCOLHENUMEROJOGADORES);
        btnMenu = new CustomButton("Voltar ao menu",DIM_BUTTON_ESCOLHENUMEROJOGADORES);
        atualizaComprimento();

        VBox opcoes = new VBox(30);
        opcoes.setPadding(new Insets(10, 10, 10, 10));
        opcoes.setSpacing(15);
        opcoes.getChildren().addAll(btnPla,btnVs,btnCom, btnMenu);
        opcoes.setAlignment(Pos.CENTER);
        setCenter(opcoes);
    }

    private void atualizaComprimento(){
        btnPla.aumentaBotaoJogadores(DIM_BUTTON_ESCOLHENUMEROJOGADORES);
        btnVs.aumentaBotaoJogadores(DIM_BUTTON_ESCOLHENUMEROJOGADORES);
        btnCom.aumentaBotaoJogadores(DIM_BUTTON_ESCOLHENUMEROJOGADORES);
        btnMenu.aumentaBotaoJogadores(DIM_BUTTON_ESCOLHENUMEROJOGADORES);
    }

    private void registaListeners() {
        btnPla.setOnAction(e->{
            quatroLinhaObservavel.escolheTipoJogador(1);
        });
        btnVs.setOnAction(e->{
            quatroLinhaObservavel.escolheTipoJogador(2);
        });
        btnCom.setOnAction(e->{
            quatroLinhaObservavel.escolheTipoJogador(3);
        });
        btnMenu.setOnAction(e->{
            quatroLinhaObservavel.voltarMenu();
        });
    }

    private void registaObservador() {
        this.quatroLinhaObservavel.addPropertyChangeListener(PROPRIEDADE_PREPARAJOGO,evt -> {
            atualiza();
        });
    }

    private void atualiza() {
        this.setVisible(quatroLinhaObservavel.getSituacaoAtual() == Situacao.ESCOLHENUMEROJOGADORES);
    }
}
