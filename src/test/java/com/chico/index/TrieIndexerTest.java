package com.chico.index;

import com.chico.domain.TrieContainer;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class TrieIndexerTest {

    private TrieIndexer target;

    @Before
    public void setUp() {
        target = new TrieIndexer();
    }

    @Test
    public void store_StoreSingleLetter() {
        //given
        TrieContainer root = TrieContainer.create();
        //when
        target.store(root, "a", "teste");

        //then
        assertThat(root.getSeriesIndex(0).isEnd()).isTrue();
        assertThat(root.getSeriesIndex(0).getValue()).isEqualToIgnoringCase("a");
        assertThat(root.getSeriesIndex(0).getPaths()).contains("teste");
    }

    @Test
    public void store_GivenZEROStoreAtNextIndex() {
        //given
        TrieContainer root = TrieContainer.create();
        //when
        target.store(root, "0", "teste");

        //then
        assertThat(root.getSeriesIndex(25).isEnd()).isTrue();
        assertThat(root.getSeriesIndex(25).getValue()).isEqualToIgnoringCase("0");
        assertThat(root.getSeriesIndex(25).getPaths()).contains("teste");
    }

    @Test
    public void store_GivenNineNumberStoreAtLastIndex() {
        //given
        TrieContainer root = TrieContainer.create();
        //when
        target.store(root, "9", "teste");

        //then
        assertThat(root.getSeriesIndex(root.getSeries().length - 1).isEnd()).isTrue();
        assertThat(root.getSeriesIndex(root.getSeries().length - 1).getValue()).isEqualToIgnoringCase("9");
        assertThat(root.getSeriesIndex(root.getSeries().length - 1).getPaths()).contains("teste");
    }


    @Test
    public void store_GivenSameWordFromDifferentPaths_ShouldAddTheNewPathToTheList() {
        //given
        final TrieContainer root = TrieContainer.create();
        final List<String> paths = asList("PathA", "PathB");

        //when
        paths.forEach(path -> target.store(root, "a", path));

        //then
        assertThat(root.getSeriesIndex(0).getPaths().size())
                .isEqualTo(2);
        assertThat(root.getSeriesIndex(0).getPaths())
                .contains("PathA", "PathB");
    }
}