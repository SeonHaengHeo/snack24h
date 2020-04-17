package com.snack24h.snack24h.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.snack24h.snack24h.model.enumtype.InquirySalesStatus;
import com.snack24h.snack24h.model.enumtype.InquiryServiceType;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class InquiryDto implements Serializable {

  private LocalDateTime createAt;
  protected Long createBy;
  private LocalDateTime updateAt;
  protected Long modifyBy;

  private Long id;

  //기업명
  private String companyName;

  //담당자
  private String contactName;

  //담당자 휴대전화
  private String cellphone;

  //이메일
  private String email;

  //서비스타입
  private InquiryServiceType inquiryServiceType;

  //영업상태
  private InquirySalesStatus inquirySalesStatus;

  //문의내용(고객입력)
  private String inquiryContents;
}