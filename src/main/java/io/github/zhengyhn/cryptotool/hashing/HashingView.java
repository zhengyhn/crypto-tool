package io.github.zhengyhn.cryptotool.hashing;

import io.github.zhengyhn.cryptotool.codec.CodecAlgorithm;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import lombok.Getter;

@Getter
public class HashingView extends Tab {
    private Button btnEncode;
    private TextArea textAreaEncode;
    private TextArea textAreaDecode;
    private ChoiceBox algorithmChoiceBox;

    public HashingView() {
        GridPane gridPane = new GridPane();

        final int textAreaRow = 30;
        Label labelDecode = new Label("Text");
        textAreaDecode = new TextArea();
        textAreaDecode.setPrefColumnCount(30);
        textAreaDecode.setPrefRowCount(textAreaRow);
        gridPane.add(labelDecode, 0, 1, 1, 1);
        gridPane.add(textAreaDecode, 0, 3, 1, textAreaRow);

        algorithmChoiceBox = new ChoiceBox();
        for (HashingAlgorithm algorithm: HashingAlgorithm.values()) {
            algorithmChoiceBox.getItems().add(algorithm.getName());
        }
        algorithmChoiceBox.setValue(HashingAlgorithm.SHA1.getName());
        btnEncode = new Button("Generate");
        gridPane.add(algorithmChoiceBox, 2, 5, 1, 2);
        gridPane.add(btnEncode, 2, 8, 1, 2);

        Label labelEncode = new Label("Digest text");
        textAreaEncode = new TextArea();
        textAreaEncode.setPrefColumnCount(30);
        textAreaEncode.setPrefRowCount(textAreaRow);
        gridPane.add(labelEncode, 4, 1, 1,2);
        gridPane.add(textAreaEncode, 4, 3, 1,textAreaRow);
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        setText("Hashing");
        setClosable(false);
        setContent(gridPane);
    }
}
