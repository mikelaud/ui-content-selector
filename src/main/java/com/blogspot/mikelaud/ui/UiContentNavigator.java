package com.blogspot.mikelaud.ui;

import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;

public class UiContentNavigator extends TitledPane {

	private Accordion mAccordion;

	private void closeTitledPane() {
		if (null != mAccordion) {
			mAccordion.getPanes().remove(this);
			mAccordion = null;
		}
	}

	public UiContentNavigator(Accordion aAccordion) {
		mAccordion = aAccordion;
		final ToggleGroup group = new ToggleGroup();

		ToggleButton tb1 = new ToggleButton("Book");
		tb1.setToggleGroup(group);
		tb1.setSelected(true);
		ToggleButton tb2 = new ToggleButton("Chapter");
		tb2.setToggleGroup(group);
		
		HBox box = new HBox();
		Button tb3 = new Button("x");
		tb3.setOnAction(actionEvent -> closeTitledPane());
		box.getChildren().add(tb3);
		box.getChildren().add(tb1);
		box.getChildren().add(tb2);

		setGraphic(box);
		TreeView<String> treeView = new TreeView<>();
		TreeItem<String> root = new TreeItem<>("Library");
		root.setExpanded(true);
		root.getChildren().add(new TreeItem<>("Book 1"));
		root.getChildren().add(new TreeItem<>("Book 2"));
		root.getChildren().add(new TreeItem<>("Book 3"));
		treeView.setRoot(root);
		
		group.selectToggle(null);
		setContent(treeView);
	}

	public Accordion getAccordion() { return mAccordion; }

}
