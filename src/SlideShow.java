import java.util.LinkedList;
import java.util.stream.Collectors;

import dto.Slide;

public class SlideShow {
    public LinkedList<Slide> slides = new LinkedList<>();
    public Slide left;
    public Slide right;

    public SlideShow(Slide first) {
        slides.add(first);
        left = first;
        right = first;
    }

    public void print() {
        System.out.println(
                slides.stream().map(s -> s.id()).collect(Collectors.joining(" , "))
        );
    }

    public void addLeft(Slide slide) {
        slides.add(0, slide);
        left = slide;
    }

    public void addRight(Slide slide) {
        slides.add(slide);
        right = slide;
    }

    public int leftScore(Slide slide) {
        return score(left, slide);
    }

    public int rightScore(Slide slide) {
        return score(right, slide);
    }

    private int score(Slide slide, Slide slide1) {
        int lScore = 0;
        int mScore = 0;
        int rScore = 0;

        for (String tag : slide.tags) {
            if (slide1.tags.contains(tag)) {
                mScore++;
            } else {
                ++lScore;
            }
        }

        rScore = slide1.tags.size() - mScore;

        return Math.min(lScore, Math.min(mScore, rScore));
    }

    @Override
    public String toString() {
        return "SlideShow{" +
                "slides=" + slides +
                '}';
    }
}
