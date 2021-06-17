package com.daofab.dmcoding.Dmcoding.DTO;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
public class Parent {
    private List<ParentData> data;
}
