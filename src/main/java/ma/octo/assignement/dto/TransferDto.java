package ma.octo.assignement.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@RequiredArgsConstructor
public class TransferDto extends TransactionDto {
    @NotEmpty
    @NotNull
    private String senderAccountNumber;
    @NotEmpty
    @NotNull
    private String recipientAccountNumber;
}
