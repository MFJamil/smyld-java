package com.smyld.test;

//********************************************************************//
//* F. PERMADI MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE      *//
//* SUITABILITY OF                                                   *//
//* THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT       *//
//* LIMITED                                                          *//
//* TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A      *//
//* PARTICULAR PURPOSE                                               *//
//********************************************************************//
import java.applet.Applet;
import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;

//*******************************************************************//
//* main class
//*******************************************************************//
public class Rayc extends Applet implements Runnable
{
// this is Java's stuff 
Thread fThread;

// size of tile (wall height)
static final int TILE_SIZE = 64;
static final int WALL_HEIGHT = 64;
static final int PROJECTIONPLANEWIDTH = 320;
static final int PROJECTIONPLANEHEIGHT = 200;
static final int ANGLE60 = PROJECTIONPLANEWIDTH;
static final int ANGLE30 = (ANGLE60/2);
static final int ANGLE15 = (ANGLE30/2);
static final int ANGLE90 = (ANGLE30*3);
static final int ANGLE180 = (ANGLE90*2);
static final int ANGLE270 = (ANGLE90*3);
static final int ANGLE360 = (ANGLE60*6);
static final int ANGLE0 = 0;
static final int ANGLE5 = (ANGLE30/6);
static final int ANGLE10 = (ANGLE5*2);

// trigonometric tables
float fSinTable[];
float fISinTable[];
float fCosTable[];
float fICosTable[];
float fTanTable[];
float fITanTable[];
float fFishTable[];
float fXStepTable[];
float fYStepTable[];

// offscreen buffer
Image fOffscreenImage;
Graphics fOffscreenGraphics;

// player's attributes
int fPlayerX = 100;
int fPlayerY = 160;
int fPlayerArc = ANGLE0;
int fPlayerDistanceToTheProjectionPlane = 277;
int fPlayerHeight =32;
int fPlayerSpeed = 16;
int fProjectionPlaneYCenter = PROJECTIONPLANEHEIGHT/2;
// the following variables are used to keep the player coordinate in the overhead map
int fPlayerMapX, fPlayerMapY, fMinimapWidth;

// movement flag
boolean fKeyUp=false;
boolean fKeyDown=false;
boolean fKeyLeft=false;
boolean fKeyRight=false;

// 2 dimensional map
byte fMap[];
static final byte W=1;                                // wall
static final byte O=0;                                // opening
static final int MAP_WIDTH=12;
static final int MAP_HEIGHT=12;

//*******************************************************************//
//* Convert arc to radian
//*******************************************************************//
float arcToRad(float arcAngle)
{
   return ((float)(arcAngle*Math.PI)/(float)ANGLE180);    
}

//*******************************************************************//
//* Create tigonometric values to make the program runs faster.
//*******************************************************************//
public void createTables()
{
  int i;
  float radian;
  fSinTable = new float[ANGLE360+1];
  fISinTable = new float[ANGLE360+1];
  fCosTable = new float[ANGLE360+1];
  fICosTable = new float[ANGLE360+1];
  fTanTable = new float[ANGLE360+1];
  fITanTable = new float[ANGLE360+1];
  fFishTable = new float[ANGLE60+1];
  fXStepTable = new float[ANGLE360+1];
  fYStepTable = new float[ANGLE360+1];

  for (i=0; i<=ANGLE360;i++)
  {
    // get the radian value (the last addition is to avoid division by 0, try removing
        // that and you'll see a hole in the wall when a ray is at 0, 90, 180, or 270 degree)
    radian = arcToRad(i) + (float)(0.0001);
    fSinTable[i]=(float)Math.sin(radian);
    fISinTable[i]=(1.0F/(fSinTable[i]));
    fCosTable[i]=(float)Math.cos(radian);
    fICosTable[i]=(1.0F/(fCosTable[i]));
    fTanTable[i]=(float)Math.tan(radian);
    fITanTable[i]=(1.0F/fTanTable[i]);

      //  you can see that the distance between xi is the same
      //  if we know the angle
      //  _____|_/next xi______________
      //       |
      //  ____/|next xi_________   slope = tan = height / dist between xi's
      //     / |
      //  __/__|_________  dist between xi = height/tan where height=tile size
      // old xi|
      //                  distance between xi = x_step[view_angle];
      //
      //
      // facine left
    // facing left
    if (i>=ANGLE90 && i<ANGLE270)
    {
      fXStepTable[i] = (float)(TILE_SIZE/fTanTable[i]);
      if (fXStepTable[i]>0)
        fXStepTable[i]=-fXStepTable[i];
    }
    // facing right
    else
    {
      fXStepTable[i] = (float)(TILE_SIZE/fTanTable[i]);
      if (fXStepTable[i]<0)
        fXStepTable[i]=-fXStepTable[i];
    }

    // FACING DOWN
    if (i>=ANGLE0 && i<ANGLE180)
    {
      fYStepTable[i] = (float)(TILE_SIZE*fTanTable[i]);
      if (fYStepTable[i]<0)
        fYStepTable[i]=-fYStepTable[i];
    }
    // FACING UP
    else
    {
      fYStepTable[i] = (float)(TILE_SIZE*fTanTable[i]);
      if (fYStepTable[i]>0)
        fYStepTable[i]=-fYStepTable[i];
    }
  }

  for (i=-ANGLE30; i<=ANGLE30; i++)
  {
      radian = arcToRad(i);
      // we don't have negative angle, so make it start at 0
      // this will give range 0 to 320
      fFishTable[i+ANGLE30] = (float)(1.0F/Math.cos(radian));
  }

      // CERATE A SIMPLE MAP
      byte[] map=
      {
              W,W,W,W,W,W,W,W,W,W,W,W,
              W,O,O,O,O,O,O,O,O,O,O,W,
              W,O,O,O,O,O,O,O,O,O,O,W,
              W,O,O,O,O,O,O,O,W,O,O,W,
              W,O,O,W,O,W,O,O,W,O,O,W,
              W,O,O,W,O,W,W,O,W,O,O,W,
              W,O,O,W,O,O,W,O,W,O,O,W,
              W,O,O,O,W,O,W,O,W,O,O,W,
              W,O,O,O,W,O,W,O,W,O,O,W,
              W,O,O,O,W,W,W,O,W,O,O,W,
              W,O,O,O,O,O,O,O,O,O,O,W,
              W,W,W,W,W,W,W,W,W,W,W,W
      };
      fMap=map;
}

//*******************************************************************//
//* Called when program starts
//*******************************************************************//
public void start()
{
  createTables();
  fThread=new Thread(this);
  fThread.start();
}

//*******************************************************************//
//* Running thread
//*******************************************************************//
public void run()
{
      requestFocus();
      // create offscreen buffer
  fOffscreenImage=createImage(size().width, size().height);
  fOffscreenGraphics=fOffscreenImage.getGraphics();

  while(true)
  {
        // rotate left
    if (fKeyLeft)
    {
      if ((fPlayerArc-=ANGLE10)<ANGLE0)
        fPlayerArc+=ANGLE360;
    }
        // rotate right
    else if (fKeyRight)
    {
      if ((fPlayerArc+=ANGLE10)>=ANGLE360)
        fPlayerArc-=ANGLE360;
    }

        //  _____     _
        // |\ arc     |
        // |  \       y
        // |    \     |
    //            -
        // |--x--|  
        //
        //  sin(arc)=y/diagonal
        //  cos(arc)=x/diagonal   where diagonal=speed
    float playerXDir=fCosTable[fPlayerArc];
    float playerYDir=fSinTable[fPlayerArc];

        // move forward
    if (fKeyUp)
    {
      fPlayerX+=(int)(playerXDir*fPlayerSpeed);
      fPlayerY+=(int)(playerYDir*fPlayerSpeed);
    }
        // move backward
    else if (fKeyDown)
    {
      fPlayerX-=(int)(playerXDir*fPlayerSpeed);
      fPlayerY-=(int)(playerYDir*fPlayerSpeed);
    }

    render();
    try
    {
      Thread.sleep(50);
    }
    catch (Exception sleepProblem)
    {
      showStatus("Sleep problem");
    }
  }
}

//*******************************************************************//
//* Draw background image
//*******************************************************************//
public void drawBackground()
{
  // sky
      int c=25;
      int r;
      for (r=0; r<PROJECTIONPLANEHEIGHT/2; r+=10)
      {
              fOffscreenGraphics.setColor(new Color(c, 125, 225));
              fOffscreenGraphics.fillRect(0, r, PROJECTIONPLANEWIDTH, 10);
              c+=20;
      }
      // ground
      c=22;
      for (; r<PROJECTIONPLANEHEIGHT; r+=15)
      {
              fOffscreenGraphics.setColor(new Color(c, 20, 20));
              fOffscreenGraphics.fillRect(0, r, PROJECTIONPLANEWIDTH, 15);
              c+=15;
      }
}

//*******************************************************************//
//* Draw map on the right side
//*******************************************************************//
public void drawOverheadMap()
{
      fMinimapWidth=5;
  for (int u=0; u<MAP_WIDTH; u++)
  {
    for (int v=0; v<MAP_HEIGHT; v++)
    {
      if (fMap[v*MAP_WIDTH+u]==W)
      {
                fOffscreenGraphics.setColor(Color.cyan);
      }
              else
      {
                fOffscreenGraphics.setColor(Color.black);
      }
          fOffscreenGraphics.fillRect(PROJECTIONPLANEWIDTH+(u*fMinimapWidth),
                      (v*fMinimapWidth), fMinimapWidth, fMinimapWidth);
    }
  }
      fPlayerMapX=PROJECTIONPLANEWIDTH+(int)(((float)fPlayerX/(float)TILE_SIZE) * fMinimapWidth);
      fPlayerMapY=(int)(((float)fPlayerY/(float)TILE_SIZE) * fMinimapWidth);
}

//*******************************************************************//
//* Draw ray on the overhead map (for illustartion purpose)
//* This is not part of the ray-casting process
//*******************************************************************//
public void drawRayOnOverheadMap(float x, float y)
{
      fOffscreenGraphics.setColor(Color.yellow);
      // draw line from the player position to the position where the ray
      // intersect with wall
      fOffscreenGraphics.drawLine(fPlayerMapX, fPlayerMapY, 
              (int)(PROJECTIONPLANEWIDTH+((float)(x*fMinimapWidth)/(float)TILE_SIZE)),
              (int)(((float)(y*fMinimapWidth)/(float)TILE_SIZE)));
      // draw a red line indication the player's direction
      fOffscreenGraphics.setColor(Color.red);
  fOffscreenGraphics.drawLine(fPlayerMapX, fPlayerMapY, 
    (int)(fPlayerMapX+fCosTable[fPlayerArc]*10),
        (int)(fPlayerMapY+fSinTable[fPlayerArc]*10));
}

//*******************************************************************//
//* Renderer
//*******************************************************************//
public void render()
{
      drawBackground();
      drawOverheadMap();

  int verticalGrid;        // horizotal or vertical coordinate of intersection
  int horizontalGrid;      // theoritically, this will be multiple of TILE_SIZE
                           // , but some trick did here might cause
                           // the values off by 1
  int distToNextVerticalGrid; // how far to the next bound (this is multiple of
  int distToNextHorizontalGrid; // tile size)
  float xIntersection;  // x and y intersections
  float yIntersection;
  float distToNextXIntersection;
  float distToNextYIntersection;

  int xGridIndex;        // the current cell that the ray is in
  int yGridIndex;

  float distToVerticalGridBeingHit;      // the distance of the x and y ray intersections from
  float distToHorizontalGridBeingHit;      // the viewpoint

  int castArc, castColumn;

  castArc = fPlayerArc;
      // field of view is 60 degree with the point of view (player's direction in the middle)
      // 30  30
      //    ^
      //  \ | /
      //   \|/
      //    v
      // we will trace the rays starting from the leftmost ray
      castArc-=ANGLE30;
      // wrap around if necessary
  if (castArc < 0)
  {
    castArc=ANGLE360 + castArc;
  }

  for (castColumn=0; castColumn<PROJECTIONPLANEWIDTH; castColumn+=5)
  {
        // ray is between 0 to 180 degree (1st and 2nd quadrant)
        // ray is facing down
    if (castArc > ANGLE0 && castArc < ANGLE180)
    {
              // truncuate then add to get the coordinate of the FIRST grid (horizontal
              // wall) that is in front of the player (this is in pixel unit)
              // ROUND DOWN
      horizontalGrid = (fPlayerY/TILE_SIZE)*TILE_SIZE  + TILE_SIZE;

      // compute distance to the next horizontal wall
      distToNextHorizontalGrid = TILE_SIZE;

      float xtemp = fITanTable[castArc]*(horizontalGrid-fPlayerY);
              // we can get the vertical distance to that wall by
              // (horizontalGrid-GLplayerY)
              // we can get the horizontal distance to that wall by
              // 1/tan(arc)*verticalDistance
              // find the x interception to that wall
      xIntersection = xtemp + fPlayerX;
    }
    // else, the ray is facing up
    else
    {
      horizontalGrid = (fPlayerY/TILE_SIZE)*TILE_SIZE;
      distToNextHorizontalGrid = -TILE_SIZE;

      float xtemp = fITanTable[castArc]*(horizontalGrid - fPlayerY);
      xIntersection = xtemp + fPlayerX;

      horizontalGrid--;
    }
        // LOOK FOR HORIZONTAL WALL
    if (castArc==ANGLE0 || castArc==ANGLE180)
    {
      distToHorizontalGridBeingHit=9999999F;//Float.MAX_VALUE;
    }
    // else, move the ray until it hits a horizontal wall
    else
    {
      distToNextXIntersection = fXStepTable[castArc];
      while (true)
      {
        xGridIndex = (int)(xIntersection/TILE_SIZE);
        // in the picture, yGridIndex will be 1
        yGridIndex = (horizontalGrid/TILE_SIZE);

        if ((xGridIndex>=MAP_WIDTH) ||
          (yGridIndex>=MAP_HEIGHT) ||
          xGridIndex<0 || yGridIndex<0)
        {
          distToHorizontalGridBeingHit = Float.MAX_VALUE;
          break;
        }
        else if ((fMap[yGridIndex*MAP_WIDTH+xGridIndex])!=O)
        {
          distToHorizontalGridBeingHit  = (xIntersection-fPlayerX)*fICosTable[castArc];
          break;
        }
        // else, the ray is not blocked, extend to the next block
        else
        {
          xIntersection += distToNextXIntersection;
          horizontalGrid += distToNextHorizontalGrid;
        }
      }
    }


    // FOLLOW X RAY
    if (castArc < ANGLE90 || castArc > ANGLE270)
    {
      verticalGrid = TILE_SIZE + (fPlayerX/TILE_SIZE)*TILE_SIZE;
      distToNextVerticalGrid = TILE_SIZE;

      float ytemp = fTanTable[castArc]*(verticalGrid - fPlayerX);
      yIntersection = ytemp + fPlayerY;
    }
    // RAY FACING LEFT
    else
    {
      verticalGrid = (fPlayerX/TILE_SIZE)*TILE_SIZE;
      distToNextVerticalGrid = -TILE_SIZE;

      float ytemp = fTanTable[castArc]*(verticalGrid - fPlayerX);
      yIntersection = ytemp + fPlayerY;

      verticalGrid--;
    }
        // LOOK FOR VERTICAL WALL
    if (castArc==ANGLE90||castArc==ANGLE270)
    {
      distToVerticalGridBeingHit = 9999999;//Float.MAX_VALUE;
    }
    else
    {
      distToNextYIntersection = fYStepTable[castArc];
      while (true)
      {
        // compute current map position to inspect
        xGridIndex = (verticalGrid/TILE_SIZE);
        yGridIndex = (int)(yIntersection/TILE_SIZE);

        if ((xGridIndex>=MAP_WIDTH) ||
          (yGridIndex>=MAP_HEIGHT) ||
          xGridIndex<0 || yGridIndex<0)
        {
          distToVerticalGridBeingHit = Float.MAX_VALUE;
          break;
        }
        else if ((fMap[yGridIndex*MAP_WIDTH+xGridIndex])!=O)
        {
          distToVerticalGridBeingHit =(yIntersection-fPlayerY)*fISinTable[castArc];
          break;
        }
        else
        {
          yIntersection += distToNextYIntersection;
          verticalGrid += distToNextVerticalGrid;
        }
      }
    }

        // DRAW THE WALL SLICE
        float scaleFactor;
        float dist;
    int topOfWall;   // used to compute the top and bottom of the sliver that
    int bottomOfWall;   // will be the staring point of floor and ceiling
    // determine which ray strikes a closer wall.
    // if yray distance to the wall is closer, the yDistance will be shorter than
        // the xDistance
    if (distToHorizontalGridBeingHit < distToVerticalGridBeingHit)
        {
              // the next function call (drawRayOnMap()) is not a part of raycating rendering part, 
              // it just draws the ray on the overhead map to illustrate the raycasting process
          drawRayOnOverheadMap(xIntersection, horizontalGrid);
      dist=distToHorizontalGridBeingHit;
      fOffscreenGraphics.setColor(Color.gray);
    }
    // else, we use xray instead (meaning the vertical wall is closer than
    //   the horizontal wall)
        else
        {
              // the next function call (drawRayOnMap()) is not a part of raycating rendering part, 
              // it just draws the ray on the overhead map to illustrate the raycasting process
          drawRayOnOverheadMap(verticalGrid, yIntersection);
      dist=distToVerticalGridBeingHit;
      fOffscreenGraphics.setColor(Color.darkGray);
        }

        // correct distance (compensate for the fishbown effect)
    dist /= fFishTable[castColumn];
        // projected_wall_height/wall_height = fPlayerDistToProjectionPlane/dist;
        int projectedWallHeight=(int)(WALL_HEIGHT*(float)fPlayerDistanceToTheProjectionPlane/dist);
    bottomOfWall = fProjectionPlaneYCenter+(int)(projectedWallHeight*0.5F);
    topOfWall = PROJECTIONPLANEHEIGHT-bottomOfWall;
        if (bottomOfWall>=PROJECTIONPLANEHEIGHT)
              bottomOfWall=PROJECTIONPLANEHEIGHT-1;
        //fOffscreenGraphics.drawLine(castColumn, topOfWall, castColumn, bottomOfWall);
        fOffscreenGraphics.fillRect(castColumn, topOfWall, 5, projectedWallHeight);

        // TRACE THE NEXT RAY
        castArc+=5;
        if (castArc>=ANGLE360)
              castArc-=ANGLE360;
  }

      // blit to screen
  paint(getGraphics());
}

//*******************************************************************//
//* Called when leaving the page.
//*******************************************************************//
public void stop()
{
      if((fThread != null) && fThread.isAlive())
      {
    fThread.stop();
    fThread = null;
      }
}

//*******************************************************************//
//* Called everytime applet need painting or whenever repaint is
//*   called.
//*******************************************************************//
public void paint(Graphics g)
{
  if (fOffscreenImage!=null)
    g.drawImage(fOffscreenImage, 0, 0, this);
}

//*******************************************************************//
//* Detect keypress
//*******************************************************************//
public boolean keyDown(Event evt, int key)
{
  switch (key)
  {
    case Event.UP:
    case 'i':
    case 'I':
      fKeyUp=true;
      break;
    case Event.DOWN:
    case 'k':
    case 'K':
      fKeyDown=true;
      break;
    case Event.LEFT:
    case 'j':
    case 'J':
      fKeyLeft=true;
      break;
    case Event.RIGHT:
    case 'l':
    case 'L':
      fKeyRight=true;
      break;
    default:
  }
  return true;
}

//*******************************************************************//
//* Detect key release
//*******************************************************************//
public boolean keyUp(Event evt, int key)
{
  switch (key)
  {
    case Event.UP:
    case 'i':
    case 'I':
      fKeyUp=false;
      break;
    case Event.DOWN:
    case 'k':
    case 'K':
      fKeyDown=false;
      break;
    case Event.LEFT:
    case 'j':
    case 'J':
      fKeyLeft=false;
      break;
    case Event.RIGHT:
    case 'l':
    case 'L':
      fKeyRight=false;
      break;
    default:
  }
  return true;
}
}




