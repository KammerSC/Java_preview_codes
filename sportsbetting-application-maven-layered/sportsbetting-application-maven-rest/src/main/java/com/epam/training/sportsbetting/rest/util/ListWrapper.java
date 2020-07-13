package com.epam.training.sportsbetting.rest.util;

import java.util.List;

public class ListWrapper {
    private List<Long> ids;

    public ListWrapper() {
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return "ListWrapper [ids=" + ids + "]";
    }
}
