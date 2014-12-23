package org.andengine.extension.debugdraw;

import org.andengine.extension.debugdraw.primitives.PolyLine;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.util.Vector2Pool;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.Fixture;

/**
 * Chain fixture representation
 * @author nazgee
 */
class RenderOfChainFixture extends RenderOfFixture {
	private float p2m;

	public RenderOfChainFixture(Fixture fixture, VertexBufferObjectManager pVBO, float p2m) {
		super(fixture);
		this.p2m = p2m;

		ChainShape fixtureShape = (ChainShape) fixture.getShape();
		int vSize = fixtureShape.getVertexCount();
		float[] xPoints = new float[vSize];
		float[] yPoints = new float[vSize];

		Vector2 vertex = Vector2Pool.obtain();
		for (int i = 0; i < fixtureShape.getVertexCount(); i++) {
			fixtureShape.getVertex(i, vertex);
			xPoints[i] = vertex.x * p2m;
			yPoints[i] = vertex.y * p2m;
		}
		Vector2Pool.recycle(vertex);

		mEntity = new PolyLine(0, 0, xPoints, yPoints, pVBO);
	}
}