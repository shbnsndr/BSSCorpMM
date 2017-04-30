USE bss_pda;

INSERT INTO family(id, `version`, created_by, created_date, family_name)
VALUES(1, 0, 'shoban', CURRENT_DATE, 'Balaraman Family');

INSERT  INTO `user`(`id`,`version`,`created_by`,`created_date`,`dob`,`gender`,`login_id`,`name`
,`password`, family_id, is_admin) 
VALUES (1,0,'shoban','2017-01-10 21:45:38','1990-03-28 00:00:00','Male','shbnsndr','Shoban','03Cgeo28++', 1, 'Y');

INSERT  INTO `user`(`id`,`version`,`created_by`,`created_date`,`dob`,`gender`,`login_id`,`name`
,`password`, family_id, is_admin) 
VALUES (2,0,'shoban','2017-01-10 21:45:38','1982-05-07 00:00:00','Female','rnkdv082','Renuka','bobbyandgirl', 1, 'N');

INSERT  INTO `user`(`id`,`version`,`created_by`,`created_date`,`dob`,`gender`,`login_id`,`name`
,`password`, family_id, is_admin) 
VALUES (3,0,'shoban','2017-01-10 21:45:38','1984-08-12 00:00:00','Female','kalpana','Kalpana','pracharak', 1, 'N');

INSERT  INTO `user`(`id`,`version`,`created_by`,`created_date`,`dob`,`gender`,`login_id`,`name`
,`password`, family_id, is_admin) 
VALUES (4,0,'shoban','2017-01-10 21:45:38','1990-12-13 00:00:00','Female','Priya','priya','priya', 1, 'N');

INSERT INTO `bss_pda`.`user_accounts` (`id`, `version`, `account_name`, `account_type`
, `current_balance`, `effective_from_date`, `remarks`, `user_id`) 
VALUES ('1', '0', 'Wallet', 'Personal Wallet', '1758', '2017-01-15 20:16:43', 'current balance', '1');

INSERT INTO `bss_pda`.`user_accounts` (`id`, `version`, `account_name`, `account_type`
, `current_balance`, `effective_from_date`, `remarks`, `user_id`) 
VALUES ('2', '0', 'ICICI', 'Bank', '17029', '2017-01-15 20:16:43', 'current balance', '1');

COMMIT;


insert into `currency_units` (`id`, `version`, `currency_desc`, `currency_unit`) values('1','1','US Dollars','USD');
insert into `currency_units` (`id`, `version`, `currency_desc`, `currency_unit`) values('2','1','Indian Rupees','INR');
insert into `currency_units` (`id`, `version`, `currency_desc`, `currency_unit`) values('3','1','Singapore/Brunei Dollars','SGD');
insert into `currency_units` (`id`, `version`, `currency_desc`, `currency_unit`) values('4','1','Australian Dollars','AUD');
insert into `currency_units` (`id`, `version`, `currency_desc`, `currency_unit`) values('5','1','British Pounds','GBP');
insert into `currency_units` (`id`, `version`, `currency_desc`, `currency_unit`) values('6','1','Euros','EUR');
insert into `currency_units` (`id`, `version`, `currency_desc`, `currency_unit`) values('7','1','Malaysian Ringgit','MYR');


insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('1','1',NULL,'USD','100');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('2','1','Old 100 dollar notes','USD','100');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('3','1',NULL,'USD','20');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('4','1',NULL,'USD','10');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('5','1',NULL,'USD','5');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('6','1',NULL,'USD','1');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('7','1',NULL,'INR','2000');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('8','1',NULL,'INR','500');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('9','1',NULL,'INR','100');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('10','1',NULL,'INR','50');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('11','1',NULL,'INR','20');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('12','1',NULL,'INR','10');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('13','1',NULL,'INR','5');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('14','1',NULL,'INR','2');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('15','1',NULL,'INR','1');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('16','1',NULL,'SGD','100');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('17','1',NULL,'SGD','50');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('18','1',NULL,'SGD','20');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('19','1',NULL,'SGD','10');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('20','1',NULL,'SGD','5');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('21','1',NULL,'SGD','1');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('22','1',NULL,'AUD','100');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('23','1',NULL,'AUD','50');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('24','1',NULL,'AUD','20');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('25','1',NULL,'AUD','10');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('26','1',NULL,'AUD','5');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('27','1',NULL,'AUD','1');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('28','1',NULL,'GBP','50');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('29','1',NULL,'GBP','20');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('30','1',NULL,'GBP','10');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('31','1',NULL,'GBP','5');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('32','1',NULL,'GBP','2');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('33','1',NULL,'GBP','1');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('34','1',NULL,'EUR','500');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('35','1',NULL,'EUR','200');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('36','1',NULL,'EUR','100');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('37','1',NULL,'EUR','50');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('38','1',NULL,'EUR','20');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('39','1',NULL,'EUR','10');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('40','1',NULL,'EUR','5');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('41','1',NULL,'EUR','1');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('42','1',NULL,'MYR','100');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('43','1',NULL,'MYR','50');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('44','1',NULL,'MYR','20');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('45','1',NULL,'MYR','10');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('46','1',NULL,'MYR','5');
insert into `currency_denominations` (`id`, `version`, `comments`, `currency_unit`, `denomination_unit`) values('47','1',NULL,'MYR','1');



