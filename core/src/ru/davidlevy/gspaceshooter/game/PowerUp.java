package ru.davidlevy.gspaceshooter.game;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * Класс PowerUp (плюшки)
 */
public class PowerUp {
    private Vector2 position;
    private Vector2 velocity;
    private float currentTime;
    private float maxTimeOfLife;
    private boolean active;

    /* Тип плюшки */
    private TypePowerUp type;

    public PowerUp() {
        this.position = new Vector2(0, 0);
        this.velocity = new Vector2(0, 0);
        this.currentTime = 0.0f;
        this.maxTimeOfLife = 3.0f;
        this.active = false;
    }

    public void update(float dt) {
        currentTime += dt;
        position.mulAdd(velocity, dt);
        if (currentTime > maxTimeOfLife) deactivate();
    }

    public void activate(float x, float y, TypePowerUp type) {
        this.position.set(x, y);
        this.type = type;
        this.velocity.set(MathUtils.random(-50.0f, 50.0f), MathUtils.random(-50.0f, 50.0f));
        this.currentTime = 0.0f;
        this.maxTimeOfLife = 3.0f;
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }

    /**
     * Начислить плюшки игроку
     *
     * @param hero игрок
     */
    public void accrue(Player hero) {
        switch (type) {
            case MONEY10:
                hero.addMoney(10);
                break;
            case MONEY25:
                hero.addMoney(25);
                break;
            case MONEY50:
                hero.addMoney(50);
                break;
            case MEDKIT:
                hero.fullRepair();
                break;
            case MAGIC:
                hero.magicShield();
                break;
        }
    }

    public TypePowerUp getType() {
        return type;
    }

    public Vector2 getPosition() {
        return position;
    }

    public boolean isActive() {
        return active;
    }
}
