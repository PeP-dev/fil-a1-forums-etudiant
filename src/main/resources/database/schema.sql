CREATE TABLE REDDIMT_User
(
    id int PRIMARY KEY NOT NULL,
    pseudo varchar(100) NOT NULL,
    password varchar(100) NOT NULL,
    email varchar(255) NOT NULL,
    avatar_url text,
    note_perso text,
    global_admin boolean NOT NULL
);

CREATE TABLE REDDIMT_Role
(
    id int PRIMARY KEY NOT NULL,
    libelle varchar(100)
);

CREATE TABLE REDDIMT_School_Type
(
    id int PRIMARY KEY NOT NULL,
    libelle varchar(255) NOT NULL
);

CREATE TABLE REDDIMT_School
(
    id int PRIMARY KEY NOT NULL,
    libelle varchar(255) NOT NULL,
    school_type int,
    description text,
    FOREIGN KEY (school_type) REFERENCES REDDIMT_School_Type(id)
);

CREATE TABLE REDDIMT_Group
(
    id int PRIMARY KEY NOT NULL,
    id_ecole int,
    libelle text NOT NULL,
    description text,
    FOREIGN KEY (id_ecole) REFERENCES REDDIMT_School(id)
);

CREATE TABLE REDDIMT_Role_groupe
(
    id_groupe int NOT NULL,
    id_user int NOT NULL,
    id_role int NOT NULL,
    CONSTRAINT PK_Role_Groupe PRIMARY KEY (id_groupe,id_user),
    FOREIGN KEY (id_groupe) REFERENCES REDDIMT_Group(id),
    FOREIGN KEY (id_user) REFERENCES REDDIMT_User(id),
    FOREIGN KEY (id_role) REFERENCES REDDIMT_Role(id)
);

CREATE TABLE REDDIMT_Category
(
    id int PRIMARY KEY NOT NULL,
    id_groupe int,
    libelle varchar(255),
    description text,
    FOREIGN KEY (id_groupe) REFERENCES REDDIMT_Group(id)
);

CREATE TABLE REDDIMT_Post
(
    id int PRIMARY KEY NOT NULL,
    id_group int NOT NULL,
    libelle varchar(255),
    description text,
    id_category int NOT NULL,
    FOREIGN KEY (id_group) REFERENCES REDDIMT_Group(id),
    FOREIGN KEY (id_category) REFERENCES REDDIMT_Category(id)
);

CREATE TABLE REDDIMT_Reply
(
    id int PRIMARY KEY NOT NULL,
    id_poste int NOT NULL,
    id_reply int NOT NULL,
    content text,
    FOREIGN KEY (id_poste) REFERENCES REDDIMT_Post(id),
    FOREIGN KEY (id_reply) REFERENCES REDDIMT_Reply(id)
);

CREATE TABLE REDDIMT_Like_Post
(
    id_user int NOT NULL,
    id_post int NOT NULL,
    CONSTRAINT PK_Like_Post PRIMARY KEY (id_user,id_post),
    FOREIGN KEY (id_user) REFERENCES REDDIMT_User(id),
    FOREIGN KEY (id_post) REFERENCES REDDIMT_Post(id)
);

CREATE TABLE REDDIMT_Like_Reply
(
    id_user int NOT NULL,
    id_reply int NOT NULL,
    CONSTRAINT PK_Like_Reply PRIMARY KEY (id_user,id_reply),
    FOREIGN KEY (id_user) REFERENCES REDDIMT_User(id),
    FOREIGN KEY (id_reply) REFERENCES REDDIMT_Reply(id)
);

CREATE TABLE REDDIMT_Cir_Category_Post
(
    id_group int NOT NULL,
    id_post int NOT NULL,
    CONSTRAINT PK_LCir_Category_Post PRIMARY KEY (id_group,id_post),
    FOREIGN KEY (id_group) REFERENCES REDDIMT_Group(id),
    FOREIGN KEY (id_post) REFERENCES REDDIMT_Post(id)
);
