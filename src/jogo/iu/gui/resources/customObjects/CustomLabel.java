package jogo.iu.gui.resources.customObjects;

import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import jogo.iu.gui.resources.constantes.ConstantesGUI;
import jogo.iu.gui.resources.managers.FontsManager;

import static jogo.iu.gui.resources.constantes.constantesPintura.*;

public class CustomLabel extends Label {
    public CustomLabel(String s,int tamanho) {
        super(s);
        super.setFont(FontsManager.loadFont(ConstantesGUI.FONTE,tamanho));
        super.setTextFill(Paint.valueOf(COR_LABEL));
    }
}
