package entity;

import level.BaseWorld;
import main.Keys;
import resources.Art;
import screen.BaseScreen;

public class TestEntity extends Entity {
	
	public Keys keys;
	int facing = 0;
	int turning = 0;
	int xPosition;
	int yPosition;
	
	int xAccel;
	int yAccel;
	
	boolean lockWalking;
	
	public TestEntity(Keys keys) {
		this.keys = keys;
	}
	
	@Override
	public void initialize(BaseWorld world) {
	}
	
	@Override
	public void tick() {
		if (keys.up.tappedDown) {
			facing = 2;
		}
		if (keys.down.tappedDown) {
			facing = 0;
		}
		if (keys.left.tappedDown) {
			facing = 1;
		}
		if (keys.right.tappedDown) {
			facing = 3;
		}
		if (!lockWalking) {
			if (keys.up.pressedDown) {
				if (facing == 2)
					yAccel--;
				lockWalking = true;
			}
			if (keys.down.pressedDown) {
				if (facing == 0)
					yAccel++;
				lockWalking = true;
			}
			if (keys.left.pressedDown) {
				if (facing == 1)
					xAccel--;
				lockWalking = true;
			}
			if (keys.right.pressedDown) {
				if (facing == 3)
					xAccel++;
				lockWalking = true;
			}
		}
		handleMovement();
	}
	
	@Override
	public void render(BaseScreen screen) {
		screen.blit(Art.player[turning][0], xPosition, yPosition, 16, 16);
	}
	
	//-----------------------------------
	//Private methods
	
	private void handleMovement() {
		if (lockWalking) {
			xPosition += xAccel;
			yPosition += yAccel;
		}
		if (xPosition % 16 == 0 && yPosition % 16 == 0) {
			lockWalking = false;
			xAccel = 0;
			yAccel = 0;
			turning = facing;
		}
	}
}
