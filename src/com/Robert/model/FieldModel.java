package com.Robert.model;

public class FieldModel extends DrawUMLProjectModel{

    private int id;
    private String accessModifier;
    private String type;
    private String name;
    private ClassModel cls;
    private int isCollection; // TODO: should be boolean, but sqlite can not cope with it


    public FieldModel(int id, String accessModifier, String type, String name, ClassModel cls, int isCollection) {
        super(id, name);
        this.accessModifier = accessModifier;
        this.type = type;
        this.cls = cls;
        this.isCollection = isCollection;
    }

    public String getAccessModifier() {
        return accessModifier;
    }

    public void setAccessModifier(String accessModifier) {
        this.accessModifier = accessModifier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ClassModel getCls() {
        return cls;
    }

    public void setCls(ClassModel cls) {
        this.cls = cls;
    }

    public int isCollection() {
        return isCollection;
    }

    public void setCollection(int isColl) {
        isCollection = isColl;
    }
}
