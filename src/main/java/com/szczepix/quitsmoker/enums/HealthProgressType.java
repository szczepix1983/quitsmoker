package com.szczepix.quitsmoker.enums;

import lombok.Getter;

@Getter
public enum HealthProgressType {

    AFTER_20_MIN("Tętno obniży się a ciśnienie krwi powróci do normy", (60 * 20)),
    AFTER_3_HOURS("Poprawi się krążenie obwodowe, czubki palców u rąk i nóg staną się cieplejsze", (60 * 60 * 3)),
    AFTER_10_HOURS("Ilość tlenu we krwi się zwiększa, zmniejsza się ilość toksycznego tlenku węgla", (60 * 60 * 10)),
    AFTER_24_HOURS("Ryzyko ostrego zawału mięśnia sercowego zmniejsza się o połowę", (60 * 60 * 24)),
    AFTER_28_HOURS("Twoje zmysły węchu i smaku zaczynają działać prawidłowo", (60 * 60 * 28)),
    AFTER_2_WEEKS("Układ krążenia wzmacnia się, poprawia się Twoja kondycja fizyczna", (60 * 60 * 24 * 14));

    long duration;
    String description;

    HealthProgressType(final String description, final long duration) {
        this.duration = duration;
        this.description = description;
    }
}
