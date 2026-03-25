package StrinovaSpire;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.core.Settings.GameLanguage;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;

import com.badlogic.gdx.graphics.Color;
import StrinovaSpire.cards.XingHui.BeiJiXing;
import StrinovaSpire.cards.XingHui.BeiJiXingFanXing;
import StrinovaSpire.cards.XingHui.BeiJiXingJiXing;
import StrinovaSpire.characters.Navigator;
import basemod.BaseMod;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditStringsSubscriber;

@SpireInitializer
public class StrinovaSpire implements EditCardsSubscriber, EditStringsSubscriber, EditCharactersSubscriber{
    public static final Logger logger = LogManager.getLogger(StrinovaSpire.class);
    public static final String MOD_ID = "StrinovaSpire";

    private static final String NavigatorButton = "StrinovaSpire/images/characters/NavigatorButton.png";
    private static final String NavigatorPortrait = "StrinovaSpire/images/characters/NavigatorPortrait.png";
    private static final String BG_ATTACK_512 = "StrinovaSpire/images/cardui/512/bg_attack_512.png";
    private static final String BG_POWER_512 = "StrinovaSpire/images/cardui/512/bg_power_512.png";
    private static final String BG_SKILL_512 = "StrinovaSpire/images/cardui/512/bg_skill_512.png";
    private static final String SMALL_ORB = "StrinovaSpire/images/characters/small_orb.png";
    private static final String BG_ATTACK_1024 = "StrinovaSpire/images/cardui/1024/bg_attack_1024.png";
    private static final String BG_POWER_1024 = "StrinovaSpire/images/cardui/1024/bg_power_1024.png";
    private static final String BG_SKILL_1024 = "StrinovaSpire/images/cardui/1024/bg_skill_1024.png";
    private static final String BIG_ORB = "StrinovaSpire/images/characters/card_orb.png";
    private static final String ENEYGY_ORB = "StrinovaSpire/images/characters/cost_orb.png";
    public static final Color NavigatorColor = new Color(79.0F / 255.0F, 185.0F / 255.0F, 9.0F / 255.0F, 1.0F);

    public static class Enums {
        @SpireEnum
        public static AbstractPlayer.PlayerClass NAVIGATOR;
        @SpireEnum
        public static AbstractCard.CardColor StrinovaColor;
    }
    public static class CardEnum {
        @SpireEnum
        public static CardLibrary.LibraryType StrinovaColor;
    }

    public StrinovaSpire() {
        BaseMod.subscribe(this);
        BaseMod.addColor(Enums.StrinovaColor, NavigatorColor, NavigatorColor, NavigatorColor, 
            NavigatorColor, NavigatorColor, NavigatorColor, NavigatorColor,BG_ATTACK_512,BG_SKILL_512,
            BG_POWER_512,ENEYGY_ORB,BG_ATTACK_1024,BG_SKILL_1024,BG_POWER_1024,BIG_ORB,SMALL_ORB);
    }

    public static void initialize() {
        new StrinovaSpire();
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new Navigator(CardCrawlGame.playerName), NavigatorButton, NavigatorPortrait, Enums.NAVIGATOR);
    }

    @Override
    public void receiveEditCards() {
        BaseMod.addCard(new BeiJiXing());
        BaseMod.addCard(new BeiJiXingFanXing());
        BaseMod.addCard(new BeiJiXingJiXing());
    }

    public static String makeID(String id) {
        return MOD_ID + ":" + id;
    }

    @Override
    public void receiveEditStrings() {
        String lang;
        if (Settings.language == GameLanguage.ZHS) {
            lang = "zhs";
        } else {
            lang = "eng";
        }
        BaseMod.loadCustomStringsFile(CardStrings.class, "StrinovaSpire/localization/" + lang + "/cards.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, "StrinovaSpire/localization/" + lang + "/characters.json");
    }


}
