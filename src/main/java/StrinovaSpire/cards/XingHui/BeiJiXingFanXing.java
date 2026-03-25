package StrinovaSpire.cards.XingHui;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import StrinovaSpire.StrinovaSpire;
import basemod.abstracts.CustomCard;

public class BeiJiXingFanXing extends CustomCard {
    public static final String ID = "StrinovaSpire:BeiJiXingFanXing";
    private static final String NAME = "星绘的繁星";
    public static final String IMG = "StrinovaSpire/images/cards/XingHui/BeiJiXing.png";
    private static final int COST = 2;
    private static final String DESCRIPTION = "造成 !D! 点伤害。";
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = StrinovaSpire.Enums.StrinovaColor;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public BeiJiXingFanXing() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 12;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(4);
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster) {
        AbstractDungeon.actionManager.addToBottom(
            new DamageAction(monster, new DamageInfo(player, damage, DamageType.NORMAL))
        );
    }
}
