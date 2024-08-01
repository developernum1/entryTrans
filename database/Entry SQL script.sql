DROP DATABASE IF EXISTS fyg;
CREATE DATABASE fyg;

DROP USER IF EXISTS 'entrydemo'@'%';
CREATE USER 'entrydemo'@'%' IDENTIFIED WITH mysql_native_password BY 'password';
GRANT ALL PRIVILEGES ON fyg.* TO 'entrydemo'@'%';
FLUSH PRIVILEGES;

USE fyg;

CREATE TABLE `T_EntryTransaction` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `taccId` bigint DEFAULT NULL,
  `entryId` bigint NOT NULL,
  `type` varchar(30) NOT NULL,
  `amount` decimal(20,2) NOT NULL,
  `transactionDate` date NOT NULL,
  `fundId` varchar(32) NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3;

CREATE TABLE `TAccount` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `accountID` bigint NOT NULL,
  `pid` bigint DEFAULT NULL,
  `code` varchar(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `accountClass` varchar(1) DEFAULT NULL,
  `accountType` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_taccount_pid` FOREIGN KEY (`pid`) REFERENCES `TAccount` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3 STATS_SAMPLE_PAGES=256;

CREATE TABLE `TAccountTags` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `color` varchar(20) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `year` smallint DEFAULT NULL,
  `isSystemLabel` bit(1) DEFAULT NULL,
  `code` varchar(100) DEFAULT NULL,
  `item_order` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3;

CREATE TABLE `TAccountTag` (
  `tagID` bigint NOT NULL,
  `taccountID` bigint NOT NULL,
  PRIMARY KEY (`taccountID`,`tagID`),
  CONSTRAINT `FK_taccountTag_taccount` FOREIGN KEY (`taccountID`) REFERENCES `TAccount` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_taccountTag_taccountTags` FOREIGN KEY (`tagID`) REFERENCES `TAccountTags` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 STATS_SAMPLE_PAGES=128;

-- Add index to T_EntryTransaction table to be a foreign key.
ALTER TABLE `T_EntryTransaction`
ADD UNIQUE INDEX `idx_entryId` (`entryId`);

CREATE TABLE `BasicBankEntry` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `entryId` bigint NOT NULL,
  `bankName` varchar(255) NOT NULL,
  `accountNumber` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_BasicBankEntry_entryId` FOREIGN KEY (`entryId`) REFERENCES `T_EntryTransaction` (`entryId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `DistributionInterest` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `entryId` bigint NOT NULL,
  `interestRate` decimal(5,2) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_DistributionInterest_entryId` FOREIGN KEY (`entryId`) REFERENCES `T_EntryTransaction` (`entryId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `Dividend` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `entryId` bigint NOT NULL,
  `companyName` varchar(255) NOT NULL,
  `dividendAmount` decimal(20,2) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_Dividend_entryId` FOREIGN KEY (`entryId`) REFERENCES `T_EntryTransaction` (`entryId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `Contribution` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `entryId` bigint NOT NULL,
  `contributorName` varchar(255) NOT NULL,
  `contributionAmount` decimal(20,2) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_Contribution_entryId` FOREIGN KEY (`entryId`) REFERENCES `T_EntryTransaction` (`entryId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `Investment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `entryId` bigint NOT NULL,
  `investmentType` varchar(255) NOT NULL,
  `investmentAmount` decimal(20,2) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_Investment_entryId` FOREIGN KEY (`entryId`) REFERENCES `T_EntryTransaction` (`entryId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Insert records into T_EntryTransaction table
INSERT INTO `T_EntryTransaction` (`taccId`, `entryId`, `type`, `amount`, `transactionDate`, `fundId`)
VALUES 
(1, 101, 'BasicBankEntry', 1000.00, '2024-07-01', 'FUND001'),
(2, 102, 'DistributionInterest', 1500.50, '2024-07-02', 'FUND002'),
(3, 103, 'Dividend', 2000.75, '2024-07-03', 'FUND003'),
(4, 104, 'Contribution', 2500.25, '2024-07-04', 'FUND004'),
(5, 105, 'Investment', 3000.00, '2024-07-05', 'FUND005');
