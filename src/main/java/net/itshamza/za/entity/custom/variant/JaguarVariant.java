package net.itshamza.za.entity.custom.variant;

import net.minecraft.Util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public enum JaguarVariant {
    NORMAL(0, true),
    BLACK(1, false);

    private static final JaguarVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(JaguarVariant::getId)).toArray(JaguarVariant[]::new);
    private final int id;
    private final boolean common;

    JaguarVariant(int p_30984_, boolean common) {
        this.id = p_30984_;
        this.common = common;
    }

    public int getId() {
        return this.id;
    }

    public static JaguarVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }

    public static JaguarVariant getCommonSpawnVariant(Random p_149246_) {
        return getSpawnVariant(p_149246_, true);
    }

    public static JaguarVariant getRareSpawnVariant(Random p_149257_) {
        return getSpawnVariant(p_149257_, false);
    }

    private static JaguarVariant getSpawnVariant(Random p_149248_, boolean p_149249_) {
        JaguarVariant[] Jaguar$variant = Arrays.stream(BY_ID).filter((p_149252_) -> {
            return p_149252_.common == p_149249_;
        }).toArray((p_149244_) -> {
            return new JaguarVariant[p_149244_];
        });
        return Util.getRandom(Jaguar$variant, p_149248_);
    }
}
