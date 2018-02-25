package com.blogspot.mikelaud.ui;

import com.blogspot.mikelaud.ui.impl.UiContentNavigatorImpl;
import com.blogspot.mikelaud.ui.impl.UiContentSelectorImpl;
import com.blogspot.mikelaud.ui.impl.UiImageImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class UiContentSelectorModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(UiContentNavigator.class).to(UiContentNavigatorImpl.class);
		bind(UiContentSelector.class).to(UiContentSelectorImpl.class).in(Singleton.class);
		bind(UiImage.class).to(UiImageImpl.class);
	}

}
