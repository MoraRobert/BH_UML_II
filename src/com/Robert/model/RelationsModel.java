package com.Robert.model;

public class RelationsModel extends DrawUMLProjectModel {

    private ClassModel parentClass;
    private ClassModel childClass;

    public RelationsModel(int id, String name, ClassModel parentClass, ClassModel childClass) {
        super(id, name);
        this.parentClass = parentClass;
        this.childClass = childClass;
    }

    public ClassModel getParentClass() { return parentClass; }

    public void setParentClass(ClassModel parentClass) {
        this.parentClass = parentClass;
    }

    public ClassModel getChildClass() {
        return childClass;
    }

    public void setChildClass(ClassModel childClass) {
        this.childClass = childClass;
    }
}
