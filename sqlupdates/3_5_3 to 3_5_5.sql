-- Fix duplicate key error
alter table users_navigator_settings
drop key userid;

alter table users_navigator_settings
    add constraint users_navigator_settings_pk
        unique (user_id, caption);

ALTER TABLE `items` ADD COLUMN `wallitem_offset` int NOT NULL DEFAULT '0';

UPDATE `items`
SET
    `x` = CAST(SUBSTRING_INDEX(SUBSTRING_INDEX(SUBSTRING_INDEX(wall_pos, ' ', 1), '=', -1), ',', 1) AS UNSIGNED),
    `y` = CAST(SUBSTRING_INDEX(SUBSTRING_INDEX(SUBSTRING_INDEX(wall_pos, ' ', 1), ',', -1), '=', -1) AS UNSIGNED),
    `wallitem_offset` = CAST(SUBSTRING_INDEX(SUBSTRING_INDEX(SUBSTRING_INDEX(wall_pos, ' ', 2), 'l=', -1), ',', 1) AS UNSIGNED),
    `rot` = CASE
                WHEN SUBSTRING_INDEX(wall_pos, ' ', -1) = 'l' THEN 0
                WHEN SUBSTRING_INDEX(wall_pos, ' ', -1) = 'r' THEN 1
        END,
    `z` = CAST(SUBSTRING_INDEX(SUBSTRING_INDEX(SUBSTRING_INDEX(wall_pos, ' ', 2), ',', -1), ' ', 1) AS UNSIGNED)
WHERE
    wall_pos LIKE ':w=%'
  AND wall_pos LIKE '% l=%'
  AND (wall_pos LIKE '% l' OR wall_pos LIKE '% r');