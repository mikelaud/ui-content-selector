package com.blogspot.mikelaud.ui;

import java.util.function.Consumer;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;

public class UiGallery extends Region {

	private final Orientation ORIENTATION;
	private final boolean HORIZONTAL;
	private final Group GROUP;
	private final Rectangle CLIP;
	
	private boolean mMousePressed = false;
	private double mMousePosition = 0;
	private double mMouseShift = 0;

	private double mGroupShift = 0;

	private double mGroupWidth = 0;
	private double mGroupHeight = 0;

	private double getMousePosition(MouseEvent aMouseEvent) {
		return HORIZONTAL ? aMouseEvent.getSceneX() : aMouseEvent.getSceneY();
	}
	
	private double getGalleryLongSide() {
		return HORIZONTAL ? getWidth() : getHeight();
	}
	
	private double getGalleryShortSide() {
		return HORIZONTAL ?  getHeight() : getWidth();
	}
	
	private double getGroupLongSide() {
		return HORIZONTAL ? mGroupWidth : mGroupHeight;
	}
	
	private double getGroupShortSide() {
		return HORIZONTAL ? mGroupHeight : mGroupWidth;
	}
	
	private void setGroupLongSide(double aGroupLongSide) {
		if (HORIZONTAL) {
			mGroupWidth = aGroupLongSide;
		}
		else {
			mGroupHeight = aGroupLongSide;
		}
	}
	
	private void setGroupShortSide(double aGroupShortSide) {
		if (HORIZONTAL) {
			mGroupHeight = aGroupShortSide;
		}
		else {
			mGroupWidth = aGroupShortSide;
		}
	}
	
	private void setMouseShift(double aNewShift) {
		mMouseShift = aNewShift;
		setNeedsLayout(true);
		requestLayout();
	}

	private void addMouseShift(double aNewShift) {
		setMouseShift(mMouseShift + aNewShift);
	}

	private void relocateGroup(double aNewShift) {
		mGroupShift = mMouseShift = aNewShift;
		if (HORIZONTAL) {
			GROUP.relocate(mGroupShift, 0);
		}
		else {
			GROUP.relocate(0, mGroupShift);
		}
	}

	private double getImageRatio(Image aImage) {
		if (HORIZONTAL) {
			return aImage.getWidth() / aImage.getHeight();
		}
		else {
			return  aImage.getHeight() / aImage.getWidth();
		}
	}
	
	private double relocateImage(ImageView aImageView, Image aImage, double aShift) {
		final double imageShortSize = getGroupShortSide();
		final double imageLongSize = imageShortSize * getImageRatio(aImage);
		if (HORIZONTAL) {
			aImageView.setFitHeight(imageShortSize);
			aImageView.setFitWidth(imageLongSize);
			aImageView.relocate(aShift, 0);
		}
		else {
			aImageView.setFitWidth(imageShortSize);
			aImageView.setFitHeight(imageLongSize);
			aImageView.relocate(0, aShift);
		}
		return imageLongSize;
	}
	
	private void layoutImages(boolean aLazy) {
		final double newShortSide = getGalleryShortSide();
		final double shortSide = getGroupShortSide();
		if (aLazy && Double.compare(shortSide, newShortSide) == 0) return;
		setGroupShortSide(newShortSide);
		double shift = 0;
		for (ImageView imageView : getImageViews()) {
			if (null == imageView) continue;
			Image image = imageView.getImage();
			if (null == image) continue;
			double imageLongSide = relocateImage(imageView, image, shift);
			shift += imageLongSide;
		}
		setGroupLongSide(shift);
	}

	private void layoutGroup() {
		if (Double.compare(mGroupShift, mMouseShift) == 0) return;
		if (HORIZONTAL) {
			GROUP.relocate(mMouseShift, 0);
		}
		else {
			GROUP.relocate(0, mMouseShift);
		}
		mGroupShift = mMouseShift;
	}

	private void layoutBounds(Consumer<Double> aRelocator) {
		final double newLongSide = getGalleryLongSide();
		final double longSide = getGroupLongSide();
		if (Double.compare(longSide, newLongSide) < 0) {
			aRelocator.accept((newLongSide - longSide) / 2);
		}
		else {
			if (Double.compare(mMouseShift, 0) > 0) {
				aRelocator.accept(0d);
			}
			else {
				if (Double.compare(mMouseShift + longSide, newLongSide) < 0) {
					aRelocator.accept(newLongSide - longSide);
				}
			}
		}
	}

	@Override
	protected void layoutChildren() {
		layoutImages(true);
		layoutGroup();
		if (mMousePressed) return;
		layoutBounds(this::relocateGroup);
	}

	public UiGallery(Orientation aOrientation) {
		ORIENTATION = aOrientation;
		HORIZONTAL = (Orientation.HORIZONTAL == ORIENTATION);
		GROUP = new Group();
		CLIP = new Rectangle();
		getChildren().add(GROUP);
		setOnMousePressed(event -> {
			mMousePressed = true;
			mMousePosition = getMousePosition(event);
		});
		setOnMouseDragged(event -> {
			double newMousePosition = getMousePosition(event);
			double mouseShift = newMousePosition - mMousePosition;
			mMousePosition = newMousePosition;
			addMouseShift(mouseShift);
		});
		setOnMouseReleased(event -> {
			layoutBounds(this::setMouseShift);
			mMousePressed = false;
		});
		CLIP.widthProperty().bind(widthProperty());
		CLIP.heightProperty().bind(heightProperty());
		setClip(CLIP);
		//
		GROUP.getChildren().addListener(new ListChangeListener<Node>() {
			@Override
			public void onChanged(Change<? extends Node> aChange) {
				layoutImages(false);
			}
		});
	}

	public UiGallery() {
		this(Orientation.HORIZONTAL);
	}

	public Orientation getOrientation() {
		return ORIENTATION;
	}
	
	@SuppressWarnings("unchecked")
	public ObservableList<ImageView> getImageViews() {
		return ObservableList.class.cast(GROUP.getChildren());
	}

}

