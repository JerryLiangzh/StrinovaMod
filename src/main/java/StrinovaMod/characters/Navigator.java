package StrinovaMod.characters;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.Zap;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.relics.Vajra;
import com.megacrit.cardcrawl.screens.CharSelectInfo;

import StrinovaMod.StrinovaMod;
import StrinovaMod.cards.XingHui.BeiJiXing;

import basemod.abstracts.CustomPlayer;

public class Navigator extends CustomPlayer {
    public static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(StrinovaMod.makeID("Navigator"));
    private static final String MY_CHARACTER_IMAGE = "StrinovaMod/images/characters/Navigator.png";
    private static final String MY_CHARACTER_IMAGE_FALLBACK = "StrinovaMod/images/characters/NavigatorPortrait.png";
    private static final String MY_CHARACTER_SHOULDER_1 = "StrinovaMod/images/characters/shoulder1.png";
    private static final String MY_CHARACTER_SHOULDER_2 = "StrinovaMod/images/characters/shoulder2.png";
    private static final String CORPSE_IMAGE = "StrinovaMod/images/characters/corpse.png";
    private static final String[] ORB_TEXTURES = new String[]{
            "StrinovaMod/images/ui/orb/layer5.png",
            "StrinovaMod/images/ui/orb/layer4.png",
            "StrinovaMod/images/ui/orb/layer3.png",
            "StrinovaMod/images/ui/orb/layer2.png",
            "StrinovaMod/images/ui/orb/layer1.png",
            "StrinovaMod/images/ui/orb/layer6.png",
            "StrinovaMod/images/ui/orb/layer5d.png",
            "StrinovaMod/images/ui/orb/layer4d.png",
            "StrinovaMod/images/ui/orb/layer3d.png",
            "StrinovaMod/images/ui/orb/layer2d.png",
            "StrinovaMod/images/ui/orb/layer1d.png"
    };

    private static final float[] LAYER_SPEED = new float[]{-40.0F, -32.0F, 20.0F, -20.0F, 0.0F, -10.0F, -8.0F, 5.0F, -5.0F, 0.0F};

    public Navigator(String name) {
        super(name, StrinovaMod.Enums.NAVIGATOR, ORB_TEXTURES, "StrinovaMod/images/ui/orb/vfx.png", LAYER_SPEED, (String) null, null);

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

        // Base game render path directly dereferences this.img, so ensure it is loaded.
        if (this.img == null) {
            this.img = ImageMaster.loadImage(MY_CHARACTER_IMAGE);
        }
        if (this.img == null) {
            this.img = loadTextureFromClasspath(MY_CHARACTER_IMAGE);
        }
        if (this.img == null) {
            this.img = ImageMaster.loadImage(MY_CHARACTER_IMAGE_FALLBACK);
        }
        if (this.img == null) {
            this.img = loadTextureFromClasspath(MY_CHARACTER_IMAGE_FALLBACK);
        }
        if (this.shoulderImg == null) {
            this.shoulderImg = ImageMaster.loadImage(MY_CHARACTER_SHOULDER_1);
        }
        if (this.shoulderImg == null) {
            this.shoulderImg = loadTextureFromClasspath(MY_CHARACTER_SHOULDER_1);
        }
        if (this.shoulder2Img == null) {
            this.shoulder2Img = ImageMaster.loadImage(MY_CHARACTER_SHOULDER_2);
        }
        if (this.shoulder2Img == null) {
            this.shoulder2Img = loadTextureFromClasspath(MY_CHARACTER_SHOULDER_2);
        }
        if (this.corpseImg == null) {
            this.corpseImg = ImageMaster.loadImage(CORPSE_IMAGE);
        }
        if (this.corpseImg == null) {
            this.corpseImg = loadTextureFromClasspath(CORPSE_IMAGE);
        }
        if (this.img == null) {
            throw new IllegalStateException("Failed to load character image: " + MY_CHARACTER_IMAGE + " (fallback: " + MY_CHARACTER_IMAGE_FALLBACK + ") | " + getTextureLoadDiagnostics(MY_CHARACTER_IMAGE));
        }
    }