insert into `forex_exchange_rates` (`id`, `version`, `date`, `from_currency`, `rate_per_unit`, `to_currency`) values('1','0','2017-04-29 00:00:00','USD','64.246','INR');
insert into `forex_exchange_rates` (`id`, `version`, `date`, `from_currency`, `rate_per_unit`, `to_currency`) values('2','0','2017-04-30 00:00:00','USD','64.261','INR');
insert into `forex_exchange_rates` (`id`, `version`, `date`, `from_currency`, `rate_per_unit`, `to_currency`) values('3','0','2017-04-26 00:00:00','USD','64.06','INR');
insert into `forex_exchange_rates` (`id`, `version`, `date`, `from_currency`, `rate_per_unit`, `to_currency`) values('4','0','2017-04-27 00:00:00','USD','64.1','INR');
insert into `forex_exchange_rates` (`id`, `version`, `date`, `from_currency`, `rate_per_unit`, `to_currency`) values('5','0','2016-11-23 00:00:00','USD','68.630531','INR');
insert into `forex_exchange_rates` (`id`, `version`, `date`, `from_currency`, `rate_per_unit`, `to_currency`) values('6','0','2017-04-28 00:00:00','USD','64.246','INR');
insert into `forex_exchange_rates` (`id`, `version`, `date`, `from_currency`, `rate_per_unit`, `to_currency`) values('10','0','2017-04-28 00:00:00','INR','0.01557','USD');
insert into `forex_exchange_rates` (`id`, `version`, `date`, `from_currency`, `rate_per_unit`, `to_currency`) values('11','0','2017-04-28 00:00:00','USD','1.3975','SGD');
insert into `forex_exchange_rates` (`id`, `version`, `date`, `from_currency`, `rate_per_unit`, `to_currency`) values('12','0','2017-04-28 00:00:00','SGD','45.97209','INR');
insert into `forex_exchange_rates` (`id`, `version`, `date`, `from_currency`, `rate_per_unit`, `to_currency`) values('13','0','2017-04-28 00:00:00','INR','0.02175','SGD');
insert into `forex_exchange_rates` (`id`, `version`, `date`, `from_currency`, `rate_per_unit`, `to_currency`) values('14','0','2017-04-28 00:00:00','USD','1.33547','AUD');
insert into `forex_exchange_rates` (`id`, `version`, `date`, `from_currency`, `rate_per_unit`, `to_currency`) values('15','0','2017-04-28 00:00:00','AUD','48.10741','INR');
insert into `forex_exchange_rates` (`id`, `version`, `date`, `from_currency`, `rate_per_unit`, `to_currency`) values('16','0','2017-04-28 00:00:00','GBP','83.20792','INR');
insert into `forex_exchange_rates` (`id`, `version`, `date`, `from_currency`, `rate_per_unit`, `to_currency`) values('17','0','2017-04-28 00:00:00','GBP','1.29515','USD');
insert into `forex_exchange_rates` (`id`, `version`, `date`, `from_currency`, `rate_per_unit`, `to_currency`) values('18','0','2017-04-28 00:00:00','EUR','1.0895','USD');
insert into `forex_exchange_rates` (`id`, `version`, `date`, `from_currency`, `rate_per_unit`, `to_currency`) values('19','0','2017-04-28 00:00:00','EUR','69.99603','INR');
insert into `forex_exchange_rates` (`id`, `version`, `date`, `from_currency`, `rate_per_unit`, `to_currency`) values('20','0','2017-04-24 00:00:00','USD','64.4335','INR');
insert into `forex_exchange_rates` (`id`, `version`, `date`, `from_currency`, `rate_per_unit`, `to_currency`) values('21','0','2017-04-22 00:00:00','USD','64.6505','INR');
insert into `forex_exchange_rates` (`id`, `version`, `date`, `from_currency`, `rate_per_unit`, `to_currency`) values('22','0','2017-04-22 00:00:00','USD','1.32556','AUD');
insert into `forex_exchange_rates` (`id`, `version`, `date`, `from_currency`, `rate_per_unit`, `to_currency`) values('23','0','2017-04-22 00:00:00','AUD','48.77222','INR');
insert into `forex_exchange_rates` (`id`, `version`, `date`, `from_currency`, `rate_per_unit`, `to_currency`) values('24','0','2017-04-22 00:00:00','INR','0.0205','AUD');
insert into `forex_exchange_rates` (`id`, `version`, `date`, `from_currency`, `rate_per_unit`, `to_currency`) values('25','0','2017-04-22 00:00:00','GBP','82.79768','INR');
insert into `forex_exchange_rates` (`id`, `version`, `date`, `from_currency`, `rate_per_unit`, `to_currency`) values('26','0','2017-04-22 00:00:00','INR','0.01208','GBP');
insert into `forex_exchange_rates` (`id`, `version`, `date`, `from_currency`, `rate_per_unit`, `to_currency`) values('27','0','2017-04-22 00:00:00','USD','1.3967','SGD');
insert into `forex_exchange_rates` (`id`, `version`, `date`, `from_currency`, `rate_per_unit`, `to_currency`) values('28','0','2017-04-21 00:00:00','USD','64.6505','INR');
insert into `forex_exchange_rates` (`id`, `version`, `date`, `from_currency`, `rate_per_unit`, `to_currency`) values('29','0','2017-04-20 00:00:00','USD','64.7','INR');
insert into `forex_exchange_rates` (`id`, `version`, `date`, `from_currency`, `rate_per_unit`, `to_currency`) values('30','0','2017-03-20 00:00:00','USD','65.3454','INR');
insert into `forex_exchange_rates` (`id`, `version`, `date`, `from_currency`, `rate_per_unit`, `to_currency`) values('31','0','2017-02-21 00:00:00','USD','66.9753','INR');
insert into `forex_exchange_rates` (`id`, `version`, `date`, `from_currency`, `rate_per_unit`, `to_currency`) values('32','0','2017-01-21 00:00:00','USD','68.09215','INR');

