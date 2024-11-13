package user.infra.panache.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class PanacheUserEntity extends PanacheEntityBase {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "document_number")
    private String documentNumber;

    @Column(name = "name")
    private String name;

    public PanacheUserEntity(String id, String documentNumber, String name) {
        this.id = id;
        this.documentNumber = documentNumber;
        this.name = name;
    }

    public PanacheUserEntity() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
