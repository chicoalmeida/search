package com.chico.index;

import com.chico.domain.TrieContainer;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;

public class IndexerTest {

    private Indexer target;

    @Before
    public void setUp() {
        target = new IndexerImpl(new TrieIndexer());
    }

    @Test
    public void indexFull_GivenValidPath_ShouldCreateValidTrie() throws URISyntaxException {
        //given
        final String filePath = getClass().getClassLoader().getResource("simpleIndex").getPath();

        //when
        final TrieContainer result = target.indexFull(filePath);

        //then
        assertThat(result)
                .isNotNull();
        assertThat(result.getSeries().length)
                .isEqualTo(35);
        assertThat(result.getSeriesIndex(0).getPaths())
                .contains("data/teste.txt", "data/teste2.txt");
        assertThat(result.getSeriesIndex(0).getValue())
                .isEqualTo("a");
        assertThat(result.getSeriesIndex(0).isEnd())
                .isTrue();
        assertThat(result.getSeriesIndex(1).getPaths())
                .contains("data/teste2.txt");
        assertThat(result.getSeriesIndex(1).getValue())
                .isEqualTo("b");
        assertThat(result.getSeriesIndex(1).isEnd())
                .isTrue();
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void indexFull_GivenInvalidPath_BreakFlow(){
        //expect3
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("You must to provide a valid path");

        //when
        final TrieContainer result = target.indexFull("");


    }
}