package com.blogspot.mikelaud.ui.impl;

import com.blogspot.mikelaud.ui.UiContentNavigator;
import com.blogspot.mikelaud.ui.UiContentSelector;
import com.blogspot.mikelaud.ui.UiGallery;
import com.google.inject.Inject;

import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class UiContentSelectorImpl extends UiContentSelector {

	// buttons
	private final Button BUTTON_CLOSE_ALL;
	private final Button BUTTON_OPEN_LIBRARY;
	private final ToolBar BOOTONS_BAR;
	// accordion
	private final Accordion ACCORDION;
	private final UiGallery ACCORDION_IMAGE;
	private final StackPane ACCORDION_PANE;
	
	private ImageView newImageView(String aFileName) {
		final Image image = new Image(getClass().getClassLoader().getResourceAsStream(aFileName));
		final ImageView imageView = new ImageView(image);
		imageView.setSmooth(true);
		return imageView;
	}
	
	private UiContentNavigator newContentNavigator(String aTitle) {
		final UiContentNavigator contentNavigator = new UiContentNavigator(ACCORDION);
		contentNavigator.setText(aTitle);
		return contentNavigator;
	}

	private void buildButtonCloseAll() {
		BUTTON_CLOSE_ALL.setText("Close All");
		BUTTON_CLOSE_ALL.setOnAction(actionEvent -> {
			ACCORDION.getPanes().clear();
		});
	}

	private void buildButtonOpenLibrary() {
		BUTTON_OPEN_LIBRARY.setText("Open Library");
		BUTTON_OPEN_LIBRARY.setOnAction(actionEvent -> {
			ACCORDION.getPanes().add(newContentNavigator("Book N"));
		});
	}

	private void buildButtonsBar() {
		BOOTONS_BAR.getItems().add(BUTTON_CLOSE_ALL);
		BOOTONS_BAR.getItems().add(BUTTON_OPEN_LIBRARY);
	}

	private void buidAccordion() {
		ACCORDION.getPanes().add(newContentNavigator("Test 1"));
		ACCORDION.getPanes().add(newContentNavigator("Test 2"));
		ACCORDION.getPanes().add(newContentNavigator("Test 3"));
	}

	private void buidAccordionImage() {
		ACCORDION_IMAGE.getImageViews().add(newImageView("Margaret_Hamilton.png"));
	}

	private void buidAccordionPane() {
		ACCORDION_PANE.getChildren().add(ACCORDION_IMAGE);
		ACCORDION_PANE.getChildren().add(ACCORDION);
	}

	private void buildUi() {
		{	// buttons
			buildButtonCloseAll();
			buildButtonOpenLibrary();
		}
		buildButtonsBar();
		{	// accordion
			buidAccordion();
			buidAccordionImage();
			buidAccordionPane();
		}
		setTop(BOOTONS_BAR);
		setCenter(ACCORDION_PANE);
	}

	@Inject
	private UiContentSelectorImpl() {
		{	// buttons
			BUTTON_CLOSE_ALL = new Button();
			BUTTON_OPEN_LIBRARY = new Button();
		}
		BOOTONS_BAR = new ToolBar();
		{	// accordion
			ACCORDION = new Accordion();
			ACCORDION_IMAGE = new UiGallery();
			ACCORDION_PANE = new StackPane();
		}
		buildUi();
	}

	// buttons
	@Override public Button getButtonCloseAll() { return BUTTON_CLOSE_ALL; }
	@Override public Button getButtonOpenLibrary() { return BUTTON_OPEN_LIBRARY; }
	@Override public ToolBar getButtonsBar() { return BOOTONS_BAR; }
	// accordion
	@Override public Accordion getAccordion() { return ACCORDION; }
	@Override public UiGallery getAccordionImage() { return ACCORDION_IMAGE; }
	@Override public StackPane getAccordionPane() { return ACCORDION_PANE; }
	
}
