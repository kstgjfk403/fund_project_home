package com.idgcapital.ufund.model;

import java.util.List;

/**
 * Created by li_zhao on 2018/1/5.
 */
public class Tree {
    private String id;

    private String label;

    private List<Tree> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Tree> getChildren() {
        return children;
    }

    public void setChildren(List<Tree> children) {
        this.children = children;
    }
}
