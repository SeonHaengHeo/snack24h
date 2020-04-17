package com.snack24h.snack24h.service.impl;

import com.snack24h.snack24h.model.domain.wefun.Inquiry;
import com.snack24h.snack24h.model.dto.InquiryDto;
import com.snack24h.snack24h.model.dto.ServiceResult;
import com.snack24h.snack24h.model.enumtype.InquirySalesStatus;
import com.snack24h.snack24h.model.enumtype.InquiryServiceType;
import com.snack24h.snack24h.repository.wefun.AccountRepository;
import com.snack24h.snack24h.repository.wefun.InquiryRepository;
import com.snack24h.snack24h.service.APIService;
import com.snack24h.snack24h.service.InquiryService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
public class InquiryServiceImpl implements InquiryService {

    private ModelMapper modelMapper;
    private InquiryRepository inquiryRepository;
    private AccountRepository accountRepository;
    private APIService apiService;

    InquiryServiceImpl(ModelMapper modelMapper,
                       InquiryRepository inquiryRepository,
                       AccountRepository accountRepository,
                       APIService apiService){
        this.modelMapper = modelMapper;
        this.inquiryRepository = inquiryRepository;
        this.accountRepository = accountRepository;
        this.apiService = apiService;
    }

    /**
     * 고객문의 저장
     * */
    @Override
    public ServiceResult inquirySave(InquiryDto inquiryDto) {
        ServiceResult serviceResult = ServiceResult.builder()
                .OK(true)
                .build();

        Inquiry inquiry = this.modelMapper.map(inquiryDto, Inquiry.class);
        inquiry.setInquiryServiceType(InquiryServiceType.스낵24);
        inquiry.setInquirySalesStatus(InquirySalesStatus.RECEIPT);

        inquiry.setAccount(this.accountRepository.findById(0l).get());

        this.inquiryRepository.save(inquiry);

        inquiryDto.setId(this.inquiryRepository.getMaxId());
        inquiryDto.setInquiryServiceType(inquiry.getInquiryServiceType());
        this.apiService.jandiInquiryWebHook(inquiryDto);
        return serviceResult;
    }
}
