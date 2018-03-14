package com.blogspot.mikelaud.ui;

import com.blogspot.mikelaud.ui.impl.UiBookImpl;
import com.blogspot.mikelaud.ui.impl.UiChapterImpl;
import com.blogspot.mikelaud.ui.impl.UiContentNavigatorImpl;
import com.blogspot.mikelaud.ui.impl.UiContentSelectorImpl;
import com.blogspot.mikelaud.ui.impl.UiContentViewerImpl;
import com.blogspot.mikelaud.ui.impl.UiImageImpl;
import com.blogspot.mikelaud.ui.impl.UiLibraryImpl;
import com.blogspot.mikelaud.ui.impl.UiVideoImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class UiContentSelectorModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(UiBook.class).to(UiBookImpl.class);
		bind(UiChapter.class).to(UiChapterImpl.class);
		bind(UiContentNavigator.class).to(UiContentNavigatorImpl.class);
		bind(UiContentSelector.class).to(UiContentSelectorImpl.class).in(Singleton.class);
		bind(UiContentViewer.class).to(UiContentViewerImpl.class).in(Singleton.class);
		bind(UiImage.class).to(UiImageImpl.class);
		bind(UiLibrary.class).to(UiLibraryImpl.class);
		bind(UiVideo.class).to(UiVideoImpl.class);
	}

}
