package Lesson3;

import Lesson3.MyExceptions.MyIllegalArgsNumberException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class PersonToFileWriter {
    private List<String> warnings = new ArrayList<>();
    private final String path;
    private HashSet<Person> persons = new LinkedHashSet<>();
    private final Person person;
    public List<String> getWarnings() {
        return new ArrayList<>(this.warnings);
    }

    public String getPath() {
        return this.path;
    }

    public PersonToFileWriter(Person person) throws IOException {
        this.person = person;
        this.path = person.getLastName() + ".txt";
        if (Files.exists(Path.of(this.path))) {
            try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
                String line;
                int lineNumber = 0;
                while ((line = reader.readLine()) != null) {
                    lineNumber += 1;
                    try {
                        Parser newParser = new Parser(line);
                        if (newParser.isValid()) {
                            if (!this.persons.add(newParser.getPerson())) {
                                warnings.add(String.format("File contains duplicate lines \"%s\", which will be deleted if continue.", newParser.getPerson().toString()));
                            }
                        }
                        else {
                            warnings.add(String.format("Errors reading line %d in %s (line will be passed if continue):", lineNumber, this.path));
                            for (int i = 0; i < newParser.getExceptions().size(); i++) {
                                warnings.add(String.format("\t%d.) %s", i + 1, newParser.getExceptions().get(i).getMessage()));
                            }
                        }
                    }
                    catch (MyIllegalArgsNumberException e) {
                        this.warnings.add(String.format("Error reading line %d in %s (line will be passed if continue): %s", lineNumber, this.path, e.getMessage()));
                    }
                }
            }
        }
        if (!this.persons.add(person)) {
            this.warnings.add(String.format("There is the same person in the file %s", this.path));
        }
    }

    public void write(boolean appendWithoutChecking) throws IOException {
        if (appendWithoutChecking) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.path, true))) {
                writer.append('\n');
                writer.append(this.person.toString());
            }
            catch (IOException e) {
                throw new IOException(e);
            }
        }
        else {
            try (FileWriter writer = new FileWriter(this.path)) {
                StringBuilder sb = new StringBuilder();
                boolean isNotFirst = false;
                for (Person person : this.persons) {
                    if (isNotFirst) sb.append(System.lineSeparator());
                    isNotFirst = true;
                    sb.append(person.toString());
                }
                writer.write(sb.toString());
            } catch (IOException e) {
                throw new IOException(e);
            }
        }
    }
}
