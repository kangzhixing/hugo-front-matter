import java.io.*;
import java.text.MessageFormat;
import java.time.Instant;

/**
 * <p>Hugo文章的扉页生成器</p>
 * <p>generate the front matter for markdown file</p>
 *
 * @author kangzhixing
 */
public class FrontMetaGenerator {

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    public static void main(String[] args) {
        String path = FrontMetaGenerator.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        File f = new File(path);
        readPost(f);
    }

    public static void readPost(File f) {
        if (f.isDirectory()) {
            for (File file : f.listFiles()) {
                readPost(file);
            }
        } else if (f.getName().endsWith(".md")) {
            writePostMeta(f);
        }
    }

    private static void writePostMeta(File f) {
        String content = FileUtil.read(f);
        if (!content.startsWith("---" + LINE_SEPARATOR)) {
            FileUtil.write(f, getFileMeta(f) + content);
        }
    }

    private static String getFileMeta(File f) {
        String parentFileName = f.getParentFile().getName();
        return MessageFormat.format("---\n" +
                        "title: \"{0}\"\n" +
                        "date: {1}\n" +
                        "{2}" +
                        "---\n", f.getName().substring(0, f.getName().length() - 3),
                Instant.ofEpochMilli(f.lastModified()).toString(),
                "post".equals(parentFileName) ? "" : "categories: [\"" + parentFileName + "\"]\n");
    }
}
