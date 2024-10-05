/** guitar hero project
4 notes fall from the top randomly
score and point line is shown
pressing a key plays the corresponding note
if you play a note when it touches the point line you get a point
if you "misplay" a note (press the note when it is not on the line) lose a point
if a note is not played you lose a point
*/

/** yPosX is the y position of the circles */
int yPos1 = 0;
int yPos2 = 0;
int yPos3 = 0;
int yPos4 = 0;

/** score of the game */
int score = 0;

/** checks if a key has been pressed */
boolean is1Pressed = false;
boolean is2Pressed = false;
boolean is3Pressed = false;
boolean is4Pressed = false;

/** checks if a note was unplayed */
boolean is1Unplayed = false;
boolean is2Unplayed = false;
boolean is3Unplayed = false;
boolean is4Unplayed = false;

/** runs once at the start of the program */
void setup() {
  size (1000, 1000);
}

/** runs once each frame */
void draw() {
  background(255);
  line (250, 0, 250, 1000);
  line (500, 0, 500, 1000);
  line (750, 0, 750, 1000);
  line (0, 750, 1000, 750);
  fill(0);
  textSize(65);
  text(score, 800, 150);
  // circle code 4 times (woo)
  /* bell and whistle: each frame the circles get a random color */
  fill(random(0,256), random(0,256), random(0,256));
  circle (125, yPos1, 225);
  yPos1+=6;
  if (yPos1 >= 1150) {
    yPos1 = int(random(-1000,0));
    is1Pressed = false;
    is1Unplayed = false;
  }
  circle (375, yPos2, 225);
  yPos2+=6;
  if (yPos2 >= 1150) {
    yPos2 = int(random(-1000,0));
    is2Pressed = false;
    is2Unplayed = false;
  }
  circle (625, yPos3, 225);
  yPos3+=6;
  if (yPos3 >= 1150) {
    yPos3 = int(random(-1000,0));
    is3Pressed = false;
    is3Unplayed = false;
  }
  circle (875, yPos4, 225);
  yPos4+=6;
  if (yPos4 >= 1150) {
    yPos4 = int(random(-1000,0));
    is4Pressed = false;
    is4Unplayed = false;
  }
  if (yPos1 > 875 && is1Unplayed == false && is1Pressed == false) {
    score-=1;
    is1Unplayed = true;
  } else if (yPos2 > 875 && is2Unplayed == false && is2Pressed == false) {
    score-=1;
    is2Unplayed = true;
  } else if (yPos3 > 875 && is3Unplayed == false && is3Pressed == false) {
    score-=1;
    is3Unplayed = true;
  } else if (yPos4 > 875 && is4Unplayed == false && is4Pressed == false) {
    score-=1;
    is4Unplayed = true;
  }
  /* bell and whistle: if you have -10 or less score the game ends */
  if (score <= -10) exit();
}
/** runs once when a key is pressed */
void keyPressed() {
  if (key == 'h') {
    if (yPos1 >= 625 && yPos1 <= 875) {
      if (is1Pressed == false) {
        is1Pressed = true;
        score+=1;
      }
    } else if (is1Pressed == false) {
      is1Pressed = true;
      score-=1;
    }
  } else if (key == 'j') {
    if (yPos2 >= 625 && yPos2 <= 875) {
      if (is2Pressed == false) {
        is2Pressed = true;
        score+=1;
      }
    } else if (is2Pressed == false) {
      is2Pressed = true;
      score-=1;
    }
  } else if (key == 'k') {
    if (yPos3 >= 625 && yPos3 <= 875) {
      if (is3Pressed == false) {
        is3Pressed = true;
        score+=1;
      } 
    } else if (is3Pressed == false) {
      is3Pressed = true;
      score-=1;
    }
  } else if (key == 'l') {
    if (yPos4 >= 625 && yPos4 <= 875) {
      if (is4Pressed == false) {
        is4Pressed = true;
        score+=1;
      } 
    } else if (is4Pressed == false) {
      is4Pressed = true;
      score-=1;
    }
    /** 2 bells and whistles here, e to exit the game
    and r to restart the game */
  } else if (key == 'e') {
    exit();
  } else if (key == 'r') {
 yPos1 = 0;
 yPos2 = 0;
 yPos3 = 0;
 yPos4 = 0;
 score = 0;
 is1Pressed = false;
 is2Pressed = false;
 is3Pressed = false;
 is4Pressed = false;
 is1Unplayed = false;
 is2Unplayed = false;
 is3Unplayed = false;
 is4Unplayed = false;
  }
}
