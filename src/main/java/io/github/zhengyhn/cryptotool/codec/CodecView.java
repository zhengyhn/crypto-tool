package io.github.zhengyhn.cryptotool.codec;

import io.github.zhengyhn.cryptotool.codec.CodecAlgorithm;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import lombok.Getter;

@Getter
public class CodecView extends Tab {
    private Button btnEncode;
    private Button btnDecode;
    private TextArea textAreaEncode;
    private TextArea textAreaDecode;
    private ChoiceBox algorithmChoiceBox;

    public CodecView() {
        GridPane gridPane = new GridPane();

        final int textAreaRow = 30;
        Label labelDecode = new Label("Decoded text");
        textAreaDecode = new TextArea();
        textAreaDecode.setPrefColumnCount(30);
        textAreaDecode.setPrefRowCount(textAreaRow);
        gridPane.add(labelDecode, 0, 1, 1, 1);
        gridPane.add(textAreaDecode, 0, 3, 1, textAreaRow);

        algorithmChoiceBox = new ChoiceBox();
        for (CodecAlgorithm algorithm: CodecAlgorithm.values()) {
            algorithmChoiceBox.getItems().add(algorithm.getName());
        }
        algorithmChoiceBox.setValue(CodecAlgorithm.BASE64.getName());
        btnEncode = new Button("Encode>>");
        btnDecode = new Button("<<Decode");
        gridPane.add(algorithmChoiceBox, 2, 5, 1, 2);
        gridPane.add(btnEncode, 2, 8, 1, 2);
        gridPane.add(btnDecode, 2, 11, 1, 2);

        Label labelEncode = new Label("Encoded text");
        textAreaEncode = new TextArea();
        textAreaEncode.setPrefColumnCount(30);
        textAreaEncode.setPrefRowCount(textAreaRow);
        gridPane.add(labelEncode, 4, 1, 1,2);
        gridPane.add(textAreaEncode, 4, 3, 1,textAreaRow);
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        setText("Encode/Decode");
        setClosable(false);
        setContent(gridPane);
    }
}
