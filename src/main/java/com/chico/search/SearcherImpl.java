package com.chico.search;

import com.chico.domain.TrieContainer;

import java.util.Set;
import java.util.TreeSet;

public class SearcherImpl implements Searcher {

    private final TrieSearcher trieSearcher;

    public SearcherImpl(TrieSearcher trieSearcher) {
        this.trieSearcher = trieSearcher;
    }

    @Override
    public Set<String> searchFiles(String[] query, TrieContainer index) {
        Set<String> matches = new TreeSet<>();
        for (String aQuery : query) {
            final Set<String> search = trieSearcher.search(index, aQuery);

            if (search == null) {
                matches.clear();
                break;
            }

            if (matches.isEmpty())
                matches.addAll(search);
            else
                matches.retainAll(search);

        }
        return matches;
    }
}
