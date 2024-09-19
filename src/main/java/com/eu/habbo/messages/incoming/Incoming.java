package com.eu.habbo.messages.incoming;

/**
 * Packet list shared by <a href="https://sulek.dev/">Sulek</a> from <a href="https://github.com/UnfamiliarLegacy">UnfamiliarLegacy (Mikee)</a>.
 */
public enum Incoming {
    // com.sulake.habbo.communication.messages.outgoing.advertisement
    GetInterstitial,
    InterstitialShown,

    // com.sulake.habbo.communication.messages.outgoing.avatar
    ChangeUserNameInRoom,
    ChangeUserName,
    CheckUserName,
    GetWardrobe,
    SaveWardrobeOutfit,

    // com.sulake.habbo.communication.messages.outgoing.camera
    RequestCameraConfiguration,
    PhotoCompetition,
    PublishPhoto,
    PurchasePhoto,
    RenderRoom,
    RenderRoomThumbnail,

    // com.sulake.habbo.communication.messages.outgoing.campaign
    OpenCampaignCalendarDoorAsStaff,
    OpenCampaignCalendarDoor,

    // com.sulake.habbo.communication.messages.outgoing.catalog
    BuildersClubPlaceRoomItem,
    BuildersClubPlaceWallItem,
    BuildersClubQueryFurniCount,
    GetBonusRareInfo,
    GetBundleDiscountRuleset,
    GetCatalogIndex,
    GetCatalogPage,
    GetCatalogPageExpiration, // NO-OP WIN63-2021
    GetCatalogPageWithEarliestExpiry,
    GetClubGift,
    GetClubOffers,
    GetDirectClubBuyAvailable, // NO-OP WIN63-2021
    GetGiftWrappingConfiguration,
    GetHabboBasicMembershipExtendOffer, // NO-OP WIN63-2021
    GetHabboClubExtendOffer,
    GetIsOfferGiftable,
    GetLimitedOfferAppearingNext,
    GetNextTargetedOffer,
    GetProductOffer,
    GetRoomAdPurchaseInfo,
    GetSeasonalCalendarDaily,
    GetSellablePetPalettes,
    GetSnowWarGameTokensOffer,
    GetTargetedOffer, // NO-OP WIN63-2021
    MarkCatalogNewAdditionsPageOpened,
    PurchaseBasicMembershipExtension,
    PurchaseFromCatalogAsGift,
    PurchaseFromCatalog,
    PurchaseRoomAd,
    PurchaseSnowWarGameTokensOffer,
    PurchaseTargetedOffer,
    PurchaseVipMembershipExtension,
    RedeemVoucher,
    RoomAdPurchaseInitiated,
    SelectClubGift,
    SetTargetedOfferState,
    ShopTargetedOfferViewed,

    // com.sulake.habbo.communication.messages.outgoing.competition
    ForwardToACompetitionRoom,
    ForwardToASubmittableRoom,
    ForwardToRandomCompetitionRoom,
    GetCurrentTimingCode,
    GetIsUserPartOfCompetition,
    GetSecondsUntil,
    RoomCompetitionInit,
    SubmitRoomToCompetition,
    VoteForRoom,

    // com.sulake.habbo.communication.messages.outgoing.collectibles
    GetCollectibleMintTokens,
    GetCollectibleMintableItemTypes,
    GetCollectibleMintingEnabled,
    GetCollectibleWalletAddresses,
    GetCollectorScore,
    GetMintTokenOffers,
    GetNftCollections,
    MintItem,
    NftCollectiblesClaimBonusItem,
    NftCollectiblesClaimRewardItem,
    PurchaseMintToken,
    GetNftTransferFee,
    NftTransferAssets,

    // com.sulake.habbo.communication.messages.outgoing.crafting
    Craft,
    CraftSecret,
    GetCraftableProducts,
    GetCraftingRecipe,
    GetCraftingRecipesAvailable,

    // com.sulake.habbo.communication.messages.outgoing.feedback
    UserFeedback, // AIR63

    // com.sulake.habbo.communication.messages.outgoing.friendfurni
    FriendFurniConfirmLock,

    // com.sulake.habbo.communication.messages.outgoing.friendlist
    AcceptFriend,
    DeclineFriend,
    FindNewFriends,
    FollowFriend,
    FriendListUpdate,
    GetFriendRequests,
    HabboSearch,
    MessengerInit,
    RemoveFriend,
    RequestFriend,
    SendMsg,
    SendRoomInvite,
    SetRelationshipStatus,
    VisitUser,
    GetMessengerHistory,

