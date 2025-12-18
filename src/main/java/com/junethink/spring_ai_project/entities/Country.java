package com.junethink.spring_ai_project.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Country {
    /*    private String name;
        private String capital;
        private String currency;
        private String language;
        private String population;*/
    private List<String> cities;
}
