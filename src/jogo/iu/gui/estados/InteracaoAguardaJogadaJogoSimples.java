package jogo.iu.gui.estados;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBoundsType;
import jogo.iu.gui.resources.alertas.Alertas;
import jogo.iu.gui.resources.customObjects.CustomButton;
import jogo.iu.gui.resources.customObjects.CustomLabel;
import jogo.iu.gui.resources.customObjects.CustomTextField;
import jogo.iu.gui.resources.managers.MusicPlayer;
import jogo.logica.Enum.Situacao;
import jogo.logica.QuatroLinhaObservavel;
import static jogo.iu.gui.resources.constantes.ConstantesGUI.*;


import java.io.IOException;
import java.util.Random;

import static jogo.iu.gui.resources.constantes.constantesPintura.COR_BACKGROUND;
import static jogo.logica.constantes.Constantes.PROPRIEDADE_JOGO;
import static jogo.logica.constantes.Constantes.PROPRIEDADE_QUATROEMLINHA;

public class InteracaoAguardaJogadaJogoSimples extends BorderPane {
    private QuatroLinhaObservavel quatroLinhaObservavel;

    private CustomButton btnJogaPecaEspecial, btnVoltaAtras;
    private CustomButton btnPecaAutomatica,btnAux,btnSair1,btnSair2;
    private CustomTextField txtJogaPecaEspecial,txtVoltasAtras;
    private CustomLabel lblInventario,lblNome;
    private VBox caixa,bot;

    private Circle[][] tabuleiro;

    static Random rand = new Random();

    public InteracaoAguardaJogadaJogoSimples(QuatroLinhaObservavel quatroLinhaObservavel){
        this.quatroLinhaObservavel = quatroLinhaObservavel;

        organizaComponentes();
        registaListeners();
        registaObservador();
        atualiza();
    }

    private void changeBackground(Circle region, Color color){
        region.setFill(color);
    }

    private void updateLabels(){
        lblInventario.setText(
                "Nº Peças Especiais: " + quatroLinhaObservavel.getnPecasEspeciais()
                        +"\nNº Créditos: "+ quatroLinhaObservavel.getnCreditos());
        txtJogaPecaEspecial.setPromptText("1...7");
        txtVoltasAtras.setPromptText("0...."+quatroLinhaObservavel.getnCreditos());
        lblNome.setText(quatroLinhaObservavel.imprimeAtual());
    }

