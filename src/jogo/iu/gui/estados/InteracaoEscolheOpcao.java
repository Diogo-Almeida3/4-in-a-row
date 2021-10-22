package jogo.iu.gui.estados;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import jogo.iu.gui.resources.alertas.Alertas;
import jogo.iu.gui.resources.customObjects.CustomButton;
import jogo.iu.gui.resources.customObjects.CustomLabel;
import jogo.iu.gui.resources.customObjects.CustomTextField;
import jogo.logica.Enum.Situacao;
import jogo.logica.QuatroLinhaObservavel;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Objects;

import static jogo.logica.constantes.Constantes.PROPRIEDADE_MENU;
import static jogo.logica.constantes.Constantes.PROPRIEDADE_QUATROEMLINHA;
import static jogo.iu.gui.resources.constantes.ConstantesGUI.*;

public class InteracaoEscolheOpcao extends BorderPane {
    private QuatroLinhaObservavel quatroLinhaObservavel;

    private CustomLabel lblReplays;
    private CustomButton btnJogo,btnCont, btnReplays, btnSair,btnVerReplay,btnCancelar;
    private CustomTextField txtInsereReplay;
    private VBox lista, principal;
    private boolean apresentaLista;

    public InteracaoEscolheOpcao(QuatroLinhaObservavel quatroLinhaObservavel) {
        this.quatroLinhaObservavel = quatroLinhaObservavel;
        apresentaLista = false;
        organizaComponentes();
        registaObservador();
        registaListeners();
        atualiza();
    }

    private void registaObservador(){
        this.quatroLinhaObservavel.addPropertyChangeListener(PROPRIEDADE_MENU,evt -> {
            atualiza();
        });
    }

    private void organizaComponentes(){
        CustomLabel lblMenu = new CustomLabel("MENU",DIM_LABEL_ESCOLHEOPCAO_MENU);
        VBox titulo = new VBox(10);
        titulo.getChildren().addAll(lblMenu);
        titulo.setAlignment(Pos.BASELINE_CENTER);
        setTop(titulo);

        btnJogo = new CustomButton("Jogar", DIM_BUTTON_ESCOLHEOPCAO);
        btnCont = new CustomButton("Continuar Jogo",DIM_BUTTON_ESCOLHEOPCAO);
        btnReplays = new CustomButton("Replays",DIM_BUTTON_ESCOLHEOPCAO);
        btnSair = new CustomButton("Sair",DIM_BUTTON_ESCOLHEOPCAO);

        principal = new VBox(10);
        principal.setPadding(new Insets(10, 10, 10, 10));
        principal.setSpacing(20);
        principal.getChildren().addAll(btnJogo,btnCont,btnReplays,btnSair);
        principal.setAlignment(Pos.CENTER);

        btnCancelar = new CustomButton("Voltar ao Menu",DIM_BUTTON_LISTA);
        btnVerReplay = new CustomButton("Ver replay",DIM_BUTTON_LISTA);
        txtInsereReplay = new CustomTextField(DIM_TEXTFIELD_ESCOLHEOPCAO);

        lblReplays = new CustomLabel("",DIM_LABEL_ESCOLHEOPCAO_LISTA);
        lista = new VBox(10);
        lista.setPadding(new Insets(10, 10, 10, 10));
        lista.setSpacing(15);
        lista.getChildren().addAll(lblReplays,txtInsereReplay,btnVerReplay,btnCancelar);
        lista.setAlignment(Pos.CENTER);
    }



    private void registaListeners(){
        btnJogo.setOnAction(e -> {
            quatroLinhaObservavel.jogar();
        });
        btnCont.setOnAction(e -> {
            quatroLinhaObservavel.carregaContinuacao("continuacaoJogoHist","continuacaoJogoReplay");
        });
        btnReplays.setOnAction(e -> {
            apresentaLista = true;
            atualiza();
        });
        btnSair.setOnAction(e -> {
            Platform.exit();
        });
        btnCancelar.setOnAction(e -> {
            apresentaLista = false;
            atualiza();
        });
        btnVerReplay.setOnAction(e -> {
            int fich;
            try{
                fich = Integer.parseInt(txtInsereReplay.getText());
                File f = new File(".");
                FilenameFilter filter = (f1, name) -> name.startsWith("jogo");
                File[] files = f.listFiles(filter);
                if(fich > 0 && fich <= Objects.requireNonNull(files).length){
                    quatroLinhaObservavel.carregaJogo(files[fich - 1].getName());
                    apresentaLista = false;
                } else
                    Alertas.geraAlerta(8);
            } catch (Exception exception){
                Alertas.geraAlerta(7);
            } finally {
                txtInsereReplay.setText("");
            }
        });
    }

    private void updateLabels(){
        File f = new File(".");
        FilenameFilter filter = (f1, name) -> name.startsWith("jogo");
        File[] files = f.listFiles(filter);
        String lista;
        lista = "Lista:\n";
        for (int i = 0; i < Objects.requireNonNull(files).length; i++)
            lista += (i + 1+ " -> "+ files[i].getName() + "\n");
        lblReplays.setText(lista);
    }

    private void atualiza() {
        if(quatroLinhaObservavel.getSituacaoAtual() == Situacao.ESCOLHEOPCAO){
            if(!apresentaLista){
                lista.setVisible(false);
                principal.setVisible(true);
                setCenter(principal);
            } else{
                updateLabels();
                lista.setVisible(true);
                principal.setVisible(false);
                setCenter(lista);
            }
        }
        this.setVisible(quatroLinhaObservavel.getSituacaoAtual() == Situacao.ESCOLHEOPCAO);
    }
}
