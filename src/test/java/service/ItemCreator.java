package service;

import model.Item;

public class ItemCreator {
    public static final String TESTDATA_SEARCH_KEY = "testdata.search.key";

    public static Item withoutRequiredFields() {
        return new Item(TestDataReader.getTestData(TESTDATA_SEARCH_KEY));
    }
}
