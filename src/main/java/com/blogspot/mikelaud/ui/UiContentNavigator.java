package com.blogspot.mikelaud.ui;

import javafx.beans.property.ObjectProperty;

import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public abstract class UiContentNavigator extends TitledPane {

	public abstract StackPane getPane();
	public abstract ObjectProperty<UiContentSelector> uiContentSelectorProperty();
	// ui
	public abstract UiLibrary getUiLibrary();
	public abstract UiBook getUiBook();
	public abstract UiChapter getUiChapter();
	// buttons
	public abstract ToggleButton getButtonLibrary();
	public abstract ToggleButton getButtonBook();
	public abstract ToggleButton getButtonChapter();
	public abstract Button getButtonClose();
	// buttons groups
	public abstract ToggleGroup getButtonsGroup();
	public abstract HBox getButtonsBox();

}
