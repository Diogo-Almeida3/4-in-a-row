package jogo.iu.gui.resources.managers;

import javafx.scene.text.Font;
import jogo.iu.gui.resources.Resources;

public class FontsManager {
    private FontsManager(){}

    public static Font loadFont(String name, double size){
        return Font.loadFont(Resources.getResourceFileAsStream("fonts/"+name),size);
    }
}
