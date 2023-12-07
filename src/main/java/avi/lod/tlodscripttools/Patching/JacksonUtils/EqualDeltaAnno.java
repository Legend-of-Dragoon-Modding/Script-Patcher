package avi.lod.tlodscripttools.Patching.JacksonUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.difflib.patch.AbstractDelta;
import com.github.difflib.patch.Chunk;
import com.github.difflib.patch.DeltaType;

public abstract class EqualDeltaAnno<T> extends AbstractDelta<T> {
    @JsonCreator
    public EqualDeltaAnno(@JsonProperty("source") Chunk<T> source, @JsonProperty("target") Chunk<T> target) {
        super(DeltaType.EQUAL,source,target);
    }
}
