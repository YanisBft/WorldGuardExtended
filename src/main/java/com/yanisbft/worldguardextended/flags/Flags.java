package com.yanisbft.worldguardextended.flags;

import com.sk89q.worldguard.protection.flags.BooleanFlag;
import com.sk89q.worldguard.protection.flags.StateFlag;

public final class Flags {

    public static final StateFlag CHICKEN_LAYING = new StateFlag("chicken-laying", true);

    public static final BooleanFlag DISABLE_DAYLIGHT_DETECTORS = new BooleanFlag("disable-daylight-detectors");
    public static final BooleanFlag DISABLE_DECORATED_POTS = new BooleanFlag("disable-decorated-pots");
    public static final BooleanFlag DISABLE_DOORS = new BooleanFlag("disable-doors");
    public static final BooleanFlag DISABLE_FENCE_GATES = new BooleanFlag("disable-fence-gates");
    public static final BooleanFlag DISABLE_TRAPDOORS = new BooleanFlag("disable-trapdoors");
}
