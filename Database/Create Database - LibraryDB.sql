IF DB_ID('LibraryDB') IS NULL
BEGIN
	CREATE DATABASE [LibraryDB]
	 CONTAINMENT = NONE
	 ON  PRIMARY 
	( 
		NAME = N'LibraryDB', 
		FILENAME = N'C:\Databases\LibraryDB.mdf' , 
		SIZE = 8192KB , 
		FILEGROWTH = 65536KB 
	)
	 LOG ON 
	( 
		NAME = N'LibraryDB_log',
		FILENAME = N'C:\Databases\LibraryDB_log.ldf' ,
		SIZE = 8192KB , 
		MAXSIZE = 8GB ,
		FILEGROWTH = 65536KB 
	)
	 WITH CATALOG_COLLATION = DATABASE_DEFAULT
END
GO