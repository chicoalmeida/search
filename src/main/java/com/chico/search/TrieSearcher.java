package com.chico.search;

import com.chico.domain.TrieContainer;

import java.util.HashSet;
import java.util.Set;

public class TrieSearcher {

    public Set<String> search(TrieContainer container, String word) {
        final Set<String> paths = new HashSet<>();

        for (int i = 0; i < word.length(); i++) {

            int index = word.charAt(i) - 97 >= 0 ? word.charAt(i) - 97 : (word.charAt(i) - 97) + 74;

            if (container.getSeriesIndex(index) != null) {
                if (word.length() - 1 != i) {
                    container = container.getSeriesIndex(index);
                } else {
                    if (container.getSeriesIndex(index).isEnd())
                        paths.addAll(container.getSeriesIndex(index).getPaths());
                }
            } else {
                paths.clear();
                break;
            }
        }
        return paths;
    }
}
