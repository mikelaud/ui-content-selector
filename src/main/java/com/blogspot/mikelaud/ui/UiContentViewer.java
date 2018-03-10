package com.blogspot.mikelaud.ui;

import javafx.scene.control.SplitPane;

public abstract class UiContentViewer extends SplitPane {

	public abstract UiContentSelector getUiContentSelector();
	public abstract UiVideo getUiVideo();

}
