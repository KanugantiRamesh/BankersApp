package thrymr.apps.materialtests.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by thrymr on 24/9/15.
 */
@ParseClassName("Updates")
public class Updates extends ParseObject {

    public String getUpdateType() {
        return getString("updateType");
    }

    public void setUpdateType(String name) {
        put("updateType", name);
    }

    public String getDescription() {
        return getString("updateNotes");
    }

    public void setDescription(String name) {
        put("updateNotes", name);
    }

    public String getUrl() {
        return getString("updateUrl");
    }

    public void setUrl(String name) {
        put("updateUrl", name);
    }

}
