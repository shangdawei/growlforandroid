package com.growlforandroid.common;

import java.io.File;
import java.net.URL;
import java.util.List;

import com.growlforandroid.gntp.HashAlgorithm;

import android.graphics.Bitmap;

/**
 * A repository of known Growl applications
 */
public interface IGrowlRegistry {
	GrowlApplication registerApplication(String name, URL icon);

	GrowlApplication getApplication(long id);
	
	GrowlApplication getApplication(String name);

	List<GrowlApplication> getApplications();
	
	Bitmap getIcon(URL icon);

	void registerResource(GrowlResource resource);

	NotificationType getNotificationType(int id);

	NotificationType getNotificationType(GrowlApplication application, String typeName);
	
	List<NotificationType> getNotificationTypes(GrowlApplication application);

	NotificationType registerNotificationType(GrowlApplication application, String typeName, String displayName,
			boolean enabled, URL iconUrl);

	byte[] getMatchingKey(String subscriberId, HashAlgorithm algorithm, String hash, String salt);

	File getResourcesDir();

	void addEventHandler(EventHandler handler);

	void removeEventHandler(EventHandler handler);

	public interface EventHandler {
		void onNotificationTypeRegistered(NotificationType type);

		void onApplicationRegistered(GrowlApplication app);
	}
}
