package com.hangbo.javabase.stream;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.*;

/**
 * @author quanhangbo
 * @date 2023/8/14 11:22
 */
@Data
@Accessors
public class Artist {

    private String name;
    private List<String> members;
    private String origin;
    private Track track;
    private Album album;

    public Artist(String name, List<String> members, String origin, Track track, Album album) {
        this.name = name;
        this.members = members;
        this.origin = origin;
        this.track = track;
        this.album = album;
    }


    @Data
    @Accessors
    static public class Track {
        private String name;

        public Track(String name) {
            this.name = name;
        }
    }

    @Data
    @Accessors
    static public class Album {
        private String name;
        private List<Track> tracks;
        private List<String> musicians;

        public Album(String name, List<Track> tracks, List<String> musicians) {
            this.name = name;
            this.tracks = tracks;
            this.musicians = musicians;
        }
    }

    public static void main(String[] args) {
        int count = 0;
        List<Artist> artists = new ArrayList<>();
        Artist superStar = new Artist("甲壳虫乐队", Arrays.asList("约翰列侬"),
                "London", new Track("helo"), new Album("oakbay",
                Arrays.asList(new Track("helo")), Arrays.asList("Tom")));

        artists.add(superStar);

        // 使用for循环来统计 来自伦敦艺术家的人数
        /**
         * 缺点：每次迭代集合类时
         */
        for (Artist artist : artists) {
            if (artist.getOrigin().equals("London")) {
                count ++;
            }
        }
        System.out.println(count);
    }

}
