package be.fooda.backend.product.model.http;

public class HttpEndpoints {

    // PAGING DEFAULTS
    public static final String PAGE_NUMBER_TEXT = "pageNo";
    public static final String PAGE_SIZE_TEXT = "pageSize";
    public static final String PAGE_NUMBER_DEFAULT_VALUE = "1";
    public static final String PAGE_SIZE_DEFAULT_VALUE = "50";

    // PRODUCT_ENDPOINTS
    public static final String PRODUCTS_FIND_ALL_TEXT = "find/all/";
    public static final String PRODUCTS_SEARCH_TEXT = "search/{keyword}";
    public static final String PRODUCTS_POST_SINGLE_TEXT = "create/one/";
    public static final String PRODUCTS_FIND_BY_ID_TEXT = "find/one/{productId}";
    public static final String PRODUCTS_FIND_EXISTS_BY_ID_TEXT = "exists/one/{productId}";
    public static final String PRODUCT_EXISTS_BY_UNIQUE_FIELDS = "exists/one/unique/";
    public static final String PRODUCTS_PUT_SINGLE_TEXT = "update/one/{productId}";
    public static final String PRODUCTS_DELETE_BY_ID = "delete/one/passive/{productId}";
    public static final String PRODUCTS_DELETE_BY_ID_PERMANENTLY = "delete/one/permanent/{productId}";

    // PRICE_ENDPOINTS
    public static final String PRICES_FIND_ALL_BY_PRODUCT_ID_TEXT = "find/{productId}/all/";
    public static final String PRICES_FIND_DEFAULT_BY_PRODUCT_ID_TEXT = "find/default/{productId}";
    public static final String PRICES_POST_SINGLE_TEXT = "create/one/";
    public static final String PRICES_FIND_BY_ID_TEXT = "find/one/{priceId}";
    public static final String PRICES_FIND_EXISTS_BY_ID_TEXT = "exists/one/{priceId}";
    public static final String PRICE_EXISTS_BY_UNIQUE_FIELDS = "exists/one/unique/";
    public static final String PRICE_PUT_SINGLE = "update/one/{priceId}";
    public static final String PRICE_DELETE_BY_ID = "delete/one/passive/{priceId}";
    public static final String PRICE_DELETE_BY_ID_PERMANENTLY = "delete/one/permanent/{priceId}";

}
