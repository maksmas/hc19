import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import dto.Photo;

public class Reader {
    List<Photo> read(String filename) throws URISyntaxException, IOException {
        List<String> lines = Files.readAllLines(Paths.get(getClass().getResource(filename).toURI()));

        int photoCount = Integer.parseInt(lines.get(0));

        List<Photo> photos = new ArrayList<>(photoCount);

        for (int i = 1; i <= photoCount; ++i) {
            photos.add(this.map(i - 1, lines.get(i)));
        }

        return photos;
    }

    private Photo map(int id, String line) {
        String[] parts = line.split(" ");

        return new Photo(id, parts[0].charAt(0), Arrays.stream(parts).skip(2).collect(Collectors.toList()));
    }
}
