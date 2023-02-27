package model;

import java.util.Objects;

public class Item {
    private String searchKey;

    public Item(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    @Override
    public String toString() {
        return "Item{" +
                "searchKey='" + searchKey + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(searchKey, item.searchKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(searchKey);
    }
}
