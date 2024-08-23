package generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import common.CommonFunctions;
import model.ContactData;
import model.GroupData;

import java.io.ObjectStreamException;
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
//--type contacts --output contacts.json --format json --count 10
    public static void main(String[] args) {
        var generator = new Generator();
        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);
        generator.run();
    }

    private void run() {
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
                    .withPhoto(CommonFunctions.randomFile("address_web_tests/src/test/resources/images")));
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


    private void save(Object date) {
    }
}
