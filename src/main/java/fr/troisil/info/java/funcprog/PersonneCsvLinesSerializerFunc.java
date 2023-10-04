package fr.troisil.info.java.funcprog;

import fr.troisil.info.java.funcprog.helpers.CsvLinesSerializerFunc;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class PersonneCsvLinesSerializerFunc implements CsvLinesSerializerFunc<Personne> {

    @Override
    public List<Personne> apply(Stream<String> lines) {

        Comparator<Personne> ageComparator = Comparator.comparing(Personne::getAge);
        Comparator<Personne> prenomComparator = Comparator.comparing(Personne::getPrenom);
        return lines.map(line -> {
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        String prenom = parts[0].trim();
                        int age = Integer.parseInt(parts[1].trim());
                        return new Personne(prenom, age);
                    } else {
                        return null;
                    }
                }).filter(Objects::nonNull)
//                .sorted((Personne p1, Personne p2)-> p1.getAge()> p2.getAge()? 1 : p1.getAge() == p2.getAge() ? 0 : -1)
                .sorted(ageComparator.thenComparing(ageComparator.reversed()))
                .sorted(prenomComparator)
                .toList();
    }
}
