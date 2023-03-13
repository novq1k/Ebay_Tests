package service;

import model.Item;

public class ItemCreator {
    public static final String SEARCH_KEY = "search.key";
    public static final String SEARCH_KEY_REQUIRED_FIELDS = "search.key.required.fields";

    public static Item withoutRequiredFields() {
        return new Item(TestDataReader.getTestData(SEARCH_KEY));
    }

    public static Item withRequiredFields() {
        return new Item(TestDataReader.getTestData(SEARCH_KEY_REQUIRED_FIELDS));
    }

}
