#include <stdio.h>
#include <cab202_graphics.h>
//gcc E3.c -o E3
//gcc E3.c -std=gnu99 -I../ZDK -L../ZDK -lzdk -lncurses -o E3


void draw_border( void ) {
	int left_Xpos = 2;
	int right_Xpos = (screen_width() - 6);
	int Ypos = (screen_height()-3);
	
draw_line( left_Xpos, 2, left_Xpos, Ypos, '&' );
draw_line( right_Xpos, 2, right_Xpos, Ypos, '&' );
draw_line( left_Xpos+1, 2, right_Xpos-1, 2, '&' );
draw_line( left_Xpos+1, Ypos, right_Xpos-1, Ypos, '&' );
    show_screen();
}

int main( void ) {
	setup_screen();
	draw_border();
	wait_char();
	cleanup_screen();
	return 0;
}