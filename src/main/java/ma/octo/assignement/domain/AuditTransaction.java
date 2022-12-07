package ma.octo.assignement.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ma.octo.assignement.domain.util.EventType;

import javax.persistence.*;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
public class AuditTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @Column(length = 100)
    private String message;
}
