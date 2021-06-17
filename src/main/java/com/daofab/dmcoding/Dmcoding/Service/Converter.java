package com.daofab.dmcoding.Dmcoding.Service;

import com.daofab.dmcoding.Dmcoding.DTO.Parent;
import com.daofab.dmcoding.Dmcoding.DTO.ParentData;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Slf4j
public class Converter<T> {

    // custom function that converts list to pagable
    Page<T> toPage(List<T> list, int pagesize, int pageNo) {
        pageNo -- ;
        int totalpages = list.size() / pagesize;
        PageRequest pageable = PageRequest.of(pageNo, pagesize);

        int max = pageNo >= totalpages ? list.size() : pagesize * (pageNo + 1);
        int min = pageNo > totalpages ? max : pagesize * pageNo;

        log.info("totalpages{} pagesize {} pageNo {}   list size {} min {}   max {} ...........", totalpages, pagesize, pageNo, list.size(),
                min, max);
        Page<T> pageResponse = new PageImpl<T>(list.subList(min, max), pageable, list.size());
        return pageResponse;
    }


}
