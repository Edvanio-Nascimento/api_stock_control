package com.edy.apiStockControl.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_suppliers")
public class Supplier implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "binary(16)", nullable = false)
    private UUID id;

    @Column(name = "company_name", length = 100, nullable = false, updatable = false)
    private String companyName;

    @Column(length = 18, nullable = false, updatable = false, unique = true)
    private String cnpj;

    @Column(name = "contact_email", length = 50, nullable = false, updatable = false)
    private String contactEmail;

    public Supplier(String companyName, String cnpj, String contactEmail) {
        this.companyName = companyName;
        this.cnpj = cnpj;
        this.contactEmail = contactEmail;
    }

    public Supplier() {
    }

    public UUID getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Supplier supplier)) return false;
        return Objects.equals(id, supplier.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
