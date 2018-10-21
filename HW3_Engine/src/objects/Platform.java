package objects;

import java.awt.Rectangle;

import processing.core.PApplet;

public class Platform extends GameObject implements Renderable {

    public int     r;
    public int     g;
    public int     b;

    public int     maxY;
    public int     minY;
    public int     maxX;
    public int     minX;

    // public Rectangle platform;

    public boolean moving;

    public Platform ( final int GUID, final boolean moving, final int x, final int y, final int width, final int height,
            final int r, final int g, final int b ) {
        super( GUID );
        this.moving = moving;
        this.r = r;
        this.g = g;
        this.b = b;
        this.rect = new Rectangle( x, y, width, height );
        this.x = x;
        this.y = y;
        this.velx = 0;
        this.vely = 0;
        this.type = "plat";
    }

    public void setMovingSettings ( final int maxY, final int minY, final int maxX, final int minX, final float vely,
            final float velx ) {
        this.moving = true;
        this.velx = velx;
        this.vely = vely;
        this.maxX = maxX;
        this.maxY = maxY;
        this.minX = minX;
        this.minY = minY;
    }

    @Override
    public void render ( final PApplet p ) {
        p.fill( r, g, b );
        p.rect( rect.x, rect.y, rect.width, rect.height );
    }

    @Override
    public void handleCollision ( final GameObject o ) {
        if ( o.type.equals( "player" ) ) {
            final Player p = (Player) o;
            if ( p.rect.y < this.rect.y ) {
                p.rect.y = this.rect.y - 31;
                p.vely = this.vely;
                p.isColliding = true;
            }
            else if ( p.rect.y > this.rect.y ) {
                p.vely = this.vely;
                p.rect.y = this.rect.y + this.rect.height;
            }
            else if ( p.velx < 0 && p.rect.x + p.velx < this.rect.x ) {
                p.velx = this.velx;
            }

        }
    }

    @Override
    public void update () {
        this.rect.y += this.vely;
        this.rect.x += this.velx;
        if ( this.rect.x > this.maxX ) {
            velx *= -1;
        }
        if ( this.rect.x < this.minY ) {
            velx *= -1;
        }
        if ( this.rect.y < this.minY ) {
            vely *= -1;
        }
        if ( this.rect.y > this.maxY ) {
            vely *= -1;
        }

    }

}
