package com.eu.habbo.messages.outgoing;

/**
 * Packet list shared by <a href="https://sulek.dev/">Sulek</a> from <a href="https://github.com/UnfamiliarLegacy">UnfamiliarLegacy (Mikee)</a>.
 */
public enum Outgoing {
    // com.sulake.habbo.communication.messages.incoming.advertisement
    Interstitial,
    RoomAdError,

    // com.sulake.habbo.communication.messages.incoming.availability
    AvailabilityStatus,
    AvailabilityTime, // NO-OP WIN63-2021
    InfoHotelClosed,
    InfoHotelClosing,
    LoginFailedHotelClosed,
    MaintenanceStatus,

    // com.sulake.habbo.communication.messages.incoming.avatar
    ChangeUserNameResult,
    CheckUserNameResult,
    FigureUpdate,
    Wardrobe,

    // com.sulake.habbo.communication.messages.incoming.callforhelp
    CfhSanction,
    CfhTopicsInit,
    SanctionStatus,

    // com.sulake.habbo.communication.messages.incoming.camera
    CameraPublishStatus,
    CameraPurchaseOK,
    CameraStorageUrl,
    CompetitionStatus,
    InitCamera,
    ThumbnailStatus,

    // com.sulake.habbo.communication.messages.incoming.campaign
    CampaignCalendarData,
    CampaignCalendarDoorOpened,

    // com.sulake.habbo.communication.messages.incoming.catalog
    BonusRareInfo,
    BuildersClubFurniCount,
    BuildersClubSubscriptionStatus,
    BundleDiscountRuleset,
    CatalogIndex,
    CatalogPageExpiration, // NO-OP WIN63-2021
    CatalogPage,
    CatalogPageWithEarliestExpiry,
    CatalogPublished,
    ClubGiftInfo,
    ClubGiftSelected,
    DirectSMSClubBuyAvailable, // NO-OP WIN63-2021
    GiftReceiverNotFound,
    GiftWrappingConfiguration,
    HabboClubExtendOffer,
    HabboClubOffers,
    IsOfferGiftable, // NO-OP WIN63-2021
    LimitedEditionSoldOut,
    LimitedOfferAppearingNext,
    NotEnoughBalance,
    ProductOffer,
    PurchaseError,
    PurchaseNotAllowed,
    PurchaseOK,
    RoomAdPurchaseInfo,
    SeasonalCalendarDailyOffer,
    SellablePetPalettes,
    SnowWarGameTokens,
    TargetedOffer,
    TargetedOfferNotFound,
    VoucherRedeemError,
    VoucherRedeemOk,

    // com.sulake.habbo.communication.messages.incoming.competition
    CompetitionEntrySubmitResult,
    CompetitionVotingInfo,
    CurrentTimingCode,
    IsUserPartOfCompetition,
    NoOwnedRoomsAlert,
    SecondsUntil,

    // com.sulake.habbo.communication.messages.incoming.collectibles
    CollectableMintableItemTypes,
    CollectibleMintTokenCount,
    CollectibleMintTokenOffers,
    CollectibleMintableItemResult,
    CollectibleMintingEnabled,
    CollectibleWalletAddresses,
    EmeraldBalance,
    NftBonusItemClaimResult,
    NftCollections,
    NftCollectionsScore,
    NftRewardItemClaimResult,
    SilverBalance,
    NftTransferAssetsResult,
    NftTransferFee,

    // com.sulake.habbo.communication.messages.incoming.crafting
    CraftableProducts,
    CraftingRecipe,
    CraftingRecipesAvailable,
    CraftingResult,

    // com.sulake.habbo.communication.messages.incoming.error
    ErrorReport,

    // com.sulake.habbo.communication.messages.incoming.friendfurni
    FriendFurniCancelLock,
    FriendFurniOtherLockConfirmed,
    FriendFurniStartConfirmation,

    // com.sulake.habbo.communication.messages.incoming.friendlist
    AcceptFriendResult,
    ConsoleMessageHistory,
    FindFriendsProcessResult,
    FollowFriendFailed,
    FriendListFragment,
    FriendListUpdate,
    FriendNotification,
    FriendRequests,
    HabboSearchResult,
    InstantMessageError,
    MessengerError,
    MessengerInit,
    MiniMailNew,
    MiniMailUnreadCount,
    NewConsole,
    NewFriendRequest,
    RoomInviteError,
    RoomInvite,

