package level;

import level.tile.Tile;
import screen.BaseScreen;
import entity.Entity;

public class TestWorld extends BaseWorld {
	public TestWorld() {
		super();
		//this.addEntity(new TestEntity(new Keys()));
	}
	
	public void addEntity(Entity e) {
		e.initialize(this);
		entities.add(e);
	}
	
	public void removeEntity(Entity e) {
		e.isRemoved = true;
	}
	
	@Override
	public void tick() {
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			if (!e.isRemoved) {
				e.tick();
			}
			if (e.isRemoved) {
				entities.remove(i--);
			}
		}
	}
	
	@Override
	public void render(BaseScreen screen, int xScroll, int yScroll) {
		int gridX0 = xScroll / Tile.WIDTH;
		int gridY0 = yScroll / Tile.HEIGHT;
		int gridX1 = (xScroll + screen.getWidth()) / Tile.WIDTH;
		int gridY1 = (yScroll + screen.getHeight()) / Tile.HEIGHT;
		
		if (xScroll < 0)
			gridX0--;
		if (yScroll < 0)
			gridY0--;
		
		screen.setOffset(-xScroll, -yScroll);
		
		for (Entity e : this.entities) {
			e.render(screen);
		}
		
		screen.setOffset(0, 0);
	}
}
