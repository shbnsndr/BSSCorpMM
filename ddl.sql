USE bss_pda;

INSERT INTO family(id, `version`, created_by, created_date, family_name)
VALUES(1, 0, 'shoban', CURRENT_DATE, 'Balaraman Family');

INSERT  INTO `user`(`id`,`version`,`created_by`,`created_date`,`dob`,`gender`,`login_id`,`name`
,`password`, family_id, is_admin) 
VALUES (1,0,'shoban','2017-01-10 21:45:38','1990-03-28 00:00:00','Male','shbnsndr','Shoban','123', 1, 'Y');

INSERT  INTO `user`(`id`,`version`,`created_by`,`created_date`,`dob`,`gender`,`login_id`,`name`
,`password`, family_id, is_admin) 
VALUES (2,0,'kalpana','2017-01-10 21:45:38','1990-03-28 00:00:00','Female','kalpana','Kalpana','123', 1, 'Y');


INSERT INTO `bss_pda`.`user_accounts` (`id`, `version`, `account_name`, `account_type`
, `current_balance`, `effective_from_date`, `remarks`, `user_id`) 
VALUES ('1', '0', 'Wallet', 'Personal Wallet', '1758', '2017-01-15 20:16:43', 'current balance', '1');

INSERT INTO `bss_pda`.`user_accounts` (`id`, `version`, `account_name`, `account_type`
, `current_balance`, `effective_from_date`, `remarks`, `user_id`) 
VALUES ('2', '0', 'ICICI', 'Bank', '17029', '2017-01-15 20:16:43', 'current balance', '1');

COMMIT;