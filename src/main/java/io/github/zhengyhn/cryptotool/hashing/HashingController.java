package io.github.zhengyhn.cryptotool.hashing;

import io.github.zhengyhn.cryptotool.codec.CodecFactory;
import io.github.zhengyhn.cryptotool.codec.ICodec;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import lombok.Getter;

public class HashingController {
    @Getter
    private HashingView hashingView;

    public HashingController() {
        hashingView = new HashingView();

        hashingView.getBtnEncode().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event){
                encode();
            }
        });
    }

    private void encode() {
        String text = hashingView.getTextAreaDecode().getText();
        String algorithm = (String) hashingView.getAlgorithmChoiceBox().getValue();
        IHashing codecImpl = HashingFactory.getInstance().getCodecImpl(algorithm);
        if (codecImpl == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("No such algorithm!");
            alert.showAndWait();
            return;
        }
        String result = codecImpl.digest(text);
        hashingView.getTextAreaEncode().setText(result);
    }
}
