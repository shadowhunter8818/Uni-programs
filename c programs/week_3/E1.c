//gcc E3.c -o E3
//gcc E3.c -std=gnu99 -I../ZDK -L../ZDK -lzdk -lncurses -o E3

#include <stdlib.h>
#include <cab202_graphics.h>
#include <cab202_timers.h>

//  (a) Declare four global integer variables, as follows:
//      left - the horizontal location of the left edge of the rectangle
//      top - the vertical location of the top edge of the rectangle 
//      width - the width of the rectangle.
//      height - the height of the rectangle.

int left;
int top;
int width;
int height;

//  (b) Declare a global variable of type char called ch.
//      This is the character that is to be used to render the rectangle.

char ch;

void draw_rect(void) {
    //  (c) Insert code to draw the outline of the rectangle defined by the global variables.
    //      If either of the width or height is less than or equal to zero,
    //      the function must not draw anything.
	if((width < 1) || (height < 1)) {
		return;
	}
	
	draw_line(left,top,left+width-1,top,ch);
	draw_line(left,top+height-1,left+width-1,top+height-1,ch);
	draw_line(left,top,left,top+height-1,ch);
	draw_line(left+width-1,top,left+width-1,top+height-,ch);
}

int main(void) {
    setup_screen();

    // draw a box.
    left = rand() % screen_width() / 2;
    top = rand() % screen_height() / 2;
    width = 1 + rand() % (screen_width() - left - 1);
    height = 1 + rand() % (screen_height() - top - 1);
    ch = '@';
    draw_rect();
    show_screen();

    // draw a box.
    left = rand() % screen_width() / 2;
    top = rand() % screen_height() / 2;
    width = 1 + rand() % (screen_width() - left - 1);
    height = 1 + rand() % (screen_height() - top - 1);
    ch = '&';
    draw_rect();
    show_screen();

    // draw a box with zero width.
    left = rand() % screen_width() / 2;
    top = rand() % screen_height() / 2;
    width = 0;
    height = 1 + rand() % (screen_height() - top - 1);
    ch = '*';
    draw_rect();
    show_screen();

    // draw a box.
    left = rand() % screen_width() / 2;
    top = rand() % screen_height() / 2;
    width = 1 + rand() % (screen_width() - left - 1);
    height = 1 + rand() % (screen_height() - top - 1);
    ch = '#';
    draw_rect();
    show_screen();

    // draw a box with negative width.
    left = rand() % screen_width() / 2;
    top = rand() % screen_height() / 2;
    width = -rand() % screen_width();
    height = 1 + rand() % (screen_height() - top - 1);
    ch = '!';
    draw_rect();
    show_screen();

    // draw a box.
    left = rand() % screen_width() / 2;
    top = rand() % screen_height() / 2;
    width = 1 + rand() % (screen_width() - left - 1);
    height = 1 + rand() % (screen_height() - top - 1);
    ch = '+';
    draw_rect();
    show_screen();

    // draw a box with zero height.
    left = rand() % screen_width() / 2;
    top = rand() % screen_height() / 2;
    width = 1 + rand() % (screen_width() - left - 1);
    height = 0;
    ch = 'a';
    draw_rect();
    show_screen();

    // draw a box.
    left = rand() % screen_width() / 2;
    top = rand() % screen_height() / 2;
    width = 1 + rand() % (screen_width() - left - 1);
    height = 1 + rand() % (screen_height() - top - 1);
    ch = 'b';
    draw_rect();
    show_screen();

    // draw a box with negative width.
    left = rand() % screen_width() / 2;
    top = rand() % screen_height() / 2;
    width = -rand() % screen_width();
    height = 1 + rand() % (screen_height() - top - 1);
    ch = 'c';
    draw_rect();
    show_screen();

    // draw a box.
    left = rand() % screen_width() / 2;
    top = rand() % screen_height() / 2;
    width = 1 + rand() % (screen_width() - left - 1);
    height = 1 + rand() % (screen_height() - top - 1);
    ch = 'd';
    draw_rect();
    show_screen();

    timer_pause(5000);
    cleanup_screen();
    return 0;
}

