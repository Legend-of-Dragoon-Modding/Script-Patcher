package avi.lod.tlodscripttools.Patching;

import java.util.ArrayList;
import java.util.List;

public class Patch {
    public String patchName;
    public String patchDescription;
    /**
     Must be unique for every single patch
     */
    public String patchPrefix;

    public List<Change> changes;

    public Patch(){
        this.changes = new ArrayList<Change>();
    }
}
