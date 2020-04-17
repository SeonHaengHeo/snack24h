package com.snack24h.snack24h.controller;

import com.snack24h.snack24h.model.dto.InquiryDto;
import com.snack24h.snack24h.model.dto.ServiceResult;
import com.snack24h.snack24h.service.InquiryService;
import org.springframework.web.bind.annotation.*;

@RestController
public class InquiryController {

    private InquiryService inquiryService;

    InquiryController(InquiryService inquiryService){
        this.inquiryService = inquiryService;
    }

    @PostMapping("/inquiry")
    public ServiceResult inquirySave(@ModelAttribute InquiryDto inquiryDto){

        ServiceResult serviceResult = this.inquiryService.inquirySave(inquiryDto);

        return serviceResult;
    }
}
