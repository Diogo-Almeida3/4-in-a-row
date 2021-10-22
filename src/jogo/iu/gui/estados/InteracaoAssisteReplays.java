package jogo.iu.gui.estados;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import jogo.iu.gui.resources.customObjects.CustomButton;
import jogo.iu.gui.resources.customObjects.CustomLabel;
import jogo.iu.gui.resources.managers.MusicPlayer;
import jogo.logica.Enum.Situacao;
import jogo.logica.QuatroLinhaObservavel;

import static jogo.iu.gui.resources.constantes.ConstantesGUI.*;
import static jogo.iu.gui.resources.constantes.constantesPintura.COR_BACKGROUND;
import static jogo.logica.constantes.Constantes.PROPRIEDADE_REPLAY;

public class InteracaoAssisteReplays extends BorderPane {
    private QuatroLinhaObservavel quatroLinhaObservavel;
    private CustomLabel lblJogador;
    private CustomButton btnAvancar,btnVoltarMenu;
    private Circle[][] tabuleiro;
    private String savePrevious;
    private int flag;
    public InteracaoAssisteReplays(QuatroLinhaObservavel quatroLinhaObservavel) {
        this.quatroLinhaObservavel = quatroLinhaObservavel;
        flag = 0;

        organizaComponentes();
        registaObservador();
        registaListeners();
        atualiza();
    }

    private void changeBackground(Circle region, Color color){
        region.setFill(color);
    }

    private void organizaComponentes() {
        int largura = quatroLinhaObservavel.getLargura();
        int altura = quatroLinhaObservavel.getAltura();
        {
            tabuleiro = new Circle[altura][largura];
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

        btnAvancar = new CustomButton("Avancar",DIM_BUTTON_TERMINA);
        btnVoltarMenu = new CustomButton("Voltar ao menu",DIM_BUTTON_TERMINA);
        lblJogador = new CustomLabel("",DIM_LABEL_JOGO_NOME);
        criaCaixas();
    }

    private void updateLabels(){
        if(quatroLinhaObservavel.imprimeAtual() != null)
            lblJogador.setText(quatroLinhaObservavel.imprimeAtual());
        else
            lblJogador.setText("");
    }

    private void criaCaixas(){
        VBox caixa = new VBox();
        caixa.setPadding(new Insets(20, 20, 20, 20));
        caixa.setSpacing(20);
        caixa.setAlignment(Pos.CENTER);
        caixa.getChildren().addAll(btnAvancar,btnVoltarMenu);
        setRight(caixa);

        VBox nome = new VBox(10);
        nome.getChildren().addAll(lblJogador);
        nome.setAlignment(Pos.BASELINE_CENTER);
        setTop(nome);
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

    private void registaListeners() {
        btnAvancar.setOnAction(e->{
            String aux = quatroLinhaObservavel.imprimeJogo();
            if(aux.equals(savePrevious)){
                quatroLinhaObservavel.voltarMenu();
            }
            else{
                if(flag == 0){
                    flag = 1;
                } else
                    MusicPlayer.playMusic("somPeca.mp3");
                savePrevious = aux;
                quatroLinhaObservavel.disparaReplay();
            }
        });
        btnVoltarMenu.setOnAction(e ->{
            quatroLinhaObservavel.voltarMenu();
        });
    }

    private void registaObservador(){
        this.quatroLinhaObservavel.addPropertyChangeListener(PROPRIEDADE_REPLAY,evt -> {
            atualiza();
        });
    }

    private void atualiza() {
        if(quatroLinhaObservavel.getSituacaoAtual() == Situacao.ASSISTEREPLAYS) {
            pintaTabuleiro();
            updateLabels();
        } else
            flag = 0;
        this.setVisible(quatroLinhaObservavel.getSituacaoAtual() == Situacao.ASSISTEREPLAYS);
    }
}
