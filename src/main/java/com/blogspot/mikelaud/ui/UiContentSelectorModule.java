package com.blogspot.mikelaud.ui;

import com.blogspot.mikelaud.ui.impl.UiContentSelectorImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class UiContentSelectorModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(UiContentSelector.class).to(UiContentSelectorImpl.class).in(Singleton.class);
	}

}
