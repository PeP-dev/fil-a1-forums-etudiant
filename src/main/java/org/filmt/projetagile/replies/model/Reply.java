package org.filmt.projetagile.replies.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Reply {

    /**
     * Identifiant de la réponse
     */
    private String id;

    /**
     * Identifiant du poste auquel est rattachée la réponse
     */
    private String postId;

    /**
     * Identifiant de la réponse de la réponse (s'il y en a une)
     */
    private String replyId;

    /**
     * Contenu de la réponse
     */
    private String content;

    /**
     * Identifiant du publicateur
     */
    private String userName;

    /**
     * Date de création reply
     */
    private Timestamp createdAt;

}
