package com.blogspot.mikelaud.ui.impl;

import com.blogspot.mikelaud.ui.UiVideo;
import com.google.inject.Inject;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

public class UiVideoImpl extends UiVideo {

	private final Rectangle CLIP;
	private final ReadOnlyObjectWrapper<MediaView> MEDIA_VIEW;
	private final DoubleProperty VOLUME_ZOOM;
	private final DoubleProperty VOLUME;

	@Override
	protected void layoutChildren() {
		final MediaView mediaView = MEDIA_VIEW.get();
		if (null == mediaView) return;
		//
		final MediaPlayer mediaPlayer = mediaView.getMediaPlayer();
		if (null == mediaPlayer) return;
		//
		final Media media = mediaPlayer.getMedia();
		if (null == media) return;
		//
		final double imageWidth = media.getWidth();
		final double imageHeight = media.getHeight();
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
		mediaView.setViewport(viewport);
		//
		mediaView.setFitWidth(windowWidth);
		mediaView.setFitHeight(windowHeight);
	}

	private EventHandler<ScrollEvent> createOnScroll(DoubleProperty aVolume, DoubleProperty aVolumeZoom) {
		return event -> aVolume.set(aVolume.get() + event.getDeltaY() * aVolumeZoom.get());
	}

	private EventHandler<MouseEvent> createOnMouseClicked(Media aMedia, MediaPlayer aMediaPlayer) {
		return event -> {
			final MouseButton mouseButton = event.getButton();
			if (MouseButton.SECONDARY == mouseButton) {
				if (Status.PLAYING == aMediaPlayer.getStatus()) {
					aMediaPlayer.pause();
				}
				else {
					aMediaPlayer.play();
				}
			}
			else if (MouseButton.PRIMARY == mouseButton) {
				final Duration duration = aMedia.getDuration();
				final Duration newPosition = duration.divide(getWidth()).multiply(event.getX());
				aMediaPlayer.seek(newPosition);
				if (Status.PLAYING != aMediaPlayer.getStatus()) {
					aMediaPlayer.play();
				}
			}
		};
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
		MEDIA_VIEW.set(mediaView);
		getChildren().add(mediaView);
		//
		mediaPlayer.volumeProperty().bind(VOLUME);
		mediaView.setOnScroll(createOnScroll(VOLUME, VOLUME_ZOOM));
		mediaView.setOnMouseClicked(createOnMouseClicked(media, mediaPlayer));
		//mediaPlayer.setOnReady(() -> mediaPlayer.play());
	}

	@Override public ReadOnlyObjectProperty<MediaView> mediaViewProperty() { return MEDIA_VIEW.getReadOnlyProperty(); }
	@Override public DoubleProperty volumeZoomProperty() { return VOLUME_ZOOM; }
	@Override public DoubleProperty volumeProperty() { return VOLUME; }

}
