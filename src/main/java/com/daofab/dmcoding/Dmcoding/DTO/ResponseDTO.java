package com.daofab.dmcoding.Dmcoding.DTO;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ResponseDTO {
    private Integer id;
    private String sender;
    private String receiver;
    private Integer totalAmount;
    private Integer totalPaidAmount;
}
