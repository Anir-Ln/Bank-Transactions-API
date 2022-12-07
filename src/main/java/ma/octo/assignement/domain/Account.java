package ma.octo.assignement.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ACCOUNT")
@Setter
@Getter
@RequiredArgsConstructor
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(length = 16, unique = true)
  private String accountNumber;

  private String rib;

  @Column(precision = 16, scale = 2)
  private BigDecimal balance;

  @ManyToOne()
  @JoinColumn(name = "user_id")
  private User user;

}
