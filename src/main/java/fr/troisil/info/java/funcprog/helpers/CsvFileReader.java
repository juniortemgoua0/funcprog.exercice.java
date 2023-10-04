package fr.troisil.info.java.funcprog.helpers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

@Slf4j
@AllArgsConstructor
public class CsvFileReader<T> implements Function<CsvLinesSerializerFunc<T>, List<T>> {
    private String path;

    @Override
    public List<T> apply(CsvLinesSerializerFunc<T> function) {
        Path filePath = Paths.get(this.path);
        List<T> result = new ArrayList<>();
        try {
            Stream<String> lines = Files.lines(filePath)
                    .skip(1);

            result = function.apply(lines);

        } catch (IOException e) {
            if (e instanceof NoSuchFileException)
                log.error("Le fichier n'a pas été retrouvé : {} ", e.getMessage(), e);
            else
                log.error("Une erreur s'est produite", e);
        }
        return result;
    }
}
