package jogo.iu.gui.estados;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import jogo.iu.gui.resources.customObjects.CustomButton;
import jogo.iu.gui.resources.customObjects.CustomLabel;
import jogo.iu.gui.resources.customObjects.CustomTextField;
import jogo.logica.Enum.Situacao;
import jogo.logica.QuatroLinhaObservavel;

import java.text.DecimalFormat;

import static jogo.iu.gui.resources.constantes.ConstantesGUI.*;
import static jogo.logica.constantes.Constantes.PROPRIEDADE_MINIJOGOS;

public class InteracaoJogaMiniJogoPalavras extends BorderPane {

    private QuatroLinhaObservavel quatroLinhaObservavel;
    private CustomButton btnEnviaResposta;
    private CustomTextField txtResposta;
    private CustomLabel lblPalavras,lblTempo,lblSequencia;
    private Timeline timeline;
    private int second;
    private String ddSecond;
    private DecimalFormat dFormat = new DecimalFormat("00");
    private int flag;

    public InteracaoJogaMiniJogoPalavras(QuatroLinhaObservavel quatroLinhaObservavel) {
        this.quatroLinhaObservavel = quatroLinhaObservavel;
        flag = 0;
        organizaComponentes();
        registaListeners();
        registaObservador();
        atualiza();
    }

    private void inserePalavras(){
        lblSequencia.setText("Sequencia:");
        lblPalavras.setText(quatroLinhaObservavel.getSequencia());
        lblTempo.setText("Tempo restante: " + quatroLinhaObservavel.getTempoPalavras());
    }

    private void countDown(){
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            second--;
            ddSecond = dFormat.format(second);
            lblTempo.setText("Tempo restante: " + ddSecond);
            if(second <= 0){
                quatroLinhaObservavel.verificaResposta("");
            }
        }));
        timeline.setCycleCount((int) quatroLinhaObservavel.getTempoPalavras());
        timeline.play();
    }

    private void organizaComponentes() {
        CustomLabel lblMiniJogo = new CustomLabel("MiniJogo Palavras",DIM_LABEL_MINIJOGOS);
        VBox titulo = new VBox(10);
        titulo.getChildren().addAll(lblMiniJogo);
        titulo.setAlignment(Pos.BASELINE_CENTER);
        setTop(titulo);

        lblSequencia = new CustomLabel("",DIM_LABEL_MINIJOGOS_PALAVRAS);
        lblTempo = new CustomLabel("",DIM_LABEL_MINIJOGOS_INFO);
        lblPalavras = new CustomLabel("",DIM_LABEL_MINIJOGOS_PALAVRAS);
        txtResposta = new CustomTextField(DIM_TEXTFIELD_MINIJOGOS);
        btnEnviaResposta = new CustomButton("Envia Reposta",DIM_BUTTON_MINIJOGOS_ENVIA);

        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(20, 20, 20, 20));
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(lblTempo,lblPalavras,txtResposta,btnEnviaResposta);
        setCenter(vBox);
    }

    private void verificaResposta(){
        if(!txtResposta.getText().equals("")){
            quatroLinhaObservavel.verificaResposta(txtResposta.getText());
        }
        txtResposta.setText("");
    }

    private void registaListeners() {
        btnEnviaResposta.setOnAction(e -> {
            verificaResposta();
        });
        txtResposta.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    verificaResposta();
                }
            }
        });
    }

    private void registaObservador() {
        this.quatroLinhaObservavel.addPropertyChangeListener(PROPRIEDADE_MINIJOGOS, evt -> {
            atualiza();
        });
    }

    private void atualiza() {
        if(quatroLinhaObservavel.getSituacaoAtual() == Situacao.JOGAMINIJOGOPALAVRAS){
            if(flag == 0){
                second = (int) quatroLinhaObservavel.getTempoPalavras();
                lblTempo.setText("Tempo restante: " + second);
                countDown();
                flag = 1;
            }
            inserePalavras();
        } else
            flag = 0;
        this.setVisible(quatroLinhaObservavel.getSituacaoAtual() == Situacao.JOGAMINIJOGOPALAVRAS);
    }
}
