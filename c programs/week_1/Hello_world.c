#include <stdio.h>
#include <cab202_graphics.h>

void draw_paddles( void ) {
	// INSERT CODE HERE
	int left_Xpos = 2;
	int right_Xpos = (screen_width() - 2);
	int Ypos = ((screen_height() - 9)/2);
/*	
//draw_line left side
	draw_line( left_Xpos, (Ypos-2), left_Xpos, (Ypos-2+8), '*' );
//draw_line right side
	draw_line( right_Xpos, (Ypos+4), right_Xpos, (Ypos+4+8), '*' );
*/
//draw_line left side
	draw_line( left_Xpos, (Ypos-2+4), left_Xpos, (Ypos-2-4), '*' );
//draw_line right side
	draw_line( right_Xpos, (Ypos+4+4), right_Xpos, (Ypos+4-4), '*' );
	show_screen( );
}

int main( void ) {
	setup_screen();

	draw_paddles();

	draw_string( 0, screen_height( ) - 1, "Press any key to finish..." );
	wait_char();
	cleanup_screen();
	return 0;
}