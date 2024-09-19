-- Fix duplicate key error
alter table users_navigator_settings
drop key userid;

alter table users_navigator_settings
    add constraint users_navigator_settings_pk
        unique (user_id, caption);
