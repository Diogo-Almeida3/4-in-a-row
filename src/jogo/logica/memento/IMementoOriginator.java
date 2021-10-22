package jogo.logica.memento;

import java.io.IOException;

public interface IMementoOriginator {
    Memento getMemento() throws IOException;
    Memento getMementoJogo() throws IOException;

    void setMemento(Memento m) throws IOException, ClassNotFoundException;
    void setMementoJogo(Memento mem);
}
