package org.filmt.projetagile.groups.model;

import java.util.Collections;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

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