    private static Texture loadTextureFromClasspath(String path) {
        Texture fromInternal = loadTextureFromInternalFile(path);
        if (fromInternal != null) {
            return fromInternal;
        }

        InputStream inputStream = openResourceStream(path);
        if (inputStream == null) {
            return null;
        }
        try {
            byte[] data = readAllBytes(inputStream);
            Pixmap pixmap = new Pixmap(data, 0, data.length);
            Texture texture = new Texture(pixmap);
            pixmap.dispose();
            return texture;
        } catch (Exception ignored) {
            return null;
        } finally {
            try {
                inputStream.close();
            } catch (IOException ignored) {
            }
        }
    }

    private static Texture loadTextureFromInternalFile(String path) {
        try {
            FileHandle handle = Gdx.files.internal(path);
            if (handle != null && handle.exists()) {
                return new Texture(handle);
            }
        } catch (Exception ignored) {
        }
        return null;
    }

    private static InputStream openResourceStream(String path) {
        InputStream stream = Navigator.class.getResourceAsStream("/" + path);
        if (stream != null) {
            return stream;
        }

        ClassLoader navigatorLoader = Navigator.class.getClassLoader();
        if (navigatorLoader != null) {
            stream = navigatorLoader.getResourceAsStream(path);
            if (stream != null) {
                return stream;
            }
        }

        ClassLoader contextLoader = Thread.currentThread().getContextClassLoader();
        if (contextLoader != null) {
            stream = contextLoader.getResourceAsStream(path);
            if (stream != null) {
                return stream;
            }
        }

        return ClassLoader.getSystemResourceAsStream(path);
    }

    private static String getTextureLoadDiagnostics(String path) {
        boolean internalExists = false;
        boolean classResourceExists = false;
        boolean navigatorLoaderExists = false;
        boolean contextLoaderExists = false;
        boolean systemLoaderExists = false;

        try {
            FileHandle handle = Gdx.files.internal(path);
            internalExists = handle != null && handle.exists();
        } catch (Exception ignored) {
        }

        try (InputStream ignored = Navigator.class.getResourceAsStream("/" + path)) {
            classResourceExists = ignored != null;
        } catch (Exception ignored) {
        }

        try {
            ClassLoader navigatorLoader = Navigator.class.getClassLoader();
            if (navigatorLoader != null) {
                try (InputStream ignored = navigatorLoader.getResourceAsStream(path)) {
                    navigatorLoaderExists = ignored != null;
                }
            }
        } catch (Exception ignored) {
        }

        try {
            ClassLoader contextLoader = Thread.currentThread().getContextClassLoader();
            if (contextLoader != null) {
                try (InputStream ignored = contextLoader.getResourceAsStream(path)) {
                    contextLoaderExists = ignored != null;
                }
            }
        } catch (Exception ignored) {
        }

        try (InputStream ignored = ClassLoader.getSystemResourceAsStream(path)) {
            systemLoaderExists = ignored != null;
        } catch (Exception ignored) {
        }

        return "internal=" + internalExists
                + ", class=" + classResourceExists
                + ", navLoader=" + navigatorLoaderExists
                + ", ctxLoader=" + contextLoaderExists
                + ", sysLoader=" + systemLoaderExists;
    }

    private static byte[] readAllBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] chunk = new byte[4096];
        int read;
        while ((read = inputStream.read(chunk)) != -1) {
            buffer.write(chunk, 0, read);
        }
        return buffer.toByteArray();
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
        return StrinovaMod.Enums.StrinovaColor;
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new Zap();
    }

    @Override
	public Color getCardTrailColor() {
		return StrinovaMod.NavigatorColor;
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
        panels.add(new CutscenePanel("StrinovaMod/images/characters/Victory1.png", "ATTACK_MAGIC_FAST_1"));
        panels.add(new CutscenePanel("StrinovaMod/images/characters/Victory2.png"));
        panels.add(new CutscenePanel("StrinovaMod/images/characters/Victory3.png"));
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
        return StrinovaMod.NavigatorColor;
    }

    @Override
    public String getVampireText() {
        return Vampires.DESCRIPTIONS[0];
    }

    @Override
    public Color getCardRenderColor() {
        return StrinovaMod.NavigatorColor;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL, AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL};
    }
}
