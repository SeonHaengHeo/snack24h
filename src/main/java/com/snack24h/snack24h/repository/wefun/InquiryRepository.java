package com.snack24h.snack24h.repository.wefun;

import com.snack24h.snack24h.model.domain.wefun.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {

    /**
     * 최근 등록되 Inquiry ID 조회
     *
     * @return
     * */

    @Query(value = "select COALESCE(MAX(i.id), 0) from Inquiry i ")
    Long getMaxId();

}
