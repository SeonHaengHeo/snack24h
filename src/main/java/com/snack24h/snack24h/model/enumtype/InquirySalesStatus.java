package com.snack24h.snack24h.model.enumtype;

public enum InquirySalesStatus {
  RECEIPT("신규신청")
  ,NEW("신규접수")
  ,PREPARE_CONTACT("고객컨택대기")
  ,CONTACT("고객컨택")
  ,ESTIMATION("견적발송")
  ,PREPARE_MEET("미팅예정")
  ,MEET("미팅완료")
  ,PREPARE_CONTRACT("전자계약발송")
  ,CONTRACT("계약완료")
  ,INSTALL("설치확인, 영업완료")
  ,CLOSE("영업종료")
  ;
  private String title;
  InquirySalesStatus(String title){
    this.title = title;
  }

  public String getTitle() {
    return title;
  }
}
