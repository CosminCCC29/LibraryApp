package com.example.app.repositories.query;

import com.google.common.primitives.Ints;

import java.util.HashMap;
import java.util.Map;

public abstract class BasicQueryCriteria {
    private static final int DEFAULT_ITEMS_PER_PAGE = 15;

    protected Map<String, Object> queryParams;
    private Integer page;
    protected Integer itemsPerPage;
    protected boolean exactMatch;
    // protected boolean verbose;
    protected Integer offset;

    protected BasicQueryCriteria(Map<String, String> params) {
        this.queryParams = new HashMap<>();

        page = Ints.tryParse(params.getOrDefault("page", ""));
        itemsPerPage = Ints.tryParse(params.getOrDefault("items_per_page", ""));

        if (itemsPerPage == null) itemsPerPage = DEFAULT_ITEMS_PER_PAGE;

        if (page == null) {
            itemsPerPage = null;
            offset = null;
        } else {
            offset = (page - 1) * itemsPerPage;
        }

        exactMatch = Boolean.parseBoolean(params.get("match"));
        //verbose = Boolean.parseBoolean(params.get("verbose"));
    }

    public abstract String createQuery();

    public Map<String, Object> getQueryParams() {
        return queryParams;
    }
}
