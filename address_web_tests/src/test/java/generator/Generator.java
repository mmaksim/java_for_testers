package generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import common.CommonFunctions;
import model.ContactData;
import model.GroupData;

import java.io.*;
import java.lang.runtime.ObjectMethods;
import java.util.ArrayList;
import java.util.List;

public class Generator {

    @Parameter(names = {"--type", "-t"})
    String type;
    @Parameter(names = {"--output", "-o"})
    String output;
    @Parameter(names = {"--format", "-f"})
    String format;
    @Parameter(names = {"--count", "-c"})
    int count;

    //--type groups --output groups.json --format json --count 3
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
        if ("groups".equals(type)) {
            return generateGroups();
        } else if ("contacts".equals(type)) {
            return generateContacts();
        } else {
            throw new IllegalArgumentException("Неизвестный тип данных " + type);
        }
    }

    private Object generateContacts() {
        var result = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            result.add(new ContactData()
                    .withLastName(CommonFunctions.randomString(i * 5))
                    .withFirstName(CommonFunctions.randomString(i * 5))
                    .withAddress(CommonFunctions.randomString(i * 5))
                    .withPhones(CommonFunctions.randomNumbers(),
                            CommonFunctions.randomNumbers(),
                            CommonFunctions.randomNumbers(),
                            CommonFunctions.randomNumbers())
                    .withEmails(CommonFunctions.randomString(i * 5) + "@mail.com",
                            CommonFunctions.randomString(i * 5) + "1@mail.com",
                            CommonFunctions.randomString(i * 5) + "1@mail.com")
                    .withPhoto(CommonFunctions.randomFile("src/test/resources/images")));
        }
        return result;
    }


    private Object generateGroups() {
        var result = new ArrayList<GroupData>();
        for (int i = 0; i < count; i++) {
            result.add(new GroupData()
                    .withName(CommonFunctions.randomString(i * 10))
                    .withHeader(CommonFunctions.randomString(i * 10))
                    .withFooter(CommonFunctions.randomString(i * 10)));
        }
        return result;
    }

    private void save(Object date) throws IOException {
        if ("json".equals(format)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            var json = mapper.writeValueAsString(date);

            try (var writer = new FileWriter(output)) {
                writer.write(json);
            }
        } else if ("yaml".equals(format)) {
            var mapper = new YAMLMapper();
            mapper.writeValue(new File(output), date);
        } else if ("xml".equals(format)) {
            var mapper = new XmlMapper();
            mapper.writeValue(new File(output), date);
        } else {
            throw new IllegalArgumentException("Неизвестный формат данных " + format);
        }

    }
}
