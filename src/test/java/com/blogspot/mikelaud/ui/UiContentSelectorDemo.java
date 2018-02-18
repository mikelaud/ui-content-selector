package com.blogspot.mikelaud.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class UiContentSelectorDemo extends Application {

	@Override
	public void start(Stage aPrimaryStage) throws Exception {
		Scene scene = new Scene(new BorderPane(), 800, 600);
		aPrimaryStage.setTitle(UiContentSelectorDemo.class.getSimpleName());
		aPrimaryStage.setScene(scene);
		aPrimaryStage.sizeToScene();
		aPrimaryStage.centerOnScreen();
		aPrimaryStage.show();
	}

	public static void main(String[] aArguments) {
		launch(aArguments);
	}

}
