import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import dto.Photo;
import dto.Slide;

class Slides {
    static List<Slide> from(List<Photo> photos) {
        List<Slide> slides = new LinkedList<>();

        photos.sort(Comparator.comparingInt(p -> p.tags.size()));

        Photo tmpVPhoto = null;

        for (Photo photo : photos) {
            if (photo.vertical) {
                if (tmpVPhoto == null) {
                    tmpVPhoto = photo;
                } else {
                    slides.add(new Slide(tmpVPhoto, photo));
                    tmpVPhoto = null;
                }
            } else {
                slides.add(new Slide(photo));
            }
        }

        return slides;
    }
}
