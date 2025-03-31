package repository;

import model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PersonDBRepository implements PersonRepository {
    private JdbcUtils dbUtils;
    private static final Logger logger = LogManager.getLogger(PersonDBRepository.class);

    public PersonDBRepository(Properties props) {
        logger.info("Initializing PersonDBRepository with properties: {}", props);
        dbUtils = new JdbcUtils(props);
    }

    @Override
    public void add(Person elem) {
        logger.traceEntry("saving person {}", elem);
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("insert into persoana (nume, prenume, varsta) values (?, ?, ?)")) {
            preStmt.setString(1, elem.getNume());
            preStmt.setString(2, elem.getPrenume());
            preStmt.setInt(3, elem.getVarsta());
            int result = preStmt.executeUpdate();
            logger.trace("Saved {} instances", result);
        } catch (SQLException ex) {
            logger.error(ex);
            System.out.println("Error DB " + ex);
        }
        logger.traceExit();
    }

    @Override
    public void update(Integer id, Person elem) {
        logger.trace("updating person with id {} with {}", id, elem);
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("update persoana set nume=?, prenume=?, varsta=? where id=?")) {
            preStmt.setString(1, elem.getNume());
            preStmt.setString(2, elem.getPrenume());
            preStmt.setInt(3, elem.getVarsta());
            preStmt.setInt(4, id);
            int result = preStmt.executeUpdate();
            logger.trace("Updated {} instances", result);
        } catch (SQLException ex) {
            logger.error(ex);
            System.out.println("Error DB " + ex);
        }
    }

    @Override
    public Iterable<Person> findAll() {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<Person> persons = new ArrayList<>();
        try (PreparedStatement preStmt = con.prepareStatement("select * from persoana");
             ResultSet result = preStmt.executeQuery()) {
            while (result.next()) {
                int id = result.getInt("id");
                String nume = result.getString("nume");
                String prenume = result.getString("prenume");
                int varsta = result.getInt("varsta");
                Person person = new Person(nume, prenume, varsta);
                person.setId(id);
                persons.add(person);
            }
        } catch (SQLException ex) {
            logger.error(ex);
            System.out.println("Error DB " + ex);
        }
        logger.traceExit(persons);
        return persons;
    }

    @Override
    public List<Person> findByName(String name) {
        logger.traceEntry("finding persons with name {}", name);
        Connection con = dbUtils.getConnection();
        List<Person> persons = new ArrayList<>();
        try (PreparedStatement preStmt = con.prepareStatement("select * from persoana where nume=?")) {
            preStmt.setString(1, name);
            try (ResultSet result = preStmt.executeQuery()) {
                while (result.next()){
                    int id = result.getInt("id");
                    String nume = result.getString("nume");
                    String prenume = result.getString("prenume");
                    int varsta = result.getInt("varsta");
                    Person person = new Person(nume, prenume, varsta);
                    person.setId(id);
                    persons.add(person);
                }
            }
        } catch (SQLException ex) {
            logger.error(ex);
            System.out.println("Error DB " + ex);
        }
        logger.traceExit(persons);
        return persons;
    }

    @Override
    public List<Person> findBetweenYears(int min, int max){
        logger.traceEntry("finding persons with age between {} and {}", min, max);
        Connection con = dbUtils.getConnection();
        List<Person> persons = new ArrayList<>();
        try (PreparedStatement preStmt = con.prepareStatement("select * from persoana where varsta between ? and ?")) {
            preStmt.setInt(1, min);
            preStmt.setInt(2, max);
            try (ResultSet result = preStmt.executeQuery()) {
                while (result.next()){
                    int id = result.getInt("id");
                    String nume = result.getString("nume");
                    String prenume = result.getString("prenume");
                    int varsta = result.getInt("varsta");
                    Person person = new Person(nume, prenume, varsta);
                    person.setId(id);
                    persons.add(person);
                }
            }
        } catch (SQLException ex) {
            logger.error(ex);
            System.out.println("Error DB " + ex);
        }
        logger.traceExit(persons);
        return persons;
    }
}
