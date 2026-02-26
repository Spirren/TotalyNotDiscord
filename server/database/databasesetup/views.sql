CREATE VIEW fullchat AS SELECT chatId, timeSent, lastEdited, username FROM Messages LEFT JOIN Users ON (Messages.userId = Users.userId) 
ORDER BY messageIndex ASC;


CREATE VIEW messageswithnames AS SELECT m.chatId, m.messageIndex, timeSent, lastEdited, username, i.content as imageContent, t.content as textContent FROM Messages m 
LEFT JOIN Users ON (m.userId = Users.userId) 
LEFT JOIN ImageMessages i ON (m.chatId = i.chatId AND m.messageIndex = i.messageIndex)
LEFT JOIN TextMessages t ON (m.chatId = t.chatId AND m.messageIndex = t.messageIndex)
-- ORDER BY messageIndex ASC;
