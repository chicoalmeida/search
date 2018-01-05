package com.chico.search;

import com.chico.domain.TrieContainer;
import com.chico.index.Indexer;
import com.chico.index.IndexerImpl;
import com.chico.index.TrieIndexer;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class TrieSearcherTest {

    private TrieSearcher target;
    private Indexer indexer;

    @Before
    public void setUp() throws Exception {
        target = new  TrieSearcher();
        indexer = new IndexerImpl(new TrieIndexer());
    }


    @Test
    public void search_GivenTheIndexedTrie_ShouldReturnThePathsWhichContainsTheQueriedWord() {
        //given
        final String filePath = getClass().getClassLoader().getResource("simpleIndex").getPath();
        final TrieContainer container = indexer.indexFull(filePath);
        final String query = "a";

        //when
        final Set<String> result = target.search(container, query);

        //then
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    public void search_GivenANotIndexWord_ShouldReturnAEmptyPath() {
        //given
        final String filePath = getClass().getClassLoader().getResource("simpleIndex").getPath();
        final TrieContainer container = indexer.indexFull(filePath);
        final String query ="chico";

        //when
        final Set<String> result = target.search(container, query);

        //then
        assertThat(result).isEmpty();
    }
}