    // com.sulake.habbo.communication.messages.outgoing.game.arena
    Game2ExitGame,
    Game2GameChat,
    Game2LoadStageReady,
    Game2PlayAgain,

    // com.sulake.habbo.communication.messages.outgoing.game.directory
    Game2CheckGameDirectoryStatus,
    Game2GetAccountGameStatus,
    Game2LeaveGame,
    Game2QuickJoinGame,
    Game2StartSnowWar,

    // com.sulake.habbo.communication.messages.outgoing.game.ingame
    Game2MakeSnowball,
    Game2RequestFullStatusUpdate,
    Game2SetUserMoveTarget,
    Game2ThrowSnowballAtHuman,
    Game2ThrowSnowballAtPosition,

    // com.sulake.habbo.communication.messages.outgoing.game.lobby
    GetResolutionAchievements,
    GetUserGameAchievements, // NO-OP WIN63-2021
    ResetResolutionAchievement,

    // com.sulake.habbo.communication.messages.outgoing.game.score
    Game2GetFriendsLeaderboard,
    Game2GetTotalGroupLeaderboard,
    Game2GetTotalLeaderboard,
    Game2GetWeeklyFriendsLeaderboard,
    Game2GetWeeklyGroupLeaderboard,
    Game2GetWeeklyLeaderboard,
    GetFriendsWeeklyCompetitiveLeaderboard, // NO-OP WIN63-2021
    GetWeeklyCompetitiveLeaderboard, // NO-OP WIN63-2021
    GetWeeklyGameReward, // NO-OP WIN63-2021
    GetWeeklyGameRewardWinners, // NO-OP WIN63-2021

    // com.sulake.habbo.communication.messages.outgoing.gifts
    GetGift, // NO-OP WIN63-2021
    ResetPhoneNumberState,
    SetPhoneNumberVerificationStatus,
    TryPhoneNumber,
    VerifyCode,

    // com.sulake.habbo.communication.messages.outgoing.groupforums
    GetForumsList,
    GetForumStats,
    GetMessages,
    GetThread,
    GetThreads,
    GetUnreadForumsCount,
    ModerateMessage,
    ModerateThread,
    PostMessage,
    UpdateForumReadMarker,
    UpdateForumSettings,
    UpdateThread,

    // com.sulake.habbo.communication.messages.outgoing.handshake
    ClientHello,
    ClientResumed, // AIR63
    ClientSuspended, // AIR63
    CompleteDiffieHandshake,
    Disconnect,
    InfoRetrieve,
    InitDiffieHandshake,
    Pong,
    SSOTicket,
    TryLogin,
    UniqueID,
    VersionCheck,

    // com.sulake.habbo.communication.messages.outgoing.help
    CallForHelpFromForumMessage,
    CallForHelpFromForumThread,
    CallForHelpFromIM,
    CallForHelpFromPhoto,
    CallForHelpFromSelfie,
    CallForHelp,
    ChatReviewGuideDecidesOnOffer,
    ChatReviewGuideDetached,
    ChatReviewGuideVote,
    ChatReviewSessionCreate,
    DeletePendingCallsForHelp,
    GetCfhStatus,
    GetClientFaqs, // NO-OP WIN63-2021
    GetFaqCategories, // NO-OP WIN63-2021
    GetFaqCategory, // NO-OP WIN63-2021
    GetFaqText, // NO-OP WIN63-2021
    GetGuideReportingStatus,
    GetPendingCallsForHelp,
    GetQuizQuestions,
    GuideSessionCreate,
    GuideSessionFeedback,
    GuideSessionGetRequesterRoom,
    GuideSessionGuideDecides,
    GuideSessionInviteRequester,
    GuideSessionIsTyping,
    GuideSessionMessage,
    GuideSessionOnDutyUpdate,
    GuideSessionReport,
    GuideSessionRequesterCancels,
    GuideSessionResolved,
    PostQuizAnswers,
    SearchFaqs, // NO-OP WIN63-2021

    // com.sulake.habbo.communication.messages.outgoing.hotlooks
    GetHotLooks,

    // com.sulake.habbo.communication.messages.outgoing.inventory.achievements
    GetAchievements,

    // com.sulake.habbo.communication.messages.outgoing.inventory.avatareffect
    AvatarEffectActivated,
    AvatarEffectSelected,

