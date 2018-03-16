package com.blogspot.mikelaud.ui.impl;

import com.blogspot.mikelaud.ui.UiLibrary;
import com.google.inject.Inject;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class UiLibraryImpl extends UiLibrary {

	private final TreeItem<String> TREE_ROOT;
	private final TreeView<String> TREE_VIEW;

	private void buildTreeRoot() {
		TREE_ROOT.setValue("Library");
		TREE_ROOT.setExpanded(true);
		{	// root items
			TREE_ROOT.getChildren().add(new TreeItem<>("Book 1"));
			TREE_ROOT.getChildren().add(new TreeItem<>("Book 2"));
			TREE_ROOT.getChildren().add(new TreeItem<>("Book 3"));
		}
	}

	private void buildTreeView() {
		TREE_VIEW.setRoot(TREE_ROOT);
	}

	private void buildUi() {
		{	// tree
			buildTreeRoot();
			buildTreeView();
		}
		setCenter(TREE_VIEW);
	}

	@Inject
	private UiLibraryImpl() {
		{	// tree
			TREE_ROOT = new TreeItem<>();
			TREE_VIEW = new TreeView<>();
		}
		buildUi();
	}

	@Override public TreeItem<String> getTreeRoot() { return TREE_ROOT; }
	@Override public TreeView<String> getTreeView() { return TREE_VIEW; }

}
