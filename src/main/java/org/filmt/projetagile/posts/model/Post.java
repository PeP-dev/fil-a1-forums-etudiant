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
    public String id;

    /**
     * Identifiant du groupe dans lequel se situe la publication
     */
    public String groupId;

    /**
     * Titre de la publication
     */
    public String title;

    /**
     * Contenu de la publication
     */
    public String content;
}
