CREATE VIEW fullchat AS SELECT chatId, content, timeSent, lastEdited, username FROM Messages LEFT JOIN Users ON (Messages.userId = Users.userId) 
ORDER BY messageIndex ASC;
