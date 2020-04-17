package com.snack24h.snack24h.service;

import com.snack24h.snack24h.model.dto.InquiryDto;

public interface APIService {

  /**
   * 문의하기 등록 시 jandi 토픽으로 정보 전달
   *
   * @param inquiryDto
   * @return
   */
  void jandiInquiryWebHook(InquiryDto inquiryDto);

}