    // com.sulake.habbo.communication.messages.incoming.game.directory
    Game2AccountGameStatus,
    Game2GameCancelled,
    Game2GameCreated,
    Game2GameDirectoryStatus,
    Game2GameLongData,
    Game2GameNotFound, // NO-OP WIN63-2021
    Game2GameStarted,
    Game2InArenaQueue,
    Game2JoiningGameFailed,
    Game2StartCounter,
    Game2StartingGameFailed,
    Game2StopCounter,
    Game2UserBlocked,
    Game2UserJoinedGame,
    Game2UserLeftGame,

    // com.sulake.habbo.communication.messages.incoming.game.lobby
    AchievementResolutionCompleted,
    AchievementResolutionProgress,
    AchievementResolutions,
    UserGameAchievements, // NO-OP WIN63-2021

    // com.sulake.habbo.communication.messages.incoming.game.score
    Game2FriendsLeaderboard,
    Game2TotalGroupLeaderboard,
    Game2TotalLeaderboard,
    Game2WeeklyFriendsLeaderboard,
    Game2WeeklyGroupLeaderboard,
    Game2WeeklySmallLeaderboard, // Not sure if real name
    Game2WeeklyLeaderboard,
    WeeklyCompetitiveFriendsLeaderboard, // NO-OP WIN63-2021
    WeeklyCompetitiveLeaderboard, // NO-OP WIN63-2021
    WeeklyGameReward, // NO-OP WIN63-2021
    WeeklyGameRewardWinners, // NO-OP WIN63-2021

    // com.sulake.habbo.communication.messages.incoming.game.snowwar.arena
    Game2ArenaEntered,
    Game2EnterArenaFailed,
    Game2EnterArena,
    Game2GameChatFromPlayer,
    Game2GameEnding,
    Game2GameRejoin,
    Game2PlayerExitedGameArena,
    Game2PlayerRematches,
    Game2StageEnding,
    Game2StageLoad,
    Game2StageRunning,
    Game2StageStarting,
    Game2StageStillLoading,

    // com.sulake.habbo.communication.messages.incoming.game.snowwar.ingame
    Game2FullGameStatus,
    Game2GameStatus,

    // com.sulake.habbo.communication.messages.incoming.gamecenter (GUESS)
    BaseJumpJoinQueue,
    BaseJumpLeaveQueue,
    BaseJumpLoadGame,
    BaseJumpLoadGameURL,
    BaseJumpUnloadGame,
    GameAchievements,
    GameAchievementsList,
    GameCenterAccountInfo,
    GameCenterFeaturedPlayers,
    GameCenterGameList,
    GameFriendNotification,

    // com.sulake.habbo.communication.messages.incoming.gifts
    PhoneCollectionState,
    TryPhoneNumberResult,
    TryVerificationCodeResult,

    // com.sulake.habbo.communication.messages.incoming.groupforums
    ForumData,
    ForumsList,
    ForumThreads,
    PostMessage,
    PostThread,
    ThreadMessages,
    UnreadForumsCount,
    UpdateMessage,
    UpdateThread,

    // com.sulake.habbo.communication.messages.incoming.handshake
    AuthenticationOK,
    CompleteDiffieHandshake,
    DisconnectReason,
    GenericError,
    IdentityAccounts,
    InitDiffieHandshake,
    IsFirstLoginOfDay,
    NoobnessLevel,
    Ping,
    UniqueMachineID,
    UserObject,
    UserRights,

    // com.sulake.habbo.communication.messages.incoming.help
    CallForHelpDisabledNotify,
    CallForHelpPendingCallsDeleted,
    CallForHelpPendingCalls,
    CallForHelpReply,
    CallForHelpResult,
    ChatReviewSessionDetached,
    ChatReviewSessionOfferedToGuide,
    ChatReviewSessionResults,
    ChatReviewSessionStarted,
    ChatReviewSessionVotingStatus,
    FaqCategories, // NO-OP WIN63-2021
    FaqCategory, // NO-OP WIN63-2021
    FaqClientFaqs, // NO-OP WIN63-2021
    FaqSearchResults, // NO-OP WIN63-2021
    FaqText, // NO-OP WIN63-2021
    GuideOnDutyStatus,
    GuideReportingStatus,
    GuideSessionAttached,
    GuideSessionDetached,
    GuideSessionEnded,
    GuideSessionError,
    GuideSessionInvitedToGuideRoom,
    GuideSessionMessage,
    GuideSessionPartnerIsTyping,
    GuideSessionRequesterRoom,
    GuideSessionStarted,
    GuideTicketCreationResult,
    GuideTicketResolution,
    HotelMergeNameChange, // NO-OP WIN63-2021
    IssueCloseNotification,
    QuizData,
    QuizResults,

