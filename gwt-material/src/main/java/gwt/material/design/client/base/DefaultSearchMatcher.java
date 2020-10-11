package gwt.material.design.client.base;

public class DefaultSearchMatcher implements SearchMatcher {

    @Override
    public boolean match(SearchObject obj, String keyword) {
        return obj.getKeyword().toLowerCase().contains(keyword.toLowerCase());
    }
}
