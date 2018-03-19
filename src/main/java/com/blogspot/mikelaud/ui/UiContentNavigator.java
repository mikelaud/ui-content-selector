package com.blogspot.mikelaud.ui;

import javafx.beans.property.ObjectProperty;

import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

public abstract class UiContentNavigator extends TitledPane {

	public abstract UiLibrary getUiLibrary();
	public abstract ObjectProperty<UiContentSelector> uiContentSelectorProperty();
	// buttons
	public abstract ToggleButton getButtonBook();
	public abstract ToggleButton getButtonChapter();
	public abstract Button getButtonClose();
	// buttons groups
	public abstract ToggleGroup getButtonsGroup();
	public abstract HBox getButtonsBox();

}
