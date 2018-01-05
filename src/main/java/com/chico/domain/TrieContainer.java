package com.chico.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class TrieContainer {

    private String value;
    private Set<String> paths;
    private boolean isEnd;
    private TrieContainer[] series;

    private TrieContainer() {
        this.paths = new HashSet<>();
        this.series = new TrieContainer[35];
    }

    public static TrieContainer create() {
        return new TrieContainer();
    }

    public Set<String> getPaths() {
        return paths;
    }

    public void addPath(String path) {
        this.paths.add(path);
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public TrieContainer[] getSeries() {
        return series;
    }

    public TrieContainer getSeriesIndex(int index) {
        return series[index];
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrieContainer container = (TrieContainer) o;
        return isEnd == container.isEnd &&
                Objects.equals(value, container.value) &&
                Objects.equals(paths, container.paths) &&
                Arrays.equals(series, container.series);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(value, paths, isEnd);
        result = 31 * result + Arrays.hashCode(series);
        return result;
    }


}
