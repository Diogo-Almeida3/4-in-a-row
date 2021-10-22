package jogo.iu.gui.estados;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import static jogo.iu.gui.resources.constantes.ConstantesGUI.*;
import jogo.iu.gui.resources.customObjects.CustomButton;
import jogo.iu.gui.resources.customObjects.CustomLabel;
import jogo.logica.Enum.Situacao;
import jogo.logica.QuatroLinhaObservavel;

import static jogo.logica.constantes.Constantes.*;

public class InteracaoInicio extends BorderPane {
    private QuatroLinhaObservavel quatroLinhaObservavel;
    private CustomButton btnComecar;

    public InteracaoInicio(QuatroLinhaObservavel quatroLinhaObservavel){
        this.quatroLinhaObservavel = quatroLinhaObservavel;
        organizaComponentes();
        registaListeners();
        registaObservador();
        atualiza();
    }

    private void organizaComponentes(){
        CustomLabel labelNome = new CustomLabel("Desenvolvedor: Diogo Dias Almeida", DIM_LABEL_INICIAL);
        CustomLabel labelNumero = new CustomLabel("Número: 2019126638", DIM_LABEL_INICIAL);
        CustomLabel labelMsg = new CustomLabel("---- BEM VINDO AO QUATRO EM LINHA ----", DIM_LABEL_INICIAL);

        btnComecar = new CustomButton("Comecar",DIM_BUTTON_INICIAL);

        VBox labelIntro = new VBox(10);
        labelIntro.setPadding(new Insets(10, 10, 10, 10));
        labelIntro.setSpacing(15);
        labelIntro.getChildren().addAll(labelNome, labelNumero, labelMsg,btnComecar);
        labelIntro.setAlignment(Pos.CENTER);
        setCenter(labelIntro);
    }

    private void registaListeners(){
        btnComecar.setOnAction(e ->{
            quatroLinhaObservavel.comecar();
        });
    }

    private void registaObservador(){
        this.quatroLinhaObservavel.addPropertyChangeListener(PROPRIEDADE_QUATROEMLINHA, evt -> {
            atualiza();
        });
    }

    private void atualiza(){
        this.setVisible(quatroLinhaObservavel.getSituacaoAtual() == Situacao.INICIO);
    }
}