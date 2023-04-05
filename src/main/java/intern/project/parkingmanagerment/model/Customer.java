package intern.project.parkingmanagerment.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@DynamicUpdate(value = true)
@Table(name = "customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerID;
    @NotBlank
    @Column(name = "name")
    private String name;
    @NotBlank
    @Column(name = "phone")
    private String phone;
    @Email
    @NotBlank
    @Column(name = "email")
    private String email;
    @NotBlank
    @Column(name = "address")
    private String address;
    @NotBlank
    @Column(name = "ward")
    private String ward;
    @NotBlank
    @Column(name = "city")
    private String city;
    @NotBlank
    @Column(name = "country")
    private String country;

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
