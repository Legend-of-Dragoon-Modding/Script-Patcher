package avi.lod.tlodscripttools.Patching;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ChestContentChange.class, name = "ChestContentChange"),
        @JsonSubTypes.Type(value = DiffChange.class, name = "DiffChange")
})
public abstract class Change {
    public final String scriptPath;

    public String changeName;
    Change(String p){scriptPath=p;}


    abstract String applyChanges(String script);
}
