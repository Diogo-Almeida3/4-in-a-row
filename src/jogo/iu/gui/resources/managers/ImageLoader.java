package jogo.iu.gui.resources.managers;

import javafx.scene.image.Image;
import jogo.iu.gui.resources.Resources;

import java.util.HashMap;

public class ImageLoader {
    static HashMap<String, Image> imgCache;
    static {
        imgCache = new HashMap<>();
    }
    public static Image getImage(String name) {
        Image img = imgCache.get(name);
        if (img != null)
            return img;
        try {
            img = new Image(Resources.getResourceFileAsStream("images/"+name));
            imgCache.put(name,img);
            return img;
        } catch (Exception e) {
        }
        return null;
    }
    public static Image getImageForce(String name) {
        imgCache.remove(name);
        return getImage(name);
    }
}
