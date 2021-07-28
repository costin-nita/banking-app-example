package atm.service.domain;

import atm.service.domain.config.BaseEntity;
import atm.service.domain.config.BaseSchema;
import atm.service.domain.config.BaseTable;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"email"})
@EntityListeners(AuditingEntityListener.class)
@Table(name = BaseTable.Auth.ACCOUNT, schema = BaseSchema.AUTH)
public class Account extends BaseEntity {

    @Column(name = "email", nullable = false, unique = true, length = 100)
    protected String email;

    @Column(name = "password", nullable = false, length = 300)
    protected String password;

    @Column(name = "first_name", nullable = false, length = 100)
    protected String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    protected String lastName;

    @Column(name = "middle_name", length = 100)
    protected String middleName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    @Setter(AccessLevel.NONE)
    protected Set<BankAccount> bankAccounts = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fromAccount")
    @Setter(AccessLevel.NONE)
    protected Set<BankDepositTransaction> bankDepositTransactions = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ownedByAccount")
    @Setter(AccessLevel.NONE)
    protected Set<BankTransferTransaction> bankTransferTransactions = new HashSet<>();

    public Account(String email, String password, String firstName, String lastName, String middleName) {
        this(null, email, password, firstName, lastName, middleName);
    }

    public Account(Long id,
                   String email,
                   String password,
                   String firstName,
                   String lastName,
                   String middleName) {

        super(id);
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }
}
