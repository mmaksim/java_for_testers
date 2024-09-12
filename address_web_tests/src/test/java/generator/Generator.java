package generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import common.CommonFunctions;
import model.ContactData;
import model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Generator {

    @Parameter(names = {"--type", "-t"})
    List<String> type= new ArrayList<>();;
    @Parameter(names = {"--output", "-o"})
    List<String> output= new ArrayList<>();;
    @Parameter(names = {"--format", "-f"})
    List<String> format= new ArrayList<>();;
    @Parameter(names = {"--count", "-c"})
    int count;

    //--type groups --output groups.xml --format xml --count 10
    //--type contacts --output contacts.json --format json --count 5
    public static void main(String[] args) throws IOException {
        var generator = new Generator();
        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);
        generator.run();
    }

    private void run() throws IOException {
        var date = generate();
        save(date);
    }

            private Object generate() {
        if (type.contains("groups")) {
            return generateGroups();
        } else if (type.contains("contacts")) {
            return generateContacts();
        } else {
            throw new IllegalArgumentException("Неизвестный тип данных " + type);
        }
    }

    private Object generateDate(Supplier<Object> dateSupplier){
       return Stream.generate(dateSupplier).limit(count).collect(Collectors.toList());
    }

    private Object generateContacts() {
        return generateDate(() -> new ContactData()
                .withLastName(CommonFunctions.randomString( 5))
                .withFirstName(CommonFunctions.randomString(5))
                .withAddress(CommonFunctions.randomString(5))
                .withPhones(CommonFunctions.randomNumbers(),
                        CommonFunctions.randomNumbers(),
                        CommonFunctions.randomNumbers(),
                        CommonFunctions.randomNumbers())
                .withEmails(CommonFunctions.randomString(5) + "@mail.com",
                        CommonFunctions.randomString(5) + "1@mail.com",
                        CommonFunctions.randomString(5) + "1@mail.com")
                .withPhoto(CommonFunctions.randomFile("src/test/resources/images")));
    }

    private Object generateGroups() {
        return generateDate(() -> new GroupData()
                .withName(CommonFunctions.randomString(10))
                .withHeader(CommonFunctions.randomString(10))
                .withFooter(CommonFunctions.randomString(10)));
    }

    private void save(Object date) throws IOException {
        if (format.contains("json")) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            var json = mapper.writeValueAsString(date);

            for (String out: output) {
                try (var writer = new FileWriter(out)) {
                    writer.write(json);
                }
            }
        } else if (format.contains("yaml")) {
            var mapper = new YAMLMapper();
            for (String out: output) {
                mapper.writeValue(new File(out), date);
            }
        } else if (format.contains("xml")) {
            var mapper = new XmlMapper();
            for (String out: output) {
                mapper.writeValue(new File(out), date);
            }
        } else {
            throw new IllegalArgumentException("Неизвестный формат данных " + format);
        }

    }
}
