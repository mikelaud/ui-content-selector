package com.blogspot.mikelaud.ui.impl;

import com.blogspot.mikelaud.ui.UiContentNavigator;
import com.blogspot.mikelaud.ui.UiContentSelector;
import com.blogspot.mikelaud.ui.UiImage;
import com.google.inject.Inject;
import com.google.inject.Provider;

import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;

public class UiContentSelectorImpl extends UiContentSelector {

	private final Provider<UiContentNavigator> UI_CONTENT_NAVIGATOR_PROVIDER;
	// buttons
	private final Button BUTTON_CLOSE_ALL;
	private final Button BUTTON_OPEN_LIBRARY;
	private final ToolBar BOOTONS_BAR;
	// accordion
	private final Accordion ACCORDION;
	private final UiImage ACCORDION_IMAGE;
	private final StackPane ACCORDION_PANE;
	
	private int mBookNumber;
	
	private UiContentNavigator newUiContentNavigator(String aTitle) {
		final UiContentNavigator uiContentNavigator = UI_CONTENT_NAVIGATOR_PROVIDER.get();
		uiContentNavigator.uiContentSelectorProperty().set(this);
		uiContentNavigator.setText(aTitle);
		return uiContentNavigator;
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
			mBookNumber++;
			final UiContentNavigator uiContentNavigator = newUiContentNavigator("Book N" + mBookNumber);
			ACCORDION.getPanes().add(uiContentNavigator);
			ACCORDION.setExpandedPane(uiContentNavigator);
		});
	}

	private void buildButtonsBar() {
		BOOTONS_BAR.getItems().add(BUTTON_CLOSE_ALL);
		BOOTONS_BAR.getItems().add(BUTTON_OPEN_LIBRARY);
	}

	private void buidAccordionImage() {
		final Image image = new Image(getClass().getClassLoader().getResourceAsStream("background.png"));
		ACCORDION_IMAGE.getImageView().setImage(image);
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
			buidAccordionImage();
			buidAccordionPane();
		}
		setTop(BOOTONS_BAR);
		setCenter(ACCORDION_PANE);
		setMinSize(0, 0);
	}

	@Inject
	private UiContentSelectorImpl
	(	Provider<UiContentNavigator> aUiContentNavigatorProvider
	,	UiImage aUiImage
	) {
		UI_CONTENT_NAVIGATOR_PROVIDER = aUiContentNavigatorProvider;
		{	// buttons
			BUTTON_CLOSE_ALL = new Button();
			BUTTON_OPEN_LIBRARY = new Button();
		}
		BOOTONS_BAR = new ToolBar();
		{	// accordion
			ACCORDION = new Accordion();
			ACCORDION_IMAGE = aUiImage;
			ACCORDION_PANE = new StackPane();
		}
		mBookNumber = 0;
		buildUi();
	}

	// buttons
	@Override public Button getButtonCloseAll() { return BUTTON_CLOSE_ALL; }
	@Override public Button getButtonOpenLibrary() { return BUTTON_OPEN_LIBRARY; }
	@Override public ToolBar getButtonsBar() { return BOOTONS_BAR; }
	// accordion
	@Override public Accordion getAccordion() { return ACCORDION; }
	@Override public UiImage getAccordionImage() { return ACCORDION_IMAGE; }
	@Override public StackPane getAccordionPane() { return ACCORDION_PANE; }
	
}
