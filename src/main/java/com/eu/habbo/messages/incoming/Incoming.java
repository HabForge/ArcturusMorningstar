package com.eu.habbo.messages.incoming;

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
    OpenCampaignCalendarDoorAsStaffComposer,
    OpenCampaignCalendarDoorComposer,

    // com.sulake.habbo.communication.messages.outgoing.catalog
    BuildersClubPlaceRoomItem,
    BuildersClubPlaceWallItem,
    BuildersClubQueryFurniCount,
    GetBonusRareInfo,
    GetBundleDiscountRulesetComposer,
    GetCatalogIndexComposer,
    GetCatalogPageComposer,
    GetCatalogPageExpirationComposer, // NO-OP WIN63-2021
    GetCatalogPageWithEarliestExpiryComposer,
    GetClubGift,
    GetClubOffers,
    GetDirectClubBuyAvailableComposer, // NO-OP WIN63-2021
    GetGiftWrappingConfigurationComposer,
    GetHabboBasicMembershipExtendOfferComposer, // NO-OP WIN63-2021
    GetHabboClubExtendOffer,
    GetIsOfferGiftableComposer,
    GetLimitedOfferAppearingNextComposer,
    GetNextTargetedOfferComposer,
    GetProductOfferComposer,
    GetRoomAdPurchaseInfoComposer,
    GetSeasonalCalendarDailyComposer,
    GetSellablePetPalettesComposer,
    GetSnowWarGameTokensOfferComposer,
    GetTargetedOfferComposer, // NO-OP WIN63-2021
    MarkCatalogNewAdditionsPageOpenedComposer,
    PurchaseBasicMembershipExtensionComposer,
    PurchaseFromCatalogAsGiftComposer,
    PurchaseFromCatalogComposer,
    PurchaseRoomAd,
    PurchaseSnowWarGameTokensOfferComposer,
    PurchaseTargetedOfferComposer,
    PurchaseVipMembershipExtensionComposer,
    RedeemVoucher,
    RoomAdPurchaseInitiatedComposer,
    SelectClubGiftComposer,
    SetTargetedOfferStateComposer,
    ShopTargetedOfferViewedComposer,

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
    CraftComposer,
    CraftSecretComposer,
    GetCraftableProductsComposer,
    GetCraftingRecipeComposer,
    GetCraftingRecipesAvailableComposer,

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
    GetMessengerHistoryComposer,

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
    Game2GetFriendsLeaderboardComposer,
    Game2GetTotalGroupLeaderboardComposer,
    Game2GetTotalLeaderboardComposer,
    Game2GetWeeklyFriendsLeaderboardComposer,
    Game2GetWeeklyGroupLeaderboardComposer,
    Game2GetWeeklyLeaderboardComposer,
    GetFriendsWeeklyCompetitiveLeaderboardComposer, // NO-OP WIN63-2021
    GetWeeklyCompetitiveLeaderboardComposer, // NO-OP WIN63-2021
    GetWeeklyGameRewardComposer, // NO-OP WIN63-2021
    GetWeeklyGameRewardWinnersComposer, // NO-OP WIN63-2021

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
    GetQuizQuestionsComposer,
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
    PostQuizAnswersComposer,
    SearchFaqs, // NO-OP WIN63-2021

    // com.sulake.habbo.communication.messages.outgoing.hotlooks
    GetHotLooks,

    // com.sulake.habbo.communication.messages.outgoing.inventory.achievements
    GetAchievementsComposer,

    // com.sulake.habbo.communication.messages.outgoing.inventory.avatareffect
    AvatarEffectActivatedComposer,
    AvatarEffectSelectedComposer,

    // com.sulake.habbo.communication.messages.outgoing.inventory.badges
    GetBadgePointLimitsComposer,
    GetBadgesComposer,
    GetIsBadgeRequestFulfilledComposer,
    RequestABadgeComposer,
    SetActivatedBadgesComposer,

    // com.sulake.habbo.communication.messages.outgoing.inventory.bots
    GetBotInventoryComposer,

    // com.sulake.habbo.communication.messages.outgoing.inventory.furni
    RequestFurniInventoryComposer,
    RequestFurniInventoryWhenNotInRoomComposer,
    RequestRoomPropertySet,

    // com.sulake.habbo.communication.messages.outgoing.inventory.pets
    CancelPetBreedingComposer,
    ConfirmPetBreedingComposer,
    GetPetInventoryComposer,

    // com.sulake.habbo.communication.messages.outgoing.inventory.purse
    GetCreditsInfoComposer,

    // com.sulake.habbo.communication.messages.outgoing.inventory.trading
    AcceptTradingComposer,
    AddItemsToTradeComposer,
    AddItemToTradeComposer,
    CloseTradingComposer,
    ConfirmAcceptTradingComposer,
    ConfirmDeclineTradingComposer,
    OpenTradingComposer,
    RemoveItemFromTradeComposer,
    UnacceptTradingComposer,
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
    GetMarketplaceItemStatsComposer,
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
    ModToolPreferencesComposer,
    ModToolSanctionComposer,
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
    RoomAdEventTabAdClickedComposer,
    RoomAdEventTabViewedComposer,
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
    NavigatorAddSavedSearchComposer,
    NavigatorDeleteSavedSearchComposer,
    NavigatorRemoveCollapsedCategory,
    NavigatorSetSearchCodeViewMode,
    NewNavigatorInitComposer,
    NewNavigatorSearchComposer,

    // com.sulake.habbo.communication.messages.outgoing.nft
    GetNftCredits,
    GetSelectedNftWardrobeOutfit,
    GetSilver,
    GetUserNftWardrobe,
    SaveUserNftWardrobe,

    // com.sulake.habbo.communication.messages.outgoing.notifications
    ResetUnseenItemIdsComposer,
    ResetUnseenItemsComposer,

    // com.sulake.habbo.communication.messages.outgoing.nux
    NewUserExperienceGetGifts,
    NewUserExperienceScriptProceedComposer,
    SelectInitialRoomComposer,

    // com.sulake.habbo.communication.messages.outgoing.poll
    PollAnswerComposer,
    PollRejectComposer,
    PollStartComposer,

    // com.sulake.habbo.communication.messages.outgoing.preferences
    SetChatPreferences,
    SetChatStylePreferenceComposer,
    SetIgnoreRoomInvites,
    SetNewNavigatorWindowPreferences,
    SetRoomCameraPreferences,
    SetSoundSettingsComposer,
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
    MuteAllInRoomComposer,
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
    CommandBotComposer,
    GetBotCommandConfigurationDataComposer,

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
    SetCustomStackingHeightComposer,
    SetMannequinFigureComposer,
    SetMannequinNameComposer,
    SetRandomState,
    SetRoomBackgroundColorDataComposer,
    SetYoutubeDisplayPlaylist,
    SpinWheelOfFortune,
    ThrowDice,
    SetAreaHideDataComposer,

    // com.sulake.habbo.communication.messages.outgoing.room.layout
    GetOccupiedTiles,
    GetRoomEntryTile,
    UpdateFloorProperties,

    // com.sulake.habbo.communication.messages.outgoing.room.pets
    BreedPets,
    CustomizePetWithFurniComposer,
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
    UpdateRoomCategoryAndTradeSettingsComposer,
    UpdateRoomFilter,

    // com.sulake.habbo.communication.messages.outgoing.sound
    AddJukeboxDiskComposer,
    GetJukeboxPlayList,
    GetNowPlaying,
    GetOfficialSongId,
    GetSongInfo,
    GetSoundMachinePlayList,
    GetSoundSettingsComposer,
    GetUserSongDisks,
    RemoveJukeboxDiskComposer,

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
    ChangeEmailComposer,
    CreateGuild,
    DeactivateGuild,
    DeselectFavouriteHabboGroup,
    GetEmailStatusComposer,
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
