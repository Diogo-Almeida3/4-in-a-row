package jogo.iu.gui.resources.customObjects;

import javafx.scene.control.TextField;

public class CustomTextField extends TextField {

    public CustomTextField(int tamanho) {
        super();
        super.setPrefWidth(tamanho * 6.65);
        super.setMaxWidth(tamanho * 6.65);
    }

    public void aumentaAltura(int tamanho){
        super.setPrefHeight(tamanho * 1.8);
        super.setMinHeight(tamanho * 1.8);
    }
}
