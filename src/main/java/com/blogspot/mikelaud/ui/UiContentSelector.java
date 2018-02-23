package com.blogspot.mikelaud.ui;

import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.TreeView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.ToolBar;

public class UiContentSelector extends BorderPane {

	private final Button BUTTON_CLOSE_ALL;
	private final Button BUTTON_OPEN_LIBRARY;
	private final ToolBar TOOL_BAR;
	private final Accordion ACCORDION;
	
	private ImageView newImageView(String aFileName) {
		final Image image = new Image(getClass().getClassLoader().getResourceAsStream(aFileName));
		final ImageView imageView = new ImageView(image);
		imageView.setSmooth(true);
		return imageView;
	}
	
	private TitledPane newTitledPane(String aTitle) {
		final TitledPane pane = new TitledPane();
		pane.setText(aTitle);
		final ToggleGroup group = new ToggleGroup();

		ToggleButton tb1 = new ToggleButton("Book");
		tb1.setToggleGroup(group);
		tb1.setSelected(true);
		ToggleButton tb2 = new ToggleButton("Chapter");
		tb2.setToggleGroup(group);
		
		HBox box = new HBox();
		Button tb3 = new Button("x");
		box.getChildren().add(tb3);
		box.getChildren().add(tb1);
		box.getChildren().add(tb2);

		pane.setGraphic(box);
		TreeView<String> treeView = new TreeView<>();
		TreeItem<String> root = new TreeItem<>("Library");
		root.setExpanded(true);
		root.getChildren().add(new TreeItem<>("Book 1"));
		root.getChildren().add(new TreeItem<>("Book 2"));
		root.getChildren().add(new TreeItem<>("Book 3"));
		treeView.setRoot(root);
		
		pane.setContent(treeView);
		return pane;
	}

	private void buildButtonCloseAll() {
		BUTTON_CLOSE_ALL.setText("Close All");
	}

	private void buildButtonOpenLibrary() {
		BUTTON_OPEN_LIBRARY.setText("Open Library");
	}

	private void buildToolBar() {
		TOOL_BAR.getItems().add(BUTTON_CLOSE_ALL);
		TOOL_BAR.getItems().add(BUTTON_OPEN_LIBRARY);
	}

	private void buidAccordion() {
		ACCORDION.getPanes().add(newTitledPane("Test 1"));
		ACCORDION.getPanes().add(newTitledPane("Test 2"));
		ACCORDION.getPanes().add(newTitledPane("Test 3"));
	}

	private void buildUi() {
		{	// buttons
			buildButtonCloseAll();
			buildButtonOpenLibrary();
		}
		buildToolBar();
		buidAccordion();
	}

	public UiContentSelector() {
		BUTTON_CLOSE_ALL = new Button();
		BUTTON_OPEN_LIBRARY = new Button();
		TOOL_BAR = new ToolBar();
		ACCORDION = new Accordion();
		//
		buildUi();
		//
		UiGallery g = new UiGallery();
		g.getImageViews().add(newImageView("Margaret_Hamilton.png"));
		StackPane stackPane = new StackPane();
		stackPane.getChildren().add(g);
		stackPane.getChildren().add(ACCORDION);
		setCenter(stackPane);
		setTop(TOOL_BAR);
	}

	public Button getButtonCloseAll() { return BUTTON_CLOSE_ALL; }
	public Button getButtonOpenLibrary() { return BUTTON_OPEN_LIBRARY; }
	public ToolBar getToolBar() { return TOOL_BAR; }
	public Accordion getAccordion() { return ACCORDION; }
	
}
