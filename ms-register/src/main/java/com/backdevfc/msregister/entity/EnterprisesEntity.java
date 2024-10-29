package com.backdevfc.msregister.entity;

import com.backdevfc.msregister.aggregates.model.Audit;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "enterprises")
@Entity
public class EnterprisesEntity extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_enterprises")
    private int idEnterprises;

    @Column(name = "num_document", length = 15, nullable = false)
    private String numDocument;

    @Column(name = "business_name", length = 150, nullable = false)
    private String businessName;

    @Column(name = "tradename", length = 150)
    private String tradeName;

    @Column(name = "status", nullable = false)
    private int status;

    @ManyToOne
    @JoinColumn(name = "enterprises_type_id_enterprises_type", nullable = false)
    private EnterprisesTypeEntity enterprisesTypeEntity;

    @ManyToOne
    @JoinColumn(name = "documents_type_id_documents_type", nullable = false)
    private DocumentsTypeEntity documentsTypeEntity;
}
