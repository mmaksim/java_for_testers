package ru.stqa.mantis.common;

import java.io.File;
import java.nio.file.Paths;
import java.util.Random;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonFunctions {


    public static String randomString(int length) {
      var rnd = new Random();
        Supplier<Integer> randomNumbers = () -> rnd.nextInt(26);
        var result = Stream.generate(randomNumbers)
                .limit(length)
                .map(i -> 'a' + i)
                .map(Character::toString)
                .collect(Collectors.joining());
       return result;
    }

    public static String randomFile(String dir){
       var fileNames = new File(dir).list();
       var random = new Random();
       var index = random.nextInt(fileNames.length);
       return Paths.get(dir, fileNames[index]).toString();
    }

    public static String randomNumbers() {
        var rnd = new Random();
        String s = "123456789";
        Supplier<Integer> randomNumbers = () -> rnd.nextInt(9);
        var result = Stream.generate(randomNumbers).limit(12)
                .map(i -> {
                    var number = "";
                    number += s.charAt(i);
                    return number;
                }).collect(Collectors.joining());
        return result;
    }

    public static String extractUrl(String message) {
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(message);
        if (matcher.find()) {
            var url = message.substring(matcher.start(), matcher.end());
            return url;
        } else return null;
    }
}
