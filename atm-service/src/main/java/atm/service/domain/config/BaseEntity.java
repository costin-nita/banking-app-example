package atm.service.domain.config;

import atm.service.domain.Account;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@ToString(of = {"id"})
@EqualsAndHashCode(of = {"id"})
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, insertable = false, unique = true)
    protected Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_account", updatable = false, insertable = false)
    protected Account createdByAccount;

    @CreatedBy
    @Column(name = "created_by_account", updatable = false)
    protected Long createdByAccountId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modified_by_account", updatable = false, insertable = false)
    protected Account modifiedByAccount;

    @LastModifiedBy
    @Column(name = "modified_by_account")
    protected Long modifiedByAccountId;

    @Column(name = "date_created", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    protected Date dateCreated;

    @Column(name = "date_modified", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    protected Date dateModified;

    //

    public BaseEntity(Long id) {
        this.id = id;
    }
}