    // com.sulake.habbo.communication.messages.incoming.hotlooks
    HotLooks,

    // com.sulake.habbo.communication.messages.incoming.inventory.achievements
    Achievement,
    Achievements,
    AchievementsScore,

    // com.sulake.habbo.communication.messages.incoming.inventory.avatareffect
    AvatarEffectActivated,
    AvatarEffectAdded,
    AvatarEffectExpired,
    AvatarEffectSelected,
    AvatarEffects,

    // com.sulake.habbo.communication.messages.incoming.inventory.badges
    BadgePointLimits,
    BadgeReceived,
    Badges,
    IsBadgeRequestFulfilled,

    // com.sulake.habbo.communication.messages.incoming.inventory.bots
    BotAddedToInventory,
    BotInventory,
    BotReceived, // NO-OP WIN63-2021
    BotRemovedFromInventory,

    // com.sulake.habbo.communication.messages.incoming.inventory.clothing
    FigureSetIdAdded, // NO-OP WIN63-2021
    FigureSetIdRemoved, // NO-OP WIN63-2021
    FigureSetIds,

    // com.sulake.habbo.communication.messages.incoming.inventory.furni
    FurniListAddOrUpdate,
    FurniList,
    FurniListInvalidate,
    FurniListRemove,
    PostItPlaced,

    // com.sulake.habbo.communication.messages.incoming.inventory.trading
    TradeOpenFailed,
    TradingAccept,
    TradingClose,
    TradingCompleted,
    TradingConfirmation,
    TradingItemList,
    TradingNoSuchItem, // NO-OP WIN63-2021
    TradingNotOpen,
    TradingOpen,
    TradingOtherNotAllowed,
    TradingYouAreNotAllowed,
    TradeSilverFee,
    TradeSilverSet,

    // com.sulake.habbo.communication.messages.incoming.inventory.pets
    ConfirmBreedingRequest,
    ConfirmBreedingResult,
    GoToBreedingNestFailure,
    NestBreedingSuccess,
    PetAddedToInventory,
    PetBreeding,
    PetInventory,
    PetReceived,
    PetRemovedFromInventory,

    // com.sulake.habbo.communication.messages.incoming.inventory.purse
    CreditBalance,

    // com.sulake.habbo.communication.messages.incoming.landingview
    PromoArticles,

    // com.sulake.habbo.communication.messages.incoming.landingview.votes
    CommunityVoteReceived,

    // com.sulake.habbo.communication.messages.incoming.marketplace
    MarketPlaceOffers,
    MarketPlaceOwnOffers,
    MarketplaceBuyOfferResult,
    MarketplaceCanMakeOfferResult,
    MarketplaceCancelOfferResult,
    MarketplaceConfiguration,
    MarketplaceItemStats,
    MarketplaceMakeOfferResult,

    // com.sulake.habbo.communication.messages.incoming.moderation
    CfhChatlog,
    IssueDeleted,
    IssueInfo,
    IssuePickFailed,
    ModeratorActionResult,
    ModeratorCaution,
    ModeratorInit,
    Moderator,
    ModeratorRoomInfo,
    ModeratorToolPreferences,
    ModeratorUserInfo,
    RoomChatlog,
    RoomVisits,
    UserBanned,
    UserChatlog,

    // com.sulake.habbo.communication.messages.incoming.mysterybox
    CancelMysteryBoxWait,
    GotMysteryBoxPrize,
    MysteryBoxKeys,
    ShowMysteryBoxWait,

    // com.sulake.habbo.communication.messages.incoming.navigator
    CanCreateRoom,
    CanCreateRoomEvent,
    CategoriesWithVisitorCount,
    CompetitionRoomsData,
    ConvertedRoomId,
    Doorbell,
    FavouriteChanged,
    Favourites,
    FlatAccessDenied,
    FlatCreated,
    GetGuestRoomResult,
    GuestRoomSearchResult,
    NavigatorSettings,
    OfficialRooms,
    PopularRoomTagsResult,
    RoomEventCancel,
    RoomEvent,
    RoomInfoUpdated,
    RoomRating,
    RoomThumbnailUpdateResult, // NO-OP WIN63-2021
    UserEventCats,
    UserFlatCats,

