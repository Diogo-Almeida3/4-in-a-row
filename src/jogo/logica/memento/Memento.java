package jogo.logica.memento;

import java.io.*;

public class Memento implements  Serializable{
    private byte [] snapshot = null;
    public Memento(Object obj) throws IOException{
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            snapshot = baos.toByteArray();
        } catch( Exception e){
            e.printStackTrace();
        } finally {
            if(oos != null)
                oos.close();
            if(baos != null)
                baos.close();
        }
    }



    public Object getSnapshot() throws IOException{
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        if(snapshot == null)
            return null;
        try{
            bais = new ByteArrayInputStream(snapshot);
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            if(bais != null)
                bais.close();
            if(ois != null)
                ois.close();
        }
        return null;
    }
}
