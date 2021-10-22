package jogo.iu.gui.estados;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import jogo.iu.gui.resources.alertas.Alertas;
import jogo.iu.gui.resources.customObjects.CustomButton;
import jogo.iu.gui.resources.customObjects.CustomLabel;
import jogo.iu.gui.resources.customObjects.CustomTextField;
import jogo.logica.Enum.Situacao;
import jogo.logica.QuatroLinhaObservavel;
import static jogo.iu.gui.resources.constantes.ConstantesGUI.*;
import static jogo.logica.constantes.Constantes.*;

public class InteracaoInsereNomes extends BorderPane {

    private QuatroLinhaObservavel quatroLinhaObservavel;
    private CustomButton btnInserir,btnVoltarMenu;
    private CustomTextField txtNome1,txtNome2;

    public InteracaoInsereNomes(QuatroLinhaObservavel quatroLinhaObservavel){
        this.quatroLinhaObservavel = quatroLinhaObservavel;
        organizaComponentes();
        registaListeners();
        registaObservador();
        atualiza();
    }

    private void organizaComponentes(){

        CustomLabel lblInsereNomes = new CustomLabel("INSERIR NOMES", DIM_LABEL_INSERENOMES);
        VBox titulo = new VBox(10);
        titulo.getChildren().addAll(lblInsereNomes);
        titulo.setAlignment(Pos.BASELINE_CENTER);
        setTop(titulo);

        btnInserir = new CustomButton("Inserir",DIM_BUTTON_INSERENOMES);
        btnVoltarMenu = new CustomButton("Voltar ao Menu",DIM_BUTTON_INSERENOMES);
        txtNome1 = new CustomTextField(DIM_TEXTFIELD_INSERENOMES);
        txtNome2 = new CustomTextField(DIM_TEXTFIELD_INSERENOMES);

        VBox texto = new VBox(15);
        texto.getChildren().addAll(txtNome1,txtNome2);
        texto.setPadding(new Insets(10, 10, 10, 10));
        texto.setAlignment(Pos.CENTER);

        VBox componentes = new VBox(25);
        componentes.getChildren().addAll(texto,btnInserir,btnVoltarMenu);
        componentes.setPadding(new Insets(10, 10, 10, 10));
        componentes.setAlignment(Pos.CENTER);
        setCenter(componentes);
    }


    private void registaListeners(){
        btnInserir.setOnAction(e ->{
            String [] nomes = new String[2];
            if(quatroLinhaObservavel.getNHumanos() == 2){
                nomes[0] = txtNome1.getText();
                nomes[1] = txtNome2.getText();
            } else if(quatroLinhaObservavel.getNHumanos() == 1){
                nomes[0] = txtNome1.getText();
                nomes[1] = "Robot2";
            } else{
                nomes[0] = "Robot1";
                nomes[1] = "Robot2";
            }
            if(!nomes[0].equals(nomes[1])){
                quatroLinhaObservavel.insereNomes(nomes);
            } else
                Alertas.geraAlerta(6);
            limpaTextField();
        });
        btnVoltarMenu.setOnAction(e->{
            limpaTextField();
            quatroLinhaObservavel.voltarMenu();
        });
    }

    private void limpaTextField(){
        txtNome1.setText("");
        txtNome2.setText("");
    }

    private void registaObservador(){
        this.quatroLinhaObservavel.addPropertyChangeListener(PROPRIEDADE_PREPARAJOGO, evt -> {
            atualiza();
        });
    }

    private void atualiza(){
        if(quatroLinhaObservavel.getNHumanos() == 2){
            txtNome1.setVisible(true);
            txtNome2.setVisible(true);
            txtNome1.setPromptText("Nome 1");
            txtNome2.setPromptText("Nome 2");
        }
        else if(quatroLinhaObservavel.getNHumanos() == 1){
            txtNome1.setPromptText("Nome 1");
            txtNome1.setVisible(true);
            txtNome2.setVisible(false);
        }
        else{
            txtNome1.setVisible(false);
            txtNome2.setVisible(false);
        }
        this.setVisible(quatroLinhaObservavel.getSituacaoAtual() == Situacao.INSERENOMES);
    }
}
