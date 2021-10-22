package jogo.logica.dados.minijogos;


import java.io.Serializable;
import java.util.Random;

public class MiniJogoMatematica implements Serializable {


    private int calculoAtual;
    private int respostasCertas;
    private int nmr1,nmr2;
    private String operator;
    private Random rand = new Random();

    public MiniJogoMatematica() {
        this.respostasCertas = 0;
    }

    public void verificaResposta(int i){
        if(calculoAtual == i)
            respostasCertas++;
    }

    public int getNmr1() {
        return nmr1;
    }
    public int getNmr2() {
        return nmr2;
    }

    public void setNmr1() {
        this.nmr1 = (rand.nextInt(50) + 1);
    }
    public void setNmr2() {
        this.nmr2 = (rand.nextInt(50) + 1);
    }

    public void geraConta(){
        setNmr1();
        setNmr2();
        switch (rand.nextInt(4)){
            case 0:
                operator = "+";
                this.calculoAtual =  nmr1 + nmr2;
                break;
            case 1:
                operator = "-";
                this.calculoAtual =  nmr1 - nmr2;
                break;
            case 2:
                operator = "*";
                this.calculoAtual =  nmr1 * nmr2;
                break;
            case 3:
                operator = "/";
                this.calculoAtual =  nmr1 / nmr2;
                break;
        }
    }

    public String getOperator() {
        return operator;
    }

    public int getCalculoAtual() {
        return calculoAtual;
    }
    public int getRespostasCertas() {
        return respostasCertas;
    }
}
