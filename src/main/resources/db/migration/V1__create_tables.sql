CREATE TABLE FILE (
    ID INT NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(255) NOT NULL,
    PATH VARCHAR(1023) NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE FILE_AGGREGATION_STATISTIC (
    ID INT NOT NULL AUTO_INCREMENT,
    FILE_ID INT NOT NULL,
    LONGEST_WORD INT NOT NULL,
    SHORTEST_WORD INT NOT NULL,
    LINES_LENGTH INT NOT NULL,
    AVERAGE_WORD_LENGTH DOUBLE NOT NULL,
    PRIMARY KEY (ID),
    CONSTRAINT FK_FILE_AGGREGATION_STATISTIC_FILE FOREIGN KEY (FILE_ID)
      REFERENCES FILE(ID) ON DELETE CASCADE
);

CREATE TABLE FILE_LINE_STATISTIC (
    ID INT NOT NULL AUTO_INCREMENT,
    FILE_ID INT NOT NULL,
    LINE_NUMBER INT NOT NULL,
    LONGEST_WORD INT NOT NULL,
    SHORTEST_WORD INT NOT NULL,
    LINE_LENGTH INT NOT NULL,
    AVERAGE_WORD_LENGTH DOUBLE NOT NULL,
    PRIMARY KEY (ID),
    CONSTRAINT FK_FILE_LINE_STATISTIC_FILE FOREIGN KEY (FILE_ID)
      REFERENCES FILE(ID) ON DELETE CASCADE
);