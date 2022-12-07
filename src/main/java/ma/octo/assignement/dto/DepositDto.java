package ma.octo.assignement.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@RequiredArgsConstructor
public class DepositDto extends TransactionDto {
    @NotNull
    @NotBlank
    private String rib;
    @NotNull
    @NotBlank
    private String senderFullName;
}
