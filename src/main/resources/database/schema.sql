CREATE TABLE REDDIMT_User
(
    id varchar(128) PRIMARY KEY NOT NULL,
    pseudo varchar(100) NOT NULL,
    password varchar(100) NOT NULL,
    email varchar(255) NOT NULL,
    avatar_url text,
    note_perso text,
    global_admin boolean NOT NULL
);

CREATE TABLE REDDIMT_Role
(
    id varchar(128) PRIMARY KEY NOT NULL,
    libelle varchar(100)
);

CREATE TABLE REDDIMT_School_Type
(
    id varchar(128) PRIMARY KEY NOT NULL,
    libelle varchar(255) NOT NULL
);

CREATE TABLE REDDIMT_School
(
    id varchar(128) PRIMARY KEY NOT NULL,
    libelle varchar(255) NOT NULL,
    school_type varchar(128),
    description text,
    FOREIGN KEY (school_type) REFERENCES REDDIMT_School_Type(id)
);

CREATE TABLE REDDIMT_Group
(
    id varchar(128) PRIMARY KEY NOT NULL,
    id_ecole varchar(128),
    libelle text NOT NULL,
    description text,
    FOREIGN KEY (id_ecole) REFERENCES REDDIMT_School(id)
);

CREATE TABLE REDDIMT_Role_groupe
(
    id_groupe varchar(128) NOT NULL,
    id_user varchar(128) NOT NULL,
    id_role varchar(128) NOT NULL,
    CONSTRAINT PK_Role_Groupe PRIMARY KEY (id_groupe,id_user),
    FOREIGN KEY (id_groupe) REFERENCES REDDIMT_Group(id),
    FOREIGN KEY (id_user) REFERENCES REDDIMT_User(id),
    FOREIGN KEY (id_role) REFERENCES REDDIMT_Role(id)
);

CREATE TABLE REDDIMT_Category
(
    id varchar(128) PRIMARY KEY NOT NULL,
    id_groupe varchar(128),
    libelle varchar(255),
    description text,
    FOREIGN KEY (id_groupe) REFERENCES REDDIMT_Group(id)
);

CREATE TABLE REDDIMT_Post
(
    id varchar(128) PRIMARY KEY NOT NULL,
    id_group varchar(128) NOT NULL,
    title varchar(255),
    post_content text,
    id_category varchar(128) NOT NULL,
    FOREIGN KEY (id_group) REFERENCES REDDIMT_Group(id),
    FOREIGN KEY (id_category) REFERENCES REDDIMT_Category(id)
);

CREATE TABLE REDDIMT_Reply
(
    id varchar(128) PRIMARY KEY NOT NULL,
    id_poste varchar(128) NOT NULL,
    id_reply varchar(128) NOT NULL,
    content text,
    FOREIGN KEY (id_poste) REFERENCES REDDIMT_Post(id),
    FOREIGN KEY (id_reply) REFERENCES REDDIMT_Reply(id)
);

CREATE TABLE REDDIMT_Like_Post
(
    id_user varchar(128) NOT NULL,
    id_post varchar(128) NOT NULL,
    CONSTRAINT PK_Like_Post PRIMARY KEY (id_user,id_post),
    FOREIGN KEY (id_user) REFERENCES REDDIMT_User(id),
    FOREIGN KEY (id_post) REFERENCES REDDIMT_Post(id)
);

CREATE TABLE REDDIMT_Like_Reply
(
    id_user varchar(128) NOT NULL,
    id_reply varchar(128) NOT NULL,
    CONSTRAINT PK_Like_Reply PRIMARY KEY (id_user,id_reply),
    FOREIGN KEY (id_user) REFERENCES REDDIMT_User(id),
    FOREIGN KEY (id_reply) REFERENCES REDDIMT_Reply(id)
);

CREATE TABLE REDDIMT_Cir_Category_Post
(
    id_group varchar(128) NOT NULL,
    id_post varchar(128) NOT NULL,
    CONSTRAINT PK_LCir_Category_Post PRIMARY KEY (id_group,id_post),
    FOREIGN KEY (id_group) REFERENCES REDDIMT_Group(id),
    FOREIGN KEY (id_post) REFERENCES REDDIMT_Post(id)
);
