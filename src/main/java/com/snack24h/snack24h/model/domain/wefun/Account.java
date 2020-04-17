package com.snack24h.snack24h.model.domain.wefun;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="account")
@Getter
@Setter
@Audited
@EntityListeners(AuditingEntityListener.class)
@ToString(exclude = "department")
public class Account implements Serializable {
  @Id
  @Column(name="account_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
}