    // com.sulake.habbo.communication.messages.outgoing.inventory.badges
    GetBadgePointLimits,
    GetBadges,
    GetIsBadgeRequestFulfilled,
    RequestABadge,
    SetActivatedBadges,

    // com.sulake.habbo.communication.messages.outgoing.inventory.bots
    GetBotInventory,

    // com.sulake.habbo.communication.messages.outgoing.inventory.furni
    RequestFurniInventory,
    RequestFurniInventoryWhenNotInRoom,
    RequestRoomPropertySet,

    // com.sulake.habbo.communication.messages.outgoing.inventory.pets
    CancelPetBreeding,
    ConfirmPetBreeding,
    GetPetInventory,

    // com.sulake.habbo.communication.messages.outgoing.inventory.purse
    GetCreditsInfo,

    // com.sulake.habbo.communication.messages.outgoing.inventory.trading
    AcceptTrading,
    AddItemsToTrade,
    AddItemToTrade,
    CloseTrading,
    ConfirmAcceptTrading,
    ConfirmDeclineTrading,
    OpenTrading,
    RemoveItemFromTrade,
    UnacceptTrading,
    SilverFee,

    // com.sulake.habbo.communication.messages.outgoing.landingview
    GetPromoArticles,

    // com.sulake.habbo.communication.messages.outgoing.landingview.votes
    CommunityGoalVote,

    // com.sulake.habbo.communication.messages.outgoing.marketplace
    BuyMarketplaceOffer,
    BuyMarketplaceTokens,
    CancelMarketplaceOffer,
    GetMarketplaceCanMakeOffer,
    GetMarketplaceConfiguration,
    GetMarketplaceItemStats,
    GetMarketplaceOffers,
    GetMarketplaceOwnOffers,
    MakeOffer,
    RedeemMarketplaceOfferCredits,

    // com.sulake.habbo.communication.messages.outgoing.moderator
    CloseIssueDefaultAction,
    CloseIssues,
    DefaultSanction,
    GetCfhChatlog,
    GetModeratorRoomInfo,
    GetModeratorUserInfo,
    GetRoomChatlog,
    GetRoomVisits,
    GetUserChatlog,
    ModAlert,
    ModBan,
    ModerateRoom,
    ModeratorAction,
    ModKick,
    ModMessage,
    ModMute,
    ModToolPreferences,
    ModToolSanction,
    ModTradingLock,
    PickIssues,
    ReleaseIssues,

    // com.sulake.habbo.communication.messages.outgoing.mysterybox
    MysteryBoxWaitingCanceled,

    // com.sulake.habbo.communication.messages.outgoing.navigator
    AddFavouriteRoom,
    CancelEvent,
    CanCreateRoom,
    CompetitionRoomsSearch,
    ConvertGlobalRoomId,
    CreateFlat,
    DeleteFavouriteRoom,
    EditEvent,
    ForwardToARandomPromotedRoom,
    ForwardToSomeRoom,
    GetCategoriesWithUserCount, // NO-OP WIN63-2021
    GetGuestRoom,
    GetOfficialRooms,
    GetPopularRoomTags,
    GetUserEventCats,
    GetUserFlatCats,
    GuildBaseSearch,
    MyFavouriteRoomsSearch,
    MyFrequentRoomHistorySearch,
    MyFriendsRoomsSearch,
    MyGuildBasesSearch,
    MyRecommendedRooms,
    MyRoomHistorySearch,
    MyRoomRightsSearch,
    MyRoomsSearch,
    PopularRoomsSearch,
    RateFlat,
    RemoveOwnRoomRightsRoom,
    RoomAdEventTabAdClicked,
    RoomAdEventTabViewed,
    RoomAdSearch,
    RoomsWhereMyFriendsAreSearch,
    RoomsWithHighestScoreSearch,
    RoomTextSearch,
    SetRoomSessionTags,
    ToggleStaffPick,
    UpdateHomeRoom,
    UpdateRoomThumbnail, // NO-OP WIN63-2021

    // com.sulake.habbo.communication.messages.outgoing.newnavigator
    NavigatorAddCollapsedCategory,
    NavigatorAddSavedSearch,
    NavigatorDeleteSavedSearch,
    NavigatorRemoveCollapsedCategory,
    NavigatorSetSearchCodeViewMode,
    NewNavigatorInit,
    NewNavigatorSearch,

    // com.sulake.habbo.communication.messages.outgoing.nft
    GetNftCredits,
    GetSelectedNftWardrobeOutfit,
    GetSilver,
    GetUserNftWardrobe,
    SaveUserNftWardrobe,

