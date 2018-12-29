package com.Robert.model;

import com.Robert.model.ClassModel;

import java.util.List;

public class MethodModel extends DrawUMLProjectModel{

    private int id;
    private String name;
    private String returnType;
    private String accessModifier;
    private List<String> parameters;
    private ClassModel cls;

    public MethodModel(int id, String name) {
        super(id, name);
    }

    public MethodModel(int id, String name, String returnType, String accessModifier, List<String> parameters, ClassModel cls) {
        super(id, name);
        this.returnType = returnType;
        this.accessModifier = accessModifier;
        this.parameters = parameters;
        this.cls = cls;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getAccessModifier() {
        return accessModifier;
    }

    public void setAccessModifier(String accessModifier) {
        this.accessModifier = accessModifier;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

    public ClassModel getCls() {
        return cls;
    }

    public void setCls(ClassModel cls) {
        this.cls = cls;
    }
}
