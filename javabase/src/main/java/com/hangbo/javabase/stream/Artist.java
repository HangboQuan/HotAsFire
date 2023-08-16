package com.hangbo.javabase.stream;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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
        private int length;

        public Track(String name, int length) {
            this.name = name;
            this.length = length;
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
                "London", new Track("helo", 30), new Album("oakbay",
                Arrays.asList(new Track("helo", 30)), Arrays.asList("Tom")));

        artists.add(superStar);

        // 使用for循环来统计 来自伦敦艺术家的人数
        /**
         * 缺点：每次迭代集合类时 都需要写很多样板代码 将for循环改造成并行方式运行也很麻烦
         */
        for (Artist artist : artists) {
            if (artist.getOrigin().equals("London")) {
                count ++;
            }
        }
        System.out.println(count);

        // for循环是一个封装了迭代的语法糖
        int count1 = 0;
        Iterator<Artist> iterator = artists.iterator();
        while (iterator.hasNext()) {
            Artist artist = iterator.next();
            if (artist.getOrigin().equals("London")) {
                count1 ++;
            }
        }
        System.out.println(count1);

        // 内部迭代计算（找出所有来自伦敦的艺术家 then 计算他们的人数）并没有改变集合的内容，而是描述stream的内容
        // count()方法计算给定stream里包含多少对象
        long count2 = artists.stream().filter(artist -> artist.getOrigin().equals("London")).count();
        System.out.println(count2);


        /**
         * filter 只刻画出了Stream 但没有产生新的集合 最终不产生新集合的方法叫做惰性求值方法
         * count 这样最终会从Stream产生值的方法叫做及早求值方法
         */
        artists.stream().filter(artist -> artist.getOrigin().equals("London"));
        // 由于使用了惰性求值 没有输出艺术家的名字
        artists.stream().filter(artist -> {
            System.out.println(artist.getName());
            return artist.getOrigin().equals("London");
        });

        // 输出艺术家的名字 filter函数接收一个函数作为参数 用lambda表示
        long count3 = artists.stream().filter(artist -> {
            System.out.println(artist.getName());
            return artist.getOrigin().equals("London");
        }).count();

        // 如果返回值是Stream是惰性求值 如果返回值是另一个值或者为空那么就是及早求值
        // 使用collect(toList())从Stream生成一个列表
        List<String> collected = Stream.of("a", "b", "c").collect(Collectors.toList());


        // 使用for循环将字符串转换为大写
        List<String> collected1 = new ArrayList<>();
        for (String string : Arrays.asList("a", "b", "hello")) {
            String uppercaseString = string.toUpperCase();
            collected1.add(uppercaseString);
        }
        System.out.println(collected1);

        // 将一种类型的值转换成另外一种类型 map => 一个流中的值->一个新的流
        List<String> collected2 = Stream.of("a", "b", "hello")
                .map(string -> string.toUpperCase())
                .collect(Collectors.toList());
        System.out.println(collected2);


        List<Integer> together = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4))
                .flatMap(numbers -> numbers.stream()).collect(Collectors.toList());

        /**
         * map 返回值为object 将流中的当前元素替换为此返回值
         * flatMap 返回一个stream, 将流中的当前元素替换为此返回流拆解的流元素, 核心是平铺
         *
         * 举例：两箱鸡蛋，每箱5个，现在要把鸡蛋加工为煎蛋，然后分给学生
         * map: 将2箱鸡蛋分别加工成煎蛋，还是放成原来的两箱，分给2组学生
         * flatMap: 将2箱鸡蛋分别加工成煎蛋，然后放到一起【10哥煎蛋】，分给10个学生
         */

        List<String[]> eggs = new ArrayList<>();
        eggs.add(new String[]{"鸡蛋_1", "鸡蛋_1", "鸡蛋_1", "鸡蛋_1", "鸡蛋_1"});
        eggs.add(new String[]{"鸡蛋_2", "鸡蛋_2", "鸡蛋_2", "鸡蛋_2", "鸡蛋_2"});
        AtomicInteger group = new AtomicInteger(1);
        AtomicInteger student = new AtomicInteger(1);

        eggs.stream().map(x -> Arrays.stream(x).map(y -> y.replace("鸡", "煎")))
                .forEach(x -> System.out.println("组" + (group.getAndIncrement()) + ":" + Arrays.toString(x.toArray())));


        eggs.stream()
                .flatMap(x -> Arrays.stream(x).map(y -> y.replace("鸡", "煎")))
                .forEach(x -> System.out.println("学生" + student.getAndIncrement() + ":" + x));

        long count4 = Stream.of(1, 2, 3)
                .reduce(0, (acc, element) -> acc + element);
        // reduce的展开操作
        BinaryOperator<Integer> accmulator = (acc, element) -> acc + element;
        int count5 = accmulator.apply(
                accmulator.apply(
                        accmulator.apply(0, 1),
                2),
        3);
        System.out.println(count4);
        System.out.println(count5);



    }

    // 找出长度>=1min的曲目
    public Set<String> findLongTracks(List<Album> albums) {
        Set<String> trackNames = new HashSet<>();
        for (Album album : albums) {
            for (Track track : album.getTracks()) {
                if (track.getLength() > 60) {
                    String name = track.getName();
                    trackNames.add(name);
                }
            }
        }
        return trackNames;
    }

    // 1. 使用Stream的forEach方法替换掉for循环
    public Set<String> findLongTracks1(List<Album> albums) {
        Set<String> trackNames = new HashSet<>();
        albums.stream()
                .forEach(album -> {
                    album.getTracks()
                            .forEach(track -> {
                                if (track.getLength() > 60) {
                                    String name = track.getName();
                                    trackNames.add(name);
                                }
                            });
                });
        return trackNames;
    }

    // 2. 找出长度大于1min的曲目 得到符合条件的曲目名称 将曲目名称加入集合Set
    public Set<String> findLongTrack2(List<Album> albums) {
        Set<String> trackNames = new HashSet<>();
        albums.stream()
                .forEach(album -> {
                    album.getTracks().stream().filter(track -> track.getLength() > 60)
                            .map(track -> track.getName())
                            .forEach(name -> trackNames.add(name));
                });
        return trackNames;
    }

    public Set<String> findLongTrack3(List<Album> albums) {
        Set<String> trackNames = new HashSet<>();
        albums.stream()
                .flatMap(album -> album.getTracks())
                .filter(track -> track.getLength() > 60);
    }

}