    // com.sulake.habbo.communication.messages.outgoing.notifications
    ResetUnseenItemIds,
    ResetUnseenItems,

    // com.sulake.habbo.communication.messages.outgoing.nux
    NewUserExperienceGetGifts,
    NewUserExperienceScriptProceed,
    SelectInitialRoom,

    // com.sulake.habbo.communication.messages.outgoing.poll
    PollAnswer,
    PollReject,
    PollStart,

    // com.sulake.habbo.communication.messages.outgoing.preferences
    SetChatPreferences,
    SetChatStylePreference,
    SetIgnoreRoomInvites,
    SetNewNavigatorWindowPreferences,
    SetRoomCameraPreferences,
    SetSoundSettings,
    SetUIFlags,

    // com.sulake.habbo.communication.messages.outgoing.quest
    AcceptQuest,
    ActivateQuest,
    CancelQuest,
    FriendRequestQuestComplete,
    GetCommunityGoalEarnedPrizes, // NO-OP WIN63-2021
    GetCommunityGoalHallOfFame,
    GetCommunityGoalProgress,
    GetConcurrentUsersGoalProgress,
    GetConcurrentUsersReward,
    GetDailyQuest,
    GetQuests,
    GetSeasonalQuestsOnly,
    OpenQuestTracker,
    RedeemCommunityGoalPrize, // NO-OP WIN63-2021
    RejectQuest,
    StartCampaign,

    // com.sulake.habbo.communication.messages.outgoing.recycler
    GetRecyclerStatus,
    GetRecyclerPrizes,
    RecycleItems,

    // com.sulake.habbo.communication.messages.outgoing.register
    UpdateFigureData,

    // com.sulake.habbo.communication.messages.outgoing.room.action
    AmbassadorAlert,
    AssignRights,
    BanUserWithDuration,
    KickUser,
    LetUserIn,
    MuteAllInRoom,
    MuteUser,
    RemoveAllRights,
    RemoveRights,
    UnbanUserFromRoom,
    UnmuteUser,

    // com.sulake.habbo.communication.messages.outgoing.room.avatar
    AvatarExpression,
    ChangeMotto,
    ChangePosture,
    CustomizeAvatarWithFurni,
    Dance,
    DropCarryItem,
    LookTo,
    PassCarryItem,
    PassCarryItemToPet,
    Sign,

    // com.sulake.habbo.communication.messages.outgoing.room.bots
    CommandBot,
    GetBotCommandConfigurationData,

    // com.sulake.habbo.communication.messages.outgoing.room.chat
    StartTyping,
    CancelTyping,
    Chat,
    Shout,
    Whisper,

    // com.sulake.habbo.communication.messages.outgoing.room.engine
    ClickFurni,
    CompostPlant,
    GetFurnitureAliases,
    GetHeightMap,
    GetItemData,
    GetPetCommands,
    GiveSupplementToPet,
    HarvestPet,
    MountPet,
    MoveAvatar,
    MoveObject,
    MovePet,
    MoveWallItem,
    Pet, // NO-OP WIN63-2021
    PickupObject,
    PlaceBot,
    PlaceObject,
    PlacePet,
    RemoveBotFromFlat,
    RemoveItem,
    RemovePetFromFlat,
    RemoveSaddleFromPet,
    SetClothingChangeData,
    SetItemData,
    SetObjectData,
    TogglePetBreedingPermission,
    TogglePetRidingPermission,
    UseFurniture,
    UseWallItem,

    // com.sulake.habbo.communication.messages.outgoing.room.furniture
    AddSpamWallPostIt,
    ControlYoutubeDisplayPlayback,
    CreditFurniRedeem,
    DiceOff,
    EnterOneWayDoor,
    ExtendRentOrBuyoutFurni,
    ExtendRentOrBuyoutStripItem,
    GetGuildFurniContextMenuInfo,
    GetRentOrBuyoutOffer,
    GetYoutubeDisplayStatus,
    OpenMysteryTrophy,
    OpenPetPackage,
    PlacePostIt,
    PresentOpen,
    RentableSpaceCancelRent,
    RentableSpaceRent,
    RentableSpaceStatus,
    RoomDimmerChangeState,
    RoomDimmerGetPresets,
    RoomDimmerSavePreset,
    SetCustomStackingHeight,
    SetMannequinFigure,
    SetMannequinName,
    SetRandomState,
    SetRoomBackgroundColorData,
    SetYoutubeDisplayPlaylist,
    SpinWheelOfFortune,
    ThrowDice,
    SetAreaHideData,

