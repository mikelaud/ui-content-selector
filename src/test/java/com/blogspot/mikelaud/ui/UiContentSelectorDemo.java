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
		final UiContentViewer uiContentViewer = injector.getInstance(UiContentViewer.class);
		//
		final Scene scene = new Scene(uiContentViewer, 1280, 720);
		aPrimaryStage.setTitle(UiContentSelectorDemo.class.getSimpleName());
		aPrimaryStage.setScene(scene);
		aPrimaryStage.sizeToScene();
		aPrimaryStage.centerOnScreen();
		aPrimaryStage.show();
		//
		final String mediaUri = getClass().getClassLoader().getResource("demo.mp4").toExternalForm();
		uiContentViewer.getUiVideo().openMedia(mediaUri);
	}

	public static void main(String[] aArguments) {
		launch(aArguments);
	}

}
