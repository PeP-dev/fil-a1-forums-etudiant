package org.filmt.projetagile.groups.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * Classe représentant un groupe dans une école
 */
@Data
@AllArgsConstructor
public class Group {

    /**
     * Identifiant de la publication
     */
    public String id;

    /**
     * Identifiant de l'école dans lequel se situe le groupe
     */
    public final String schoolId;

    /**
     * Libellé du groupe, affiché dans les liens
     */
    public final String label;

    /**
     * Description du groupe, affiché à la demande de détails
     */
    public final String description;

    /**
     * Liste des catégories
     */
    List<Category> categories = Collections.emptyList();
}
