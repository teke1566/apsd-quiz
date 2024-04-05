package edu.miu.cs489;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("teke", "beyene", "cap LLC", "java developer",
                List.of(new PhoneNumber("6412339162", "Home")),
                List.of(new EmailAddress("teke@gmail.com", "Home"),
                        new EmailAddress("@cap.com", "Work"))));
        contacts.add(new Contact("abebe", "dereje", "insa", "Director", Collections.emptyList(), Collections.emptyList()));
        contacts.add(new Contact("ayele", "belay", "insa", "HR Manager",
                List.of(new PhoneNumber("6412339595", "Work")),
                List.of(new EmailAddress("belay@insa.com", "Work"))));

        Collections.sort(contacts, (c1, c2) -> c1.getLastName().compareToIgnoreCase(c2.getLastName()));

        for (Contact contact : contacts) {
            System.out.println(contactToJson(contact));
        }
    }

    private static String contactToJson(Contact contact) {
        StringBuilder json = new StringBuilder();
        json.append("{\n");
        json.append("\t\"firstName\": \"").append(contact.getFirstName()).append("\",\n");
        json.append("\t\"lastName\": \"").append(contact.getLastName()).append("\",\n");
        json.append("\t\"company\": \"").append(contact.getCompany()).append("\",\n");
        json.append("\t\"jobTitle\": \"").append(contact.getJobTitle()).append("\",\n");

        json.append("\t\"phoneNumbers\": [\n");
        List<PhoneNumber> phoneNumbers = contact.getPhoneNumbers();
        for (int i = 0; i < phoneNumbers.size(); i++) {
            PhoneNumber phoneNumber = phoneNumbers.get(i);
            json.append("\t\t{\n");
            json.append("\t\t\t\"number\": \"").append(phoneNumber.getNumber()).append("\",\n");
            json.append("\t\t\t\"label\": \"").append(phoneNumber.getLabel()).append("\"\n");
            json.append("\t\t}");
            if (i < phoneNumbers.size() - 1) {
                json.append(",");
            }
            json.append("\n");
        }
        json.append("\t],\n");

        json.append("\t\"emailAddresses\": [\n");
        List<EmailAddress> emailAddresses = contact.getEmailAddresses();
        for (int i = 0; i < emailAddresses.size(); i++) {
            EmailAddress emailAddress = emailAddresses.get(i);
            json.append("\t\t{\n");
            json.append("\t\t\t\"address\": \"").append(emailAddress.getAddress()).append("\",\n");
            json.append("\t\t\t\"label\": \"").append(emailAddress.getLabel()).append("\"\n");
            json.append("\t\t}");
            if (i < emailAddresses.size() - 1) {
                json.append(",");
            }
            json.append("\n");
        }
        json.append("\t]\n");

        json.append("}");
        return json.toString();
    }
}
