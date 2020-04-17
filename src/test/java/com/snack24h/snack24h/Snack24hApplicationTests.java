package com.snack24h.snack24h;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snack24h.snack24h.model.dto.InquiryDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.social.jandi.Jandi;
import org.springframework.social.jandi.imlp.JandiTemplate;
import org.springframework.social.jandi.type.ConnectInfo;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

@SpringBootTest
class Snack24hApplicationTests {

  @Test
  public void jandiInquiryWebHook(){
    RestTemplate restTemplate = new RestTemplate();
    ObjectMapper objectMapper = new ObjectMapper();
    InquiryDto inquiryDto = new InquiryDto();

    inquiryDto.setId(10l);
    inquiryDto.setCompanyName("개발팀");
    inquiryDto.setContactName("허선행");
    inquiryDto.setCellphone("01072376608");
    inquiryDto.setEmail("shheo@snack24h.com");
    inquiryDto.setInquiryContents("테스트 입니다.");

    if (inquiryDto != null && inquiryDto.getId() > 0){
      String companyInfo = "";

      companyInfo = "기업명: " + inquiryDto.getCompanyName();
      String contractInfo = "담당자 이름: " + inquiryDto.getContactName() + " 연락처: " + inquiryDto.getCellphone() + " 이메일: " + inquiryDto.getEmail();

      // url -> dev
      Jandi jandi = new JandiTemplate("https://wh.jandi.com/connect-api/webhook/17753056/832c470163e047cb7361f1b40bbaf101", restTemplate, objectMapper);
//            Jandi jandi = new JandiTemplate("https://wh.jandi.com/connect-api/webhook/17753056/9475df63f35b9e1358e6e23fd1afa979", restTemplate, objectMapper);
      jandi.getWebhookOperations().sendMessage(
              "[[위펀오피스 바로가기]](https://office.wefun.kr/office/sales/inquiry/update/"+inquiryDto.getId()+") - 신규 기업 신청",
              "#FAC11B",
              Arrays.asList(
                      new ConnectInfo("[기업 정보]", companyInfo, null),
                      new ConnectInfo("[담당자 정보]", contractInfo, null),
                      new ConnectInfo("[상담 요청내용]", inquiryDto.getInquiryContents(), null)
              )
      );
    }

  }

  private String encode(String str) {
    String result = "";
    try {
      result = new String(str.getBytes("UTF-8"), "ISO-8859-1");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
      result = "encoding error!!";
    }
    return result;
  }
}
