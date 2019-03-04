import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    static void write(String filename, SlideShow ss) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        bw.write(String.valueOf(ss.slides.size()));
        bw.newLine();

        ss.slides.stream().forEachOrdered(slide -> {
            try {
                if (slide.hId != null) {
                    bw.write(String.valueOf(slide.hId));
                } else {
                    bw.write(slide.vId1 + " " + slide.vId2);
                }
                bw.newLine();
            } catch (Throwable t) {
                System.out.println("Shouldnt happen");
            }
        });

        bw.flush();
        bw.close();
    }
}
