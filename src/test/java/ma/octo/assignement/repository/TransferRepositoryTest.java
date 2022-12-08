package ma.octo.assignement.repository;

import ma.octo.assignement.domain.Transfer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class TransferRepositoryTest {

  @Autowired
  private TransferRepository transferRepository;

  private Transfer transfer;
  @BeforeEach
  void setUp() {
    transfer = new Transfer();
    transfer.setAmount(BigDecimal.valueOf(250));
    transferRepository.deleteAll();
    transferRepository.save(transfer);
  }

  @AfterEach
  void tearDown() {
    transfer = null;
  }

  @Test
  public void findOne() {
    Optional<Transfer> transfer_ = transferRepository.findById(transfer.getId());
    assert transfer_.isPresent();
    assertEquals(transfer_.get().getId(), transfer.getId());
  }

  @Test
  public void findAll() {
    Transfer transfer_ = new Transfer(); transfer_.setAmount(BigDecimal.valueOf(300));
    transferRepository.save(transfer_);

    List<Transfer> transferList = transferRepository.findAll();
    assertNotNull(transferList);
    assertEquals(transferList.size(), 2);
  }

  @Test
  public void save() {
    Transfer transfer_ = new Transfer(); transfer_.setAmount(BigDecimal.valueOf(300));
    Transfer saved = transferRepository.save(transfer_);
    assertEquals(saved.getId(), transfer_.getId());
    assertEquals(saved.getAmount(), transfer_.getAmount());
  }

  @Test
  public void delete() {
    transferRepository.deleteById(transfer.getId());
    Optional<Transfer> found = transferRepository.findById(transfer.getId());
    assertFalse(found.isPresent());
  }
}