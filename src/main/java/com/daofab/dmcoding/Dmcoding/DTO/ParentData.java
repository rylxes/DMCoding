package com.daofab.dmcoding.Dmcoding.DTO;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ParentData {
    private Integer id;
    private String sender;
    private String receiver;
    private Integer totalAmount;
}
