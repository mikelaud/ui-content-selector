package com.blogspot.mikelaud.ui.impl;

import com.blogspot.mikelaud.ui.UiImage;
import com.google.inject.Inject;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class UiImageImpl extends UiImage {

	private final Rectangle CLIP;
	private final ImageView IMAGE_VIEW;
	
	@Override
	protected void layoutChildren() {
		final Image image = IMAGE_VIEW.getImage();
		if (null == image) return;
		//
		final double imageWidth = image.getWidth();
		final double imageHeight = image.getHeight();
		if (imageHeight <= 0) return;
		//
		final double windowWidth = getWidth();
		final double windowHeight = getHeight();
		if (windowHeight <= 0) return;
		//
		final double imageRation = imageWidth / imageHeight;
		final double windowRation = windowWidth / windowHeight;
		if (windowRation <= 0) return;
		//
		double viewWidth;
		double viewHeigth;
		//
		double viewX;
		double viewY;
		//
		if (windowRation > imageRation) {
			viewWidth = imageWidth;
			viewHeigth = imageWidth / windowRation;
			//
			final double extraHeight = imageHeight - viewHeigth;
			//
			viewX = 0;
			viewY = extraHeight / 2;
		}
		else {
			viewWidth = imageHeight * windowRation;
			viewHeigth = imageHeight;
			//
			final double extraWidth = imageWidth - viewWidth;
			//
			viewX = extraWidth / 2;
			viewY = 0;
		}
		//
		final Rectangle2D viewport = new Rectangle2D(viewX, viewY, viewWidth, viewHeigth);
		IMAGE_VIEW.setViewport(viewport);
		//
		IMAGE_VIEW.setFitWidth(windowWidth);
		IMAGE_VIEW.setFitHeight(windowHeight);
	}

	private void buildUi() {
		CLIP.widthProperty().bind(widthProperty());
		CLIP.heightProperty().bind(heightProperty());
		setClip(CLIP);
		//
		IMAGE_VIEW.setSmooth(true);
		getChildren().add(IMAGE_VIEW);
		//
		setMinSize(0, 0);
	}

	@Inject
	private UiImageImpl() {
		CLIP = new Rectangle();
		IMAGE_VIEW = new ImageView();
		buildUi();
	}

	@Override public ImageView getImageView() { return IMAGE_VIEW; }

}
