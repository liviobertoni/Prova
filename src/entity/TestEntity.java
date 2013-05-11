package entity;

import level.BaseWorld;
import level.tile.Tile;
import main.Keys;
import resources.Art;
import screen.BaseScreen;

public class TestEntity extends Entity {
	
	public Keys keys;
	public int xPosition;
	public int yPosition;
	int facing = 0;
	int turning = 0;
	
	int testValue = 0;
	
	int xAccel;
	int yAccel;
	
	boolean lockWalking;
	
	public TestEntity(Keys keys) {
		this.keys = keys;
	}
	
	public void setCenter(BaseScreen screen) {
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
		//Blits the entity onto the screen, being offsetted to the left, which fits snugly in the world grids.
		screen.blit(Art.player[turning][0], (screen.getWidth() - Tile.WIDTH * 2) / 2 + xPosition, (screen.getHeight() - Tile.HEIGHT) / 2 + yPosition, 16, 16);
		
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