    // com.sulake.habbo.communication.messages.outgoing.room.layout
    GetOccupiedTiles,
    GetRoomEntryTile,
    UpdateFloorProperties,

    // com.sulake.habbo.communication.messages.outgoing.room.pets
    BreedPets,
    CustomizePetWithFurni,
    GetPetInfo,
    PetSelected,
    RespectPet,

    // com.sulake.habbo.communication.messages.outgoing.room.session
    ChangeQueue,
    OpenFlatConnection,
    Quit,

    // com.sulake.habbo.communication.messages.outgoing.roomdirectory
    RoomNetworkOpenConnection,

    // com.sulake.habbo.communication.messages.outgoing.roomsettings
    DeleteRoom,
    GetBannedUsersFromRoom,
    GetCustomRoomFilter,
    GetFlatControllers,
    GetRoomSettings,
    SaveRoomSettings,
    UpdateRoomCategoryAndTradeSettings,
    UpdateRoomFilter,

    // com.sulake.habbo.communication.messages.outgoing.sound
    AddJukeboxDisk,
    GetJukeboxPlayList,
    GetNowPlaying,
    GetOfficialSongId,
    GetSongInfo,
    GetSoundMachinePlayList,
    GetSoundSettings,
    GetUserSongDisks,
    RemoveJukeboxDisk,

    // com.sulake.habbo.communication.messages.outgoing.talent
    GetTalentTrackLevel,
    GetTalentTrack,
    GuideAdvertisementRead,

    // com.sulake.habbo.communication.messages.outgoing.tracking
    EventLog,
    LagWarningReport,
    LatencyPingReport,
    LatencyPingRequest,
    PerformanceLog,
    PerformanceTabletLog, // AIR63

    // com.sulake.habbo.communication.messages.outgoing.userclassification
    PeerUsersClassification,
    RoomUsersClassification,

    // com.sulake.habbo.communication.messages.outgoing.userdefinedroomevents
    ApplySnapshot,
    Open,
    UpdateAction,
    UpdateAddon,
    UpdateCondition,
    UpdateSelector,
    UpdateTrigger,
    UpdateVariable,

    // com.sulake.habbo.communication.messages.outgoing.userdefinedroomevents.wiredmenu
    WiredClearErrorLogs,
    WiredGetAllVariableHolders,
    WiredGetAllVariables,
    WiredGetErrorLogs,
    WiredGetRoomSettings,
    WiredGetRoomStats,
    WiredGetVariablesForObject,
    WiredSetObjectVariableValue,
    WiredSetPreferences,
    WiredSetRoomSettings,
    WiredGetAllVariablesHash,
    WiredGetAllVariablesDiffs,

    // com.sulake.habbo.communication.messages.outgoing.users
    AccountSafetyLockGetQuestions, // AIR63
    AccountSafetyLockUnlock, // AIR63
    AddAdminRightsToMember,
    ApproveAllMembershipRequests,
    ApproveMembershipRequest,
    ApproveName,
    ChangeEmail,
    CreateGuild,
    DeactivateGuild,
    DeselectFavouriteHabboGroup,
    GetEmailStatus,
    GetExtendedProfileByName,
    GetExtendedProfile,
    GetGuildCreationInfo,
    GetGuildEditInfo,
    GetGuildEditorData,
    GetGuildMembers,
    GetGuildMemberships,
    GetHabboGroupBadges,
    GetHabboGroupDetails,
    GetIgnoredUsers,
    GetMOTD,
    GetMemberGuildItemCount,
    GetRelationshipStatusInfo,
    GetSelectedBadges,
    GiveStarGemToUser,
    IgnoreUserId,
    IgnoreUser,
    JoinHabboGroup,
    KickMember,
    RejectMembershipRequest,
    RemoveAdminRightsFromMember,
    RespectUser,
    ScrGetKickbackInfo,
    ScrGetUserInfo,
    SelectFavouriteHabboGroup,
    UnblockGroupMember,
    UnignoreUser,
    UpdateGuildBadge,
    UpdateGuildColors,
    UpdateGuildIdentity,
    UpdateGuildSettings,
    GetUserNftChatStyles,

    // com.sulake.habbo.communication.messages.outgoing.vault
    CreditVaultStatus,
    IncomeRewardClaim,
    IncomeRewardStatus,
    WithdrawCreditVault,
}
