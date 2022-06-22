package org.filmt.projetagile.posts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

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
     * Identifiant du publicateur
     */
    private String userName;

    /**
     * Date de création post
     */
    private Timestamp createdAt;
}
