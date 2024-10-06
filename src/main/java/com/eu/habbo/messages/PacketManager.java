package com.eu.habbo.messages;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.gameclients.GameClient;
import com.eu.habbo.messages.incoming.Incoming;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.incoming.avatar.ChangeUserNameEvent;
import com.eu.habbo.messages.incoming.avatar.CheckUserNameEvent;
import com.eu.habbo.messages.incoming.avatar.GetWardrobeEvent;
import com.eu.habbo.messages.incoming.avatar.SaveWardrobeOutfitEvent;
import com.eu.habbo.messages.incoming.camera.*;
import com.eu.habbo.messages.incoming.campaign.OpenCampaignCalendarDoorAsStaffEvent;
import com.eu.habbo.messages.incoming.campaign.OpenCampaignCalendarDoorEvent;
import com.eu.habbo.messages.incoming.catalog.*;
import com.eu.habbo.messages.incoming.competition.GetCurrentTimingCodeEvent;
import com.eu.habbo.messages.incoming.competition.GetSecondsUntilEvent;
import com.eu.habbo.messages.incoming.crafting.*;
import com.eu.habbo.messages.incoming.friendfurni.FriendFurniConfirmLockEvent;
import com.eu.habbo.messages.incoming.friendlist.*;
import com.eu.habbo.messages.incoming.game.arena.ChatEvent;
import com.eu.habbo.messages.incoming.game.lobby.GetResolutionAchievementsEvent;
import com.eu.habbo.messages.incoming.groupforums.*;
import com.eu.habbo.messages.incoming.handshake.*;
import com.eu.habbo.messages.incoming.help.*;
import com.eu.habbo.messages.incoming.inventory.achievements.GetAchievementsEvent;
import com.eu.habbo.messages.incoming.inventory.avatareffect.AvatarEffectActivatedEvent;
import com.eu.habbo.messages.incoming.inventory.avatareffect.AvatarEffectSelectedEvent;
import com.eu.habbo.messages.incoming.inventory.badges.GetBadgePointLimitsEvent;
import com.eu.habbo.messages.incoming.inventory.badges.GetBadgesEvent;
import com.eu.habbo.messages.incoming.inventory.badges.SetActivatedBadgesEvent;
import com.eu.habbo.messages.incoming.inventory.bots.GetBotInventoryEvent;
import com.eu.habbo.messages.incoming.inventory.furni.RequestFurniInventoryEvent;
import com.eu.habbo.messages.incoming.inventory.furni.RequestRoomPropertySetEvent;
import com.eu.habbo.messages.incoming.inventory.pets.CancelPetBreedingEvent;
import com.eu.habbo.messages.incoming.inventory.pets.ConfirmPetBreedingEvent;
import com.eu.habbo.messages.incoming.inventory.pets.GetPetInventoryEvent;
import com.eu.habbo.messages.incoming.inventory.purse.GetCreditsInfoEvent;
import com.eu.habbo.messages.incoming.inventory.trading.*;
import com.eu.habbo.messages.incoming.landingview.GetPromoArticlesEvent;
import com.eu.habbo.messages.incoming.marketplace.*;
import com.eu.habbo.messages.incoming.moderator.*;
import com.eu.habbo.messages.incoming.navigator.*;
import com.eu.habbo.messages.incoming.newnavigator.*;
import com.eu.habbo.messages.incoming.nux.NewUserExperienceGetGiftsEvent;
import com.eu.habbo.messages.incoming.nux.NewUserExperienceScriptProceedEvent;
import com.eu.habbo.messages.incoming.poll.PollAnswerEvent;
import com.eu.habbo.messages.incoming.poll.PollRejectEvent;
import com.eu.habbo.messages.incoming.poll.PollStartEvent;
import com.eu.habbo.messages.incoming.preferences.*;
import com.eu.habbo.messages.incoming.recycler.GetRecyclerPrizesEvent;
import com.eu.habbo.messages.incoming.recycler.GetRecyclerStatusEvent;
import com.eu.habbo.messages.incoming.recycler.RecycleItemsEvent;
import com.eu.habbo.messages.incoming.register.UpdateFigureDataEvent;
import com.eu.habbo.messages.incoming.room.action.*;
import com.eu.habbo.messages.incoming.room.avatar.*;
import com.eu.habbo.messages.incoming.room.bots.CommandBotEvent;
import com.eu.habbo.messages.incoming.room.bots.GetBotCommandConfigurationDataEvent;
import com.eu.habbo.messages.incoming.room.chat.CancelTypingEvent;
import com.eu.habbo.messages.incoming.room.chat.ShoutEvent;
import com.eu.habbo.messages.incoming.room.chat.StartTypingEvent;
import com.eu.habbo.messages.incoming.room.chat.WhisperEvent;
import com.eu.habbo.messages.incoming.room.engine.*;
import com.eu.habbo.messages.incoming.room.furniture.*;
import com.eu.habbo.messages.incoming.room.layout.GetOccupiedTilesEvent;
import com.eu.habbo.messages.incoming.room.layout.GetRoomEntryTileEvent;
import com.eu.habbo.messages.incoming.room.layout.UpdateFloorPropertiesEvent;
import com.eu.habbo.messages.incoming.room.pets.BreedPetsEvent;
import com.eu.habbo.messages.incoming.room.pets.CustomizePetWithFurniEvent;
import com.eu.habbo.messages.incoming.room.pets.GetPetInfoEvent;
import com.eu.habbo.messages.incoming.room.pets.RespectPetEvent;
import com.eu.habbo.messages.incoming.room.session.OpenFlatConnectionEvent;
import com.eu.habbo.messages.incoming.room.session.QuitEvent;
import com.eu.habbo.messages.incoming.roomsettings.*;
import com.eu.habbo.messages.incoming.sound.*;
import com.eu.habbo.messages.incoming.talent.GetTalentTrackEvent;
import com.eu.habbo.messages.incoming.talent.GetTalentTrackLevelEvent;
import com.eu.habbo.messages.incoming.tracking.EventLogEvent;
import com.eu.habbo.messages.incoming.tracking.LatencyPingRequestEvent;
import com.eu.habbo.messages.incoming.userdefinedroomevents.ApplySnapshotEvent;
import com.eu.habbo.messages.incoming.userdefinedroomevents.UpdateActionEvent;
import com.eu.habbo.messages.incoming.userdefinedroomevents.UpdateConditionEvent;
import com.eu.habbo.messages.incoming.userdefinedroomevents.UpdateTriggerEvent;
import com.eu.habbo.messages.incoming.users.*;
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
        this.registerHandler(Incoming.AmbassadorAlert, AmbassadorAlertEvent.class);
        this.registerHandler(Incoming.VisitUser, VisitUserEvent.class);
    }

    private void registerCatalog() throws Exception {
        this.registerHandler(Incoming.GetRecyclerPrizes, GetRecyclerPrizesEvent.class);
        this.registerHandler(Incoming.GetBundleDiscountRuleset, GetBundleDiscountRulesetEvent.class);
        this.registerHandler(Incoming.GetGiftWrappingConfiguration, GetGiftWrappingConfigurationEvent.class);
        this.registerHandler(Incoming.GetMarketplaceConfiguration, GetMarketplaceConfigurationEvent.class);
        this.registerHandler(Incoming.GetCatalogIndex, GetCatalogIndexEvent.class);
        this.registerHandler(Incoming.BuildersClubQueryFurniCount, BuildersClubQueryFurniCountEvent.class);
        this.registerHandler(Incoming.GetCatalogPage, GetCatalogPageEvent.class);
        this.registerHandler(Incoming.PurchaseFromCatalogAsGift, PurchaseFromCatalogAsGiftEvent.class);
        this.registerHandler(Incoming.PurchaseFromCatalog, PurchaseFromCatalogEvent.class);
        this.registerHandler(Incoming.RedeemVoucher, RedeemVoucherEvent.class);
        this.registerHandler(Incoming.GetRecyclerStatus, GetRecyclerStatusEvent.class);
        this.registerHandler(Incoming.RecycleItems, RecycleItemsEvent.class);
        this.registerHandler(Incoming.PresentOpen, PresentOpenEvent.class);
        this.registerHandler(Incoming.GetMarketplaceOwnOffers, GetMarketplaceOwnOffersEvent.class);
        this.registerHandler(Incoming.CancelMarketplaceOffer, CancelMarketplaceOfferEvent.class);
        this.registerHandler(Incoming.GetMarketplaceOffers, GetMarketplaceOffersEvent.class);
        this.registerHandler(Incoming.GetMarketplaceItemStats, GetMarketplaceItemStatsEvent.class);
        this.registerHandler(Incoming.BuyMarketplaceOffer, BuyMarketplaceOfferEvent.class);
        this.registerHandler(Incoming.GetMarketplaceCanMakeOffer, GetMarketplaceCanMakeOfferEvent.class);
        this.registerHandler(Incoming.MakeOffer, MakeOfferEvent.class);
        this.registerHandler(Incoming.RedeemMarketplaceOfferCredits, RedeemMarketplaceOfferCreditsEvent.class);
        this.registerHandler(Incoming.GetSellablePetPalettes, GetSellablePetPalettesEvent.class);
        this.registerHandler(Incoming.ApproveName, ApproveNameEvent.class);
        this.registerHandler(Incoming.GetClubOffers, GetClubOffersEvent.class);
        this.registerHandler(Incoming.GetClubGift, GetClubGiftEvent.class);
        this.registerHandler(Incoming.GetProductOffer, GetProductOfferEvent.class);
        this.registerHandler(Incoming.PurchaseTargetedOffer, PurchaseTargetedOfferEvent.class);
        this.registerHandler(Incoming.SetTargetedOfferState, SetTargetedOfferStateEvent.class);
        this.registerHandler(Incoming.SelectClubGift, SelectClubGiftEvent.class);
        this.registerHandler(Incoming.ScrGetKickbackInfo, ScrGetKickbackInfoEvent.class);
        this.registerHandler(Incoming.GetHabboClubExtendOffer, GetHabboClubExtendOfferEvent.class);
        this.registerHandler(Incoming.PurchaseVipMembershipExtension, PurchaseVipMembershipExtensionEvent.class);
    }

    private void registerEvent() throws Exception {
        this.registerHandler(Incoming.OpenCampaignCalendarDoor, OpenCampaignCalendarDoorEvent.class);
        this.registerHandler(Incoming.OpenCampaignCalendarDoorAsStaff, OpenCampaignCalendarDoorAsStaffEvent.class);
    }

    private void registerHandshake() throws Exception {
        this.registerHandler(Incoming.ClientHello, ClientHelloEvent.class);
        this.registerHandler(Incoming.InitDiffieHandshake, InitDiffieHandshakeEvent.class);
        this.registerHandler(Incoming.CompleteDiffieHandshake, CompleteDiffieHandshakeEvent.class);
        this.registerHandler(Incoming.SSOTicket, SSOTicketEvent.class);
        this.registerHandler(Incoming.UniqueID, UniqueIDEvent.class);
        this.registerHandler(Incoming.GetIgnoredUsers, GetIgnoredUsersEvent.class);
        this.registerHandler(Incoming.LatencyPingRequest, LatencyPingRequestEvent.class);
    }

    private void registerFriends() throws Exception {
        this.registerHandler(Incoming.GetMOTD, GetMOTDEvent.class);
        this.registerHandler(Incoming.SetRelationshipStatus, SetRelationshipStatusEvent.class);
        this.registerHandler(Incoming.RemoveFriend, RemoveFriendEvent.class);
        this.registerHandler(Incoming.HabboSearch, HabboSearchEvent.class);
        this.registerHandler(Incoming.RequestFriend, RequestFriendEvent.class);
        this.registerHandler(Incoming.AcceptFriend, AcceptFriendEvent.class);
        this.registerHandler(Incoming.DeclineFriend, DeclineFriendEvent.class);
        this.registerHandler(Incoming.SendMsg, SendMsgEvent.class);
        this.registerHandler(Incoming.GetFriendRequests, GetFriendRequestsEvent.class);
        this.registerHandler(Incoming.FollowFriend, FollowFriendEvent.class);
        this.registerHandler(Incoming.MessengerInit, MessengerInitEvent.class);
        this.registerHandler(Incoming.FindNewFriends, FindNewFriendsEvent.class);
        this.registerHandler(Incoming.SendRoomInvite, SendRoomInviteEvent.class);
    }

    private void registerUsers() throws Exception {
        this.registerHandler(Incoming.InfoRetrieve, InfoRetrieveEvent.class);
        this.registerHandler(Incoming.GetCreditsInfo, GetCreditsInfoEvent.class);
        this.registerHandler(Incoming.ScrGetUserInfo, ScrGetUserInfoEvent.class);
        this.registerHandler(Incoming.GetSoundSettings, GetSoundSettingsEvent.class);
        this.registerHandler(Incoming.GetTalentTrackLevel, GetTalentTrackLevelEvent.class);
        this.registerHandler(Incoming.GetExtendedProfile, GetExtendedProfileEvent.class);
        this.registerHandler(Incoming.GetRelationshipStatusInfo, GetRelationshipStatusInfoEvent.class);
        this.registerHandler(Incoming.GetWardrobe, GetWardrobeEvent.class);
        this.registerHandler(Incoming.SaveWardrobeOutfit, SaveWardrobeOutfitEvent.class);
        this.registerHandler(Incoming.ChangeMotto, ChangeMottoEvent.class);
        this.registerHandler(Incoming.UpdateFigureData, UpdateFigureDataEvent.class);
        this.registerHandler(Incoming.SetActivatedBadges, SetActivatedBadgesEvent.class);
        this.registerHandler(Incoming.GetSelectedBadges, GetSelectedBadgesEvent.class);
        this.registerHandler(Incoming.SetSoundSettings, SetSoundSettingsEvent.class);
        this.registerHandler(Incoming.SetRoomCameraPreferences, SetRoomCameraPreferencesEvent.class);
        this.registerHandler(Incoming.SetIgnoreRoomInvites, SetIgnoreRoomInvitesEvent.class);
        this.registerHandler(Incoming.SetChatPreferences, SetChatPreferencesEvent.class);
        this.registerHandler(Incoming.AvatarEffectActivated, AvatarEffectActivatedEvent.class);
        this.registerHandler(Incoming.AvatarEffectSelected, AvatarEffectSelectedEvent.class);
        this.registerHandler(Incoming.EventLog, EventLogEvent.class);
        this.registerHandler(Incoming.NewUserExperienceScriptProceed, NewUserExperienceScriptProceedEvent.class);
        this.registerHandler(Incoming.NewUserExperienceGetGifts, NewUserExperienceGetGiftsEvent.class);
        this.registerHandler(Incoming.CheckUserName, CheckUserNameEvent.class);
        this.registerHandler(Incoming.ChangeUserName, ChangeUserNameEvent.class);
        this.registerHandler(Incoming.SetChatStylePreference, SetChatStylePreferenceEvent.class);
        this.registerHandler(Incoming.SetUIFlags, SetUIFlagsEvent.class);
    }

    private void registerNavigator() throws Exception {
        this.registerHandler(Incoming.GetUserFlatCats, GetUserFlatCatsEvent.class);
        this.registerHandler(Incoming.PopularRoomsSearch, PopularRoomsSearchEvent.class);
        this.registerHandler(Incoming.RoomsWithHighestScoreSearch, RoomsWithHighestScoreSearchEvent.class);
        this.registerHandler(Incoming.MyRoomsSearch, MyRoomsSearchEvent.class);
        this.registerHandler(Incoming.CanCreateRoom, CanCreateRoomEvent.class);
        this.registerHandler(Incoming.GetUnreadForumsCount, GetUnreadForumsCountEvent.class);
        this.registerHandler(Incoming.CreateFlat, CreateFlatEvent.class);
        this.registerHandler(Incoming.GetPopularRoomTags, GetPopularRoomTagsEvent.class);
        // this.registerHandler(Incoming.SearchRoomsByTagEvent, SearchRoomsByTagEventEvent.class);
        this.registerHandler(Incoming.RoomTextSearch, RoomTextSearchEvent.class);
        this.registerHandler(Incoming.RoomsWhereMyFriendsAreSearch, RoomsWhereMyFriendsAreSearchEvent.class);
        this.registerHandler(Incoming.MyFriendsRoomsSearch, MyFriendsRoomsSearchEvent.class);
        this.registerHandler(Incoming.MyRoomRightsSearch, MyRoomRightsSearchEvent.class);
        this.registerHandler(Incoming.MyGuildBasesSearch, MyGuildBasesSearchEvent.class);
        this.registerHandler(Incoming.MyFavouriteRoomsSearch, MyFavouriteRoomsSearchEvent.class);
        this.registerHandler(Incoming.MyRoomHistorySearch, MyRoomHistorySearchEvent.class);
        this.registerHandler(Incoming.NewNavigatorInit, NewNavigatorInitEvent.class);
        this.registerHandler(Incoming.NewNavigatorSearch, NewNavigatorSearchEvent.class);
        this.registerHandler(Incoming.ForwardToSomeRoom, ForwardToSomeRoomEvent.class);
        this.registerHandler(Incoming.GetUserEventCats, GetUserEventCatsEvent.class);
        this.registerHandler(Incoming.SetNewNavigatorWindowPreferences, SetNewNavigatorWindowPreferencesEvent.class);
        this.registerHandler(Incoming.DeleteRoom, DeleteRoomEvent.class);
        this.registerHandler(Incoming.NavigatorSetSearchCodeViewMode, NavigatorSetSearchCodeViewModeEvent.class);
        this.registerHandler(Incoming.NavigatorAddCollapsedCategory, NavigatorAddCollapsedCategoryEvent.class);
        this.registerHandler(Incoming.NavigatorRemoveCollapsedCategory, NavigatorRemoveCollapsedCategoryEvent.class);
        this.registerHandler(Incoming.NavigatorAddSavedSearch, NavigatorAddSavedSearchEvent.class);
        this.registerHandler(Incoming.NavigatorDeleteSavedSearch, NavigatorDeleteSavedSearchEvent.class);
    }

    private void registerHotelview() throws Exception {
        this.registerHandler(Incoming.Quit, QuitEvent.class);
        this.registerHandler(Incoming.GetBonusRareInfo, GetBonusRareInfoEvent.class);
        this.registerHandler(Incoming.GetPromoArticles, GetPromoArticlesEvent.class);
        this.registerHandler(Incoming.GetCurrentTimingCode, GetCurrentTimingCodeEvent.class);
        this.registerHandler(Incoming.GetLimitedOfferAppearingNext, GetLimitedOfferAppearingNextEvent.class);
        this.registerHandler(Incoming.GetSecondsUntil, GetSecondsUntilEvent.class);
    }

    private void registerInventory() throws Exception {
        this.registerHandler(Incoming.GetBadges, GetBadgesEvent.class);
        this.registerHandler(Incoming.GetBotInventory, GetBotInventoryEvent.class);
        this.registerHandler(Incoming.RequestFurniInventory, RequestFurniInventoryEvent.class);
        this.registerHandler(Incoming.RequestFurniInventoryWhenNotInRoom, RequestFurniInventoryEvent.class);
        this.registerHandler(Incoming.GetPetInventory, GetPetInventoryEvent.class);
    }

    void registerRooms() throws Exception {
        this.registerHandler(Incoming.OpenFlatConnection, OpenFlatConnectionEvent.class);
        this.registerHandler(Incoming.GetFurnitureAliases, GetHeightMapEvent.class);
        this.registerHandler(Incoming.GetHeightMap, GetHeightMapEvent.class);
        this.registerHandler(Incoming.RateFlat, RateFlatEvent.class);
        this.registerHandler(Incoming.GetGuestRoom, GetGuestRoomEvent.class);
        this.registerHandler(Incoming.SaveRoomSettings, SaveRoomSettingsEvent.class);
        this.registerHandler(Incoming.PlaceObject, PlaceObjectEvent.class);
        this.registerHandler(Incoming.MoveObject, MoveObjectEvent.class);
        this.registerHandler(Incoming.MoveWallItem, MoveWallItemEvent.class);
        this.registerHandler(Incoming.PickupObject, PickupObjectEvent.class);
        this.registerHandler(Incoming.RequestRoomPropertySet, RequestRoomPropertySetEvent.class);
        this.registerHandler(Incoming.StartTyping, StartTypingEvent.class);
        this.registerHandler(Incoming.CancelTyping, CancelTypingEvent.class);
        this.registerHandler(Incoming.UseFurniture, UseFurnitureEvent.class);
        this.registerHandler(Incoming.UseWallItem, UseWallItemEvent.class);
        this.registerHandler(Incoming.SetRoomBackgroundColorData, SetRoomBackgroundColorDataEvent.class);
        this.registerHandler(Incoming.SetMannequinName, SetMannequinNameEvent.class);
        this.registerHandler(Incoming.SetMannequinFigure, SetMannequinFigureEvent.class);
        this.registerHandler(Incoming.SetClothingChangeData, SetClothingChangeDataEvent.class);
        this.registerHandler(Incoming.SetObjectData, SetObjectDataEvent.class);
        this.registerHandler(Incoming.GetRoomSettings, GetRoomSettingsEvent.class);
        this.registerHandler(Incoming.RoomDimmerGetPresets, RoomDimmerGetPresetsEvent.class);
        this.registerHandler(Incoming.RoomDimmerChangeState, RoomDimmerChangeStateEvent.class);
        this.registerHandler(Incoming.DropCarryItem, DropCarryItemEvent.class);
        this.registerHandler(Incoming.LookTo, LookToEvent.class);
        this.registerHandler(Incoming.Chat, ChatEvent.class);
        this.registerHandler(Incoming.Shout, ShoutEvent.class);
        this.registerHandler(Incoming.Whisper, WhisperEvent.class);
        this.registerHandler(Incoming.AvatarExpression, AvatarExpressionEvent.class);
        this.registerHandler(Incoming.ChangePosture, ChangePostureEvent.class);
        this.registerHandler(Incoming.Dance, DanceEvent.class);
        this.registerHandler(Incoming.Sign, SignEvent.class);
        this.registerHandler(Incoming.MoveAvatar, MoveAvatarEvent.class);
        this.registerHandler(Incoming.RespectUser, RespectUserEvent.class);
        this.registerHandler(Incoming.AssignRights, AssignRightsEvent.class);
        this.registerHandler(Incoming.RemoveOwnRoomRightsRoom, RemoveOwnRoomRightsRoomEvent.class);
        this.registerHandler(Incoming.GetFlatControllers, GetFlatControllersEvent.class);
        this.registerHandler(Incoming.RemoveAllRights, RemoveAllRightsEvent.class);
        this.registerHandler(Incoming.RemoveRights, RemoveRightsEvent.class);
        this.registerHandler(Incoming.PlaceBot, PlaceBotEvent.class);
        this.registerHandler(Incoming.RemoveBotFromFlat, RemoveBotFromFlatEvent.class);
        this.registerHandler(Incoming.CommandBot, CommandBotEvent.class);
        this.registerHandler(Incoming.GetBotCommandConfigurationData, GetBotCommandConfigurationDataEvent.class);
        this.registerHandler(Incoming.ThrowDice, ThrowDiceEvent.class);
        this.registerHandler(Incoming.DiceOff, DiceOffEvent.class);
        this.registerHandler(Incoming.SpinWheelOfFortune, SpinWheelOfFortuneEvent.class);
        this.registerHandler(Incoming.CreditFurniRedeem, CreditFurniRedeemEvent.class);
        this.registerHandler(Incoming.PlacePet, PlacePetEvent.class);
        this.registerHandler(Incoming.KickUser, KickUserEvent.class);
        this.registerHandler(Incoming.SetCustomStackingHeight, SetCustomStackingHeightEvent.class);
        this.registerHandler(Incoming.EnterOneWayDoor, EnterOneWayDoorEvent.class);
        this.registerHandler(Incoming.LetUserIn, LetUserInEvent.class);
        this.registerHandler(Incoming.CustomizeAvatarWithFurni, CustomizeAvatarWithFurniEvent.class);
        this.registerHandler(Incoming.PlacePostIt, PlacePostItEvent.class);
        this.registerHandler(Incoming.GetItemData, GetItemDataEvent.class);
        this.registerHandler(Incoming.SetItemData, SetItemDataEvent.class);
        this.registerHandler(Incoming.RemoveItem, RemoveItemEvent.class);
        this.registerHandler(Incoming.RoomDimmerSavePreset, RoomDimmerSavePresetEvent.class);
        this.registerHandler(Incoming.RentableSpaceRent, RentableSpaceRentEvent.class);
        this.registerHandler(Incoming.RentableSpaceCancelRent, RentableSpaceCancelRentEvent.class);
        this.registerHandler(Incoming.UpdateHomeRoom, UpdateHomeRoomEvent.class);
        this.registerHandler(Incoming.PassCarryItem, PassCarryItemEvent.class);
        this.registerHandler(Incoming.MuteAllInRoom, MuteAllInRoomEvent.class);
        this.registerHandler(Incoming.GetCustomRoomFilter, GetCustomRoomFilterEvent.class);
        this.registerHandler(Incoming.UpdateRoomFilter, UpdateRoomFilterEvent.class);
        this.registerHandler(Incoming.ToggleStaffPick, ToggleStaffPickEvent.class);
        this.registerHandler(Incoming.GetBannedUsersFromRoom, GetBannedUsersFromRoomEvent.class);
        this.registerHandler(Incoming.GetOfficialSongId, GetOfficialSongIdEvent.class);
        this.registerHandler(Incoming.GetSongInfo, GetSongInfoEvent.class);
        this.registerHandler(Incoming.AddJukeboxDisk, AddJukeboxDiskEvent.class);
        this.registerHandler(Incoming.RemoveJukeboxDisk, RemoveJukeboxDiskEvent.class);
        this.registerHandler(Incoming.GetNowPlaying, GetNowPlayingEvent.class);
        this.registerHandler(Incoming.GetUserSongDisks, GetUserSongDisksEvent.class);
        this.registerHandler(Incoming.GetJukeboxPlayList, GetJukeboxPlayListEvent.class);
        this.registerHandler(Incoming.AddSpamWallPostIt, AddSpamWallPostItEvent.class);
        this.registerHandler(Incoming.GetRoomAdPurchaseInfo, GetRoomAdPurchaseInfoEvent.class);
        this.registerHandler(Incoming.PurchaseRoomAd, PurchaseRoomAdEvent.class);
        this.registerHandler(Incoming.EditEvent, EditEventEvent.class);
        this.registerHandler(Incoming.IgnoreUser, IgnoreUserEvent.class);
        this.registerHandler(Incoming.UnignoreUser, UnignoreUserEvent.class);
        this.registerHandler(Incoming.MuteUser, MuteUserEvent.class);
        this.registerHandler(Incoming.BanUserWithDuration, BanUserWithDurationEvent.class);
        this.registerHandler(Incoming.UnbanUserFromRoom, UnbanUserFromRoomEvent.class);
        this.registerHandler(Incoming.GetYoutubeDisplayStatus, GetYoutubeDisplayStatusEvent.class);
        this.registerHandler(Incoming.ControlYoutubeDisplayPlayback, ControlYoutubeDisplayPlaybackEvent.class);
        this.registerHandler(Incoming.SetYoutubeDisplayPlaylist, SetYoutubeDisplayPlaylistEvent.class);
        this.registerHandler(Incoming.AddFavouriteRoom, AddFavouriteRoomEvent.class);
        this.registerHandler(Incoming.FriendFurniConfirmLock, FriendFurniConfirmLockEvent.class);
        this.registerHandler(Incoming.DeleteFavouriteRoom, DeleteFavouriteRoomEvent.class);
        this.registerHandler(Incoming.SetRandomState, SetRandomStateEvent.class);
        this.registerHandler(Incoming.SetAreaHideData, SetAreaHideDataEvent.class);
    }

    void registerPolls() throws Exception {
        this.registerHandler(Incoming.PollReject, PollRejectEvent.class);
        this.registerHandler(Incoming.PollStart, PollStartEvent.class);
        this.registerHandler(Incoming.PollAnswer, PollAnswerEvent.class);
    }

    void registerModTool() throws Exception {
        this.registerHandler(Incoming.GetModeratorRoomInfo, GetModeratorRoomInfoEvent.class);
        this.registerHandler(Incoming.GetRoomChatlog, GetRoomChatlogEvent.class);
        this.registerHandler(Incoming.GetModeratorUserInfo, GetModeratorUserInfoEvent.class);
        this.registerHandler(Incoming.PickIssues, PickIssuesEvent.class);
        this.registerHandler(Incoming.CloseIssues, CloseIssuesEvent.class);
        this.registerHandler(Incoming.ReleaseIssues, ReleaseIssuesEvent.class);
        this.registerHandler(Incoming.ModMessage, ModMessageEvent.class);
        this.registerHandler(Incoming.ModKick, ModKickEvent.class);
        this.registerHandler(Incoming.ModeratorAction, ModeratorActionEvent.class);
        this.registerHandler(Incoming.ModerateRoom, ModerateRoomEvent.class);
        this.registerHandler(Incoming.GetRoomVisits, GetRoomVisitsEvent.class);
        this.registerHandler(Incoming.GetCfhChatlog, GetCfhChatlogEvent.class);
        this.registerHandler(Incoming.GetUserChatlog, GetUserChatlogEvent.class);
        this.registerHandler(Incoming.ModAlert, ModAlertEvent.class);
        this.registerHandler(Incoming.ModMute, ModMuteEvent.class);
        this.registerHandler(Incoming.ModBan, ModBanEvent.class);
        this.registerHandler(Incoming.ModTradingLock, ModTradingLockEvent.class);
        this.registerHandler(Incoming.ModToolSanction, ModToolSanctionEvent.class);
        this.registerHandler(Incoming.CloseIssueDefaultAction, CloseIssueDefaultActionEvent.class);

        this.registerHandler(Incoming.GetPendingCallsForHelp, GetPendingCallsForHelpEvent.class);
        this.registerHandler(Incoming.GetGuideReportingStatus, GetGuideReportingStatusEvent.class);
        this.registerHandler(Incoming.ChatReviewSessionCreate, ChatReviewSessionCreateEvent.class);
        this.registerHandler(Incoming.CallForHelp, CallForHelpEvent.class);
        this.registerHandler(Incoming.CallForHelpFromIM, CallForHelpFromIMEvent.class);
        this.registerHandler(Incoming.CallForHelpFromForumThread, CallForHelpFromForumThreadEvent.class);
        this.registerHandler(Incoming.CallForHelpFromForumMessage, CallForHelpFromForumMessageEvent.class);
        this.registerHandler(Incoming.CallForHelpFromPhoto, CallForHelpFromPhotoEvent.class);
    }

    void registerTrading() throws Exception {
        this.registerHandler(Incoming.OpenTrading, OpenTradingEvent.class);
        this.registerHandler(Incoming.AddItemToTrade, AddItemToTradeEvent.class);
        this.registerHandler(Incoming.AddItemsToTrade, AddItemsToTradeEvent.class);
        this.registerHandler(Incoming.RemoveItemFromTrade, RemoveItemFromTradeEvent.class);
        this.registerHandler(Incoming.AcceptTrading, AcceptTradingEvent.class);
        this.registerHandler(Incoming.UnacceptTrading, UnacceptTradingEvent.class);
        this.registerHandler(Incoming.ConfirmAcceptTrading, ConfirmAcceptTradingEvent.class);
        this.registerHandler(Incoming.CloseTrading, CloseTradingEvent.class);
        this.registerHandler(Incoming.ConfirmDeclineTrading, ConfirmDeclineTradingEvent.class);
    }

    void registerGuilds() throws Exception {
        this.registerHandler(Incoming.GetGuildCreationInfo, GetGuildCreationInfoEvent.class);
        this.registerHandler(Incoming.GetGuildEditorData, GetGuildEditorDataEvent.class);
        this.registerHandler(Incoming.CreateGuild, CreateGuildEvent.class);
        this.registerHandler(Incoming.GetHabboGroupDetails, GetHabboGroupDetailsEvent.class);
        this.registerHandler(Incoming.GetGuildEditInfo, GetGuildEditInfoEvent.class);
        this.registerHandler(Incoming.GetGuildMembers, GetGuildMembersEvent.class);
        this.registerHandler(Incoming.JoinHabboGroup, JoinHabboGroupEvent.class);
        this.registerHandler(Incoming.UpdateGuildIdentity, UpdateGuildIdentityEvent.class);
        this.registerHandler(Incoming.UpdateGuildBadge, UpdateGuildBadgeEvent.class);
        this.registerHandler(Incoming.UpdateGuildColors, UpdateGuildColorsEvent.class);
        this.registerHandler(Incoming.RemoveAdminRightsFromMember, RemoveAdminRightsFromMemberEvent.class);
        this.registerHandler(Incoming.KickMember, KickMemberEvent.class);
        this.registerHandler(Incoming.UpdateGuildSettings, UpdateGuildSettingsEvent.class);
        this.registerHandler(Incoming.ApproveMembershipRequest, ApproveMembershipRequestEvent.class);
        this.registerHandler(Incoming.RejectMembershipRequest, RejectMembershipRequestEvent.class);
        this.registerHandler(Incoming.AddAdminRightsToMember, AddAdminRightsToMemberEvent.class);
        this.registerHandler(Incoming.SelectFavouriteHabboGroup, SelectFavouriteHabboGroupEvent.class);
        this.registerHandler(Incoming.GetGuildMemberships, GetGuildMembershipsEvent.class);
        this.registerHandler(Incoming.GetGuildFurniContextMenuInfo, GetGuildFurniContextMenuInfoEvent.class);
        this.registerHandler(Incoming.GetMemberGuildItemCount, GetMemberGuildItemCountEvent.class);
        this.registerHandler(Incoming.DeselectFavouriteHabboGroup, DeselectFavouriteHabboGroupEvent.class);
        this.registerHandler(Incoming.DeactivateGuild, DeactivateGuildEvent.class);
        this.registerHandler(Incoming.GetForumsList, GetForumsListEvent.class);
        this.registerHandler(Incoming.GetThreads, GetThreadsEvent.class);
        this.registerHandler(Incoming.GetForumStats, GetForumStatsEvent.class);
        this.registerHandler(Incoming.PostMessage, PostMessageEvent.class);
        this.registerHandler(Incoming.UpdateForumSettings, UpdateForumSettingsEvent.class);
        this.registerHandler(Incoming.GetMessages, GetMessagesEvent.class);
        this.registerHandler(Incoming.ModerateMessage, ModerateMessageEvent.class);
        this.registerHandler(Incoming.ModerateThread, ModerateThreadEvent.class);
        this.registerHandler(Incoming.UpdateThread, UpdateThreadEvent.class);
        this.registerHandler(Incoming.GetHabboGroupBadges, GetHabboGroupBadgesEvent.class);
    }

    void registerPets() throws Exception {
        this.registerHandler(Incoming.GetPetInfo, GetPetInfoEvent.class);
        this.registerHandler(Incoming.RemovePetFromFlat, RemovePetFromFlatEvent.class);
        this.registerHandler(Incoming.RespectPet, RespectPetEvent.class);
        this.registerHandler(Incoming.GetPetCommands, GetPetCommandsEvent.class);
        this.registerHandler(Incoming.CustomizePetWithFurni, CustomizePetWithFurniEvent.class);
        this.registerHandler(Incoming.TogglePetRidingPermission, TogglePetRidingPermissionEvent.class);
        this.registerHandler(Incoming.MountPet, MountPetEvent.class);
        this.registerHandler(Incoming.RemoveSaddleFromPet, RemoveSaddleFromPetEvent.class);
        this.registerHandler(Incoming.TogglePetBreedingPermission, TogglePetBreedingPermissionEvent.class);
        this.registerHandler(Incoming.CompostPlant, CompostPlantEvent.class);
        this.registerHandler(Incoming.BreedPets, BreedPetsEvent.class);
        this.registerHandler(Incoming.MovePet, MovePetEvent.class);
        this.registerHandler(Incoming.OpenPetPackage, OpenPetPackageEvent.class);
        this.registerHandler(Incoming.CancelPetBreeding, CancelPetBreedingEvent.class);
        this.registerHandler(Incoming.ConfirmPetBreeding, ConfirmPetBreedingEvent.class);
    }

    void registerWired() throws Exception {
        this.registerHandler(Incoming.UpdateTrigger, UpdateTriggerEvent.class);
        this.registerHandler(Incoming.UpdateAction, UpdateActionEvent.class);
        this.registerHandler(Incoming.UpdateCondition, UpdateConditionEvent.class);
        this.registerHandler(Incoming.ApplySnapshot, ApplySnapshotEvent.class);
    }

    void registerUnknown() throws Exception {
        this.registerHandler(Incoming.GetResolutionAchievements, GetResolutionAchievementsEvent.class);
        this.registerHandler(Incoming.GetTalentTrack, GetTalentTrackEvent.class);
        this.registerHandler(Incoming.GetBadgePointLimits, GetBadgePointLimitsEvent.class);
        this.registerHandler(Incoming.GetCfhStatus, GetCfhStatusEvent.class);
    }

    void registerFloorPlanEditor() throws Exception {
        this.registerHandler(Incoming.UpdateFloorProperties, UpdateFloorPropertiesEvent.class);
        this.registerHandler(Incoming.GetOccupiedTiles, GetOccupiedTilesEvent.class);
        this.registerHandler(Incoming.GetRoomEntryTile, GetRoomEntryTileEvent.class);
    }

    void registerAchievements() throws Exception {
        this.registerHandler(Incoming.GetAchievements, GetAchievementsEvent.class);
    }

    void registerGuides() throws Exception {
        this.registerHandler(Incoming.GuideSessionOnDutyUpdate, GuideSessionOnDutyUpdateEvent.class);
        this.registerHandler(Incoming.GuideSessionCreate, GuideSessionCreateEvent.class);
        this.registerHandler(Incoming.GuideSessionIsTyping, GuideSessionIsTypingEvent.class);
        this.registerHandler(Incoming.GuideSessionReport, GuideSessionReportEvent.class);
        this.registerHandler(Incoming.GuideSessionFeedback, GuideSessionFeedbackEvent.class);
        this.registerHandler(Incoming.GuideSessionMessage, GuideSessionMessageEvent.class);
        this.registerHandler(Incoming.GuideSessionRequesterCancels, GuideSessionRequesterCancelsEvent.class);
        this.registerHandler(Incoming.GuideSessionGuideDecides, GuideSessionGuideDecidesEvent.class);
        this.registerHandler(Incoming.GuideSessionInviteRequester, GuideSessionInviteRequesterEvent.class);
        this.registerHandler(Incoming.GuideSessionGetRequesterRoom, GuideSessionGetRequesterRoomEvent.class);
        this.registerHandler(Incoming.GuideSessionResolved, GuideSessionResolvedEvent.class);

        this.registerHandler(Incoming.ChatReviewGuideDetached, ChatReviewGuideDetachedEvent.class);
        this.registerHandler(Incoming.ChatReviewGuideDecidesOnOffer, ChatReviewGuideDecidesOnOfferEvent.class);
        this.registerHandler(Incoming.ChatReviewGuideVote, ChatReviewGuideVoteEvent.class);
    }

    void registerCrafting() throws Exception {
        this.registerHandler(Incoming.GetCraftableProducts, GetCraftableProductsEvent.class);
        this.registerHandler(Incoming.GetCraftingRecipe, GetCraftingRecipeEvent.class);
        this.registerHandler(Incoming.Craft, CraftEvent.class);
        this.registerHandler(Incoming.CraftSecret, CraftSecretEvent.class);
        this.registerHandler(Incoming.GetCraftingRecipesAvailable, GetCraftingRecipesAvailableEvent.class);
    }

    void registerCamera() throws Exception {
        this.registerHandler(Incoming.RenderRoom, RenderRoomEvent.class);
        this.registerHandler(Incoming.RequestCameraConfiguration, RequestCameraConfigurationEvent.class);
        this.registerHandler(Incoming.PurchasePhoto, PurchasePhotoEvent.class);
        this.registerHandler(Incoming.RenderRoomThumbnail, RenderRoomThumbnailEvent.class);
        this.registerHandler(Incoming.PublishPhoto, PublishPhotoEvent.class);
    }
}