package ma.octo.assignement.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("deposit")
@Setter
@Getter
@RequiredArgsConstructor
public class Deposit extends Transaction {

  @Column
  private String senderFullName;

//  @Column
//  private String rib;
//  when we get the rib from the sender, we get the correspondant account to the rib and store the account
}
