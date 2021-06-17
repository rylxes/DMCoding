package com.daofab.dmcoding.Dmcoding.Controller;

import com.daofab.dmcoding.Dmcoding.DTO.ResponseDTO;
import com.daofab.dmcoding.Dmcoding.Service.DataFormat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@Api(value = "Parent/Child", description = "Parent / Child")
public class DataController {

    private final DataFormat dataFormat;

    public DataController(DataFormat dataFormat) {
        this.dataFormat = dataFormat;
    }

    // Get All Parents
    @GetMapping(path = "/parent/list", produces = "application/json")
    public ResponseEntity<Page<ResponseDTO>> parentList(int pageNo) throws Exception {
        return new ResponseEntity<>(dataFormat.allParentTransaction(pageNo), HttpStatus.OK);
    }

    // Get All Children of a parent
    @GetMapping(path = "/child/transactions", produces = "application/json")
    public ResponseEntity<Page<ResponseDTO>> parentList(int parentId, int pageNo) throws Exception {
        return new ResponseEntity<>(dataFormat.allChildTransaction(parentId, pageNo), HttpStatus.OK);
    }


}
