package avi.lod.tlodscripttools.Patching;

import avi.lod.tlodscripttools.Preferences;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.difflib.DiffUtils;
import com.github.difflib.patch.Patch;
import com.github.difflib.patch.PatchFailedException;
import org.legendofdragoon.scripting.tokens.Script;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
public class DiffChange extends Change{
    public Patch<String> diff;

    public DiffChange(@JsonProperty("path") String path, @JsonProperty("changeName") String changeName, @JsonProperty("diff") Patch<String> diff){
        super(path, changeName);
        this.diff = diff;
    }

    @JsonIgnore
    public DiffChange(String path, String changeName, String updatedPath) throws IOException {
        super(path, changeName);
        String SCFileDumpLoc = Preferences.prefs.get("sc_files_folder");
        final byte[] bytes;
        Path ogFilePath = Paths.get(SCFileDumpLoc + path + ".og");
        if(Files.exists(ogFilePath)) {
            bytes = Files.readAllBytes(ogFilePath);
        }else{
            bytes = Files.readAllBytes(Paths.get(SCFileDumpLoc + path));
        }
        Script script = ScriptParser.disassembler.disassemble(bytes);
        String decompiled = ScriptParser.translator.translate(script,ScriptParser.meta);

        List<String> originalScript = Arrays.asList(decompiled.split("\n"));
        List<String> updatedScript = Arrays.asList(Files.readString(Paths.get(updatedPath)).split("\n"));
        this.diff = DiffUtils.diff(originalScript,updatedScript);
        //this.updatedScript = Arrays.asList(Files.readString(filePath).split("\n"));
    }
    String applyChanges(String script){
        try {
            List<String> patchedLines = DiffUtils.patch(Arrays.asList(script.split("\n")),this.diff);
            script = String.join("\n",patchedLines);
        } catch (PatchFailedException e) {
            throw new RuntimeException(e);
        }
        return script;
    }

}