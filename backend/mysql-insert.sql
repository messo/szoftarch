INSERT INTO `roles` (`id`,`roleType`,`description`) VALUES (1,'ADMIN','Adminisztr�tor');
INSERT INTO `roles` (`id`,`roleType`,`description`) VALUES (2,'LEADER','Projektvezet�');
INSERT INTO `roles` (`id`,`roleType`,`description`) VALUES (3,'PARTICIPANT','Projektr�sztvev�');

INSERT INTO `users` (`id`,`firstName`,`lastName`,`email`,`password`) VALUES (1,'Admin','Admin','admin@admin','21232f297a57a5a743894a0e4a801fc3');

INSERT INTO `user_has_role` (`id`,`user_id`,`role_id`) VALUES (1,1,1);
