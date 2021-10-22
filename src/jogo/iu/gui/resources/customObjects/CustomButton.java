package jogo.iu.gui.resources.customObjects;

import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import jogo.iu.gui.resources.constantes.ConstantesGUI;
import jogo.iu.gui.resources.managers.FontsManager;

import static jogo.iu.gui.resources.constantes.constantesPintura.COR_BORDER;

public class CustomButton extends Button {

    public CustomButton(String s,int tamanho) {
        super(s);
        super.setFont(FontsManager.loadFont(ConstantesGUI.FONTE,tamanho));
        super.setPrefSize(tamanho* 10,tamanho*2);
        super.setBorder(new Border(new BorderStroke(Paint.valueOf(COR_BORDER),
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                new BorderWidths(3))));
        this.setId("my-button");
        super.setOnMouseEntered(e -> super.setId("button-hover"));
        super.setOnMouseExited(e -> super.setId("my-button"));
    }

    public void aumentaBotaoJogadores(int tamanho){
        super.setPrefWidth(tamanho * 13);
        super.setMinWidth(tamanho * 13);
    }
    public void aumentaBotaoJogo(int tamanho){
        super.setPrefWidth(tamanho * 18.8);
        super.setMinWidth(tamanho * 18.8);
    }
    public void diminuiBotaoJogo(int tamanho){
        super.setPrefWidth(tamanho * 6.65);
        super.setMaxWidth(tamanho * 6.65);
    }
}
