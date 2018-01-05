package com.chico;

import com.chico.domain.TrieContainer;
import com.chico.index.Indexer;
import com.chico.index.IndexerImpl;
import com.chico.index.TrieIndexer;
import com.chico.search.Searcher;
import com.chico.search.SearcherImpl;
import com.chico.search.TrieSearcher;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.lang.System.out;

public class App {

    private static final String PATH = "./data";

    public static void main(String[] args) {
//        final String[] query = args[0].split(" ");
        final String[] query = {"walt", "disney"};
        if (query.length == 0) {
            throw new IllegalArgumentException("You must to specify a query string");
        }

        checkDataIndex(PATH);

        //Index Files
        Instant startIndex = Instant.now();
        System.out.println("Starting indexing the files");
        Indexer indexer = new IndexerImpl(new TrieIndexer());
        TrieContainer index = indexer.indexFull(PATH);
        Instant finishIndex = Instant.now();
        System.out.println(String.format("Index finished in: %s/ms \n", TimeUnit.NANOSECONDS.toMillis(Duration.between(startIndex, finishIndex).getNano())));


        //Search
        Instant startSearch = Instant.now();
        Searcher searcher = new SearcherImpl(new TrieSearcher());
        Set<String> paths = searcher.searchFiles(query, index);
        Instant finishSearch = Instant.now();
        printResult(paths,  "test");
        System.out.println(String.format("This search took: %s/ms \n", TimeUnit.NANOSECONDS.toMillis(Duration.between(startSearch, finishSearch).getNano())));
    }

    private static void printResult(final Set<String> paths, final String query) {
        out.println(String.format("Foram encontradas %s ocorrências pelo termo \"%s\".", paths.size(), query));
        out.println(String.format("Os arquivos que possuem \"%s\" são: ", query));
        paths.forEach(out::println);

    }

    private static void checkDataIndex(final String path) {
        final Path data = Paths.get(path);
        if (!Files.exists(data)) {
            System.out.println("No data found. Extract the files into the root folder of the project");
            System.exit(1);
        }

    }
}
