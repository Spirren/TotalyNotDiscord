CREATE TABLE Chats(
    chatId INTEGER PRIMARY KEY,
    timeCreated TIMESTAMP NOT NULL
);

CREATE TABLE Users(
    userId INTEGER PRIMARY KEY,
    username TEXT NOT NULL,
    email TEXT NOT NULL,
    birthYear DATE NOT NULL,
    password INTEGER NOT NULL
);

CREATE TABLE ChatMembers(
    chatId INTEGER REFERENCES Chats(chatId) ON UPDATE CASCADE,
    userId INTEGER REFERENCES Users(userId) ON UPDATE CASCADE,
    PRIMARY KEY (chatId, userId)
);

CREATE TABLE Messages(
    chatId INTEGER NOT NULL,
    userId INTEGER NOT NULL,
    messageIndex INTEGER NOT NULL,
    content TEXT NOT NULL,
    timeSent TIMESTAMP NOT NULL,
    lastEdited TIMESTAMP,
    FOREIGN KEY (chatId, userId) REFERENCES ChatMembers(chatId, userId) ON UPDATE CASCADE,
    PRIMARY KEY (chatId, messageIndex)
);