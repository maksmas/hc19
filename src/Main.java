import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import dto.Photo;
import dto.Slide;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        List<Photo> photos = new Reader().read(FileNames.e);
        List<Slide> slidePool = Slides.from(photos).stream()
                .sorted((slide, t1) -> t1.tagCount - slide.tagCount)
                .collect(Collectors.toList());

        SlideShow slideShow = new SlideShow(slidePool.get(0));
        slidePool.remove(0);

        while (!slidePool.isEmpty()) {
            System.out.println(slidePool.size());
            int maxRScore = -1, maxRSId = -1, maxLScore = -1, maxLSId = -1;

            for (int i = 0; i < slidePool.size(); ++i) {
                Slide s = slidePool.get(i);

                if (
                        maxRScore != -1 && maxLScore != -1 &&
                                Math.abs(slideShow.left.tagCount - s.tagCount) > 3 &&
                                Math.abs(slideShow.right.tagCount - s.tagCount) > 3) {
                    break;
                }

                int lScore = slideShow.leftScore(s);
                int rScore = slideShow.rightScore(s);


                if (lScore > maxLScore) {
                    maxLScore = lScore;
                    maxLSId = i;
                } else if (rScore > maxRScore) {
                    maxRScore = rScore;
                    maxRSId = i;
                }
            }

            if (maxLScore != -1) {
                Slide leftSlide = slidePool.get(maxLSId);
                slidePool.remove(maxLSId);
                slideShow.addLeft(leftSlide);
            }

            if (maxLSId != maxRSId && maxRScore != -1) {
                if (maxRSId > maxLSId) {
                    maxRSId--;
                }

                Slide rightSlide = slidePool.get(maxRSId);
                slidePool.remove(maxRSId);
                slideShow.addRight(rightSlide);
            }
        }

        Writer.write("e.out", slideShow);
    }
}
