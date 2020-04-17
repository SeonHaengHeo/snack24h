package com.snack24h.snack24h.model.domain.wefun;

import com.snack24h.snack24h.model.enumtype.InquirySalesStatus;
import com.snack24h.snack24h.model.enumtype.InquiryServiceType;
import com.snack24h.snack24h.model.enumtype.YN;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="inquiry")
@Getter
@Setter
@Audited
@EntityListeners(AuditingEntityListener.class)
@ToString
public class Inquiry implements Serializable {

  @Column(name="create_at", nullable = false, updatable = false)
  @CreationTimestamp
  private LocalDateTime createAt;

  @CreatedBy
  @Column(name="create_id", updatable = false)
  protected Long createBy;

  @UpdateTimestamp
  @Column(name="update_at", nullable = false)
  private LocalDateTime updateAt;


  @LastModifiedBy
  @Column(name="update_id")
  protected Long modifyBy;

  @Id
  @Column(name="inquiry_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  //기업명
  private String companyName;

  //담당자
  private String contactName;

  //담당자 휴대전화
  private String cellphone;

  //이메일
  private String email;

  // 등록작업자
  @OneToOne
  @JoinColumn(name = "create_account_id")
  private Account account;

  //서비스 타입
  @Enumerated(EnumType.STRING)
  private InquiryServiceType inquiryServiceType;

  //영업상태
  @Enumerated(EnumType.STRING)
  private InquirySalesStatus inquirySalesStatus;

  //문의내용(고객입력)
  @Lob
  private String inquiryContents;

  // 개인정보 동의여부
  private YN isPrivateAgree;


  //맥주
  private int curationBeer;

  //원두커피
  private int curationCoffee;
}
