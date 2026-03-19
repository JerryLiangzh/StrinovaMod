package StrinovaMod;

import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.badlogic.gdx.graphics.Color;
import StrinovaMod.cards.XingHui.BeiJiXing;
import basemod.BaseMod;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditStringsSubscriber;


@SpireInitializer // 加载mod的注解
public class StrinovaMod implements EditCardsSubscriber, EditStringsSubscriber {
    public static final String MOD_ID = "StrinovaMod";

    // 人物选择界面按钮的图片
    private static final String NavigatorButton = "StrinovaMod/images/characters/NavigatorButton.png";
    // 人物选择界面的立绘
    private static final String NavigatorPortrait = "StrinovaMod/images/characters/NavigatorPortrait.png";
    // 攻击牌的背景（小尺寸）
    private static final String BG_ATTACK_512 = "StrinovaMod/images/cardui/512/bg_attack_512.png";
    // 能力牌的背景（小尺寸）
    private static final String BG_POWER_512 = "StrinovaMod/images/cardui/512/bg_power_512.png";
    // 技能牌的背景（小尺寸）
    private static final String BG_SKILL_512 = "StrinovaMod/images/cardui/512/bg_skill_512.png";
    // 在卡牌和遗物描述中的能量图标
    private static final String SMALL_ORB = "StrinovaMod/images/characters/small_orb.png";
    // 攻击牌的背景（大尺寸）
    private static final String BG_ATTACK_1024 = "StrinovaMod/images/cardui/1024/bg_attack_1024.png";
    // 能力牌的背景（大尺寸）
    private static final String BG_POWER_1024 = "StrinovaMod/images/cardui/1024/bg_power_1024.png";
    // 技能牌的背景（大尺寸）
    private static final String BG_SKILL_1024 = "StrinovaMod/images/cardui/1024/bg_skill_1024.png";
    // 在卡牌预览界面的能量图标
    private static final String BIG_ORB = "StrinovaMod/images/characters/card_orb.png";
    // 小尺寸的能量图标（战斗中，牌堆预览）
    private static final String ENEYGY_ORB = "StrinovaMod/images/characters/cost_orb.png";
    public static final Color NavigatorColor = new Color(79.0F / 255.0F, 185.0F / 255.0F, 9.0F / 255.0F, 1.0F);
    // 构造方法
    public StrinovaMod() {
        BaseMod.subscribe(this); // 告诉basemod你要订阅事件
        BaseMod.addColor(EXAMPLE_GREEN, NavigatorButton, NavigatorPortrait, NavigatorColor, NavigatorColor, 
            NavigatorColor, NavigatorColor, NavigatorColor,BG_ATTACK_512,BG_SKILL_512,BG_POWER_512,
            ENEYGY_ORB,BG_ATTACK_1024,BG_SKILL_1024,BG_POWER_1024,BIG_ORB,SMALL_ORB);
    }

    // 注解需要调用的方法，必须写
    public static void initialize() {
        new StrinovaMod();
    }

    // 当basemod开始注册mod卡牌时，便会调用这个函数
    @Override
    public void receiveEditCards() {
        // 这里写添加你卡牌的代码
        BaseMod.addCard(new BeiJiXing());
    }

    public static String assetPath(String string) {
        return MOD_ID + "/images/" + string;
    }

    public static String makeID(String string) {
        return MOD_ID + ":" + string;
    }

    @Override
    public void receiveEditStrings() {
        throw new UnsupportedOperationException("Unimplemented method 'receiveEditStrings'");
    }


}