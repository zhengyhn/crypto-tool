package io.github.zhengyhn.cryptotool.codec;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import lombok.Getter;

public class CodecController {
    @Getter
    private CodecView codecView;

    public CodecController() {
        codecView = new CodecView();

        codecView.getBtnEncode().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event){
                encode();
            }
        });
        codecView.getBtnDecode().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event){
                decode();
            }
        });
    }

    public CodecView getCodecView() {
        return codecView;
    }

    private void encode() {
        String text = codecView.getTextAreaDecode().getText();
        String algorithm = (String)codecView.getAlgorithmChoiceBox().getValue();
        ICodec codecImpl = CodecFactory.getInstance().getCodecImpl(algorithm);
        if (codecImpl == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("No such algorithm!");
            alert.showAndWait();
            return;
        }
        String result = codecImpl.encode(text);
        codecView.getTextAreaEncode().setText(result);
    }

    private void decode() {
        String text = codecView.getTextAreaEncode().getText();
        String algorithm = (String)codecView.getAlgorithmChoiceBox().getValue();
        ICodec codecImpl = CodecFactory.getInstance().getCodecImpl(algorithm);
        if (codecImpl == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("No such algorithm!");
            alert.showAndWait();
            return;
        }
        String result = codecImpl.decode(text);
        codecView.getTextAreaDecode().setText(result);
    }
}
