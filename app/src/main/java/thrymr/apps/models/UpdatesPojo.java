package thrymr.apps.models;

/**
 * Created by thrmyr on 24/9/15.
 */
public class UpdatesPojo {

    String evevt, description;

    public UpdatesPojo(String evevt, String description) {
        this.evevt = evevt;
        this.description = description;
    }

    public String getEvevt() {
        return evevt;
    }

    public void setEvevt(String evevt) {
        this.evevt = evevt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
