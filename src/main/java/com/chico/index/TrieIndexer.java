package com.chico.index;

import com.chico.domain.TrieContainer;

public class TrieIndexer {

    public void store(TrieContainer container, String word, String path) {
        for (int i = 0; i < word.length(); i++) {

            char character = word.charAt(i);

            int index = character - 97 >= 0 ? character - 97 : (character - 97) + 74;

            if (container.getSeriesIndex(index) != null) {
                container = container.getSeriesIndex(index);
                if (word.length() - 1 == i) {
                    container.setEnd(true);
                    container.addPath(path);
                }
            } else {
                final TrieContainer trie = TrieContainer.create();
                trie.setEnd(word.length() - 1 == i);
                if (trie.isEnd()) {
                    trie.addPath(path);
                }
                trie.setValue(String.valueOf(character));
                container.getSeries()[index] = trie;
                container = container.getSeriesIndex(index);
            }
        }
    }
}
