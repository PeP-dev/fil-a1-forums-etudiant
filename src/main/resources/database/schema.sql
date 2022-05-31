CREATE TABLE REDDIMT_User
(
    user_name varchar(128) PRIMARY KEY NOT NULL,
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
    id_group varchar(128) NOT NULL,
    user_name varchar(128) NOT NULL,
    id_role varchar(128) NOT NULL,
    CONSTRAINT PK_Role_Groupe PRIMARY KEY (id_group,user_name),
    FOREIGN KEY (id_group) REFERENCES REDDIMT_Group(id),
    FOREIGN KEY (user_name) REFERENCES REDDIMT_User(user_name),
    FOREIGN KEY (id_role) REFERENCES REDDIMT_Role(id)
);

CREATE TABLE REDDIMT_Category
(
    id varchar(128) PRIMARY KEY NOT NULL,
    id_group varchar(128),
    libelle varchar(255),
    description text,
    FOREIGN KEY (id_group) REFERENCES REDDIMT_Group(id)
);

CREATE TABLE REDDIMT_Post
(
    id varchar(128) PRIMARY KEY NOT NULL,
    id_group varchar(128) NOT NULL,
    title varchar(255),
    post_content text,
    id_category varchar(128) NOT NULL,
    user_name varchar(128) NOT NULL,
    FOREIGN KEY (user_name) REFERENCES REDDIMT_User(user_name),
    FOREIGN KEY (id_group) REFERENCES REDDIMT_Group(id),
    FOREIGN KEY (id_category) REFERENCES REDDIMT_Category(id)
);

CREATE TABLE REDDIMT_Reply
(
    id varchar(128) PRIMARY KEY NOT NULL,
    id_post varchar(128) NOT NULL,
    id_reply varchar(128),
    content text,
    FOREIGN KEY (id_post) REFERENCES REDDIMT_Post(id),
    FOREIGN KEY (id_reply) REFERENCES REDDIMT_Reply(id)
);

CREATE TABLE REDDIMT_Like_Post
(
    user_name varchar(128) NOT NULL,
    id_post varchar(128) NOT NULL,
    CONSTRAINT PK_Like_Post PRIMARY KEY (user_name,id_post),
    FOREIGN KEY (user_name) REFERENCES REDDIMT_User(user_name),
    FOREIGN KEY (id_post) REFERENCES REDDIMT_Post(id)
);

CREATE TABLE REDDIMT_Like_Reply
(
    user_name varchar(128) NOT NULL,
    id_reply varchar(128) NOT NULL,
    CONSTRAINT PK_Like_Reply PRIMARY KEY (user_name,id_reply),
    FOREIGN KEY (user_name) REFERENCES REDDIMT_User(user_name),
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
