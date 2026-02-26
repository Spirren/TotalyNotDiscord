INSERT INTO Chats VALUES (0,'2026-02-14 19:45:00.523');
INSERT INTO Chats VALUES (1,'2026-02-11');
INSERT INTO Chats VALUES (2,'2026-02-12');

INSERT INTO Users VALUES (3,'foo','foo@gmail.com','2026-02-13',132433);
INSERT INTO Users VALUES (4,'bar','bar@gmail.com','2026-02-14',132434);
INSERT INTO Users VALUES (5,'foobar','foobar@gmail.com','2026-02-15',132435);

INSERT INTO ChatMembers VALUES (0, 3);
INSERT INTO ChatMembers VALUES (0, 4);
INSERT INTO ChatMembers VALUES (0, 5);
INSERT INTO ChatMembers VALUES (1, 3);
INSERT INTO ChatMembers VALUES (1, 4);
INSERT INTO ChatMembers VALUES (2, 4);
INSERT INTO ChatMembers VALUES (2, 5);





INSERT INTO Messages VALUES (0, 3, 0, '2026-02-14 19:45:00.523', '2026-02-14 19:45:00.523');
INSERT INTO Messages VALUES (0, 3, 1, '2026-02-14 19:45:00.523');
INSERT INTO Messages VALUES (0, 4, 2, '2026-02-14 19:45:00.523');
INSERT INTO Messages VALUES (0, 5, 3, '2026-02-14 19:45:00.523');
INSERT INTO Messages VALUES (1, 3, 0, '2026-02-14 19:45:00.523');
INSERT INTO Messages VALUES (1, 3, 1, '2026-02-14 19:45:00.523');
INSERT INTO Messages VALUES (2, 4, 0, '2026-02-14 19:45:00.523');
INSERT INTO Messages VALUES (2, 5, 1, '2026-02-14 19:45:00.523');


INSERT INTO TextMessages VALUES (0, 0, 'dummy text 0, 1');
INSERT INTO TextMessages VALUES (0, 1, 'dummy text 0, 2');
INSERT INTO TextMessages VALUES (0, 2, 'dummy text 0, 3');
INSERT INTO TextMessages VALUES (1, 0, 'dummy text 1, 1');
INSERT INTO TextMessages VALUES (1, 1, 'dummy text 1, 2');
INSERT INTO TextMessages VALUES (2, 0, 'dummy text 2, 1');
INSERT INTO TextMessages VALUES (2, 1, 'dummy text 2, 2');



INSERT INTO ImageMessages VALUES (0, 3, decode(repeat('0123456789abcdef', 1), 'hex'));