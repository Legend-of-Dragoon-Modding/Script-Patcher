package avi.lod.tlodscripttools.Patching.JacksonUtils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.difflib.patch.ConflictOutput;

import java.io.Serializable;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties("CONFLICT_PRODUCES_EXCEPTION")
public abstract class PatchAnno<T> implements Serializable {

    @JsonIgnore
    public ConflictOutput<T> conflictOutput = null;
}
