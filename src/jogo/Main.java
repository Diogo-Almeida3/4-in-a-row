package jogo;

import jogo.iu.texto.IUQuatroLinha;
import jogo.logica.memento.Gestor;

import java.io.IOException;

//todo Corrigir diagrama de estados (nomes)



public class Main {
    public static void main(String[] args) throws IOException {
        Gestor jogoEstados = new Gestor();
        IUQuatroLinha iuQuatroLinha = new IUQuatroLinha(jogoEstados);
        iuQuatroLinha.corre();
    }
}

