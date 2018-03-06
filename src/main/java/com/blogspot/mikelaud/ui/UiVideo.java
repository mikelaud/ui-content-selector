package com.blogspot.mikelaud.ui;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.scene.layout.Region;
import javafx.scene.media.MediaView;

public abstract class UiVideo extends Region {

	public abstract void openMedia(String aMediaUri);
	public abstract void closeMedia();
	
	public abstract ReadOnlyObjectProperty<MediaView> mediaViewProperty();
	public abstract DoubleProperty volumeZoomProperty();
	public abstract DoubleProperty volumeProperty();

}
