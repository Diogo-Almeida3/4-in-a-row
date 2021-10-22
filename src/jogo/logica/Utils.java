package jogo.logica;

import java.io.File;
import java.io.FilenameFilter;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Utils {
    static Scanner sc;
    static Random rand;
    static {
        sc = new Scanner(System.in);
        rand = new Random();
    }
    public Utils() {}

    public static int pedePosicaoPeca(boolean jogador,int [] livres){
        int posicao = 0,i;

            do {

                try {
                    if(jogador){
                        System.out.println("Posicao [1...7]: ");
                        posicao = sc.nextInt();
                    } else
                        posicao = rand.nextInt(7) + 1;
                    for (i = 0; i < livres.length; i++) {
                        if (posicao == livres[i] && livres[i] != 0) {
                            break;
                        } else if(livres[i] == 0){
                            posicao = -1;
                            if(jogador)
                                System.out.println("Coluna Cheia");
                            break;
                        }
                    }

                } catch (InputMismatchException e) {
                    System.out.println("Input errado");
                    sc.nextLine();
                }
            } while (posicao < 1 || posicao > 7);
        return posicao;
    }

    public static int pedePosicaoPecaEspecial(boolean jogador, int[] vazias,int nPecasEspeciais){
        int posicao = 0,i;
        if(jogador && nPecasEspeciais > 0) {
            do {
                System.out.println("Posicao [1...7]: ");
                try {
                    posicao = sc.nextInt();
                    for (i = 0; i < vazias.length; i++) {
                        if (posicao == vazias[i] && vazias[i] != 0) {
                            System.out.println("Coluna Vazia");
                            posicao = -1;
                            break;
                        }
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Input errado");
                    sc.nextLine();
                }
            } while (posicao < 1 || posicao > 7);
        }
        return posicao;
    }

    public static int pedeRecuos(boolean jogador, int totalCreditos){
        int recuos = 0;
        if(jogador){
            do {
                System.out.print("Jogadas [0..." + totalCreditos + "]");
                try {
                    recuos = sc.nextInt();
                } catch(InputMismatchException e){
                    System.out.println("Input errado");
                    sc.nextLine();
                }
            } while (recuos < 0 || recuos > totalCreditos);
        }
        return recuos;
    }

    public static String pedeString(String pergunta) {
        String resposta;
        do {
            System.out.println(pergunta);
            resposta = sc.nextLine();
        } while (resposta.isEmpty());
        return resposta.trim();
    }

    public static int escolheOpcao(String... opcoes) {
        int opcao;
        System.out.println("Menu");
        do {
            for (int i = 0; i < opcoes.length-1; i++)
                System.out.printf("%3d - %s\n",i+1,opcoes[i]);
            System.out.printf("\n%3d - %s\n",0,opcoes[opcoes.length-1]);
            opcao = pedeInteiro("\n> ");
        } while (opcao<0 || opcao>=opcoes.length);
        return opcao;
    }

    public static int pedeInteiro(String pergunta) {
        System.out.print(pergunta);
        while (!sc.hasNextInt())
            sc.next();
        int valor = sc.nextInt();
        sc.nextLine();

        return valor;
    }

    public static String [] pedeNomes(int nHumanos) {
        String [] nomes = new String[2];
        for (int i = 0; i < 2; i++) {
            if(nHumanos == 2){
                do {
                    nomes[i] = setNomes();
                } while(nomes[i].isEmpty());
            } else if(nHumanos == 1){
                if(i == 0)
                    do {
                        nomes[i] = setNomes();
                    } while(nomes[i].isEmpty());
                else
                    nomes[i] = "Robot" + i;
            } else{
                nomes[i] = "Robot " + (i+1);
            }
        }
        return nomes;
    }

    private static String setNomes(){
        System.out.print("Nome: ");
        return sc.nextLine().trim();
    }

    public static String verificaInventario(int creditos, int pecas){
        return "Créditos: " + creditos + "\nPeças especiais: " + pecas ;
    }


    public static String escolheFicheiro() {
        File f = new File(".");
        FilenameFilter filter = (f1, name) -> name.startsWith("jogo");
        File[] files = f.listFiles(filter);
        int fich = -1;
        do {
            System.out.println("Lista:");
            for (int i = 0; i < files.length; i++)
                System.out.printf("%3d - %s\n", i + 1, files[i].getName());
            System.out.printf("\n%3d - %s\n", 0, "Cancelar");
            fich = pedeInteiro("\n> ");
        } while(fich < 0 || fich > files.length);
        if(fich > 0)
            return files[fich - 1].getName();
        else
            return "";
    }
}
