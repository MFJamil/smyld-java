package org.smyld.app.pe.model.user;

import java.util.HashSet;

/**
 * @author jamil
 *
 */
public interface PEUser {
	public HashSet<String> getRoles();
	public UserSettings getSettings();
}
