package com.blogspot.mikelaud.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UiContentSelectorDemo extends Application {

	@Override
	public void start(Stage aPrimaryStage) throws Exception {
		Scene scene = new Scene(new UiContentSelector(), 600, 800);
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
