-- Fix duplicate key error
alter table users_navigator_settings
drop key userid;

alter table users_navigator_settings
    add constraint users_navigator_settings_pk
        unique (user_id, caption);

-- Rename happyness to happiness
UPDATE `emulator_texts` SET `key` = 'generic.pet.happiness', `value` = 'Happiness' WHERE `key` = 'generic.pet.happyness';
ALTER TABLE `pet_commands_data` CHANGE `cost_happyness` `cost_happiness` int(11) NOT NULL DEFAULT '0';
ALTER TABLE `users_pets` CHANGE `happyness` `happiness` int(11) NOT NULL DEFAULT '100';

INSERT INTO `emulator_texts` (`key`, `value`) VALUES ('crackables.reward_received.message', '${wiredfurni.rewardsuccess.body}');