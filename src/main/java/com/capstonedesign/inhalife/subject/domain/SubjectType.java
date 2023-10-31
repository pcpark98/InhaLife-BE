package com.capstonedesign.inhalife.subject.domain;

import com.capstonedesign.inhalife.subject.exception.NotExistedSubjectTypeException;

import java.util.ArrayList;
import java.util.List;

public enum SubjectType {
    REQUIRED_LIBERAL_ARTS("교양필수"),
    ELECTIVE_MAJOR("전공선택"),
    REQUIRED_MAJOR("전공필수"),
    TEACHING_COURSE("교직과정"),
    ELECTIVE_LIBERAL_ARTS("교양선택"),
    ELECTIVE_COURSE("일반선택"),
    CORE_LIBERAL_ARTS("핵심교양");

    private final String name;

    private SubjectType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    /**
     * 한글 명을 enum 명으로 바꿔주는 함수
     */
    public static SubjectType nameOf(String name) {
        for(SubjectType type : SubjectType.values()) {
            if(type.getName().equals(name)) {
                return type;
            }
        }

        throw new NotExistedSubjectTypeException();
    }

    public static List<String> getSubjectTypeList() {
        List<String> subjectTypeList = new ArrayList<>();

        for(SubjectType type : SubjectType.values()) {
            subjectTypeList.add(type.getName());
        }
        return subjectTypeList;
    }
}