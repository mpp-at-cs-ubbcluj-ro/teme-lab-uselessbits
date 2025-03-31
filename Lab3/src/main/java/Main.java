import model.Person;
import repository.PersonDBRepository;
import repository.PersonRepository;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties props=new Properties();
        try {
            props.load(new FileReader("bd.config"));
        } catch (IOException e) {
            System.out.println("Cannot find bd.config "+e);
        }

        PersonRepository personRepository=new PersonDBRepository(props);
        personRepository.add(new Person("Popescu","Ion S", 20));
        System.out.println("Toate persoanele din db");
        for(Person person:personRepository.findAll())
            System.out.println(person);
        String nume="Popescu";
        System.out.println("Oameni cu numele "+nume);
        for(Person person:personRepository.findByName(nume))
            System.out.println(person);
    }
}