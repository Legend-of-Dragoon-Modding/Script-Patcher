package avi.lod.tlodscripttools.Patching.JacksonUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.difflib.patch.AbstractDelta;
import com.github.difflib.patch.Chunk;
import com.github.difflib.patch.DeltaType;

import java.util.List;


public  abstract class InsertDeltaAnno<T> extends AbstractDelta<T>{
    @JsonCreator
    public InsertDeltaAnno(@JsonProperty("source") Chunk<T> source, @JsonProperty("target") Chunk<T> target) {
        super(DeltaType.INSERT,source,target);
    }

}