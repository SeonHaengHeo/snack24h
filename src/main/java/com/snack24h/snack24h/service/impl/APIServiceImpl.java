package com.snack24h.snack24h.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snack24h.snack24h.model.dto.InquiryDto;
import com.snack24h.snack24h.repository.wefun.InquiryRepository;
import com.snack24h.snack24h.service.APIService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.jandi.Jandi;
import org.springframework.social.jandi.imlp.JandiTemplate;
import org.springframework.social.jandi.type.ConnectInfo;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.*;

@org.springframework.stereotype.Service
@Transactional
public class APIServiceImpl implements APIService {

    private ModelMapper modelMapper;
    private InquiryRepository inquiryRepository;
    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;

    @Value("${spring.profiles}")
    private String profiles;

    @Autowired
    public APIServiceImpl(ModelMapper modelMapper,
                          InquiryRepository inquiryRepository) {
        this.modelMapper = modelMapper;
        this.inquiryRepository = inquiryRepository;
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    /**
     * 문의하기 등록 시 jandi 토픽으로 정보 전달
     *
     * @param inquiryDto
     * @return
     */
    @Override
    public void jandiInquiryWebHook(InquiryDto inquiryDto){
        if (inquiryDto != null && inquiryDto.getId() > 0){
            String companyInfo = "";

            companyInfo = "기업명: " + inquiryDto.getCompanyName();
            String contractInfo = "담당자 이름: " + inquiryDto.getContactName() + " 연락처: " + inquiryDto.getCellphone() + " 이메일: " + inquiryDto.getEmail();
            // 운영환경이 아니면 개발토픽에 전달
            String apiUrl = "https://wh.jandi.com/connect-api/webhook/17753056/832c470163e047cb7361f1b40bbaf101";
            String color ="";

            if (profiles.equals("production")){
                apiUrl = "https://wh.jandi.com/connect-api/webhook/17753056/9475df63f35b9e1358e6e23fd1afa979";
            }

            switch (inquiryDto.getInquiryServiceType().toString()){
                case "스낵24":
                    color = "#FAC11B";
                    break;
                default:
                    color = "#FAC11B";
                    break;
            }

            Jandi jandi = new JandiTemplate(apiUrl, restTemplate, objectMapper);
            jandi.getWebhookOperations().sendMessage(
                    "[[위펀오피스 바로가기]](https://office.wefun.kr/office/sales/inquiry/update/"+ inquiryDto.getId()+")"
                            +" - "+ inquiryDto.getInquiryServiceType()
                            + " 신규 기업 신청",
                    color,
                    Arrays.asList(
                            new ConnectInfo("[기업 정보]", companyInfo, null),
                            new ConnectInfo("[담당자 정보]", contractInfo, null),
                            new ConnectInfo("[상담 요청내용]", inquiryDto.getInquiryContents(), null)
                    )
            );
        }

    }

}

