package jogo.logica.dados.minijogos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MiniJogoPalavras implements Serializable{

    private List<String> palavras;
    private String sequencia;
    private transient BufferedReader br;

    public MiniJogoPalavras() {
        palavras = new ArrayList<>();
        sequencia = "";
        try {
            br = new BufferedReader(new FileReader("Palavras.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void leFicheiro(){
        String str = "";
        while(str != null){
            try {
                str = br.readLine();
                if(str != null)
                    palavras.add(str);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setSequencia(){
        Random rand = new Random();

        for (int i = 0; i < 5; i++) {
            sequencia = sequencia.concat(palavras.get(rand.nextInt(100)) + " ");
        }
        sequencia = sequencia.trim().toLowerCase();
    }

    public int getTempo(){
        return sequencia.length() / 2;
    }

    public String getSequencia() {
        return sequencia;
    }
}
