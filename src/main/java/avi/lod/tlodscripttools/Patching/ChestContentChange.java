package avi.lod.tlodscripttools.Patching;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ChestContentChange extends Change{
    public int itemIndex;
    public String acquiredText;
    public String inventoryFullText;

    public ChestContentChange(@JsonProperty("path") String path,@JsonProperty("itemIndex") int itemIndex,@JsonProperty("inventoryFullText") String inventoryFullText){
        super(path);
        this.itemIndex = itemIndex;
        this.inventoryFullText = inventoryFullText;
        setAcquiredText();
    }

    @JsonIgnore
    public ChestContentChange(@JsonProperty("name") String path,@JsonProperty("itemIndex") int itemIndex){
        this(path,itemIndex,"Cannot carry any more items.");
    }
    public void setAcquiredText(){
        this.acquiredText = "Acquired " + Util.getItemNameFromId(itemIndex);
    }
    @JsonIgnore
    public ChestContentChange(String path){
        super(path);
    }
    @JsonIgnore
    String applyChanges(String script) {
        String oldItemId = "";
        int currentIndex = script.indexOf(ScriptParser.meta.methods[864].name) + ScriptParser.meta.methods[864].name.length() + 1;
        char c;
        while (true) {
            c = script.charAt(currentIndex);
            if(c == ','){break;}
            currentIndex++;
            oldItemId = new StringBuilder(oldItemId).insert(oldItemId.length(),c).toString();
        }
        oldItemId = oldItemId.trim();
        String newItemId = Integer.toHexString(itemIndex);
        newItemId = new StringBuilder(newItemId).insert(0,"0x").toString();
        script = script.replace(oldItemId, newItemId);

        String oldAcquiredText = "Acquired " + Util.getItemNameFromId(Integer.valueOf(oldItemId.substring(2),16));
        script = script.replace(oldAcquiredText, acquiredText);
        return script;
    }
}