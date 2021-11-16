package android.src.main.java.io.flutter.plugins.firebase.dynamiclinks;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import java.util.HashMap;
import java.util.Map;

public class Utils {
  static Map<String, Object> getExceptionDetails(@Nullable Exception exception) {
    Map<String, Object> details = new HashMap<>();
    // There aren't any Dynamic Link Exceptions in the reference:
    // https://firebase.google.com/docs/reference/android/com/google/firebase/dynamiclinks/package-summary
    details.put("code", "unknown");
    if (exception != null) {
      details.put("message", exception.getMessage());
    } else {
      details.put("message", "An unknown error has occurred.");
    }
    return details;
  }

  static Map<String, Object> getMapFromPendingDynamicLinkData(
      PendingDynamicLinkData pendingDynamicLinkData) {
    Map<String, Object> dynamicLink = new HashMap<>();

    Uri link = pendingDynamicLinkData.getLink();
    dynamicLink.put("link", link != null ? link.toString() : null);

    Map<String, Object> utmParameters = new HashMap<>();

    for (String key : pendingDynamicLinkData.getUtmParameters().keySet()) {
      utmParameters.put(key, pendingDynamicLinkData.getUtmParameters().get(key).toString());
    }

    dynamicLink.put("utmParameters", utmParameters);

    Map<String, Object> androidData = new HashMap<>();
    androidData.put("clickTimestamp", pendingDynamicLinkData.getClickTimestamp());
    androidData.put("minimumVersion", pendingDynamicLinkData.getMinimumAppVersion());

    dynamicLink.put("android", androidData);
    return dynamicLink;
  }
}