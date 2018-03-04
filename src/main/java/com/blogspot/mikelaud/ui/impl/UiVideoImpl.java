package com.blogspot.mikelaud.ui.impl;

import com.blogspot.mikelaud.ui.UiVideo;
import com.google.inject.Inject;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;

import javafx.scene.media.MediaView;

public class UiVideoImpl extends UiVideo {

	private final ObjectProperty<MediaView> MEDIA_VIEW;
	private final DoubleProperty VOLUME_ZOOM;
	private final DoubleProperty VOLUME;

	@Override
	protected void layoutChildren() {
		// void
	}

	@Inject
	private UiVideoImpl() {
		MEDIA_VIEW = new SimpleObjectProperty<>(null);
		VOLUME_ZOOM = new SimpleDoubleProperty(0.0005);
		VOLUME = new SimpleDoubleProperty(0);
	}

	@Override public ObjectProperty<MediaView> mediaViewProperty() { return MEDIA_VIEW; }
	@Override public DoubleProperty volumeZoomProperty() { return VOLUME_ZOOM; }
	@Override public DoubleProperty volumeProperty() { return VOLUME; }

}
