package com.chico.search;

import com.chico.domain.TrieContainer;

import java.util.Set;

public interface Searcher {
    Set<String> searchFiles(String[] query, TrieContainer index);
}
