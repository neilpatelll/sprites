
import java.awt.*;
import java.awt.image.*;
import java.util.Vector;

public abstract class Sprites {

    protected Image image;
    protected int x, y;
    protected int initialX, initialY; 

    protected boolean moving = false;
    protected boolean attack = false; 
    protected boolean retreat = false; 
    protected Dimension d;

    protected Point[] attackPoints = new Point[1], retreatPoints = new Point[1];

    final static int RANDOM_MOVE_SEED = 50; 
    final static int RANDOM_FIRE_SEED = 30; 
    protected int randomMoveTime = (int) (((Math.random() * RANDOM_MOVE_SEED)
            + 1)
            + 50);
    protected int currentMoveTime = 0; 


    protected int randomFireTime = (int) (((Math.random() * RANDOM_FIRE_SEED)
            + 1)
            + 30);
    protected int currentFireTime = 0; 

    public void draw(Graphics g, int x, int y) {
        g.drawImage(image, x, y, null);
    }

    public int getRightSide() {
        return x + d.width;
    }
    
    public int getLeftSide() {
        return x;
    }

    public void translate(int x, int y) {
        this.x += x;
        this.y += y;
    }

    public Dimension getDimension() {
        return d;
    }

    public int getXCoordinate() {
        return x;
    }

    public int getYCoordinate(){
        return y;
    }

    public int getXCenter() {
        return x + (d.width / 2);
    }

    public int getYCenter() {
        return y + (d.height / 2);
    }

    protected Image makeImage(int w, int h, int[] pixels) {
        return Toolkit.getDefaultToolkit().createImage(
                new MemoryImageSource(w, h, pixels, 0, w));
    }
    protected int setPixel(RGBColor color) {
        int a, r, g, b;
        int tempPixel;

        a = color.getTransparency();
        r = color.getRed();
        g = color.getGreen();
        b = color.getBlue();

        tempPixel = (a << 24) | (r << 16) | (g << 8) | b;

        return tempPixel;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean yes) {
        moving = yes;
    }

    public void setCurve(Point[] points) {
        attackPoints = points;
        retreatPoints = new Point[points.length];

        int j = points.length - 1;
        for (int i = 0; i < points.length; i++) {
            retreatPoints[i] = points[j];
            j--;
        }
    }
    
    public void retreatShip() {
        for (int i = 0; i < retreatPoints.length; i++) {
            if (retreatPoints[i] != null) {
                x = retreatPoints[i].x;
                y = retreatPoints[i].y;
                retreatPoints[i] = null;
                i = attackPoints.length;
            }
            // If all entries in the curve are null
            if (retreatPoints[retreatPoints.length - 1] == null) {
                x = initialX;
                y = initialY;
            }
        }
    }

    public void attackShip(Vector enemyBullets, PlayerShip player) {
        for (int i = 0; i < attackPoints.length; i++) {
            if (attackPoints[i] != null) {
                x = attackPoints[i].x;
                y = attackPoints[i].y;
                attackPoints[i] = null;
                i = attackPoints.length;
            }
            if (attackPoints[attackPoints.length / 2] != null) {
                if (randomFireTime > currentFireTime) {
                } else {
                    currentFireTime = 0;
                    enemyBullets.add(new EnemyBullet(this, player));
                    randomFireTime = (int) (((Math.random() * RANDOM_FIRE_SEED)
                            + 1)
                            + 60);
                }
            }
            if (attackPoints[attackPoints.length - 1] == null) {
                setAttack(false);
                setRetreat(true);
            }
        }
        currentFireTime++;
    }

 public boolean checkTime() {
        if (randomMoveTime == currentMoveTime) {
            randomMoveTime = (int) (((Math.random() * RANDOM_MOVE_SEED) + 1)
                    + 50);
            currentMoveTime = 0;
            setAttack(true);
            return true;
        } 
        else if (attack || retreat) {
            return true;
        } 
        else {
            currentMoveTime++;
            return false;
        }
    }

    public boolean isAttacking() {
        return attack;
    }

    public boolean isRetreating() {
        return retreat;
    }

    public void setAttack(boolean temp) {
        attack = temp;
    }

    public void setRetreat(boolean temp) {
        retreat = temp;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isAtInitialLocation() {
        if (x == initialX && y == initialY) {
            return true;
        } else {
            return false;
        }
    }

    public abstract int getScore();
}
