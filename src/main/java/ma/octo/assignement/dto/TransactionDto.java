package ma.octo.assignement.dto;




import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
public abstract class TransactionDto {
    private Long id;

    @NotNull
    @NotEmpty
    private String reason;

    @NotNull
    @Range(min = 10, max = 10000, message = "Montant minimal est 10, et montant maximal est 10000.")
    private BigDecimal amount;

    @NotNull
    private Date date;
}
