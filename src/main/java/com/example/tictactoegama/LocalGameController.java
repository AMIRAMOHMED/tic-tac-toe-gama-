package com.example.tictactoegama;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class LocalGameController {

    @FXML
    GridPane gameGrid;
    @FXML
    Label playerXNametxt,playerONametxt;
    @FXML
    Text gameStatus;
    char[][] game;
    int XScore,OScore,numberOfPlayes;
    static String playerXName,playerOName;
    String currentPlayer;

    @FXML
    public void initialize() {
        int XScore=0,OScore=0;
        game=new char[3][3];
        numberOfPlayes=0;
        playerXNametxt.setText(playerXName);
        playerONametxt.setText(playerOName);
        gameStatus.setText(playerXName+"'s Turn");
    }
    @FXML
    public void handleButtonClick(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        if (clickedButton.getText().isEmpty()) {

            int row = GridPane.getRowIndex(clickedButton);
            int col = GridPane.getColumnIndex(clickedButton);
            if(numberOfPlayes%2==0) {
                updateButtonStyle(clickedButton, "X");
                gameStatus.setText(playerOName+"'s Turn");
                currentPlayer=playerXName;
            }
            else {
                updateButtonStyle(clickedButton, "O");
                gameStatus.setText(playerXName+"'s Turn");
                currentPlayer=playerOName;
            }
            //clickedButton.setDisable(true);
            if(checkWin(row, col)==1){
                endGame(currentPlayer);
            }else
                numberOfPlayes++;

        }
    }


    private int checkWin(int x, int y) {

        game[x][y]=((numberOfPlayes%2)==0)?'x':'y';
        if(numberOfPlayes>3){
            if(game[x][0]==game[x][1]&&game[x][1]==game[x][2])
                return 1;
            else if(game[0][y]==game[1][y]&&game[0][y]==game[2][y])
                return 1;
            else if ((x+y)%2==0) {
                if(game[0][0]==game[1][1]&&game[1][1]==game[2][2])
                    return 1;
                else if (game[0][2] == game[1][1] && game[1][1] == game[2][0]) {
                    return 1;
                }
            }
        }
        if(numberOfPlayes==8){
            System.out.println("draw");
        }
        return 0;

    }
    private void updateButtonStyle(Button button, String symbol) {
        if ("X".equals(symbol)) {
            button.setStyle(
                    "-fx-background-color: rgba(70, 163, 255, 0.2); " +
                            "-fx-text-fill: #46A3FF; " +
                            "-fx-font-size: 36px; " +
                            "-fx-border-color: #E3E3E3;"
            );
            button.setText("X");
        } else if ("O".equals(symbol)) {
            button.setStyle(
                    "-fx-background-color: rgba(255, 130, 126, 0.2); " +
                            "-fx-text-fill: #FF827E; " +
                            "-fx-font-size: 36px; " +
                            "-fx-border-color: #E3E3E3;"
            );
            button.setText("O");
        }
    }
    private void endGame(String winner) {
        String message = "Player " + winner + " wins!";
        gameStatus.setText(message);
        disableButtons();
    }

    private void disableButtons() {
        for (Node node : gameGrid.getChildren()) {
            if (node instanceof Button) {
                ((Button) node).setDisable(true);
            }
        }
    }
}
