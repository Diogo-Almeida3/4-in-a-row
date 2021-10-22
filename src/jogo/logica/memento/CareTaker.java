package jogo.logica.memento;

import jogo.logica.dados.JogoQuatroLinhaDados;

import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

import static java.lang.Integer.parseInt;

public class CareTaker implements Serializable {

    private final IMementoOriginator originator;

    // Deque<Memento> stackHist = new ArrayDeque<>();
    private Stack<Memento> stackHist = new Stack<>();
    private Stack<Memento> stackReplay = new Stack<>();
    public CareTaker(IMementoOriginator originator) {
        this.originator = originator;
    }

    public void gravaMemento(){
        try{
            stackHist.push(originator.getMemento());
        } catch(IOException e){
            e.printStackTrace();
            stackHist.clear();
        }
    }

    public void undo(){
        if(stackHist.isEmpty())
            return;
        try{
            Memento memAnterior = stackHist.pop();
            originator.setMemento(memAnterior);
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
            stackHist.clear();
        }
    }

    public String nomeFicheiro(){
        int maiorNum = 0,menorNum = 0,pos = 0;
        File f = new File(".");
        FilenameFilter filter = (f1, name) -> name.startsWith("jogo");
        File[] files = f.listFiles(filter);
        assert files != null;
        for (int i = 0; i < files.length; i++) {
            String[] ls = files[i].getName().split("jogo");
            if(i == 0)
                maiorNum = menorNum = parseInt(ls[1]);

            if(maiorNum < parseInt(ls[1]))
                maiorNum = parseInt(ls[1]);
            else if( menorNum > parseInt(ls[1])){
                menorNum = parseInt(ls[1]);
                pos = i;
            }
        }
        if(files.length >= 5)
            files[pos].delete();
        return  "jogo" + (maiorNum + 1);
    }

    //Grava as jogadas num ficheiro
    public void gravaJogo() throws IOException {
        ObjectOutputStream oos = null;
        try
        {
            oos = new ObjectOutputStream(new FileOutputStream(nomeFicheiro()));
            oos.writeObject(stackReplay);
            oos.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        } finally {
            oos.close();
        }
    }

    public void carregaJogo(String s) {
        ObjectInputStream ois = null;
        try
        {
            ois = new ObjectInputStream(new FileInputStream(s));
            stackReplay = (Stack<Memento>) ois.readObject();
            ois.close();
        } catch (EOFException e){
        System.out.println("Fim de ficheiro:" + s);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    //Grava Cada jogada para replays
    public void gravaMementoJogo(){
        try {
            stackReplay.push(originator.getMementoJogo());
        } catch (IOException e) {
            e.printStackTrace();
            stackReplay.clear();
        }
    }

    public void veReplay(){
        if(stackReplay.isEmpty())
            return;
        Memento mem = stackReplay.remove(0);
        originator.setMementoJogo(mem);
    }

    public void gravaContinuacao() throws IOException {
        ObjectOutputStream oos = null;
        try
        {
            oos = new ObjectOutputStream(new FileOutputStream("continuacaoJogoHist"));
            oos.writeObject(stackHist);
            oos.close();
            oos = new ObjectOutputStream(new FileOutputStream("continuacaoJogoReplay"));
            oos.writeObject(stackReplay);
            oos.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        } finally {
            oos.close();
        }
    }

    public void carregaContinuacao(String hist,String replay) {
        ObjectInputStream ois = null;
        try
        {
            ois = new ObjectInputStream(new FileInputStream(hist));
            stackHist = (Stack<Memento>) ois.readObject();
            ois.close();
            ois = new ObjectInputStream(new FileInputStream(replay));
            stackReplay = (Stack<Memento>) ois.readObject();
            ois.close();
        } catch(FileNotFoundException e)
        {
            return;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void redoJogo(){
        if(stackHist.isEmpty())
            return;
        try{
            Memento memAnterior = stackHist.get(stackSize() - 1);
            originator.setMemento(memAnterior);
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
            stackHist.clear();
        }
    }

    public void reiniciaHistoricoEstados(){
        stackHist.clear();
    }

    public void reiniciaHistoricoReplay(){
        stackReplay.clear();
    }

    public int stackSize(){
        return stackHist.size();
    }
}
