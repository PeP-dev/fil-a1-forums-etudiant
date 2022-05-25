package org.filmt.projetagile.school.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class School {

    /**
     * Identifiant de l'ecole'
     */
    private String id;

    /**
     * Libelle de l'ecole
     */
    private String libelle;

    /**
     * Type de l'ecole
     */
    private String school_type;

    /**
     * Description de l'ecole
     */
    private String description;


}
