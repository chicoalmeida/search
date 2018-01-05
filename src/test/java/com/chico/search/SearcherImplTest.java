package com.chico.search;

import com.chico.domain.TrieContainer;
import com.chico.index.Indexer;
import com.chico.index.IndexerImpl;
import com.chico.index.TrieIndexer;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class SearcherImplTest {

    private Searcher target;
    private Indexer indexer;

    @Before
    public void setUp() throws Exception {
        target = new SearcherImpl(new TrieSearcher());
        indexer = new IndexerImpl(new TrieIndexer());
    }


    @Test
    public void searchFiles_GivenAWordThatAppearsIntoTwoFiles_ShouldReturnItsPaths() {

        //given
        final String filePath = getClass().getClassLoader().getResource("simpleIndex").getPath();
        final TrieContainer container = indexer.indexFull(filePath);
        final String[] query = {"a", "b"};

        //when
        final Set<String> result = target.searchFiles(query, container);

        //then
        assertThat(result.size())
                .as("As only the teste2.txt has both words only its path will be available")
                .isEqualTo(1);
    }


    @Test
    public void searchFiles_ReturnEmptySet_WhenTheWordsDoNotAppearOnTheSameFile() {

        //given
        final String filePath = getClass().getClassLoader().getResource("simpleIndex").getPath();
        final TrieContainer container = indexer.indexFull(filePath);
        final String[] query = {"a", "b", "c"};

        //when
        final Set<String> result = target.searchFiles(query, container);

        //then
        assertThat(result.size())
                .as("As none of the queried words appears in a single file the result will be empty")
                .isEqualTo(0);
    }
}