package thrymr.apps.bankersapp;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by thrymr on 22/9/15.
 */
@ParseClassName("Task")
public class Task extends ParseObject {
    public Task(){

    }

    public boolean isCompleted(){
        return getBoolean("completed");
    }

    public void setCompleted(boolean complete){
        put("completed", complete);
    }

    public String getDescription(){
        return getString("description");
    }

    public void setDescription(String description){
        put("description", description);
    }
}
