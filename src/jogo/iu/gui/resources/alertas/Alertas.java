package jogo.iu.gui.resources.alertas;

import javafx.scene.control.Alert;
import javafx.stage.Stage;
import jogo.iu.gui.resources.managers.ImageLoader;

public class Alertas {

    private Alertas(){}

    public static void geraAlerta(int codigo){
        Alert erroInput = new Alert(Alert.AlertType.WARNING);
        Stage stage = (Stage) erroInput.getDialogPane().getScene().getWindow();
        stage.getIcons().add(ImageLoader.getImage("errorIcon.png"));
        switch (codigo) {
            case 0 -> {
                erroInput.setTitle("Erro de introdução de dados");
                erroInput.setHeaderText("Dados inválidos. Por favor introduza números inteiros.");
            }
            case 1 -> {
                erroInput.setTitle("Erro ao jogar peça especial");
                erroInput.setHeaderText("Não tem peças no inventário.");
            }
            case 2 -> {
                erroInput.setTitle("Erro ao jogar peça especial");
                erroInput.setHeaderText("A coluna encontra-se vazia.");
            }
            case 3 -> {
                erroInput.setTitle("Erro ao voltar atrás");
                erroInput.setHeaderText("Não tem créditos suficientes.");
            }
            case 4 -> {
                erroInput.setTitle("Erro ao entrar no minijogo");
                erroInput.setHeaderText("Não repetir o minijogo.");
            }
            case 5 -> {
                erroInput.setTitle("Erro ao jogar peça");
                erroInput.setHeaderText("A coluna encontra-se cheia.");
            }
            case 6 -> {
                erroInput.setTitle("Erro ao definir nomes");
                erroInput.setHeaderText("Os nomes são iguais. Por favor introduza nomes diferentes.");
            }
            case 7 -> {
                erroInput.setTitle("Erro ao aceder ao replay");
                erroInput.setHeaderText("Dados inválidos. Por favor introduza números inteiros.");
            }
            case 8 -> {
                erroInput.setTitle("Erro ao aceder ao replay");
                erroInput.setHeaderText("O número do replay introduzido não está disponivel.");
            }
            case 9 -> {
                erroInput.setTitle("Erro ao responder no MiniJogo");
                erroInput.setHeaderText("Dados inválidos. Por favor introduza um número inteiro.");
            }
        }
        erroInput.showAndWait();
        stage.close();
    }

    public static void geraInformacao(int codigo){
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        Stage stage = (Stage) info.getDialogPane().getScene().getWindow();
        stage.getIcons().add(ImageLoader.getImage("infoIcon.png"));
        switch (codigo) {
            case 0 -> {
                info.setTitle("Dados");
                info.setHeaderText("Desenvolvedor: Diogo Dias Almeida\nNúmero: 2019126638\nEmail: a2019126638@isec.pt");
            }
            case 1 -> {
                info.setTitle("Regras");
                info.setHeaderText("""
                        O objetivo do jogo “quatro em linha” é colocar
                        quatro peças numa linha contínua na vertical,
                        horizontal ou diagonal. As jogadas são alternadas
                        e só pode ser jogada uma peça por jogada.O jogo
                        termina quando um jogador colocar quatro peças
                         em linha ou quando o tabuleiro estiver cheio.""");
            }
        }
        info.showAndWait();
        stage.close();
    }


}
