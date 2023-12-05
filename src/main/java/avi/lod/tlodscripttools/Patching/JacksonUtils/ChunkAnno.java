package avi.lod.tlodscripttools.Patching.JacksonUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public  abstract class ChunkAnno<T>{


    @JsonCreator
    public ChunkAnno(@JsonProperty("position") int position, @JsonProperty("lines") List<T> lines, @JsonProperty("changePosition") List<Integer> changePosition) {
    }

}