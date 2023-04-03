package intern.project.parkingmanagerment.model;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "contract")
public class Contract implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_id")
    private Long contractID;
    
    @ManyToOne
    @JoinColumn(name = "contract_type_id")
    private ContractType contractType;

    @Column(name = "time_expired")
    private Date timeExpired;
    
    @ManyToOne
    @JoinColumn(name = "add_by")
    private User addedBy;
    
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToMany(mappedBy = "contract",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    List<Vehicle> Vehicles;
    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "deleted_at")
    private Date deletedAt;
    
    // Constructors, getters, and setters
}
