package com.blogspot.mikelaud.ui;

import com.blogspot.mikelaud.ui.UiGallery;

import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public abstract class UiContentSelector extends BorderPane {

	// buttons
	public abstract Button getButtonCloseAll();
	public abstract Button getButtonOpenLibrary();
	public abstract ToolBar getButtonsBar();
	// accordion
	public abstract Accordion getAccordion();
	public abstract UiGallery getAccordionImage();
	public abstract StackPane getAccordionPane();

}
