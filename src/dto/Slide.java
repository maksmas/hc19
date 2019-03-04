package dto;

import java.util.HashSet;
import java.util.Set;

public class Slide {
    public Integer hId;
    public Integer vId1;
    public Integer vId2;

    public Set<String> tags;
    public int tagCount;

    public Slide(Photo photo) {
        this.hId = photo.id;
        this.tags = new HashSet<>(photo.tags);
        tagCount = this.tags.size();
    }

    public Slide(Photo photo1, Photo photo2) {
        vId1 = photo1.id;
        vId2 = photo2.id;

        this.tags = new HashSet<>(photo1.tags);
        this.tags.addAll(photo2.tags);
        this.tagCount = this.tags.size();
    }


    public String id() {
        return hId == null ? vId1 + " " + vId2 : hId + "";
    }

    @Override
    public String toString() {
        return "Slide{" +
                "hId=" + hId +
                ", vId1=" + vId1 +
                ", vId2=" + vId2 +
                ", tags=" + tags +
                '}';
    }
}
