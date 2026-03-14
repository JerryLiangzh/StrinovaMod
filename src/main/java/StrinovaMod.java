import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

import basemod.BaseMod;
import basemod.interfaces.EditCardsSubscriber;

@SpireInitializer // 加载mod的注解
public class StrinovaMod implements EditCardsSubscriber {
    // 构造方法
    public StrinovaMod() {
        BaseMod.subscribe(this); // 告诉basemod你要订阅事件
    }

    // 注解需要调用的方法，必须写
    public static void initialize() {
        new StrinovaMod();
    }

    // 当basemod开始注册mod卡牌时，便会调用这个函数
    @Override
    public void receiveEditCards() {
        // 这里写添加你卡牌的代码
    }
}