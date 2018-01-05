package com.chico.index;

import com.chico.domain.TrieContainer;
import com.chico.exception.FileProcessingException;
import com.chico.exception.LineProcessingException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class IndexerImpl implements Indexer {

    private final TrieIndexer trieIndexer;

    public IndexerImpl(TrieIndexer trieIndexer) {
        this.trieIndexer = trieIndexer;
    }

    @Override
    public TrieContainer indexFull(final String filesPath) {

        if (filesPath == null || filesPath.isEmpty())
            throw new IllegalArgumentException("You must to provide a valid path");

        TrieContainer root = TrieContainer.create();

        try (final Stream<Path> paths = Files.walk(Paths.get(filesPath))) {
            paths
                    .filter(path -> !Files.isDirectory(path))
                    .forEach(path -> {
                        try {
                            Files.readAllLines(path).forEach(l -> Stream.of(l.split(" ")).forEach(word -> {
                                final String sanitized = word.toLowerCase().replaceAll("\\W", "").replaceAll("_", " ");
                                final String pathData = String.format("data/%s", path.getFileName().toString());
                                this.trieIndexer.store(root, sanitized, pathData);
                            }));
                        } catch (Exception e) {
                            throw new LineProcessingException(String.format("An error has occurred while attempting to process the file line: [%s]", e.getMessage()));

                        }

                    });

        } catch (IOException e) {
            throw new FileProcessingException(String.format("An error has occurred while attempting to process the files: [%s]", e.getMessage()));
        }

        return root;

    }
}
