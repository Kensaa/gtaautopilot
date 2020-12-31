package fr.kensa.gtaautopilot;

import java.awt.*;

public class Fields {
    public static final int turnTime = 5;
    public static final int bigTurnTime = 600;
    public static final int forwardTime = 150;

    public static final Point m1 = new Point(30,870);
    public static final Point m2 = new Point(300,1060);
    public static final Rectangle map = new Rectangle(m1.x,m1.y,m2.x-m1.x,m2.y-m1.y);

    public static final Color gpsColor = new Color(255,175,175);

    public static final Point player1 = new Point(132,125);


    public static final Point arrowTopP1 = new Point(127,124);
    public static final Point arrowTopP2 = new Point(133,125);
    public static final Rectangle arrowTop = new Rectangle(arrowTopP1.x,arrowTopP1.y,arrowTopP2.x-arrowTopP1.x,arrowTopP2.y-arrowTopP1.y);

    public static final Point arrowRightP1 = new Point(142,125);
    public static final Point arrowRightP2 = new Point(143,131);
    public static final Rectangle arrowRight = new Rectangle(arrowRightP1.x,arrowRightP1.y,arrowRightP2.x-arrowRightP1.x,arrowRightP2.y-arrowRightP1.y);

    public static final Point arrowLeftP1 = new Point(122,125);
    public static final Point arrowLeftP2 = new Point(123,131);
    public static final Rectangle arrowLeft = new Rectangle(arrowLeftP1.x,arrowLeftP1.y,arrowLeftP2.x-arrowLeftP1.x,arrowLeftP2.y-arrowLeftP1.y);

    public static final Point arrowVeryLeftP1 = new Point(112,120);
    public static final Point arrowVeryLeftP2 = new Point(115,137);
    public static final Rectangle arrowVeryLeft = new Rectangle(arrowVeryLeftP1.x,arrowVeryLeftP1.y,arrowVeryLeftP2.x-arrowVeryLeftP1.x,arrowVeryLeftP2.y-arrowVeryLeftP1.y);

    public static final Point arrowVeryRightP1 = new Point(152,120);
    public static final Point arrowVeryRightP2 = new Point(155,137);
    public static final Rectangle arrowVeryRight = new Rectangle(arrowVeryRightP1.x,arrowVeryRightP1.y,arrowVeryRightP2.x-arrowVeryRightP1.x,arrowVeryRightP2.y-arrowVeryRightP1.y);
}
