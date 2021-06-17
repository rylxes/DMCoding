package com.daofab.dmcoding.Dmcoding.Service;

import com.daofab.dmcoding.Dmcoding.DTO.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
@Slf4j
public class DataFormat {

    private int pagesize = 2;

    public Page<ResponseDTO> allParentTransaction(int pageNo) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Parent parent = objectMapper.readValue(new File("Parent.json"), Parent.class);
            List<ResponseDTO> responseDTOList = new ArrayList<>();

            parent.getData().forEach((parentData) -> {
                ResponseDTO responseDTO = new ResponseDTO();
                responseDTO.setId(parentData.getId());
                responseDTO.setReceiver(parentData.getReceiver());
                responseDTO.setSender(parentData.getSender());
                responseDTO.setTotalAmount(parentData.getTotalAmount());

                Integer theSum = totalAmount(parentData.getId());
                responseDTO.setTotalPaidAmount(theSum);

                responseDTOList.add(responseDTO);
            });

            log.info("parentDataResponseList --> " + responseDTOList);

            Converter<ResponseDTO> data = new Converter<>();
            Page<ResponseDTO> theData = data.toPage(responseDTOList, pagesize, pageNo);
            log.info("the Data --> " + theData.toList());
            return theData;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    // Calculates total amount paid by parentID
    private Integer totalAmount(int parentID) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Child child = objectMapper.readValue(new File("Child.json"), Child.class);
            return child.getData().stream().filter((childData) -> childData.getParentId().equals(parentID)).mapToInt(ChildData::getPaidAmount).sum();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    // Get Single Parent Data by ID
    private ParentData findByID(int parentID) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Parent parent = objectMapper.readValue(new File("Parent.json"), Parent.class);
            return parent.getData().stream().filter((parentData) -> parentData.getId().equals(parentID)).findFirst().orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Page<ResponseDTO> allChildTransaction(int parentId, int pageNo) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Child child = objectMapper.readValue(new File("Child.json"), Child.class);
            ParentData parentData = findByID(parentId);
            List<ResponseDTO> responseDTOList = new ArrayList<>();

            child.getData().stream().filter((childData) -> childData.getParentId().equals(parentId)).forEach((childDataEach) -> {
                ResponseDTO responseDTO = new ResponseDTO();
                responseDTO.setId(childDataEach.getId());
                responseDTO.setReceiver(parentData.getReceiver());
                responseDTO.setSender(parentData.getSender());
                responseDTO.setTotalAmount(totalAmount(parentData.getId()));
                responseDTO.setTotalPaidAmount(childDataEach.getPaidAmount());
                responseDTOList.add(responseDTO);
            });

            log.info("childDataResponseList --> " + responseDTOList);

            Converter<ResponseDTO> data = new Converter<>();
            Page<ResponseDTO> theData = data.toPage(responseDTOList, pagesize, pageNo);
            log.info("the Data --> " + theData.toList());
            return theData;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

}
