package com.daofab.dmcoding.Dmcoding.DTO;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ChildData {
    private Integer id;
    private Integer parentId;
    private Integer paidAmount;
}
