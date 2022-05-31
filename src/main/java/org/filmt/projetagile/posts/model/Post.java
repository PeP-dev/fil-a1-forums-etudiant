package org.filmt.projetagile.posts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe représentant une publication dans un groupe
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    /**
     * Identifiant de la publication
     */
    private String id;

    /**
     * Identifiant du groupe dans lequel se situe la publication
     */
    private String groupId;

    /**
     * Titre de la publication
     */
    private String title;

    /**
     * Contenu de la publication
     */
    private String content;

    /**
     * Catégorie de la publication
     */
    private String categoryId;

    /**
     * Utilisateur qui a poste
     */
    private String userName;
}
