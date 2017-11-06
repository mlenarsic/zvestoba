INSERT INTO uporabnik (ime, priimek, uporabnisko_ime, email) VALUES ('Petra', 'Kos', 'petrakos', 'petra.kos@hotmail.com');
INSERT INTO uporabnik (ime, priimek, uporabnisko_ime, email) VALUES ('Miha', 'Novak', 'mihanovak', 'miha.novak@gmail.com');
INSERT INTO storitev (id, naziv, opis, ponudnikid, tocke) VALUES (1,'Nakup','Stranka je kupila posode naše znamke',1,10);
INSERT INTO storitev (id, naziv, opis, ponudnikid, tocke) VALUES (2,'Striženje','Stranka je s seboj pripeljala dve novi stranki',2,10);
INSERT INTO tocke (ponudnik_id, zbrane_tocke, id) VALUES (1,10,1);
INSERT INTO tocke (ponudnik_id, zbrane_tocke, id) VALUES (2,10,2);