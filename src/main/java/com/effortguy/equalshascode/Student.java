package com.effortguy.equalshascode;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class Student {
    String id;
    String name;

    @Builder
    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
