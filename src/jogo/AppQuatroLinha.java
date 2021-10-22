package jogo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jogo.iu.gui.Root;
import jogo.iu.gui.resources.managers.ImageLoader;
import jogo.logica.QuatroLinhaObservavel;
import jogo.logica.memento.Gestor;

import static jogo.iu.gui.resources.constantes.ConstantesGUI.*;

public class AppQuatroLinha extends Application {


    public static void main(String[] args) {launch(args);}

    @Override
    public void start(Stage stage) throws Exception {
        Gestor jogoEstados = new Gestor();
        QuatroLinhaObservavel quatroLinhaObservavel = new QuatroLinhaObservavel(jogoEstados);
        Scene scene = new Scene(new Root(quatroLinhaObservavel));
        stage.getIcons().add(ImageLoader.getImage("iconPage.png"));
        stage.setMinHeight(DIMY_INICIAL);
        stage.setMinWidth(DIMX_INICIAL);
        stage.setMaxWidth(DIMX_INICIAL + 60);
        stage.setMaxHeight(DIMY_INICIAL + 60);
        stage.setScene(scene);
        stage.setTitle("Quatro Em Linha");
        stage.setOnCloseRequest(windowEvent -> Platform.exit());
        stage.show();

/*
        Stage stage2 = new Stage();
        Scene scene2 = new Scene(new Root(quatroLinhaObservavel));
        stage2.getIcons().add(ImageLoader.getImage("iconPage.png"));
        stage2.setMinHeight(DIMY_INICIAL);
        stage2.setMinWidth(DIMX_INICIAL);
        stage2.setMaxWidth(DIMX_INICIAL + 60);
        stage2.setMaxHeight(DIMY_INICIAL + 60);
        stage2.setScene(scene2);
        stage2.setTitle("Quatro Em Linha");
        stage2.setOnCloseRequest(windowEvent -> Platform.exit());
        stage2.show();*/
    }
}
