package ma.octo.assignement.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("transfer")
@Getter
@Setter
@RequiredArgsConstructor
public class Transfer extends Transaction {

  @ManyToOne
  private Account senderAccount;
}
