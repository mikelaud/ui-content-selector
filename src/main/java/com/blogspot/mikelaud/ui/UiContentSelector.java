package com.blogspot.mikelaud.ui;

import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToggleButton;

public class UiContentSelector extends StackPane {

	private ImageView createImageView(String aName) {
		return new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(aName)));
	}
	
	private TitledPane newTitledPane(String aTitle) {
		final TitledPane pane = new TitledPane();
		pane.setText(aTitle);
		pane.setContent(new Button(aTitle));
		
		final ToggleGroup group = new ToggleGroup();

		ToggleButton tb1 = new ToggleButton("Book");
		tb1.setToggleGroup(group);
		tb1.setSelected(true);
		ToggleButton tb2 = new ToggleButton("Chapter");
		tb2.setToggleGroup(group);
		
		HBox box = new HBox();
		box.getChildren().add(new Button("x"));
		box.getChildren().add(tb1);
		box.getChildren().add(tb2);

		pane.setGraphic(box);
		return pane;
	}

	private Accordion newAccordion() {
		final Accordion accordion = new Accordion();
		accordion.getPanes().add(newTitledPane("Test 1"));
		accordion.getPanes().add(newTitledPane("Test 2"));
		accordion.getPanes().add(newTitledPane("Test 3"));
		return accordion;
	}

	public UiContentSelector() {
		final Accordion accordion = newAccordion();
		UiGallery g = new UiGallery();
		g.getImageViews().add(createImageView("Margaret_Hamilton.png"));
		getChildren().add(g);
		getChildren().add(accordion);
	}

}
