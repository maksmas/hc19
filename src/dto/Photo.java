package dto;

import java.util.List;

public class Photo {
    public int id;
    public List<String> tags;
    public boolean vertical;

    public Photo(int id, char orientation, List<String> tags) {
        this.id = id;
        this.tags = tags;
        vertical = orientation == 'V';
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", tags=" + tags +
                ", vertical=" + vertical +
                '}';
    }
}
