package com.bilgeadam.model;

import java.util.Comparator;

public class SortByDate implements Comparator<PostModel> {

    @Override
    public int compare(PostModel o1, PostModel o2) {
        return o2.getUpdateDate().compareTo(o1.getUpdateDate());
    }
}
