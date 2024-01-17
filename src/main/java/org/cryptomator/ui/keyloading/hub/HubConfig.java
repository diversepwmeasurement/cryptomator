package org.cryptomator.ui.keyloading.hub;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.jetbrains.annotations.Nullable;

import java.net.URI;

// needs to be accessible by JSON decoder
@JsonIgnoreProperties(ignoreUnknown = true)
public class HubConfig {

	public String clientId;
	public String authEndpoint;
	public String tokenEndpoint;
	public String authSuccessUrl;
	public String authErrorUrl;
	public @Nullable String apiBaseUrl;
	@Deprecated // use apiBaseUrl + "/devices/"
	public String devicesResourceUrl;

	/**
	 * Get the URI pointing to the <code>/api/</code> base resource.
	 *
	 * @return <code>/api/</code> URI
	 * @apiNote URI is guaranteed to end on <code>/</code>
	 */
	public URI getApiBaseUrl() {
		if (apiBaseUrl != null) {
			// make sure to end on "/":
			return URI.create(apiBaseUrl + "/").normalize();
		} else { // legacy approach
			assert devicesResourceUrl != null;
			// make sure to end on "/":
			return URI.create(devicesResourceUrl + "/..").normalize();
		}
	}

	public URI getWebappBaseUrl() {
		return getApiBaseUrl().resolve("../app/");
	}
}
