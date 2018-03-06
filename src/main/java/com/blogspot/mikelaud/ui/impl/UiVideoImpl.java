package com.blogspot.mikelaud.ui.impl;

import com.blogspot.mikelaud.ui.UiVideo;
import com.google.inject.Inject;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleDoubleProperty;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Rectangle;

public class UiVideoImpl extends UiVideo {

	private final Rectangle CLIP;
	private final ReadOnlyObjectWrapper<MediaView> MEDIA_VIEW;
	private final DoubleProperty VOLUME_ZOOM;
	private final DoubleProperty VOLUME;

	@Override
	protected void layoutChildren() {
		// void
	}

	private void buildUi() {
		CLIP.widthProperty().bind(widthProperty());
		CLIP.heightProperty().bind(heightProperty());
		setClip(CLIP);
		//
		setMinSize(0, 0);
	}

	@Inject
	private UiVideoImpl() {
		CLIP = new Rectangle();
		MEDIA_VIEW = new ReadOnlyObjectWrapper<>(null);
		VOLUME_ZOOM = new SimpleDoubleProperty(0.0005);
		VOLUME = new SimpleDoubleProperty(0);
		buildUi();
	}

	@Override
	public void closeMedia() {
		final MediaView mediaView = MEDIA_VIEW.get();
		if (null == mediaView) return;
		//
		mediaView.fitHeightProperty().unbind();
		mediaView.fitWidthProperty().unbind();
		//
		final MediaPlayer mediaPlayer = mediaView.getMediaPlayer();
		if (null != mediaPlayer) {
			mediaPlayer.stop();
			mediaPlayer.volumeProperty().unbind();
			mediaPlayer.onReadyProperty().unbind();
			mediaPlayer.dispose();
			//
			mediaView.setOnScroll(null);
			mediaView.setOnMouseClicked(null);
			mediaView.setMediaPlayer(null);
		}
		//
		getChildren().remove(mediaView);
		MEDIA_VIEW.set(null);
	}

	@Override
	public void openMedia(String aMediaUri) {
		if (null == aMediaUri) return;
		closeMedia();
		//
		final Media media = new Media(aMediaUri);
		final MediaPlayer mediaPlayer = new MediaPlayer(media);
		final MediaView mediaView = new MediaView();
		mediaView.setMediaPlayer(mediaPlayer);
		//
		mediaView.setPreserveRatio(true);
		mediaView.setSmooth(true);
		//
		mediaView.fitHeightProperty().bind(heightProperty());
		mediaView.fitWidthProperty().bind(widthProperty());
		//
		MEDIA_VIEW.set(mediaView);
		getChildren().add(mediaView);
	}

	@Override public ReadOnlyObjectProperty<MediaView> mediaViewProperty() { return MEDIA_VIEW.getReadOnlyProperty(); }
	@Override public DoubleProperty volumeZoomProperty() { return VOLUME_ZOOM; }
	@Override public DoubleProperty volumeProperty() { return VOLUME; }

}
