package ma.octo.assignement.util;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Accessors(fluent = true)
public class Constants {
    @Value("${jwt.token.access.validity}")
    private long ACCESS_TOKEN_EXPIRES;

    @Value("${jwt.token.refresh.validity}")
    private long REFRESH_TOKEN_EXPIRES;

    @Value("${jwt.signing.key}")
    private String SIGNING_KEY;

    @Value("${jwt.authorities.key}")
    private String AUTHORITIES_KEY;
}
