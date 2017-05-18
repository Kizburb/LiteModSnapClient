package nl.kingcrafting.snapclient.events;

import com.darkmagician6.eventapi.events.Cancellable;
import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.callables.EventCancellable;
import net.minecraft.util.DamageSource;

/**
 * Created by Jasper on 17-5-2017.
 */
public class EventPlayerDamage implements Event {


    private DamageSource damageSource;
    private float amount;


    public EventPlayerDamage(DamageSource s , Float f) {
        this.damageSource = s;
        this.amount = f;


    }

    public DamageSource getDamageSource() {
        return damageSource;
    }

    public float getAmount() {
        return amount;
    }


}
