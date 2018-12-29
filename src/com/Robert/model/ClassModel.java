package com.Robert.model;

import java.util.List;

public class ClassModel extends DrawUMLProjectModel {

    private String type;
    private List<FieldModel> fields;
    private List<MethodModel> methods;

    public ClassModel(int id, String name, String type) {
        super(id, name);
        this.type = type;
    }

    public ClassModel(int id, String name, String type,
                      List<FieldModel> fields, List<MethodModel> methods) {
        super(id, name);
        this.type = type;
        this.fields = fields;
        this.methods = methods;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<FieldModel> getFields() {
        return fields;
    }

    public void setFields(List<FieldModel> fields) {
        this.fields = fields;
    }

    public List<MethodModel> getMethods() {
        return methods;
    }

    public void setMethods(List<MethodModel> methods) {
        this.methods = methods;
    }
}
