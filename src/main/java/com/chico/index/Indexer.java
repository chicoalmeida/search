package com.chico.index;

import com.chico.domain.TrieContainer;

public interface Indexer {
    TrieContainer indexFull(String filesPath);
}
