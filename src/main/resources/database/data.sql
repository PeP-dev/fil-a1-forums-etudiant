INSERT INTO reddimt_user (user_name, pseudo, password, email, avatar_url, note_perso, global_admin)
    VALUES 
    ('patrick', 'patpat', 'bonjour', 'grigeboroilla-4269@yopmail.com', 'ceciestuneurl.com','ceci est une note perso', FALSE),
    ('gisele', 'gigi', 'hello', 'quicrouhecazi-4471@yopmail.com', 'ceciestpasuneurl.com','ceci est pas note perso', FALSE);
    

INSERT INTO reddimt_role (id, libelle)
    VALUES 
    ('1', 'eleve'),
    ('2', 'administrateur');

INSERT INTO reddimt_school_type (id, libelle)
    VALUES 
    ('1', 'Ecole de commerce'),
    ('2', 'Ecole ingenieur');

INSERT INTO reddimt_school (id, libelle, school_type, description)
    VALUES 
    ('1','IMT', '2', 'Ceci est une école ingénieur classé 5ème'),
    ('2', 'HEC', '1', 'Ceci est une école de commerce mais aucune idée du classement');

INSERT INTO reddimt_group (id, id_ecole, libelle, description)
    VALUES 
    ('1', '1', 'FIL', 'groupe FIL de école IMT'),
    ('2', '1', 'FISE', 'groupe FISE de école IMT'),
    ('3', '2', 'Sport', 'groupe Sport de école HEC');


INSERT INTO reddimt_role_groupe (id_group, user_name, id_role)
    VALUES 
    ('1', 'gisele', '2'),
    ('2', 'patrick', '1'),
    ('1', 'patrick', '1');


INSERT INTO reddimt_category (id, id_group, libelle, description)
    VALUES 
    ('1', '1', 'Administration', 'ceci est la catégorie formation'),
    ('2', '1', 'Informations Generale', 'ceci est la catégorie info g'),
    ('3', '1', 'Autres', 'ceci est la catégorie autres'),
    ('4', '2', 'Autres', 'ceci est la catégorie autres'),
    ('5', '3', 'Sport', 'ceci est la catégorie Sport');


INSERT INTO reddimt_post (id, id_group, title, post_content, id_category, user_name)
    VALUES 
    ('1', '1', 'Ça veut dire quoi FIL ?', 'Je me demande ce que signifie FIL', '2', 'gisele'),
    ('2', '3', 'Quels sont les sports dispos ?', 'Je suis sportif et je veux savoir quels sont les sports cools présents.', '3', 'patrick');

INSERT INTO reddimt_reply (id, id_post, id_reply, content)
    VALUES 
    ('1', '1', null, 'FIL signifie Filière Ingienerie Logicielle'),
    ('2', '2', null,'Il y a bcp de sports co et solo dispos !'),
    ('3', '1', '1', 'Est-ce que ce ne serait pas formation plutôt ?');


INSERT INTO reddimt_like_post (user_name, id_post)
    VALUES 
    ('valeur 1', 'valeur 2'),
    ('valeur 1', 'valeur 2');


INSERT INTO reddimt_like_reply (user_name, id_reply)
    VALUES 
    ('valeur 1', 'valeur 2'),
    ('valeur 1', 'valeur 2');


INSERT INTO reddimt_cir_category_post (id_group, id_post)
    VALUES 
    ('valeur 1', 'valeur 2'),
    ('valeur 1', 'valeur 2');




INSERT INTO reddimt_post (id, id_group, title, post_content, id_category, user_name)
    VALUES 
    ('3', '1', 'Comment se passe inscription ?', 'Je me demande quel est le process inscription', '1', 'gisele');






