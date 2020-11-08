USE [LibraryDB]
GO

IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[ISSUE]') AND type in (N'U'))
	DROP TABLE [ISSUE]
GO
IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[ACCOUNTS]') AND type in (N'U'))
	DROP TABLE [ACCOUNTS]
GO
IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[BOOKS]') AND type in (N'U'))
	DROP TABLE [BOOKS]
GO
IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[ROLE]') AND type in (N'U'))
	DROP TABLE [ROLE]
GO
IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[RATING]') AND type in (N'U'))
	DROP TABLE [RATING]
GO
IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[CONDITION]') AND type in (N'U'))
	DROP TABLE [CONDITION]
GO


CREATE TABLE [ROLE](
[ID] INT IDENTITY(1,1) NOT NULL,
[ROLE] NVARCHAR(32) NOT NULL,
CONSTRAINT PK_ROLE_ID PRIMARY KEY (ID)
)

CREATE TABLE [RATING](
[ID] INT IDENTITY(1,1) NOT NULL,
[RATING] INT NOT NULL,
CONSTRAINT PK_RATING_ID PRIMARY KEY (ID)
)

CREATE TABLE [CONDITION](
[ID] INT IDENTITY(1,1) NOT NULL,
[CONDITION] NVARCHAR(32) NOT NULL,
CONSTRAINT PK_CONDITION_ID PRIMARY KEY (ID)
)

CREATE TABLE [ACCOUNTS](
[ID] INT IDENTITY(1,1) NOT NULL,
[FIRSTNAME] NVARCHAR(32) NOT NULL,
[LASTNAME] NVARCHAR(32) NOT NULL,
[UCN] NVARCHAR(16) NOT NULL,
[PHONENUMBER] NVARCHAR(16) NOT NULL,
[EMAIL] NVARCHAR(32) NOT NULL,
[ADDRESS] NVARCHAR(512) NOT NULL,
[USERNAME] NVARCHAR(32) NOT NULL,
[PASSWORD] NVARCHAR(32) NOT NULL,
[ROLE_ID] INT NOT NULL,
[RATING_ID] INT NOT NULL,
[APPROVED] BIT NOT NULL,
CONSTRAINT PK_ACCOUNTS_ID PRIMARY KEY (ID),
CONSTRAINT UX_ACCOUNTS_USERNAME UNIQUE (USERNAME),
CONSTRAINT UX_ACCOUNTS_UCN UNIQUE (UCN),
CONSTRAINT FK_ACCOUNTS_ROLE_ID FOREIGN KEY (ROLE_ID) REFERENCES ROLE(ID),
CONSTRAINT FK_ACCOUNTS_RATING_ID FOREIGN KEY (RATING_ID) REFERENCES RATING(ID)
)

CREATE TABLE [BOOKS](
[ID] INT IDENTITY(1,1) NOT NULL,
[TITLE] NVARCHAR(64) NOT NULL,
[AUTHOR] NVARCHAR(32) NOT NULL,
[GENRE] NVARCHAR(32) NOT NULL,
[CONDITION_ID] INT NOT NULL,
[AVAILABLE] BIT NOT NULL,
CONSTRAINT PK_BOOKS_ID PRIMARY KEY (ID),
CONSTRAINT FK_BOOKS_CONDITION_ID FOREIGN KEY (CONDITION_ID) REFERENCES CONDITION(ID)
)

CREATE TABLE [ISSUE](
[ID] INT IDENTITY(1,1) NOT NULL,
[BOOK_ID] INT NOT NULL,
[ACCOUNT_ID] INT NOT NULL,
[ISSUE_DATE] DATE NOT NULL,
[RETURN_DATE] DATE NOT NULL,
[RETURNED_DATE] DATE,
[RETURNED_CONDITION_ID] INT,
[APPROVED] BIT NOT NULL,
CONSTRAINT PK_ISSUE_ID PRIMARY KEY (ID),
CONSTRAINT FK_ISSUE_BOOK_ID FOREIGN KEY (BOOK_ID) REFERENCES BOOKS(ID),
CONSTRAINT FK_ISSUE_ACCOUNTS_ID FOREIGN KEY (ACCOUNT_ID) REFERENCES ACCOUNTS(ID),
CONSTRAINT FK_ISSUE_RETURNED_CONDITION FOREIGN KEY (RETURNED_CONDITION_ID) REFERENCES CONDITION(ID)
)