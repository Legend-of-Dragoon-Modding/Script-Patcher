package avi.lod.tlodscripttools.Patching;

import avi.lod.tlodscripttools.Patching.JacksonUtils.*;
import avi.lod.tlodscripttools.Preferences;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.github.difflib.patch.*;
import org.legendofdragoon.scripting.tokens.Script;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedHashMap;
import java.util.Map;

public class Patcher {

    public void applyPatch(Patch p) throws IOException {
        LinkedHashMap<String, String> scripts = new LinkedHashMap<>();
        String SCFileDumpLoc = Preferences.prefs.get("sc_files_folder");
        for(Change change:p.changes){
            if(!scripts.containsKey(change.scriptPath)){
                final byte[] bytes;
                Path ogFilePath = Paths.get(SCFileDumpLoc + change.scriptPath + ".og");
                if(Files.exists(ogFilePath)) {
                    bytes = Files.readAllBytes(ogFilePath);
                }else{
                    bytes = Files.readAllBytes(Paths.get(SCFileDumpLoc + change.scriptPath));
                }
                Script script = ScriptParser.disassembler.disassemble(bytes);
                String decompiled = ScriptParser.translator.translate(script,ScriptParser.meta);
                scripts.put(change.scriptPath,decompiled);
            }
        }
        for(int i = 0; i < p.changes.size(); i++){
            String s = p.changes.get(i).applyChanges(scripts.get(p.changes.get(i).scriptPath));
            scripts.put(p.changes.get(i).scriptPath,s);
        }
        for(Map.Entry<String,String> scriptEntry: scripts.entrySet()) {
            Script recompiled = ScriptParser.lexer.lex(scriptEntry.getValue());
            final int[] result = ScriptParser.compiler.compile(recompiled);
            final String completeFilePath = SCFileDumpLoc + scriptEntry.getKey();
            boolean ogFileExists = Files.exists(Paths.get(completeFilePath + ".og"));
            if (ogFileExists) {
                File changedOldFile = new File(completeFilePath);
                changedOldFile.delete();
            } else {
                File oldFile = new File(completeFilePath);
                File newFile = new File(completeFilePath + ".og");
                oldFile.renameTo(newFile);
            }
            Files.write(Paths.get(SCFileDumpLoc + scriptEntry.getKey()), intsToBytes(result), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }
    }
    public void cleanPatches(){

    }

    public void savePatch(Patch p) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();

        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
                .allowIfSubType("com.github.difflib.patch.ChangeDelta")
                .allowIfSubType("com.github.difflib.patch.UpdateDelta")
                .allowIfSubType("com.github.difflib.patch.DeleteDelta")
                .allowIfSubType("com.github.difflib.patch.InsertDelta")
                .allowIfSubType("java.util.ArrayList")
                .build();
        objectMapper.activateDefaultTyping(ptv);

        objectMapper.addMixIn(InsertDelta.class, InsertDeltaAnno.class);
        objectMapper.addMixIn(DeleteDelta.class, DeleteDeltaAnno.class);
        objectMapper.addMixIn(ChangeDelta.class, ChangeDeltaAnno.class);
        objectMapper.addMixIn(EqualDelta.class, EqualDeltaAnno.class);
        objectMapper.addMixIn(Chunk.class, ChunkAnno.class);
        objectMapper.addMixIn(com.github.difflib.patch.Patch.class, PatchAnno.class);

        String jsonString = objectMapper.writeValueAsString(p);
        Files.write(Paths.get(p.patchName + ".json"),jsonString.getBytes(),  StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
    public Patch loadPatch(String path){
        try {
            byte[] jsonBytes = Files.readAllBytes(Paths.get(path));
            String jsonString = new String(jsonBytes);
            ObjectMapper objectMapper = new ObjectMapper();

            PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
                    .allowIfSubType("com.github.difflib.patch.ChangeDelta")
                    .allowIfSubType("com.github.difflib.patch.UpdateDelta")
                    .allowIfSubType("com.github.difflib.patch.DeleteDelta")
                    .allowIfSubType("com.github.difflib.patch.InsertDelta")
                    .allowIfSubType("java.util.ArrayList")
                    .build();

            objectMapper.activateDefaultTyping(ptv);
            objectMapper.addMixIn(InsertDelta.class, InsertDeltaAnno.class);
            objectMapper.addMixIn(DeleteDelta.class, DeleteDeltaAnno.class);
            objectMapper.addMixIn(ChangeDelta.class, ChangeDeltaAnno.class);
            objectMapper.addMixIn(EqualDelta.class, EqualDeltaAnno.class);
            objectMapper.addMixIn(Chunk.class, ChunkAnno.class);
            objectMapper.addMixIn(com.github.difflib.patch.Patch.class, PatchAnno.class);

            Patch patch = objectMapper.readValue(jsonString, Patch.class);
            return patch;
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }
    private static byte[] intsToBytes(final int[] ints) {
        final ByteBuffer buffer = ByteBuffer.allocate(ints.length * 0x4).order(ByteOrder.LITTLE_ENDIAN);
        buffer.asIntBuffer().put(ints);
        return buffer.array();
    }
}
