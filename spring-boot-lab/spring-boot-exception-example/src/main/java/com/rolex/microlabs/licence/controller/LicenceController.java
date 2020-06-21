package com.rolex.microlabs.licence.controller;


import com.rolex.microlabs.licence.dto.LicenceAddRequest;
import com.rolex.microlabs.licence.dto.SimpleLicenceDTO;
import com.rolex.microlabs.licence.service.ILicenceService;
import com.rolex.microlabs.licence.dto.LicenceParam;
import com.rolex.microlabs.licence.dto.QueryData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author rolex
 * @since 2020-06-20
 */
@RestController
@RequestMapping("/employee/licence")
public class LicenceController {
    @Autowired
    ILicenceService licenceService;

    @GetMapping("/list")
    public QueryData<SimpleLicenceDTO> test(@Validated LicenceParam licenceParam){
        QueryData<SimpleLicenceDTO> licences = licenceService.getLicences(licenceParam);
        return licences;
    }

    @PostMapping("/add")
    public void add(@Validated @RequestBody LicenceAddRequest request){
        licenceService.addLicence(request);
    }
}

