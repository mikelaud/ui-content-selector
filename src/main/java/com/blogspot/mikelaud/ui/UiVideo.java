package com.blogspot.mikelaud.ui;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.scene.layout.Region;
import javafx.scene.media.MediaView;

public abstract class UiVideo extends Region {

	public abstract ObjectProperty<MediaView> mediaViewProperty();
	public abstract DoubleProperty volumeZoomProperty();
	public abstract DoubleProperty volumeProperty();

}
