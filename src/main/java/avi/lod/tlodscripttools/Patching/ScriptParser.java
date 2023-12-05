package avi.lod.tlodscripttools.Patching;

import com.opencsv.exceptions.CsvException;
import org.legendofdragoon.scripting.Disassembler;
import org.legendofdragoon.scripting.Lexer;
import org.legendofdragoon.scripting.ScriptMeta;
import org.legendofdragoon.scripting.Translator;
import org.legendofdragoon.scripting.Compiler;
import java.io.IOException;
import java.nio.file.Path;

public final class ScriptParser{
    public static final ScriptMeta meta;
    static {
        try {
            meta = new ScriptMeta(Path.of("."));
            disassembler = new Disassembler(meta);
            lexer = new Lexer(meta);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }
    public static final Disassembler disassembler;
    public static final Translator translator = new Translator();
    public static final Compiler compiler = new Compiler();
    public static final Lexer lexer;
}