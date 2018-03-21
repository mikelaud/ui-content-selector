package com.blogspot.mikelaud.ui.impl;

import com.blogspot.mikelaud.ui.UiBook;
import com.blogspot.mikelaud.ui.UiChapter;
import com.blogspot.mikelaud.ui.UiContentNavigator;
import com.blogspot.mikelaud.ui.UiContentSelector;
import com.blogspot.mikelaud.ui.UiLibrary;
import com.google.inject.Inject;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import javafx.scene.control.Button;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

public class UiContentNavigatorImpl extends UiContentNavigator {

	private final ObjectProperty<UiContentSelector> UI_CONTENT_SELECTOR;
	// ui
	private final UiLibrary UI_LIBRARY;
	private final UiBook UI_BOOK;
	private final UiChapter UI_CHAPTER;
	// buttons
	private final ToggleButton BUTTON_BOOK;
	private final ToggleButton BUTTON_CHAPTER;
	private final Button BUTTON_CLOSE;
	// buttons groups
	private final ToggleGroup BUTTONS_GROUP;
	private final HBox BUTTONS_BOX;

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
			new ToggleButton().setToggleGroup(BUTTONS_GROUP);
			BUTTON_BOOK.setToggleGroup(BUTTONS_GROUP);
			BUTTON_CHAPTER.setToggleGroup(BUTTONS_GROUP);
		}
		BUTTONS_GROUP.selectToggle(null);
		BUTTONS_GROUP.selectedToggleProperty().addListener(listener -> {
			final Toggle selectedToggle = BUTTONS_GROUP.getSelectedToggle();
			if (selectedToggle == BUTTON_BOOK) {
				setContent(new Button("Book 1"));
			}
			else if (selectedToggle == BUTTON_CHAPTER) {
				setContent(new Button("Chapter 1"));
			}
			else if (selectedToggle == null) {
				setContent(UI_LIBRARY);
			}
		});
	}

	private void buildButtonsBox() {
		BUTTONS_BOX.getChildren().add(BUTTON_CLOSE);
		BUTTONS_BOX.getChildren().add(BUTTON_BOOK);
		BUTTONS_BOX.getChildren().add(BUTTON_CHAPTER);
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
		setGraphic(BUTTONS_BOX);
		setContent(UI_LIBRARY);
		setMinSize(0, 0);
	}

	@Inject
	private UiContentNavigatorImpl
	(	UiLibrary aUiLibrary
	,	UiBook aUiBook
	,	UiChapter aUiChapter
	) {
		UI_CONTENT_SELECTOR = new SimpleObjectProperty<>(null);
		// ui
		UI_LIBRARY = aUiLibrary;
		UI_BOOK = aUiBook;
		UI_CHAPTER = aUiChapter;
		{	// buttons
			BUTTON_CLOSE = new Button();
			BUTTON_BOOK = new ToggleButton();
			BUTTON_CHAPTER = new ToggleButton();
		}
		{	// buttons groups
			BUTTONS_GROUP = new ToggleGroup();
			BUTTONS_BOX = new HBox();
		}
		buildUi();
	}

	@Override public ObjectProperty<UiContentSelector> uiContentSelectorProperty() { return UI_CONTENT_SELECTOR; }
	// ui
	@Override public UiLibrary getUiLibrary() { return UI_LIBRARY; }
	@Override public UiBook getUiBook() { return UI_BOOK; }
	@Override public UiChapter getUiChapter() { return UI_CHAPTER; }
	// buttons
	@Override public ToggleButton getButtonBook() { return BUTTON_BOOK; }
	@Override public ToggleButton getButtonChapter() { return BUTTON_CHAPTER; }
	@Override public Button getButtonClose() { return BUTTON_CLOSE; }
	// buttons groups
	@Override public ToggleGroup getButtonsGroup() { return BUTTONS_GROUP; }
	@Override public HBox getButtonsBox() { return BUTTONS_BOX; }

}
