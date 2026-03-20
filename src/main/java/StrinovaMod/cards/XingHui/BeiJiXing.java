package StrinovaMod.cards.XingHui;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import StrinovaMod.StrinovaMod;
import basemod.abstracts.CustomCard;

public class BeiJiXing extends CustomCard {
    public static final String ID = ("StrinovaMod:BeiJiXing");
    private static final String NAME = "星绘的北极星";
    public static final String IMG = ("StrinovaMod/images/cards/XingHui/BeiJiXing.png");
    private static final int COST = 1;
    private static final String DESCRIPTION = "造成 !D! 点伤害。";
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = StrinovaMod.Enums.StrinovaColor;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public BeiJiXing() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 6;
        this.tags.add(CardTags.STARTER_STRIKE);
        this.tags.add(CardTags.STRIKE);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(3);
            this.rawDescription = "造成 !D! 点伤害。";
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer paramAbstractPlayer, AbstractMonster paramAbstractMonster) {
        AbstractDungeon.actionManager.addToBottom(
            new DamageAction(paramAbstractMonster,
                new DamageInfo(paramAbstractPlayer, damage, DamageType.NORMAL)
            )
        );
    }
    
}
