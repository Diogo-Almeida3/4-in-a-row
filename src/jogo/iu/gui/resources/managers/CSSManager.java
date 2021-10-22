package jogo.iu.gui.resources.managers;

import javafx.scene.Parent;
import jogo.iu.gui.resources.Resources;

public class CSSManager {
    private CSSManager() {}

    public static void setCSS(Parent parent, String name){
        parent.getStylesheets().add(Resources.getResourceFilename("css/"+name));
    }
}
