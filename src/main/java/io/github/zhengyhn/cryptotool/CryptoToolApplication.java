package io.github.zhengyhn.cryptotool;

import io.github.zhengyhn.cryptotool.codec.CodecController;
import io.github.zhengyhn.cryptotool.codec.CodecView;
import io.github.zhengyhn.cryptotool.hashing.HashingController;
import io.github.zhengyhn.cryptotool.hashing.HashingView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CryptoToolApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        TabPane tabPane = new TabPane();

        CodecView codecView = new CodecController().getCodecView();
        tabPane.getTabs().add(codecView);
        HashingView hashingView = new HashingController().getHashingView();
        tabPane.getTabs().add(hashingView);

        VBox vBox = new VBox(tabPane);
        Scene scene = new Scene(vBox, 1080, 720);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Crypto Tool");
        primaryStage.setWidth(1080);
        primaryStage.setHeight(720);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