    private void organizaComponentes(){
        int largura = quatroLinhaObservavel.getLargura();
        int altura = quatroLinhaObservavel.getAltura();

            tabuleiro = new Circle[altura][largura];  // 6 x 7
            GridPane grid = new GridPane();
            grid.setVgap(DIM_GAP_JOGO);
            grid.setHgap(DIM_GAP_JOGO);
            for (int i = 0; i < altura; i++) {
                for (int j = 0; j < largura; j++) {
                    Circle circulo = new Circle();
                    circulo.setRadius(DIM_TABULEIRO_JOGO);
                    changeBackground(circulo, Color.WHITE);
                    grid.add(circulo, j, i);
                    tabuleiro[i][j] = circulo;
                    circulo.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            if (quatroLinhaObservavel.getTipoJogador()) {
                                int i, j = 0, flag = 0;
                                int [] livres = quatroLinhaObservavel.verificaColunaCheia();
                                Circle clickCirculo = (Circle) mouseEvent.getTarget();
                                for (i = 0; i < altura && flag == 0; i++)
                                    for (j = 0; j < largura; j++)
                                        if (tabuleiro[i][j].equals(clickCirculo)) {
                                            flag = 1;
                                            break;
                                        }

                                for (i = 0; i < livres.length; i++) {
                                    if (j + 1 == livres[i] && livres[i] != 0)  //Coluna livre
                                        break;
                                    else if (livres[i] == 0) { //Coluna cheia
                                        Alertas.geraAlerta(5);
                                        j = -1;
                                        break;
                                    }
                                }
                                if (j != -1) {
                                    quatroLinhaObservavel.jogaPeca(j + 1);
                                    MusicPlayer.playMusic("somPeca.mp3");

                                }
                            }
                        }
                    });
                }
            }
            grid.setAlignment(Pos.CENTER);
            grid.setBackground(new Background(new BackgroundFill(Paint.valueOf(COR_BACKGROUND), new CornerRadii(DIM_CORNERS_JOGO),new Insets(DIM_TOPBOTTOM_JOGO,DIM_LEFTRIGHT_JOGO,DIM_TOPBOTTOM_JOGO,DIM_LEFTRIGHT_JOGO))));
            this.setCenter(grid);


        btnPecaAutomatica = new CustomButton("Joga peça",DIM_BUTTON_JOGO);
        btnAux = new CustomButton("Joga peça",DIM_BUTTON_JOGO);
        btnJogaPecaEspecial = new CustomButton("Joga Peça Especial",DIM_BUTTON_JOGO);
        txtJogaPecaEspecial = new CustomTextField(DIM_TEXTFIELD_JOGO);

        btnVoltaAtras = new CustomButton("Volta Atrás",DIM_BUTTON_JOGO);
        txtVoltasAtras = new CustomTextField(DIM_TEXTFIELD_JOGO);
        lblInventario = new CustomLabel("Inventario",DIM_LABEL_JOGO_INVENTARIO);
        btnSair1 = new CustomButton("Sair",DIM_BUTTON_JOGO);
        btnSair2 = new CustomButton("Sair",DIM_BUTTON_JOGO);
        criaCaixas();
    }

    private void criaCaixas(){
        HBox especial = new HBox(10);
        especial.getChildren().addAll(txtJogaPecaEspecial, btnJogaPecaEspecial);
        HBox voltaAtras = new HBox(10);
        voltaAtras.getChildren().addAll(txtVoltasAtras,btnVoltaAtras);

        aumentaCampos();
        caixa = new VBox();
        caixa.setPadding(new Insets(20, 20, 20, 20));
        caixa.setSpacing(20);
        caixa.setAlignment(Pos.TOP_CENTER);
        caixa.getChildren().addAll(btnAux,lblInventario,especial,voltaAtras,btnSair1);


        bot = new VBox();
        bot.setPadding(new Insets(20, 20, 20, 20));
        bot.setSpacing(20);
        bot.setAlignment(Pos.CENTER);
        bot.getChildren().addAll(btnPecaAutomatica,btnSair2);

        lblNome = new CustomLabel("",DIM_LABEL_JOGO_NOME);
        VBox nome = new VBox(10);
        nome.getChildren().addAll(lblNome);
        nome.setAlignment(Pos.BASELINE_CENTER);
        setTop(nome);
    }

    private void aumentaCampos(){
        txtJogaPecaEspecial.aumentaAltura(DIM_TEXTFIELD_JOGO);
        txtVoltasAtras.aumentaAltura(DIM_TEXTFIELD_JOGO);
        btnSair1.aumentaBotaoJogo(DIM_BUTTON_JOGO);
        btnSair2.aumentaBotaoJogo(DIM_BUTTON_JOGO);
        btnPecaAutomatica.aumentaBotaoJogo(DIM_BUTTON_JOGO);
    }

    private void registaListeners(){
        btnSair1.setOnAction(e->{
            try {
                quatroLinhaObservavel.gravaContinuacao();
                Platform.exit();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        btnSair2.setOnAction(e->{
            try {
                quatroLinhaObservavel.gravaContinuacao();
                Platform.exit();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        btnJogaPecaEspecial.setOnAction(e->{
            try {
                int posicao = Integer.parseInt(txtJogaPecaEspecial.getText());
                if (posicao > 0 && posicao < 8 && quatroLinhaObservavel.getnPecasEspeciais() > 0) {
                    int i;
                    int[] vazias = quatroLinhaObservavel.verificaColunasVazias();

                    for (i = 0; i < vazias.length; i++) {
                        if (posicao == vazias[i] && vazias[i] != 0) {
                            Alertas.geraAlerta(2);
                            posicao = -1;
                            break;
                        }
                    }
                    if (posicao != -1) {
                        quatroLinhaObservavel.jogaPecaEspecial(posicao);
                        for (i = 0; i < quatroLinhaObservavel.getAltura(); i++)
                            changeBackground(tabuleiro[i][posicao - 1], Color.WHITE);
                    }
                } else
                    Alertas.geraAlerta(1);
            } catch (Exception exception){
                Alertas.geraAlerta(0);
            } finally {
                limpaTextFields();
            }
        });
        btnVoltaAtras.setOnAction(e->{
            try{
                if(quatroLinhaObservavel.getnCreditos() > 0 && Integer.parseInt(txtVoltasAtras.getText()) <= quatroLinhaObservavel.getnCreditos() && Integer.parseInt(txtVoltasAtras.getText()) > 0 ){
                    quatroLinhaObservavel.voltaAtras(Integer.parseInt(txtVoltasAtras.getText()));
                    pintaTabuleiro();
                }
                else
                    Alertas.geraAlerta(3);
            } catch (Exception exception){
                Alertas.geraAlerta(0);
            }
            finally {
                limpaTextFields();
            }
        });
        btnPecaAutomatica.setOnAction(e->{
            int pos = rand.nextInt(7) +1,i;
            int [] livres = quatroLinhaObservavel.verificaColunaCheia();

            for (i = 0; i < livres.length; i++) {
                if (pos == livres[i] && livres[i] != 0) {
                    break;
                } else if (livres[i] == 0) {
                    pos = -1;
                    break;
                }
            }
            if (pos != -1) {
                quatroLinhaObservavel.jogaPeca(pos);
                MusicPlayer.playMusic("somPeca.mp3");
            }
            limpaTextFields();
        });
    }

    private void limpaTextFields(){
        txtJogaPecaEspecial.setText("");
        txtVoltasAtras.setText("");
    }

    private void registaObservador(){
        this.quatroLinhaObservavel.addPropertyChangeListener(PROPRIEDADE_JOGO, evt -> {
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


    private void atualiza(){

        if(quatroLinhaObservavel.getSituacaoAtual() == Situacao.AGUARDAJOGADA){
            updateLabels();
            pintaTabuleiro();
            if(quatroLinhaObservavel.getTipoJogador()){
                setRight(caixa);
                btnAux.setVisible(false);
            } else{
                setRight(bot);
            }
        }
        this.setVisible(quatroLinhaObservavel.getSituacaoAtual() == Situacao.AGUARDAJOGADA);
    }
}