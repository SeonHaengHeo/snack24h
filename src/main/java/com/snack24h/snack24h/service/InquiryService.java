package com.snack24h.snack24h.service;

import com.snack24h.snack24h.model.dto.InquiryDto;
import com.snack24h.snack24h.model.dto.ServiceResult;
import org.springframework.stereotype.Service;

@Service
public interface InquiryService {

    /**
     * 고객문의 저장
     * */
    ServiceResult inquirySave(InquiryDto inquiryDto);
}
