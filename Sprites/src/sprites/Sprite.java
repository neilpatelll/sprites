import java.awt.*;

abstract class Sprite {
  protected boolean visible;           // is sprite visible
  protected boolean active;            // is sprite updatable

  // abstract methods:
  abstract void paint (Graphics g);
  abstract void update();

  // accessor methods:
  public boolean isVisible() {
    return visible;
  }

  public void setVisible(boolean b) {
    visible = b;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean b) {
    active = b;
  }

  // suspend the sprite
  public void suspend() {
    setVisible(false);
    setActive(false);
  }

  // restore the sprite
  public void restore() {
    setVisible(true);
    setActive(true);
  }

}


abstract class Sprite2D extends Sprite {

  protected int locx;
  protected int locy;

  Color color;
  boolean fill;

  public boolean getFill() {
    return fill;
  }

  public void setFill(boolean b) {
    fill = b;
  }

  public void setColor(Color c) {
    color = c;
  }

  public Color getColor() {
    return color;
  }


}

 class BitmapSprite extends Sprite {
   protected int locx;
   protected int locy;

   // image dimensions
   protected int width,height;

   protected Image image;                   // the bitmap

   public BitmapSprite(int x,int y,Image i) {
     locx = x;
     locy = y;
     image = i;
     restore();
   }

   public void setSize(int w,int h) {
     width = w;
     height = h;
   }

   public void update() {

     // do nothing

   }

   public void paint(Graphics g) {
     if (visible) {
       g.drawImage(image,locx-BigPanel.view_x,locy-BigPanel.view_y,null);
     }
   }
 }

 /////////////////////////////////////////////////////////////////
 class RectSprite extends Sprite2D {

   protected int width, height;    // dimensions of rectangle


   public RectSprite(int x,int y,int w,int h,Color c) {
     locx = x;
     locy = y;
     width = w;
     height = h;
     color = c;
     fill = false;                 // default: don't fill
     restore();                    // restore the sprite
   }

   // provide implementation of abstract methods:

   public void update() {

     // does nothing

   }


   // check if sprite's visible before painting
   public void paint(Graphics g) {
     if (visible) {
       g.setColor(color);

       if (fill) {
 	g.fillRect(locx-BigPanel.view_x,locy-BigPanel.view_y,width,height);
       }

       else {
 	g.drawRect(locx-BigPanel.view_x,locy-BigPanel.view_y,width,height);
       }

     }

   }

 }

 // Moveable interface. Note that the declarations public
 // and abstract are optional. All methods specified in an
 // interface are automatically public and abstract!

 interface Moveable {
   public abstract void setPosition(int c, int d);
   public abstract void setVelocity(int x, int y);
   public abstract void updatePosition();
 }
