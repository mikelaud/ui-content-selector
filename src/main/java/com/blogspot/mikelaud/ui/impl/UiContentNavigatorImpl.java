package com.blogspot.mikelaud.ui.impl;

import com.blogspot.mikelaud.ui.UiContentNavigator;
import com.blogspot.mikelaud.ui.UiContentSelector;
import com.google.inject.Inject;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;

public class UiContentNavigatorImpl extends UiContentNavigator {

	private final ObjectProperty<UiContentSelector> UI_CONTENT_SELECTOR;
	// buttons
	private final ToggleButton BUTTON_BOOK;
	private final ToggleButton BUTTON_CHAPTER;
	private final Button BUTTON_CLOSE;
	// buttons groups
	private final ToggleGroup BUTTONS_GROUP;
	private final HBox BUTTONS_BOX;
	// tree
	private final TreeItem<String> TREE_ROOT;
	private final TreeView<String> TREE_VIEW;

	private void closeTitledPane() {
		final UiContentSelector uiContentSelector = UI_CONTENT_SELECTOR.get();
		if (null != uiContentSelector) {
			uiContentSelector.getAccordion().getPanes().remove(this);
			UI_CONTENT_SELECTOR.set(null);
		}
	}

	private void buildButtonClose() {
		BUTTON_CLOSE.setText("x");
		BUTTON_CLOSE.setOnAction(actionEvent -> closeTitledPane());
	}

	private void buildButtonBook() {
		BUTTON_BOOK.setText("Book");
	}

	private void buildButtonChapter() {
		BUTTON_CHAPTER.setText("Chapter");
	}

	private void buildButtonsGroup() {
		{	// buttons
			BUTTON_BOOK.setToggleGroup(BUTTONS_GROUP);
			BUTTON_CHAPTER.setToggleGroup(BUTTONS_GROUP);
		}
		BUTTONS_GROUP.selectToggle(null);
	}

	private void buildButtonsBox() {
		BUTTONS_BOX.getChildren().add(BUTTON_CLOSE);
		BUTTONS_BOX.getChildren().add(BUTTON_BOOK);
		BUTTONS_BOX.getChildren().add(BUTTON_CHAPTER);
	}

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
		{	// buttons
			buildButtonClose();
			buildButtonBook();
			buildButtonChapter();
		}
		{	// buttons groups
			buildButtonsGroup();
			buildButtonsBox();
		}
		{	// tree
			buildTreeRoot();
			buildTreeView();
		}
		setGraphic(BUTTONS_BOX);
		setContent(TREE_VIEW);
		setMinSize(0, 0);
	}

	@Inject
	private UiContentNavigatorImpl() {
		UI_CONTENT_SELECTOR = new SimpleObjectProperty<>(null);
		{	// buttons
			BUTTON_CLOSE = new Button();
			BUTTON_BOOK = new ToggleButton();
			BUTTON_CHAPTER = new ToggleButton();
		}
		{	// buttons groups
			BUTTONS_GROUP = new ToggleGroup();
			BUTTONS_BOX = new HBox();
		}
		{	// tree
			TREE_ROOT = new TreeItem<>();
			TREE_VIEW = new TreeView<>();
		}
		buildUi();
	}

	@Override public ObjectProperty<UiContentSelector> uiContentSelectorProperty() { return UI_CONTENT_SELECTOR; }
	// buttons
	@Override public ToggleButton getButtonBook() { return BUTTON_BOOK; }
	@Override public ToggleButton getButtonChapter() { return BUTTON_CHAPTER; }
	@Override public Button getButtonClose() { return BUTTON_CLOSE; }
	// buttons groups
	@Override public ToggleGroup getButtonsGroup() { return BUTTONS_GROUP; }
	@Override public HBox getButtonsBox() { return BUTTONS_BOX; }
	// tree
	@Override public TreeItem<String> getTreeRoot() { return TREE_ROOT; }
	@Override public TreeView<String> getTreeView() { return TREE_VIEW; }

}
