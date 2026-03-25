package StrinovaSpire.characters;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.Zap;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.relics.Vajra;
import com.megacrit.cardcrawl.screens.CharSelectInfo;

import StrinovaSpire.StrinovaSpire;
import StrinovaSpire.cards.XingHui.BeiJiXing;

import basemod.abstracts.CustomPlayer;

public class Navigator extends CustomPlayer {
    public static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(StrinovaSpire.makeID("Navigator"));
    private static final String MY_CHARACTER_IMAGE = "StrinovaSpire/images/characters/Navigator.png";
    private static final String MY_CHARACTER_SHOULDER_1 = "StrinovaSpire/images/characters/shoulder1.png";
    private static final String MY_CHARACTER_SHOULDER_2 = "StrinovaSpire/images/characters/shoulder2.png";
    private static final String CORPSE_IMAGE = "StrinovaSpire/images/characters/corpse.png";
    private static final String[] ORB_TEXTURES = new String[]{
            "StrinovaSpire/images/ui/orb/layer5.png",
            "StrinovaSpire/images/ui/orb/layer4.png",
            "StrinovaSpire/images/ui/orb/layer3.png",
            "StrinovaSpire/images/ui/orb/layer2.png",
            "StrinovaSpire/images/ui/orb/layer1.png",
            "StrinovaSpire/images/ui/orb/layer6.png",
            "StrinovaSpire/images/ui/orb/layer5d.png",
            "StrinovaSpire/images/ui/orb/layer4d.png",
            "StrinovaSpire/images/ui/orb/layer3d.png",
            "StrinovaSpire/images/ui/orb/layer2d.png",
            "StrinovaSpire/images/ui/orb/layer1d.png"
    };

    private static final float[] LAYER_SPEED = new float[]{-40.0F, -32.0F, 20.0F, -20.0F, 0.0F, -10.0F, -8.0F, 5.0F, -5.0F, 0.0F};

    public Navigator(String name) {
        super(name, StrinovaSpire.Enums.NAVIGATOR, ORB_TEXTURES, "StrinovaSpire/images/ui/orb/vfx.png", LAYER_SPEED, (String) null, null);

        this.dialogX = (this.drawX + 0.0F * Settings.scale);
        this.dialogY = (this.drawY + 150.0F * Settings.scale);

        this.initializeClass(
                MY_CHARACTER_IMAGE,
                MY_CHARACTER_SHOULDER_1, MY_CHARACTER_SHOULDER_2,
                CORPSE_IMAGE,
                this.getLoadout(),
                0.0F, 0.0F, 200.0F, 220.0F,
                new EnergyManager(3)
        );
    }

    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(BeiJiXing.ID);
        retVal.add(BeiJiXing.ID);
        retVal.add(BeiJiXing.ID);
        retVal.add(BeiJiXing.ID);
        retVal.add(BeiJiXing.ID);
        return retVal;
    }

    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(Vajra.ID);
        return retVal;
    }

    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(
                characterStrings.NAMES[0],
                characterStrings.TEXT[0],
                130,
                130,
                2,
                180,
                5,
                this,
                this.getStartingRelics(),
                this.getStartingDeck(),
                false
        );
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        return characterStrings.NAMES[0];
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return StrinovaSpire.Enums.StrinovaColor;
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new Zap();
    }

    @Override
	public Color getCardTrailColor() {
		return StrinovaSpire.NavigatorColor;
	}

    @Override
    public int getAscensionMaxHPLoss() {
        return 5;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontBlue;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);
    }

    @Override
    public ArrayList<CutscenePanel> getCutscenePanels() {
        ArrayList<CutscenePanel> panels = new ArrayList<>();
        panels.add(new CutscenePanel("StrinovaSpire/images/characters/Victory1.png", "ATTACK_MAGIC_FAST_1"));
        panels.add(new CutscenePanel("StrinovaSpire/images/characters/Victory2.png"));
        panels.add(new CutscenePanel("StrinovaSpire/images/characters/Victory3.png"));
        return panels;
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_HEAVY";
    }

    @Override
    public String getLocalizedCharacterName() {
        return characterStrings.NAMES[0];
    }

    @Override
    public AbstractPlayer newInstance() {
        return new Navigator(this.name);
    }

    @Override
    public String getSpireHeartText() {
        return characterStrings.TEXT[1];
    }

    @Override
    public Color getSlashAttackColor() {
        return StrinovaSpire.NavigatorColor;
    }

    @Override
    public String getVampireText() {
        return Vampires.DESCRIPTIONS[0];
    }

    @Override
    public Color getCardRenderColor() {
        return StrinovaSpire.NavigatorColor;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL, AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL};
    }
}
