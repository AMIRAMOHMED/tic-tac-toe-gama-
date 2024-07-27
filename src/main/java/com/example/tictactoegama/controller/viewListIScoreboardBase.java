package xo;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class viewListIScoreboardBase extends AnchorPane {

    protected final Label playerNameLable;
    protected final Label scoreLable;

    public viewListIScoreboardBase(String playerName, int score) {

        playerNameLable = new Label();
        scoreLable = new Label();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(55.0);
        setPrefWidth(466.0);

        playerNameLable.setLayoutX(28.0);
        playerNameLable.setLayoutY(14.0);
        playerNameLable.setStyle("-fx-font-size: 18; -fx-font-family: 'Roboto', sans-serif; -fx-font-weight: 400;");
        playerNameLable.setText(playerName);

        scoreLable.setLayoutX(392.0);
        scoreLable.setLayoutY(14.0);
        scoreLable.setStyle("-fx-font-family: 'Roboto', sans-serif; -fx-font-size: 18; -fx-font-weight: 500; -fx-text-fill: #FFB048;");
        scoreLable.setText(""+score);

        getChildren().add(playerNameLable);
        getChildren().add(scoreLable);

    }
}
