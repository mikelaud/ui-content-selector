package com.blogspot.mikelaud.ui;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;

public abstract class UiLibrary extends BorderPane {

	public abstract TreeItem<String> getTreeRoot();
	public abstract TreeView<String> getTreeView();

}
