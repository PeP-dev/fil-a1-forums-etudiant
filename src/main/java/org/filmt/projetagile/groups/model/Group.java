package org.filmt.projetagile.groups.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe représentant un groupe dans une école
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group {

    /**
     * Identifiant de la publication
     */
    public String id;

    /**
     * Identifiant de l'école dans lequel se situe le groupe
     */
    public String schoolId;

    /**
     * Libellé du groupe, affiché dans les liens
     */
    public String label;

    /**
     * Description du groupe, affiché à la demande de détails
     */
    public String description;
}
