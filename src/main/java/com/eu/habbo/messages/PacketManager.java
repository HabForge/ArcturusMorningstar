package com.eu.habbo.messages;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.gameclients.GameClient;
import com.eu.habbo.messages.incoming.Incoming;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.incoming.achievements.RequestAchievementsEvent;
import com.eu.habbo.messages.incoming.ambassadors.AmbassadorAlertCommandEvent;
import com.eu.habbo.messages.incoming.ambassadors.AmbassadorVisitCommandEvent;
import com.eu.habbo.messages.incoming.camera.*;
import com.eu.habbo.messages.incoming.catalog.*;
import com.eu.habbo.messages.incoming.catalog.marketplace.*;
import com.eu.habbo.messages.incoming.catalog.recycler.OpenRecycleBoxEvent;
import com.eu.habbo.messages.incoming.catalog.recycler.RecycleEvent;
import com.eu.habbo.messages.incoming.catalog.recycler.ReloadRecyclerEvent;
import com.eu.habbo.messages.incoming.catalog.recycler.RequestRecyclerLogicEvent;
import com.eu.habbo.messages.incoming.crafting.*;
import com.eu.habbo.messages.incoming.events.calendar.AdventCalendarForceOpenEvent;
import com.eu.habbo.messages.incoming.events.calendar.AdventCalendarOpenDayEvent;
import com.eu.habbo.messages.incoming.floorplaneditor.FloorPlanEditorRequestBlockedTilesEvent;
import com.eu.habbo.messages.incoming.floorplaneditor.FloorPlanEditorRequestDoorSettingsEvent;
import com.eu.habbo.messages.incoming.floorplaneditor.FloorPlanEditorSaveEvent;
import com.eu.habbo.messages.incoming.friends.*;
import com.eu.habbo.messages.incoming.guardians.GuardianAcceptRequestEvent;
import com.eu.habbo.messages.incoming.guardians.GuardianNoUpdatesWantedEvent;
import com.eu.habbo.messages.incoming.guardians.GuardianVoteEvent;
import com.eu.habbo.messages.incoming.guides.*;
import com.eu.habbo.messages.incoming.guilds.*;
import com.eu.habbo.messages.incoming.guilds.forums.*;
import com.eu.habbo.messages.incoming.handshake.*;
import com.eu.habbo.messages.incoming.helper.MySanctionStatusEvent;
import com.eu.habbo.messages.incoming.helper.RequestTalentTrackEvent;
import com.eu.habbo.messages.incoming.hotelview.*;
import com.eu.habbo.messages.incoming.inventory.RequestInventoryBadgesEvent;
import com.eu.habbo.messages.incoming.inventory.RequestInventoryBotsEvent;
import com.eu.habbo.messages.incoming.inventory.RequestInventoryItemsEvent;
import com.eu.habbo.messages.incoming.inventory.RequestInventoryPetsEvent;
import com.eu.habbo.messages.incoming.modtool.*;
import com.eu.habbo.messages.incoming.navigator.*;
import com.eu.habbo.messages.incoming.polls.AnswerPollEvent;
import com.eu.habbo.messages.incoming.polls.CancelPollEvent;
import com.eu.habbo.messages.incoming.polls.GetPollDataEvent;
import com.eu.habbo.messages.incoming.rooms.*;
import com.eu.habbo.messages.incoming.rooms.bots.BotPickupEvent;
import com.eu.habbo.messages.incoming.rooms.bots.BotPlaceEvent;
import com.eu.habbo.messages.incoming.rooms.bots.BotSaveSettingsEvent;
import com.eu.habbo.messages.incoming.rooms.bots.BotSettingsEvent;
import com.eu.habbo.messages.incoming.rooms.items.*;
import com.eu.habbo.messages.incoming.rooms.items.jukebox.*;
import com.eu.habbo.messages.incoming.rooms.items.lovelock.LoveLockStartConfirmEvent;
import com.eu.habbo.messages.incoming.rooms.items.rentablespace.RentSpaceCancelEvent;
import com.eu.habbo.messages.incoming.rooms.items.rentablespace.RentSpaceEvent;
import com.eu.habbo.messages.incoming.rooms.items.youtube.YoutubeRequestPlaylistChange;
import com.eu.habbo.messages.incoming.rooms.items.youtube.YoutubeRequestPlaylists;
import com.eu.habbo.messages.incoming.rooms.items.youtube.YoutubeRequestStateChange;
import com.eu.habbo.messages.incoming.rooms.pets.*;
import com.eu.habbo.messages.incoming.rooms.promotions.BuyRoomPromotionEvent;
import com.eu.habbo.messages.incoming.rooms.promotions.RequestPromotionRoomsEvent;
import com.eu.habbo.messages.incoming.rooms.promotions.UpdateRoomPromotionEvent;
import com.eu.habbo.messages.incoming.rooms.users.*;
import com.eu.habbo.messages.incoming.trading.*;
import com.eu.habbo.messages.incoming.unknown.RequestResolutionEvent;
import com.eu.habbo.messages.incoming.unknown.UnknownEvent1;
import com.eu.habbo.messages.incoming.users.*;
import com.eu.habbo.messages.incoming.wired.WiredApplySetConditionsEvent;
import com.eu.habbo.messages.incoming.wired.WiredConditionSaveDataEvent;
import com.eu.habbo.messages.incoming.wired.WiredEffectSaveDataEvent;
import com.eu.habbo.messages.incoming.wired.WiredTriggerSaveDataEvent;
import com.eu.habbo.plugin.EventHandler;
import com.eu.habbo.plugin.events.emulator.EmulatorConfigUpdatedEvent;
import gnu.trove.map.hash.THashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PacketManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(PacketManager.class);

    private static final List<Incoming> logList = new ArrayList<>();
    public static boolean DEBUG_SHOW_PACKETS = false;
    private final THashMap<Incoming, Class<? extends MessageHandler>> incoming;
    private final THashMap<Incoming, List<ICallable>> callables;

    public PacketManager() throws Exception {
        this.incoming = new THashMap<>();
        this.callables = new THashMap<>();

        this.registerHandshake();
        this.registerCatalog();
        this.registerEvent();
        this.registerFriends();
        this.registerNavigator();
        this.registerUsers();
        this.registerHotelview();
        this.registerInventory();
        this.registerRooms();
        this.registerPolls();
        this.registerUnknown();
        this.registerModTool();
        this.registerTrading();
        this.registerGuilds();
        this.registerPets();
        this.registerWired();
        this.registerAchievements();
        this.registerFloorPlanEditor();
        this.registerAmbassadors();
        this.registerGuides();
        this.registerCrafting();
        this.registerCamera();
    }

    @EventHandler
    public static void onConfigurationUpdated(EmulatorConfigUpdatedEvent event) {
        logList.clear();

        for (String s : Emulator.getConfig().getValue("debug.show.headers").split(";")) {
            if (s.isEmpty()) {
                continue;
            }

            try {
                logList.add(Incoming.valueOf(s));
            } catch (Exception e) {
                LOGGER.error("Failed to add {} to packet log list, check your debug.show.headers config", s, e);
            }
        }
    }

    public void registerHandler(Incoming header, Class<? extends MessageHandler> handler) throws Exception {
        if (this.incoming.containsKey(header)) {
            throw new Exception("Header already registered. Failed to register " + handler.getName() + " with header " + header);
        }

        this.incoming.putIfAbsent(header, handler);
    }

    public void registerCallable(Incoming header, ICallable callable) {
        this.callables.putIfAbsent(header, new ArrayList<>());
        this.callables.get(header).add(callable);
    }

    @Deprecated
    public void unregisterCallables(Incoming header) {
        if (this.callables.containsKey(header)) {
            this.callables.clear();
        }
    }

    public void handlePacket(GameClient client, ClientMessage packet) {
        if (client == null || Emulator.isShuttingDown)
            return;

        try {
            if (this.incoming.containsKey(packet.getHeader())) {
                Class<? extends MessageHandler> handlerClass = this.incoming.get(packet.getHeader());

                if (handlerClass == null) throw new Exception("Unknown message " + packet.getHeader());

                if (client.getHabbo() == null && !handlerClass.isAnnotationPresent(NoAuthMessage.class)) {
                    if (DEBUG_SHOW_PACKETS) {
                        LOGGER.warn("Client packet {} requires an authenticated session.", packet.getHeader());
                    }

                    return;
                }

                final MessageHandler handler = handlerClass.newInstance();

                if (handler.getRatelimit() > 0) {
                    if (client.messageTimestamps.containsKey(handlerClass) && System.currentTimeMillis() - client.messageTimestamps.get(handlerClass) < handler.getRatelimit()) {
                        if (PacketManager.DEBUG_SHOW_PACKETS) {
                            LOGGER.warn("Client packet {} was ratelimited.", packet.getHeader());
                        }

                        return;
                    } else {
                        client.messageTimestamps.put(handlerClass, System.currentTimeMillis());
                    }
                }

                if (logList.contains(packet.getHeader()) && client.getHabbo() != null) {
                    LOGGER.info("User {} sent packet {} with body {}", client.getHabbo().getHabboInfo().getUsername(), packet.getHeader(), packet.getMessageBody());
                }

                handler.client = client;
                handler.packet = packet;

                if (this.callables.containsKey(packet.getHeader())) {
                    for (ICallable callable : this.callables.get(packet.getHeader())) {
                        callable.call(handler);
                    }
                }

                if (!handler.isCancelled) {
                    handler.handle();
                }
            }
        } catch (Exception e) {
            LOGGER.error("Caught exception", e);
        }
    }

    private void registerAmbassadors() throws Exception {
        this.registerHandler(Incoming.AmbassadorAlert, AmbassadorAlertCommandEvent.class);
        this.registerHandler(Incoming.VisitUser, AmbassadorVisitCommandEvent.class);
    }

    private void registerCatalog() throws Exception {
        this.registerHandler(Incoming.GetRecyclerPrizes, RequestRecyclerLogicEvent.class);
        this.registerHandler(Incoming.GetBundleDiscountRuleset, RequestDiscountEvent.class);
        this.registerHandler(Incoming.GetGiftWrappingConfiguration, RequestGiftConfigurationEvent.class);
        this.registerHandler(Incoming.GetMarketplaceConfiguration, RequestMarketplaceConfigEvent.class);
        this.registerHandler(Incoming.GetCatalogIndex, RequestCatalogModeEvent.class);
        this.registerHandler(Incoming.BuildersClubQueryFurniCount, RequestCatalogIndexEvent.class);
        this.registerHandler(Incoming.GetCatalogPage, RequestCatalogPageEvent.class);
        this.registerHandler(Incoming.PurchaseFromCatalogAsGift, CatalogBuyItemAsGiftEvent.class);
        this.registerHandler(Incoming.PurchaseFromCatalog, CatalogBuyItemEvent.class);
        this.registerHandler(Incoming.RedeemVoucher, RedeemVoucherEvent.class);
        this.registerHandler(Incoming.GetRecyclerStatus, ReloadRecyclerEvent.class);
        this.registerHandler(Incoming.RecycleItems, RecycleEvent.class);
        this.registerHandler(Incoming.PresentOpen, OpenRecycleBoxEvent.class);
        this.registerHandler(Incoming.GetMarketplaceOwnOffers, RequestOwnItemsEvent.class);
        this.registerHandler(Incoming.CancelMarketplaceOffer, TakeBackItemEvent.class);
        this.registerHandler(Incoming.GetMarketplaceOffers, RequestOffersEvent.class);
        this.registerHandler(Incoming.GetMarketplaceItemStats, RequestItemInfoEvent.class);
        this.registerHandler(Incoming.BuyMarketplaceOffer, BuyItemEvent.class);
        this.registerHandler(Incoming.GetMarketplaceCanMakeOffer, RequestSellItemEvent.class);
        this.registerHandler(Incoming.MakeOffer, SellItemEvent.class);
        this.registerHandler(Incoming.RedeemMarketplaceOfferCredits, RequestCreditsEvent.class);
        this.registerHandler(Incoming.GetSellablePetPalettes, RequestPetBreedsEvent.class);
        this.registerHandler(Incoming.ApproveName, CheckPetNameEvent.class);
        this.registerHandler(Incoming.GetClubOffers, RequestClubDataEvent.class);
        this.registerHandler(Incoming.GetClubGift, RequestClubGiftsEvent.class);
        this.registerHandler(Incoming.GetProductOffer, CatalogSearchedItemEvent.class);
        this.registerHandler(Incoming.PurchaseTargetedOffer, PurchaseTargetOfferEvent.class);
        this.registerHandler(Incoming.SetTargetedOfferState, TargetOfferStateEvent.class);
        this.registerHandler(Incoming.SelectClubGift, CatalogSelectClubGiftEvent.class);
        this.registerHandler(Incoming.ScrGetKickbackInfo, RequestClubCenterEvent.class);
        this.registerHandler(Incoming.GetHabboClubExtendOffer, CatalogRequestClubDiscountEvent.class);
        this.registerHandler(Incoming.PurchaseVipMembershipExtension, CatalogBuyClubDiscountEvent.class);
    }

    private void registerEvent() throws Exception {
        this.registerHandler(Incoming.OpenCampaignCalendarDoor, AdventCalendarOpenDayEvent.class);
        this.registerHandler(Incoming.OpenCampaignCalendarDoorAsStaff, AdventCalendarForceOpenEvent.class);
    }

    private void registerHandshake() throws Exception {
        this.registerHandler(Incoming.ClientHello, ClientHelloEvent.class);
        this.registerHandler(Incoming.InitDiffieHandshake, InitDiffieHandshakeEvent.class);
        this.registerHandler(Incoming.CompleteDiffieHandshake, CompleteDiffieHandshakeEvent.class);
        this.registerHandler(Incoming.SSOTicket, SecureLoginEvent.class);
        this.registerHandler(Incoming.UniqueID, MachineIDEvent.class);
        this.registerHandler(Incoming.GetIgnoredUsers, UsernameEvent.class);
        this.registerHandler(Incoming.LatencyPingRequest, PingEvent.class);
    }

    private void registerFriends() throws Exception {
        this.registerHandler(Incoming.GetMOTD, RequestFriendsEvent.class);
        this.registerHandler(Incoming.SetRelationshipStatus, ChangeRelationEvent.class);
        this.registerHandler(Incoming.RemoveFriend, RemoveFriendEvent.class);
        this.registerHandler(Incoming.HabboSearch, SearchUserEvent.class);
        this.registerHandler(Incoming.RequestFriend, FriendRequestEvent.class);
        this.registerHandler(Incoming.AcceptFriend, AcceptFriendRequestEvent.class);
        this.registerHandler(Incoming.DeclineFriend, DeclineFriendRequestEvent.class);
        this.registerHandler(Incoming.SendMsg, FriendPrivateMessageEvent.class);
        this.registerHandler(Incoming.GetFriendRequests, RequestFriendRequestsEvent.class);
        this.registerHandler(Incoming.FollowFriend, StalkFriendEvent.class);
        this.registerHandler(Incoming.MessengerInit, RequestInitFriendsEvent.class);
        this.registerHandler(Incoming.FindNewFriends, FindNewFriendsEvent.class);
        this.registerHandler(Incoming.SendRoomInvite, InviteFriendsEvent.class);
    }

    private void registerUsers() throws Exception {
        this.registerHandler(Incoming.InfoRetrieve, RequestUserDataEvent.class);
        this.registerHandler(Incoming.GetCreditsInfo, RequestUserCreditsEvent.class);
        this.registerHandler(Incoming.ScrGetUserInfo, RequestUserClubEvent.class);
        this.registerHandler(Incoming.GetSoundSettings, RequestMeMenuSettingsEvent.class);
        this.registerHandler(Incoming.GetTalentTrackLevel, RequestUserCitizinShipEvent.class);
        this.registerHandler(Incoming.GetExtendedProfile, RequestUserProfileEvent.class);
        this.registerHandler(Incoming.GetRelationshipStatusInfo, RequestProfileFriendsEvent.class);
        this.registerHandler(Incoming.GetWardrobe, RequestUserWardrobeEvent.class);
        this.registerHandler(Incoming.SaveWardrobeOutfit, SaveWardrobeEvent.class);
        this.registerHandler(Incoming.ChangeMotto, SaveMottoEvent.class);
        this.registerHandler(Incoming.UpdateFigureData, UserSaveLookEvent.class);
        this.registerHandler(Incoming.SetActivatedBadges, UserWearBadgeEvent.class);
        this.registerHandler(Incoming.GetSelectedBadges, RequestWearingBadgesEvent.class);
        this.registerHandler(Incoming.SetSoundSettings, SaveUserVolumesEvent.class);
        this.registerHandler(Incoming.SetRoomCameraPreferences, SaveBlockCameraFollowEvent.class);
        this.registerHandler(Incoming.SetIgnoreRoomInvites, SaveIgnoreRoomInvitesEvent.class);
        this.registerHandler(Incoming.SetChatPreferences, SavePreferOldChatEvent.class);
        this.registerHandler(Incoming.AvatarEffectActivated, ActivateEffectEvent.class);
        this.registerHandler(Incoming.AvatarEffectSelected, EnableEffectEvent.class);
        this.registerHandler(Incoming.EventLog, UserActivityEvent.class);
        this.registerHandler(Incoming.NewUserExperienceScriptProceed, UserNuxEvent.class);
        this.registerHandler(Incoming.NewUserExperienceGetGifts, PickNewUserGiftEvent.class);
        this.registerHandler(Incoming.CheckUserName, ChangeNameCheckUsernameEvent.class);
        this.registerHandler(Incoming.ChangeUserName, ConfirmChangeNameEvent.class);
        this.registerHandler(Incoming.SetChatStylePreference, ChangeChatBubbleEvent.class);
        this.registerHandler(Incoming.SetUIFlags, UpdateUIFlagsEvent.class);
    }

    private void registerNavigator() throws Exception {
        this.registerHandler(Incoming.GetUserFlatCats, RequestRoomCategoriesEvent.class);
        this.registerHandler(Incoming.PopularRoomsSearch, RequestPopularRoomsEvent.class);
        this.registerHandler(Incoming.RoomsWithHighestScoreSearch, RequestHighestScoreRoomsEvent.class);
        this.registerHandler(Incoming.MyRoomsSearch, RequestMyRoomsEvent.class);
        this.registerHandler(Incoming.CanCreateRoom, RequestCanCreateRoomEvent.class);
        this.registerHandler(Incoming.GetUnreadForumsCount, RequestPromotedRoomsEvent.class);
        this.registerHandler(Incoming.CreateFlat, RequestCreateRoomEvent.class);
        this.registerHandler(Incoming.GetPopularRoomTags, RequestTagsEvent.class);
        // this.registerHandler(Incoming.SearchRoomsByTagEvent, SearchRoomsByTagEvent.class);
        this.registerHandler(Incoming.RoomTextSearch, SearchRoomsEvent.class);
        this.registerHandler(Incoming.RoomsWhereMyFriendsAreSearch, SearchRoomsFriendsNowEvent.class);
        this.registerHandler(Incoming.MyFriendsRoomsSearch, SearchRoomsFriendsOwnEvent.class);
        this.registerHandler(Incoming.MyRoomRightsSearch, SearchRoomsWithRightsEvent.class);
        this.registerHandler(Incoming.MyGuildBasesSearch, SearchRoomsInGroupEvent.class);
        this.registerHandler(Incoming.MyFavouriteRoomsSearch, SearchRoomsMyFavouriteEvent.class);
        this.registerHandler(Incoming.MyRoomHistorySearch, SearchRoomsVisitedEvent.class);
        this.registerHandler(Incoming.NewNavigatorInit, RequestNewNavigatorDataEvent.class);
        this.registerHandler(Incoming.NewNavigatorSearch, RequestNewNavigatorRoomsEvent.class);
        this.registerHandler(Incoming.ForwardToSomeRoom, NewNavigatorActionEvent.class);
        this.registerHandler(Incoming.GetUserEventCats, RequestNavigatorSettingsEvent.class);
        this.registerHandler(Incoming.SetNewNavigatorWindowPreferences, SaveWindowSettingsEvent.class);
        this.registerHandler(Incoming.DeleteRoom, RequestDeleteRoomEvent.class);
        this.registerHandler(Incoming.NavigatorSetSearchCodeViewMode, NavigatorCategoryListModeEvent.class);
        this.registerHandler(Incoming.NavigatorAddCollapsedCategory, NavigatorCollapseCategoryEvent.class);
        this.registerHandler(Incoming.NavigatorRemoveCollapsedCategory, NavigatorUncollapseCategoryEvent.class);
        this.registerHandler(Incoming.NavigatorAddSavedSearch, AddSavedSearchEvent.class);
        this.registerHandler(Incoming.NavigatorDeleteSavedSearch, DeleteSavedSearchEvent.class);
    }

    private void registerHotelview() throws Exception {
        this.registerHandler(Incoming.Quit, HotelViewEvent.class);
        this.registerHandler(Incoming.GetBonusRareInfo, HotelViewRequestBonusRareEvent.class);
        this.registerHandler(Incoming.GetPromoArticles, RequestNewsListEvent.class);
        this.registerHandler(Incoming.GetCurrentTimingCode, HotelViewDataEvent.class);
        this.registerHandler(Incoming.GetLimitedOfferAppearingNext, HotelViewRequestLTDAvailabilityEvent.class);
        this.registerHandler(Incoming.GetSecondsUntil, HotelViewRequestSecondsUntilEvent.class);
    }

    private void registerInventory() throws Exception {
        this.registerHandler(Incoming.GetBadges, RequestInventoryBadgesEvent.class);
        this.registerHandler(Incoming.GetBotInventory, RequestInventoryBotsEvent.class);
        this.registerHandler(Incoming.RequestFurniInventory, RequestInventoryItemsEvent.class);
        this.registerHandler(Incoming.RequestFurniInventoryWhenNotInRoom, RequestInventoryItemsEvent.class);
        this.registerHandler(Incoming.GetPetInventory, RequestInventoryPetsEvent.class);
    }

    void registerRooms() throws Exception {
        this.registerHandler(Incoming.OpenFlatConnection, RequestRoomLoadEvent.class);
        this.registerHandler(Incoming.GetFurnitureAliases, RequestRoomHeightmapEvent.class);
        this.registerHandler(Incoming.GetHeightMap, RequestRoomHeightmapEvent.class);
        this.registerHandler(Incoming.RateFlat, RoomVoteEvent.class);
        this.registerHandler(Incoming.GetGuestRoom, RequestRoomDataEvent.class);
        this.registerHandler(Incoming.SaveRoomSettings, RoomSettingsSaveEvent.class);
        this.registerHandler(Incoming.PlaceObject, RoomPlaceItemEvent.class);
        this.registerHandler(Incoming.MoveObject, RotateMoveItemEvent.class);
        this.registerHandler(Incoming.MoveWallItem, MoveWallItemEvent.class);
        this.registerHandler(Incoming.PickupObject, RoomPickupItemEvent.class);
        this.registerHandler(Incoming.RequestRoomPropertySet, RoomPlacePaintEvent.class);
        this.registerHandler(Incoming.StartTyping, RoomUserStartTypingEvent.class);
        this.registerHandler(Incoming.CancelTyping, RoomUserStopTypingEvent.class);
        this.registerHandler(Incoming.UseFurniture, ToggleFloorItemEvent.class);
        this.registerHandler(Incoming.UseWallItem, ToggleWallItemEvent.class);
        this.registerHandler(Incoming.SetRoomBackgroundColorData, RoomBackgroundEvent.class);
        this.registerHandler(Incoming.SetMannequinName, MannequinSaveNameEvent.class);
        this.registerHandler(Incoming.SetMannequinFigure, MannequinSaveLookEvent.class);
        this.registerHandler(Incoming.SetClothingChangeData, FootballGateSaveLookEvent.class);
        this.registerHandler(Incoming.SetObjectData, AdvertisingSaveEvent.class);
        this.registerHandler(Incoming.GetRoomSettings, RequestRoomSettingsEvent.class);
        this.registerHandler(Incoming.RoomDimmerGetPresets, MoodLightSettingsEvent.class);
        this.registerHandler(Incoming.RoomDimmerChangeState, MoodLightTurnOnEvent.class);
        this.registerHandler(Incoming.DropCarryItem, RoomUserDropHandItemEvent.class);
        this.registerHandler(Incoming.LookTo, RoomUserLookAtPoint.class);
        this.registerHandler(Incoming.Chat, RoomUserTalkEvent.class);
        this.registerHandler(Incoming.Shout, RoomUserShoutEvent.class);
        this.registerHandler(Incoming.Whisper, RoomUserWhisperEvent.class);
        this.registerHandler(Incoming.AvatarExpression, RoomUserActionEvent.class);
        this.registerHandler(Incoming.ChangePosture, RoomUserSitEvent.class);
        this.registerHandler(Incoming.Dance, RoomUserDanceEvent.class);
        this.registerHandler(Incoming.Sign, RoomUserSignEvent.class);
        this.registerHandler(Incoming.MoveAvatar, RoomUserWalkEvent.class);
        this.registerHandler(Incoming.RespectUser, RoomUserGiveRespectEvent.class);
        this.registerHandler(Incoming.AssignRights, RoomUserGiveRightsEvent.class);
        this.registerHandler(Incoming.RemoveOwnRoomRightsRoom, RoomRemoveRightsEvent.class);
        this.registerHandler(Incoming.GetFlatControllers, RequestRoomRightsEvent.class);
        this.registerHandler(Incoming.RemoveAllRights, RoomRemoveAllRightsEvent.class);
        this.registerHandler(Incoming.RemoveRights, RoomUserRemoveRightsEvent.class);
        this.registerHandler(Incoming.PlaceBot, BotPlaceEvent.class);
        this.registerHandler(Incoming.RemoveBotFromFlat, BotPickupEvent.class);
        this.registerHandler(Incoming.CommandBot, BotSaveSettingsEvent.class);
        this.registerHandler(Incoming.GetBotCommandConfigurationData, BotSettingsEvent.class);
        this.registerHandler(Incoming.ThrowDice, TriggerDiceEvent.class);
        this.registerHandler(Incoming.DiceOff, CloseDiceEvent.class);
        this.registerHandler(Incoming.SpinWheelOfFortune, TriggerColorWheelEvent.class);
        this.registerHandler(Incoming.CreditFurniRedeem, RedeemItemEvent.class);
        this.registerHandler(Incoming.PlacePet, PetPlaceEvent.class);
        this.registerHandler(Incoming.KickUser, RoomUserKickEvent.class);
        this.registerHandler(Incoming.SetCustomStackingHeight, SetStackHelperHeightEvent.class);
        this.registerHandler(Incoming.EnterOneWayDoor, TriggerOneWayGateEvent.class);
        this.registerHandler(Incoming.LetUserIn, HandleDoorbellEvent.class);
        this.registerHandler(Incoming.CustomizeAvatarWithFurni, RedeemClothingEvent.class);
        this.registerHandler(Incoming.PlacePostIt, PostItPlaceEvent.class);
        this.registerHandler(Incoming.GetItemData, PostItRequestDataEvent.class);
        this.registerHandler(Incoming.SetItemData, PostItSaveDataEvent.class);
        this.registerHandler(Incoming.RemoveItem, PostItDeleteEvent.class);
        this.registerHandler(Incoming.RoomDimmerSavePreset, MoodLightSaveSettingsEvent.class);
        this.registerHandler(Incoming.RentableSpaceRent, RentSpaceEvent.class);
        this.registerHandler(Incoming.RentableSpaceCancelRent, RentSpaceCancelEvent.class);
        this.registerHandler(Incoming.UpdateHomeRoom, SetHomeRoomEvent.class);
        this.registerHandler(Incoming.PassCarryItem, RoomUserGiveHandItemEvent.class);
        this.registerHandler(Incoming.MuteAllInRoom, RoomMuteEvent.class);
        this.registerHandler(Incoming.GetCustomRoomFilter, RequestRoomWordFilterEvent.class);
        this.registerHandler(Incoming.UpdateRoomFilter, RoomWordFilterModifyEvent.class);
        this.registerHandler(Incoming.ToggleStaffPick, RoomStaffPickEvent.class);
        this.registerHandler(Incoming.GetBannedUsersFromRoom, RoomRequestBannedUsersEvent.class);
        this.registerHandler(Incoming.GetOfficialSongId, JukeBoxRequestTrackCodeEvent.class);
        this.registerHandler(Incoming.GetSongInfo, JukeBoxRequestTrackDataEvent.class);
        this.registerHandler(Incoming.AddJukeboxDisk, JukeBoxAddSoundTrackEvent.class);
        this.registerHandler(Incoming.RemoveJukeboxDisk, JukeBoxRemoveSoundTrackEvent.class);
        this.registerHandler(Incoming.GetNowPlaying, JukeBoxRequestPlayListEvent.class);
        this.registerHandler(Incoming.GetUserSongDisks, JukeBoxEventOne.class);
        this.registerHandler(Incoming.GetJukeboxPlayList, JukeBoxEventTwo.class);
        this.registerHandler(Incoming.AddSpamWallPostIt, SavePostItStickyPoleEvent.class);
        this.registerHandler(Incoming.GetRoomAdPurchaseInfo, RequestPromotionRoomsEvent.class);
        this.registerHandler(Incoming.PurchaseRoomAd, BuyRoomPromotionEvent.class);
        this.registerHandler(Incoming.EditEvent, UpdateRoomPromotionEvent.class);
        this.registerHandler(Incoming.IgnoreUser, IgnoreRoomUserEvent.class);
        this.registerHandler(Incoming.UnignoreUser, UnIgnoreRoomUserEvent.class);
        this.registerHandler(Incoming.MuteUser, RoomUserMuteEvent.class);
        this.registerHandler(Incoming.BanUserWithDuration, RoomUserBanEvent.class);
        this.registerHandler(Incoming.UnbanUserFromRoom, UnbanRoomUserEvent.class);
        this.registerHandler(Incoming.GetYoutubeDisplayStatus, YoutubeRequestPlaylists.class);
        this.registerHandler(Incoming.ControlYoutubeDisplayPlayback, YoutubeRequestStateChange.class);
        this.registerHandler(Incoming.SetYoutubeDisplayPlaylist, YoutubeRequestPlaylistChange.class);
        this.registerHandler(Incoming.AddFavouriteRoom, RoomFavoriteEvent.class);
        this.registerHandler(Incoming.FriendFurniConfirmLock, LoveLockStartConfirmEvent.class);
        this.registerHandler(Incoming.DeleteFavouriteRoom, RoomUnFavoriteEvent.class);
        this.registerHandler(Incoming.SetRandomState, UseRandomStateItemEvent.class);
    }

    void registerPolls() throws Exception {
        this.registerHandler(Incoming.PollReject, CancelPollEvent.class);
        this.registerHandler(Incoming.PollStart, GetPollDataEvent.class);
        this.registerHandler(Incoming.PollAnswer, AnswerPollEvent.class);
    }

    void registerModTool() throws Exception {
        this.registerHandler(Incoming.GetModeratorRoomInfo, ModToolRequestRoomInfoEvent.class);
        this.registerHandler(Incoming.GetRoomChatlog, ModToolRequestRoomChatlogEvent.class);
        this.registerHandler(Incoming.GetModeratorUserInfo, ModToolRequestUserInfoEvent.class);
        this.registerHandler(Incoming.PickIssues, ModToolPickTicketEvent.class);
        this.registerHandler(Incoming.CloseIssues, ModToolCloseTicketEvent.class);
        this.registerHandler(Incoming.ReleaseIssues, ModToolReleaseTicketEvent.class);
        this.registerHandler(Incoming.ModMessage, ModToolAlertEvent.class);
        this.registerHandler(Incoming.ModKick, ModToolKickEvent.class);
        this.registerHandler(Incoming.ModeratorAction, ModToolRoomAlertEvent.class);
        this.registerHandler(Incoming.ModerateRoom, ModToolChangeRoomSettingsEvent.class);
        this.registerHandler(Incoming.GetRoomVisits, ModToolRequestRoomVisitsEvent.class);
        this.registerHandler(Incoming.GetCfhChatlog, ModToolRequestIssueChatlogEvent.class);
        this.registerHandler(Incoming.GetUserChatlog, ModToolRequestUserChatlogEvent.class);
        this.registerHandler(Incoming.ModAlert, ModToolSanctionAlertEvent.class);
        this.registerHandler(Incoming.ModMute, ModToolSanctionMuteEvent.class);
        this.registerHandler(Incoming.ModBan, ModToolSanctionBanEvent.class);
        this.registerHandler(Incoming.ModTradingLock, ModToolSanctionTradeLockEvent.class);
        this.registerHandler(Incoming.ModToolSanction, ModToolIssueChangeTopicEvent.class);
        this.registerHandler(Incoming.CloseIssueDefaultAction, ModToolIssueDefaultSanctionEvent.class);

        this.registerHandler(Incoming.GetPendingCallsForHelp, RequestReportRoomEvent.class);
        this.registerHandler(Incoming.GetGuideReportingStatus, RequestReportUserBullyingEvent.class);
        this.registerHandler(Incoming.ChatReviewSessionCreate, ReportBullyEvent.class);
        this.registerHandler(Incoming.CallForHelp, ReportEvent.class);
        this.registerHandler(Incoming.CallForHelpFromIM, ReportFriendPrivateChatEvent.class);
        this.registerHandler(Incoming.CallForHelpFromForumThread, ReportThreadEvent.class);
        this.registerHandler(Incoming.CallForHelpFromForumMessage, ReportCommentEvent.class);
        this.registerHandler(Incoming.CallForHelpFromPhoto, ReportPhotoEvent.class);
    }

    void registerTrading() throws Exception {
        this.registerHandler(Incoming.OpenTrading, TradeStartEvent.class);
        this.registerHandler(Incoming.AddItemToTrade, TradeOfferItemEvent.class);
        this.registerHandler(Incoming.AddItemsToTrade, TradeOfferMultipleItemsEvent.class);
        this.registerHandler(Incoming.RemoveItemFromTrade, TradeCancelOfferItemEvent.class);
        this.registerHandler(Incoming.AcceptTrading, TradeAcceptEvent.class);
        this.registerHandler(Incoming.UnacceptTrading, TradeUnAcceptEvent.class);
        this.registerHandler(Incoming.ConfirmAcceptTrading, TradeConfirmEvent.class);
        this.registerHandler(Incoming.CloseTrading, TradeCloseEvent.class);
        this.registerHandler(Incoming.ConfirmDeclineTrading, TradeCancelEvent.class);
    }

    void registerGuilds() throws Exception {
        this.registerHandler(Incoming.GetGuildCreationInfo, RequestGuildBuyRoomsEvent.class);
        this.registerHandler(Incoming.GetGuildEditorData, RequestGuildPartsEvent.class);
        this.registerHandler(Incoming.CreateGuild, RequestGuildBuyEvent.class);
        this.registerHandler(Incoming.GetHabboGroupDetails, RequestGuildInfoEvent.class);
        this.registerHandler(Incoming.GetGuildEditInfo, RequestGuildManageEvent.class);
        this.registerHandler(Incoming.GetGuildMembers, RequestGuildMembersEvent.class);
        this.registerHandler(Incoming.JoinHabboGroup, RequestGuildJoinEvent.class);
        this.registerHandler(Incoming.UpdateGuildIdentity, GuildChangeNameDescEvent.class);
        this.registerHandler(Incoming.UpdateGuildBadge, GuildChangeBadgeEvent.class);
        this.registerHandler(Incoming.UpdateGuildColors, GuildChangeColorsEvent.class);
        this.registerHandler(Incoming.RemoveAdminRightsFromMember, GuildRemoveAdminEvent.class);
        this.registerHandler(Incoming.KickMember, GuildRemoveMemberEvent.class);
        this.registerHandler(Incoming.UpdateGuildSettings, GuildChangeSettingsEvent.class);
        this.registerHandler(Incoming.ApproveMembershipRequest, GuildAcceptMembershipEvent.class);
        this.registerHandler(Incoming.RejectMembershipRequest, GuildDeclineMembershipEvent.class);
        this.registerHandler(Incoming.AddAdminRightsToMember, GuildSetAdminEvent.class);
        this.registerHandler(Incoming.SelectFavouriteHabboGroup, GuildSetFavoriteEvent.class);
        this.registerHandler(Incoming.GetGuildMemberships, RequestOwnGuildsEvent.class);
        this.registerHandler(Incoming.GetGuildFurniContextMenuInfo, RequestGuildFurniWidgetEvent.class);
        this.registerHandler(Incoming.GetMemberGuildItemCount, GuildConfirmRemoveMemberEvent.class);
        this.registerHandler(Incoming.DeselectFavouriteHabboGroup, GuildRemoveFavoriteEvent.class);
        this.registerHandler(Incoming.DeactivateGuild, GuildDeleteEvent.class);
        this.registerHandler(Incoming.GetForumsList, GuildForumListEvent.class);
        this.registerHandler(Incoming.GetThreads, GuildForumThreadsEvent.class);
        this.registerHandler(Incoming.GetForumStats, GuildForumDataEvent.class);
        this.registerHandler(Incoming.PostMessage, GuildForumPostThreadEvent.class);
        this.registerHandler(Incoming.UpdateForumSettings, GuildForumUpdateSettingsEvent.class);
        this.registerHandler(Incoming.GetMessages, GuildForumThreadsMessagesEvent.class);
        this.registerHandler(Incoming.ModerateMessage, GuildForumModerateMessageEvent.class);
        this.registerHandler(Incoming.ModerateThread, GuildForumModerateThreadEvent.class);
        this.registerHandler(Incoming.UpdateThread, GuildForumThreadUpdateEvent.class);
        this.registerHandler(Incoming.GetHabboGroupBadges, GetHabboGuildBadgesMessageEvent.class);
    }

    void registerPets() throws Exception {
        this.registerHandler(Incoming.GetPetInfo, RequestPetInformationEvent.class);
        this.registerHandler(Incoming.RemovePetFromFlat, PetPickupEvent.class);
        this.registerHandler(Incoming.RespectPet, ScratchPetEvent.class);
        this.registerHandler(Incoming.GetPetCommands, RequestPetTrainingPanelEvent.class);
        this.registerHandler(Incoming.CustomizePetWithFurni, PetUseItemEvent.class);
        this.registerHandler(Incoming.TogglePetRidingPermission, PetRideSettingsEvent.class);
        this.registerHandler(Incoming.MountPet, PetRideEvent.class);
        this.registerHandler(Incoming.RemoveSaddleFromPet, HorseRemoveSaddleEvent.class);
        this.registerHandler(Incoming.TogglePetBreedingPermission, ToggleMonsterplantBreedableEvent.class);
        this.registerHandler(Incoming.CompostPlant, CompostMonsterplantEvent.class);
        this.registerHandler(Incoming.BreedPets, BreedMonsterplantsEvent.class);
        this.registerHandler(Incoming.MovePet, MovePetEvent.class);
        this.registerHandler(Incoming.OpenPetPackage, PetPackageNameEvent.class);
        this.registerHandler(Incoming.CancelPetBreeding, StopBreedingEvent.class);
        this.registerHandler(Incoming.ConfirmPetBreeding, ConfirmPetBreedingEvent.class);
    }

    void registerWired() throws Exception {
        this.registerHandler(Incoming.UpdateTrigger, WiredTriggerSaveDataEvent.class);
        this.registerHandler(Incoming.UpdateAction, WiredEffectSaveDataEvent.class);
        this.registerHandler(Incoming.UpdateCondition, WiredConditionSaveDataEvent.class);
        this.registerHandler(Incoming.ApplySnapshot, WiredApplySetConditionsEvent.class);
    }

    void registerUnknown() throws Exception {
        this.registerHandler(Incoming.GetResolutionAchievements, RequestResolutionEvent.class);
        this.registerHandler(Incoming.GetTalentTrack, RequestTalentTrackEvent.class);
        this.registerHandler(Incoming.GetBadgePointLimits, UnknownEvent1.class);
        this.registerHandler(Incoming.GetCfhStatus, MySanctionStatusEvent.class);
    }

    void registerFloorPlanEditor() throws Exception {
        this.registerHandler(Incoming.UpdateFloorProperties, FloorPlanEditorSaveEvent.class);
        this.registerHandler(Incoming.GetOccupiedTiles, FloorPlanEditorRequestBlockedTilesEvent.class);
        this.registerHandler(Incoming.GetRoomEntryTile, FloorPlanEditorRequestDoorSettingsEvent.class);
    }

    void registerAchievements() throws Exception {
        this.registerHandler(Incoming.GetAchievements, RequestAchievementsEvent.class);
    }

    void registerGuides() throws Exception {
        this.registerHandler(Incoming.GuideSessionOnDutyUpdate, RequestGuideToolEvent.class);
        this.registerHandler(Incoming.GuideSessionCreate, RequestGuideAssistanceEvent.class);
        this.registerHandler(Incoming.GuideSessionIsTyping, GuideUserTypingEvent.class);
        this.registerHandler(Incoming.GuideSessionReport, GuideReportHelperEvent.class);
        this.registerHandler(Incoming.GuideSessionFeedback, GuideRecommendHelperEvent.class);
        this.registerHandler(Incoming.GuideSessionMessage, GuideUserMessageEvent.class);
        this.registerHandler(Incoming.GuideSessionRequesterCancels, GuideCancelHelpRequestEvent.class);
        this.registerHandler(Incoming.GuideSessionGuideDecides, GuideHandleHelpRequestEvent.class);
        this.registerHandler(Incoming.GuideSessionInviteRequester, GuideInviteUserEvent.class);
        this.registerHandler(Incoming.GuideSessionGetRequesterRoom, GuideVisitUserEvent.class);
        this.registerHandler(Incoming.GuideSessionResolved, GuideCloseHelpRequestEvent.class);

        this.registerHandler(Incoming.ChatReviewGuideDetached, GuardianNoUpdatesWantedEvent.class);
        this.registerHandler(Incoming.ChatReviewGuideDecidesOnOffer, GuardianAcceptRequestEvent.class);
        this.registerHandler(Incoming.ChatReviewGuideVote, GuardianVoteEvent.class);
    }

    void registerCrafting() throws Exception {
        this.registerHandler(Incoming.GetCraftableProducts, RequestCraftingRecipesEvent.class);
        this.registerHandler(Incoming.GetCraftingRecipe, CraftingAddRecipeEvent.class);
        this.registerHandler(Incoming.Craft, CraftingCraftItemEvent.class);
        this.registerHandler(Incoming.CraftSecret, CraftingCraftSecretEvent.class);
        this.registerHandler(Incoming.GetCraftingRecipesAvailable, RequestCraftingRecipesAvailableEvent.class);
    }

    void registerCamera() throws Exception {
        this.registerHandler(Incoming.RenderRoom, CameraRoomPictureEvent.class);
        this.registerHandler(Incoming.RequestCameraConfiguration, RequestCameraConfigurationEvent.class);
        this.registerHandler(Incoming.PurchasePhoto, CameraPurchaseEvent.class);
        this.registerHandler(Incoming.RenderRoomThumbnail, CameraRoomThumbnailEvent.class);
        this.registerHandler(Incoming.PublishPhoto, CameraPublishToWebEvent.class);
    }
}