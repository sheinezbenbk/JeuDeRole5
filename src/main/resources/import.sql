insert into qualite (bonus_qualite, couleur, nom) values (0,'grey','commun'),(1,'#00FFFFFF','rare'),(2,'#FF69B4FF','epique'),(3,'#FFA500FF','légendaire');
insert into type_arme (nombre_Des,valeur_De_Max,multiplicateur_critique,activation_critique,nom) values (1,8,3,20,'claquette');
insert into type_armure (bonus_type, nom) values ('2','leger'),('2','cuir'),('5', 'cote De Maille'),('36', 'Berbèrie');
-- insert into type_accessoire (nom, typebonus) values ('commun','grey'),('rare','#00FFFFFF',),('epique','#FF69B4FF'),('légendaire','#FFA500FF');
insert into role (nom) values ('admin');
insert into role (nom) values ('joueur');
insert into utilisateur ( email, mdp) values ('admin@email.com','$2a$10$MJn5SDUCgsm3XEyvELGQI.lcCzCXtxhA8hunr9jX9yvDd6/FkjxYO');
insert into utilisateur_role (role_id, utilisateur_id) VALUES (1,1), (2,1);