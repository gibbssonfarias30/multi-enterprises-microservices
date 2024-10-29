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
@Table(name = "documents_type")
@Entity
public class DocumentsTypeEntity extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_documents_type")
    private int idDocumentsType;

    @Column(name = "cod_type", length = 45, nullable = false)
    private String codType;

    @Column(name = "desc_type", length = 45, nullable = false)
    private String descType;

    @Column(name = "status", nullable = false)
    private int status;

}
