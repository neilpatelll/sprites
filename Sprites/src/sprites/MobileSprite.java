
import java.awt.Point;
import java.awt.Graphics2D;

/**
 * Extension of Sprite -- has velocity, acceleration, and rotation.
 */
public class MobileSprite extends Sprite {

    //must both be private to prevent updates to one without the other
    private double rotationDeg = 0;
    private double rotationRad = 0;
    protected Point centreOfRotation = new Point(0, 0);

    public double vx = 0;
    public double vy = 0;

    public double ax = 0;
    public double ay = 0;

    /**
     * Gets rotation in degrees.
     */
    public double getRotation() {
        return getRotation(false);
    }

    /**
     * Gets rotation in degrees or radians.
     */
    public double getRotation(boolean inRadians) {
        return (inRadians ? rotationRad : rotationDeg);
    }

    public double getRotationDegrees() {
        return rotationDeg;
    }

    public double getRotationRadians() {
        return rotationRad;
    }

    /**
     * Set the rotation, in either degrees or radians
     *
     * @param newRotation the new rotation, in either degrees or radians
     * @param inRadians is the new rotation specified in radians, or degrees?
     */
    public void setRotation(double newRotation, boolean inRadians) {
        if (inRadians) {
            rotationRad = newRotation;
            rotationDeg = rotationRad * 180 / Math.PI;
        } else {
            rotationDeg = newRotation;
            rotationRad = rotationDeg * Math.PI / 180;
        }
    }

    /**
     * Sets the rotation in degrees.
     *
     * @param newRotation the new rotation, in degrees.
     */
    public void setRotation(double newRotation) {
        setRotation(newRotation, false);
    }

    /**
     * Updates the position based oon velocity, and velocity based on acceleration.
     */
    public void drawSprite(Graphics2D g) {
        //update acceleration and velocity
        vx += ax;
        vy += ay;

        x += vx;
        y += vy;
    }
}
