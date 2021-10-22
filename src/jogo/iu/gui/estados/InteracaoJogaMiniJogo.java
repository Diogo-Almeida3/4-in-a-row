package jogo.iu.gui.estados;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import jogo.iu.gui.resources.customObjects.CustomButton;
import jogo.iu.gui.resources.customObjects.CustomLabel;
import jogo.logica.Enum.Situacao;
import jogo.logica.QuatroLinhaObservavel;

import static jogo.iu.gui.resources.constantes.ConstantesGUI.*;
import static jogo.logica.constantes.Constantes.PROPRIEDADE_MINIJOGOS;
import static jogo.logica.constantes.Constantes.PROPRIEDADE_QUATROEMLINHA;

public class InteracaoJogaMiniJogo extends BorderPane {
    private QuatroLinhaObservavel quatroLinhaObservavel;
    private CustomButton btnComeca,btnCancela;

    public InteracaoJogaMiniJogo(QuatroLinhaObservavel quatroLinhaObservavel){
        this.quatroLinhaObservavel = quatroLinhaObservavel;
        organizaComponentes();
        registaListeners();
        registaObservador();
        atualiza();
    }

    private void organizaComponentes() {
        CustomLabel lblMiniJogo = new CustomLabel("MINIJOGOS",DIM_LABEL_ESCOLHEOPCAO_MENU);
        VBox titulo = new VBox(10);
        titulo.getChildren().addAll(lblMiniJogo);
        titulo.setAlignment(Pos.BASELINE_CENTER);
        setTop(titulo);

        btnComeca = new CustomButton("Começa MiniJogo",DIM_BUTTON_ESCOLHEOPCAO);
        btnCancela = new CustomButton("Cancelar",DIM_BUTTON_ESCOLHEOPCAO);

        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(20, 20, 20, 20));
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(btnComeca,btnCancela);
        setCenter(vBox);
    }

    private void registaListeners() {
        btnComeca.setOnAction(e -> {
            quatroLinhaObservavel.iniciaJogo();
        });
        btnCancela.setOnAction(e -> {
            quatroLinhaObservavel.voltarJogo();
        });
    }


    private void registaObservador() {
        this.quatroLinhaObservavel.addPropertyChangeListener(PROPRIEDADE_MINIJOGOS, evt -> {
            atualiza();
        });
    }

    private void atualiza() {
        this.setVisible(quatroLinhaObservavel.getSituacaoAtual() == Situacao.JOGAMINIJOGO);
    }
}
