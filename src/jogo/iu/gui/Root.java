package jogo.iu.gui;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import jogo.logica.QuatroLinhaObservavel;

public class Root extends BorderPane {
    private QuatroLinhaObservavel quatroLinhaObservavel;
    private PrincipalPane principalPane;

    public Root(QuatroLinhaObservavel quatroLinhaObservavel) {
        this.quatroLinhaObservavel = quatroLinhaObservavel;
        vistaInicial();
    }


    private void vistaInicial(){
        //Background
        BackgroundFill background_fill = new BackgroundFill(Paint.valueOf("#5D737E"),
                CornerRadii.EMPTY, Insets.EMPTY);
        setBackground(new Background(background_fill));

        principalPane = new PrincipalPane(quatroLinhaObservavel);
        this.setCenter(principalPane);
    }

}
