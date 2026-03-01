CREATE VIEW fullchat AS SELECT chatId, timeSent, lastEdited, username FROM Messages LEFT JOIN Users ON (Messages.userId = Users.userId) 
ORDER BY messageIndex ASC;


-- CREATE VIEW messageswithnames AS SELECT m.chatId, m.messageIndex, timeSent, lastEdited, username, i.content as imageContent, t.content as textContent FROM Messages m 
-- LEFT JOIN Users ON (m.userId = Users.userId) 
-- LEFT JOIN ImageMessages i ON (m.chatId = i.chatId AND m.messageIndex = i.messageIndex)
-- LEFT JOIN TextMessages t ON (m.chatId = t.chatId AND m.messageIndex = t.messageIndex);


-- CREATE VIEW allMessages AS SELECT chatId, messageIndex, timeSent, lastEdited, username, i.content as imageContent, t.content as textContent FROM Messages m 
-- LEFT JOIN Users ON (m.userId = Users.userId) 
-- LEFT JOIN ImageMessages i ON (m.chatId = i.chatId AND m.messageIndex = i.messageIndex)
-- LEFT JOIN TextMessages t ON (m.chatId = t.chatId AND m.messageIndex = t.messageIndex)


CREATE VIEW ImageMessagesView AS SELECT m.chatId, m.messageIndex, timeSent, lastEdited, username FROM Messages m
LEFT JOIN Users ON (m.userId = Users.userId) 
INNER JOIN ImageMessages i ON (m.chatId = i.chatId AND m.messageIndex = i.messageIndex);



CREATE VIEW TextMessagesView AS SELECT m.chatId, m.messageIndex, timeSent, lastEdited, username FROM Messages m
LEFT JOIN Users ON (m.userId = Users.userId) 
INNER JOIN TextMessages t ON (m.chatId = t.chatId AND m.messageIndex = t.messageIndex);


CREATE VIEW allMessagesView AS SELECT chatId, messageIndex, timeSent, lastEdited, username, type FROM
(SELECT chatId, messageIndex, timeSent, lastEdited, username, 'imageMessage' as type from ImageMessagesView)
UNION 
(SELECT chatId, messageIndex, timeSent, lastEdited, username, 'textMessage' as type from TextMessagesView);