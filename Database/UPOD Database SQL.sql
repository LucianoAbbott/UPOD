CREATE DATABASE UPOD;

CREATE TABLE PAGE(
PageId INT,
title VARCHAR(50) NOT NULL,
URL VARCHAR(100) NOT NULL,
editing BOOLEAN NOT NULL,
PRIMARY KEY (PageId)
);

CREATE TABLE EQUATION(
equationId INT,
equationURL VARCHAR(200),
PRIMARY KEY (equationId) );

CREATE TABLE VARIABLE (
varId INT,
symbol CHAR(3),
name VARCHAR(20),
category VARCHAR(20),
description VARCHAR(400),
PRIMARY KEY (varId) );

CREATE TABLE EQUVAR(
equVarId INT,
equationId INT,
varId INT,
PRIMARY KEY (equVarId),
FOREIGN KEY (equationId) REFERENCES Equation(equationId), 
FOREIGN KEY (varId) REFERENCES Variable(varId) );

CREATE TABLE GRAPHIC(
graphicId INT,
graphicURL VARCHAR (200),
description VARCHAR (144),
PRIMARY KEY(graphicId));

CREATE TABLE SECTION(
sectionId INT,
pageId INT,
sectionTitle VARCHAR(100) NOT NULL,
sectionText VARCHAR (4000),
equationId INT,
graphicId INT,
PRIMARY KEY(pageId,sectionId),
FOREIGN KEY(pageId) REFERENCES PAGE(pageId),
FOREIGN KEY(equationId) REFERENCES EQUATION(equationId),
FOREIGN KEY(graphicId) REFERENCES GRAPHIC(graphicId)
);