    // com.sulake.habbo.communication.messages.incoming.newnavigator
    NavigatorCollapsedCategories,
    NavigatorLiftedRooms,
    NavigatorMetaData,
    NavigatorSavedSearches,
    NavigatorSearchResultBlocks,
    NewNavigatorPreferences,

    // com.sulake.habbo.communication.messages.incoming.nft
    UserNftWardrobe,
    UserNftWardrobeSelection,
    UserNftChatStyles,

    // com.sulake.habbo.communication.messages.incoming.notifications
    ActivityPoints,
    ClubGiftNotification,
    ElementPointer,
    HabboAchievementNotification,
    HabboActivityPointNotification,
    HabboBroadcast,
    InfoFeedEnable,
    MOTDNotification,
    NotificationDialog,
    OfferRewardDelivered,
    PetLevelNotification,
    RestoreClient,
    UnseenItems,

    // com.sulake.habbo.communication.messages.incoming.nux
    NewUserExperienceGiftOffer,
    NewUserExperienceNotComplete,
    SelectInitialRoom,

    // com.sulake.habbo.communication.messages.incoming.perk
    CitizenshipVipOfferPromoEnabled,
    PerkAllowances,

    // com.sulake.habbo.communication.messages.incoming.poll
    PollContents,
    PollError,
    PollOffer,
    QuestionAnswered,
    Question,
    QuestionFinished,

    // com.sulake.habbo.communication.messages.incoming.preferences
    AccountPreferences,

    // com.sulake.habbo.communication.messages.incoming.quest
    CommunityGoalEarnedPrizes, // NO-OP WIN63-2021
    CommunityGoalHallOfFame,
    CommunityGoalProgress,
    ConcurrentUsersGoalProgress,
    EpicPopup,
    QuestCancelled,
    QuestCompleted,
    QuestDaily,
    Quest,
    Quests,
    SeasonalQuests,

    // com.sulake.habbo.communication.messages.incoming.recycler
    RecyclerStatus,
    RecyclerFinished,
    RecyclerPrizes,

    // com.sulake.habbo.communication.messages.incoming.room.action
    AvatarEffect,
    CarryObject,
    Dance,
    Expression,
    Sleep,
    UseObject,

    // com.sulake.habbo.communication.messages.incoming.room.camera
    CameraSnapshot, // NO-OP WIN63-2021

    // com.sulake.habbo.communication.messages.incoming.room.chat
    Chat,
    FloodControl,
    RemainingMutePeriod,
    RoomChatSettings,
    RoomFilterSettings,
    Shout,
    UserTyping,
    Whisper,

    // com.sulake.habbo.communication.messages.incoming.room.bots
    BotCommandConfiguration,
    BotError,
    BotForceOpenContextMenu,
    BotSkillListUpdate,

    // com.sulake.habbo.communication.messages.incoming.room.engine
    BuildersClubPlacementWarning,
    FavoriteMembershipUpdate,
    FloorHeightMap,
    FurnitureAliases,
    HeightMap,
    HeightMapUpdate,
    ItemAdd,
    ItemDataUpdate,
    ItemRemove,
    ItemStateUpdate,
    ItemUpdate,
    Items,
    ItemsStateUpdate,
    ObjectAdd,
    ObjectDataUpdate,
    ObjectRemove,
    ObjectRemoveMultiple,
    ObjectRemoveConfirm,
    ObjectUpdate,
    ObjectsDataUpdate,
    Objects,
    RoomEntryInfo,
    RoomProperty,
    RoomVisualizationSettings,
    SlideObjectBundle,
    SpecialRoomEffect,
    UserChange,
    UserRemove,
    UserUpdate,
    Users,
    WiredFurniMove,
    WiredMovements,
    WiredUserMove,
    WiredWallItemMove,

    // com.sulake.habbo.communication.messages.incoming.room.furniture
    CustomStackingHeightUpdate,
    CustomUserNotification,
    DiceValue,
    FurniRentOrBuyoutOffer,
    GuildFurniContextMenuInfo,
    OneWayDoorStatus,
    OpenPetPackageRequested,
    OpenPetPackageResult,
    PresentOpened,
    RentableSpaceRentFailed,
    RentableSpaceRentOk,
    RentableSpaceStatus,
    RequestSpamWallPostIt,
    RoomDimmerPresets,
    RoomMessageNotification,
    YoutubeControlVideo,
    YoutubeDisplayPlaylists,
    YoutubeDisplayVideo,
    AreaHide,

