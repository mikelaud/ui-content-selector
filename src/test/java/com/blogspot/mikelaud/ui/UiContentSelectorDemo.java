package com.blogspot.mikelaud.ui;

import com.google.inject.Guice;
import com.google.inject.Injector;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UiContentSelectorDemo extends Application {

	@Override
	public void start(Stage aPrimaryStage) throws Exception {
		final Injector injector = Guice.createInjector(new UiContentSelectorModule());
		final UiContentSelector uiContentSelector = injector.getInstance(UiContentSelector.class);
		//
		final Scene scene = new Scene(uiContentSelector, 600, 800);
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
