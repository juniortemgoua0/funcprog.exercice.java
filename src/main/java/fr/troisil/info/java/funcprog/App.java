package fr.troisil.info.java.funcprog;

import fr.troisil.info.java.funcprog.helpers.CsvFileReader;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class App {
    public static void main(String[] args) {
        PersonneCsvLinesSerializerFunc personneCsvLinesSerializer = new PersonneCsvLinesSerializerFunc();
        CsvFileReader<Personne> csvFileReader = new CsvFileReader<>("src/main/resources/personnes.csv");

        List<Personne> personnes = csvFileReader.apply(personneCsvLinesSerializer);
        log.info("{}",personnes);
    }
}