    // com.sulake.habbo.communication.messages.incoming.room.layout
    RoomEntryTile,
    RoomOccupiedTiles,

    // com.sulake.habbo.communication.messages.incoming.room.permissions
    YouAreController,
    YouAreNotController,
    YouAreOwner,

    // com.sulake.habbo.communication.messages.incoming.room.pets
    PetBreedingResult,
    PetCommands,
    PetExperience,
    PetFigureUpdate,
    PetInfo,
    PetLevelUpdate,
    PetPlacingError,
    PetRespectFailed,
    PetStatusUpdate,

    // com.sulake.habbo.communication.messages.incoming.room.session
    CantConnect,
    CloseConnection,
    FlatAccessible,
    GamePlayerValue,
    OpenConnection,
    RoomForward,
    RoomQueueStatus,
    RoomReady,
    YouArePlayingGame,
    YouAreSpectator,
    YouAreNotSpectator,
    HanditemConfiguration,

    // com.sulake.habbo.communication.messages.incoming.roomsettings
    BannedUsersFromRoom,
    FlatControllerAdded,
    FlatControllerRemoved,
    FlatControllers,
    MuteAllInRoom,
    NoSuchFlat,
    RoomSettingsData,
    RoomSettingsError,
    RoomSettingsSaved,
    RoomSettingsSaveError,
    ShowEnforceRoomCategoryDialog,
    UserUnbannedFromRoom,

    // com.sulake.habbo.communication.messages.incoming.sound
    JukeboxPlayListFull,
    JukeboxSongDisks,
    NowPlaying,
    OfficialSongId,
    PlayList,
    PlayListSongAdded,
    TraxSongInfo,
    UserSongDisksInventory,

    // com.sulake.habbo.communication.messages.incoming.talent
    TalentLevelUp,
    TalentTrackLevel,
    TalentTrack,

    // com.sulake.habbo.communication.messages.incoming.tracking
    LatencyPingResponse,

    // com.sulake.habbo.communication.messages.incoming.userclassification
    UserClassification,

    // com.sulake.habbo.communication.messages.incoming.userdefinedroomevents
    Open,
    WiredFurniAction,
    WiredFurniAddon,
    WiredFurniCondition,
    WiredFurniSelector,
    WiredFurniTrigger,
    WiredFurniVariable,
    WiredRewardResult,
    WiredSaveSuccess,
    WiredValidationError,

    // com.sulake.habbo.communication.messages.incoming.userdefinedroomevents.wiredmenu
    WiredAllVariableHolders,
    WiredAllVariables,
    WiredErrorLogs,
    WiredMenuError,
    WiredPermissions,
    WiredRoomSettings,
    WiredRoomStats,
    WiredVariablesForObject,
    WiredAllVariablesHash,
    WiredAllVariablesDiffs,

    // com.sulake.habbo.communication.messages.incoming.users
    AccountSafetyLockQuestions, // AIR63
    AccountSafetyLockStatusChange,
    ApproveName,
    ChangeEmailResult,
    EmailStatusResult,
    ExtendedProfileChanged,
    ExtendedProfile,
    GroupDetailsChanged,
    GroupMembershipRequested,
    GuildCreated,
    GuildCreationInfo,
    GuildEditFailed,
    GuildEditInfo,
    GuildEditorData,
    GuildMemberFurniCountInHQ,
    GuildMemberMgmtFailed,
    GuildMembers,
    GuildMembershipRejected,
    GuildMembershipUpdated,
    GuildMemberships,
    HabboGroupBadges,
    HabboGroupDeactivated,
    HabboGroupDetails,
    HabboGroupJoinFailed,
    HabboUserBadges,
    HandItemReceived,
    IgnoreResult,
    IgnoredUsers,
    InClientLink,
    PetRespectNotification,
    PetSupplementedNotification,
    RelationshipStatusInfo,
    RespectNotification,
    RoomUserTags, // (OLD ?)
    ScrSendKickbackInfo,
    ScrSendUserInfo,
    UserNameChanged,

    // com.sulake.habbo.communication.messages.parser.vault
    CreditVaultStatus,
    IncomeRewardClaimResponse,
    IncomeRewardStatus,
}
