package com.Archiver.config;

/**
 * @Author: Archiver
 * @Description: property(属性)实体类
 * @Date: Created in 12:48 2020/1/31
 * @Modified By:
 */
public class Property {

    private String name;

    private String value;

    private String ref;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    @Override
    public String toString() {
        return "Property{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", ref='" + ref + '\'' +
                '}';
    }
}
