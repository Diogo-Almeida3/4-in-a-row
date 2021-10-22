package jogo.iu.gui.estados;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import jogo.iu.gui.resources.customObjects.CustomButton;
import jogo.iu.gui.resources.customObjects.CustomLabel;
import jogo.logica.Enum.Situacao;
import jogo.logica.QuatroLinhaObservavel;

import java.io.IOException;
import static jogo.iu.gui.resources.constantes.ConstantesGUI.*;
import static jogo.iu.gui.resources.constantes.constantesPintura.COR_BACKGROUND;
import static jogo.logica.constantes.Constantes.*;

public class InteracaoTerminaJogo extends BorderPane {
    private QuatroLinhaObservavel quatroLinhaObservavel;
    private CustomButton btnReinicia,btnSair;
    private CustomLabel lblVencedor;
    private Circle[][] tabuleiro;

    public InteracaoTerminaJogo(QuatroLinhaObservavel quatroLinhaObservavel) {
        this.quatroLinhaObservavel = quatroLinhaObservavel;
        organizaComponentes();
        registaListeners();
        registaObservador();
        atualiza();
    }

    private void changeBackground(Circle region, Color color){
        region.setFill(color);
    }

    private void insereVencedor(){
        lblVencedor.setText("Vencedor: " + quatroLinhaObservavel.getVencedor());
    }

    private void organizaComponentes() {

        int largura = quatroLinhaObservavel.getLargura(); //7
        int altura = quatroLinhaObservavel.getAltura(); // 6
        {
            tabuleiro = new Circle[altura][largura];  // 6 x 7
            GridPane grid = new GridPane();
            grid.setVgap(DIM_GAP_JOGO);
            grid.setHgap(DIM_GAP_JOGO);
            for (int i = 0; i < altura; i++) {
                for (int j = 0; j < largura; j++) {
                    Circle pane = new Circle();
                    pane.setRadius(DIM_TABULEIRO_JOGO);
                    changeBackground(pane, Color.WHITE);
                    grid.add(pane, j, i);
                    tabuleiro[i][j] = pane;
                }
            }
            grid.setAlignment(Pos.CENTER);
            grid.setBackground(new Background(new BackgroundFill(Paint.valueOf(COR_BACKGROUND), new CornerRadii(DIM_CORNERS_JOGO),new Insets(DIM_TOPBOTTOM_JOGO,DIM_LEFTRIGHT_JOGO,DIM_TOPBOTTOM_JOGO,DIM_LEFTRIGHT_JOGO))));
            this.setCenter(grid);
        }

        btnReinicia = new CustomButton("Reiniciar Jogo",DIM_BUTTON_TERMINA);
        btnSair = new CustomButton("Sair",DIM_BUTTON_TERMINA);
        lblVencedor = new CustomLabel("",DIM_LABEL_JOGO_NOME);
        criaCaixas();
    }

    private void criaCaixas(){
        VBox caixa = new VBox();
        caixa.setPadding(new Insets(20, 20, 20, 20));
        caixa.setSpacing(20);
        caixa.setAlignment(Pos.CENTER);
        caixa.getChildren().addAll(btnReinicia,btnSair);
        setRight(caixa);

        VBox nome = new VBox(10);
        nome.getChildren().addAll(lblVencedor);
        nome.setAlignment(Pos.BASELINE_CENTER);
        setTop(nome);
    }

    private void registaListeners() {
        btnSair.setOnAction(e -> {
            try {
                quatroLinhaObservavel.gravaJogo();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            Platform.exit();
        });
        btnReinicia.setOnAction(e -> {
            try {
                quatroLinhaObservavel.reiniciaJogo();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }

    private void registaObservador() {
        this.quatroLinhaObservavel.addPropertyChangeListener(PROPRIEDADE_TERMINA, evt -> {
            atualiza();
        });
    }

    private void pintaTabuleiro(){
        int [][] pos = quatroLinhaObservavel.getPos();
        for(int i = 0; i < quatroLinhaObservavel.getAltura(); i++){
            for(int j = 0; j < quatroLinhaObservavel.getLargura();j++){
                if(pos[i][j] == 0)
                    changeBackground(tabuleiro[i][j], Color.WHITE);
                else if(pos[i][j] == 1)
                    changeBackground(tabuleiro[i][j], Color.RED);
                else if(pos[i][j] == 2)
                    changeBackground(tabuleiro[i][j], Color.YELLOW);
            }
        }
    }

    private void atualiza() {
        if(quatroLinhaObservavel.getSituacaoAtual() == Situacao.TERMINAJOGO){
            insereVencedor();
            pintaTabuleiro();
        }
        this.setVisible(quatroLinhaObservavel.getSituacaoAtual() == Situacao.TERMINAJOGO);
    }
}
