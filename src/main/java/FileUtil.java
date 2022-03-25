import java.io.*;

public class FileUtil {

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    public static String read(File file) {
        String line;
        StringBuffer buf = new StringBuffer();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            // 循环读取文件的每一行, 对需要修改的行进行修改, 放入缓冲对象中
            while ((line = br.readLine()) != null) {
                buf.append(line);
                buf.append(LINE_SEPARATOR);
            }
        } catch (Exception e) {
            System.out.println("fail to read file " + file.getName());
        }
        return buf.toString();
    }

    public static void write(File file, String content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            // 将内容写入文件中
            bw.write(content);
            System.out.println("Successfully wrote the front matter for file named " + file.getName());
        } catch (Exception e) {
            System.out.println("fail to write file " + file.getName());
        }
    }
}
