//gcc The_Diamonds_of_Doom.c -std=gnu99 -I../ZDK -L../ZDK -lzdk -lncurses -o The_Diamonds_of_Doom

#include<ncurses.h>

#include <stdlib.h>
#include <math.h>
#include <cab202_graphics.h>
#include <cab202_sprites.h>
#include <cab202_timers.h>


// Configuration
#define DELAY (10) /* Millisecond delay between game updates */
#define START_SCREEN_WIDTH (40)
#define START_SCREEN_HEIGHT (14)
#define END_SCREEN_WIDTH (27)
#define END_SCREEN_HEIGHT (6)
#define SHIP_WIDTH (7)
#define SHIP_HEIGHT (3)
#define DIAMOND_WIDTH (5)
#define DIAMOND_HEIGHT (5)
#define MISSILE_WIDTH (1)
#define MISSILE_HEIGHT (1)

// Game state.
bool game_over = false; /* Set this to true when game is over */
bool update_screen = true; /* Set to false to prevent screen update. */

char * Start_screen_image =
/**/ 	"########################################"                                     
/**/    "#         CAB202 Assignment 1          #"                                      
/**/    "#        The Diamonds of Doom          #"                                      
/**/    "#            Mark Parsons              #"                                      
/**/    "#              n8590460                #"                                      
/**/    "########################################"                                      
/**/    "#              Controls                #"                                      
/**/    "#      q         : quit                #"                                      
/**/    "#      h         : help                #"                                      
/**/    "#      Arrow keys: move left/right     #"                                      
/**/    "#     z, x, c, v : shoot bullet        #"                                      
/**/    "########################################"                                      
/**/    "#       Press a key to play...         #"                                      
/**/    "########################################";

char * End_screen_image =
/**/	"###########################"                                            
/**/	"#                         #"                                            
/**/    "#        Game Over        #"                                            
/**/    "#    Play again (y/n)?    #"                                            
/**/    "#                         #"                                            
/**/    "###########################";

char * Ship_image =
/**/  "   |   "                                                     
/**/  "  /O\\  "                                                     
/**/  "|/___\\|";

char * Diamond =
/**/  "  C  "
/**/  " CCC "
/**/  "CCCCC"
/**/  " CCC "
/**/  "  C  ";

char * missile = 
/**/ "!";

sprite_id Missile;
sprite_id Start_screen;
sprite_id End_screen;
sprite_id Ship;
sprite_id Diamonds;

int lives = 3;
int score = 0;
int game_time_m = 0;
int game_time_s = 00;
int mili_timer = 0;
int missile_check = 0;
int first_run_check = 0;
bool collision = false;

void screen_setup(void) {
	// setting up start screen to be in the center of the screen
	int x_S = (screen_width() - START_SCREEN_WIDTH) * 50/100;
	int y_S = (screen_height() - START_SCREEN_HEIGHT) * 50/100;
	Start_screen = sprite_create(x_S,y_S,START_SCREEN_WIDTH,START_SCREEN_HEIGHT,Start_screen_image);
	// setting game over screen
	int x_G = (screen_width() - END_SCREEN_WIDTH) * 50/100;
	int y_G = (screen_height() - END_SCREEN_HEIGHT) * 50/100;
	End_screen = sprite_create(x_G,y_G,END_SCREEN_WIDTH,END_SCREEN_HEIGHT,End_screen_image);
	// setting up ship
	int x_ship = (screen_width() - SHIP_WIDTH) * 50/100;
	int y_ship = (screen_height() - SHIP_HEIGHT - 1);
	Ship = sprite_create(x_ship,y_ship,SHIP_WIDTH,SHIP_HEIGHT,Ship_image);
}

void create_ship(void) {
	// setting up ship
	int x_ship = (screen_width() - SHIP_WIDTH) * 50/100;
	int y_ship = (screen_height() - SHIP_HEIGHT - 1);
	Ship = sprite_create(x_ship,y_ship,SHIP_WIDTH,SHIP_HEIGHT,Ship_image);
}

void create_diamonds (void) {
	//Setting up diamonds
	int x_diamond = 2 + rand() % (screen_width() - DIAMOND_WIDTH-2);
	int y_diamond = 3;
	Diamonds = sprite_create(x_diamond,y_diamond,DIAMOND_WIDTH,DIAMOND_HEIGHT,Diamond);
	sprite_turn_to(Diamonds,1,0);
	sprite_turn(Diamonds,-(40 + rand() % 110));
	sprite_step(Diamonds);
}

void fire_one_missile (void){
	int x_missile = sprite_x(Ship)+(SHIP_WIDTH / 2);
	int y_missile = screen_height()-4;
	Missile = sprite_create(x_missile,y_missile,MISSILE_WIDTH,MISSILE_HEIGHT,missile);
	sprite_draw(Missile);
	sprite_turn_to(Missile,0,-1);
	sprite_step(Missile);
}

void Start_help(void) {
	while ( get_char() >= 0 ) {}
	clear_screen();
	sprite_draw(Start_screen);
	show_screen();
	wait_char();
	clear_screen();
	
}

void draw_borders(void) {
	int left_border = 0;
	int right_border = (screen_width() - 1);
	int bottom = (screen_height()-1);
	draw_line( left_border, 0, left_border, bottom, '#' );
	draw_line( right_border, 0, right_border, bottom, '#' );
	draw_line( left_border+1, 0, right_border-1, 0, '#' );
	draw_line( left_border+1, bottom, right_border-1, bottom, '#' );
	draw_line( left_border+1, 2, right_border-1, 2, '#' );
}

void game_setup(void) {
	clear_screen();
	
	//Game timer
	mili_timer++;
	if (mili_timer == 100){
		game_time_s++;
		mili_timer = 0;
	}
	if (game_time_s == 60) {
		game_time_s = 0;
		game_time_m++;
	}
	
	int key = get_char();
	int loop = 0;
	double missile_y = sprite_y(Missile);
	int ship_x = round(sprite_x(Ship));
	if (key == 'h') Start_help();
	
	//drawing objects
	draw_formatted(1,1," Lives = %4d               # Score = %4d               # Time = %0.2d:%0.2d",lives, score, game_time_m, game_time_s);
	draw_borders();
	sprite_draw(Ship);
	sprite_draw(Diamonds);
	sprite_draw(Missile);
	if (first_run_check == 0) {
		sprite_hide(Missile);
		first_run_check++;
	}
	//Ship actions
	if (key == KEY_LEFT  && ship_x > 1 ) sprite_move (Ship,-1,0);
	if (key == KEY_RIGHT && ship_x + SHIP_WIDTH < screen_width()-1) sprite_move (Ship,+1,0);
	if (key == 'z' && sprite_visible(Missile) != true) {
		fire_one_missile();
		sprite_show(Missile);
		//missile_check++;
	}
	//Diamond and missile movement + rebound 
	//Diamond
	double diamond_x = sprite_x(Diamonds);
	double diamond_y = sprite_y(Diamonds);
	if(mili_timer % 20 == 0 || mili_timer == 0 ) {
		sprite_step(Diamonds);
	}
	if(mili_timer == 19 || mili_timer == 39 || mili_timer == 59 || mili_timer == 79 || mili_timer == 99){
	if ( diamond_x <= 2 || diamond_x + DIAMOND_WIDTH >= (screen_width() - 1)) sprite_turn_to(Diamonds,-sprite_dx(Diamonds),sprite_dy(Diamonds));
	if ( round(diamond_y) == 3) sprite_turn_to(Diamonds,sprite_dx(Diamonds),-sprite_dy(Diamonds));
	if (diamond_y + DIAMOND_HEIGHT >= (screen_height()-1) && fabs(sprite_dx(Diamonds)) > 0.2) sprite_turn_to(Diamonds,sprite_dx(Diamonds),-sprite_dy(Diamonds));
	if (diamond_y + 1 + DIAMOND_HEIGHT >= (screen_height()-1) && fabs(sprite_dx(Diamonds)) < 0.2) sprite_turn_to(Diamonds,sprite_dx(Diamonds),-sprite_dy(Diamonds));
	}
	//Missile
	if(mili_timer == 0 || mili_timer % 10 == 0) {
		if(sprite_visible(Missile) == true) sprite_step(Missile);
		if ( missile_y == 3) sprite_hide(Missile);
	}

	//missile hit/Diamond boundry box setup
	int Diamond_collision_x[] = {round(sprite_x(Diamonds) + DIAMOND_WIDTH / 2), round(sprite_x(Diamonds) + DIAMOND_WIDTH / 2), round(sprite_x(Diamonds) + DIAMOND_WIDTH / 2) + 1, round(sprite_x(Diamonds) + DIAMOND_WIDTH / 2) - 1, round(sprite_x(Diamonds) + DIAMOND_WIDTH / 2), round(sprite_x(Diamonds) + DIAMOND_WIDTH / 2) + 1, round(sprite_x(Diamonds) + DIAMOND_WIDTH / 2) - 1,  round(sprite_x(Diamonds) + DIAMOND_WIDTH / 2) + 2, round(sprite_x(Diamonds) + DIAMOND_WIDTH / 2) - 2};
	int Diamond_collision_y[] = {round(sprite_y(Diamonds)) + 4, round(sprite_y(Diamonds)) + 3, round(sprite_y(Diamonds)) + 3, round(sprite_y(Diamonds)) + 3, round(sprite_y(Diamonds)) + 2, round(sprite_y(Diamonds)) + 2, round(sprite_y(Diamonds)) + 2, round(sprite_y(Diamonds)) + 2, round(sprite_y(Diamonds)) + 2};
	if(mili_timer == 0 || mili_timer == 9 || mili_timer == 19 || mili_timer == 29 || mili_timer == 39 || mili_timer == 49 || mili_timer == 59 || mili_timer == 69 || mili_timer == 79 || mili_timer == 89 || mili_timer == 99) {	
		for (int i = 0; i < 9; i++) {
			if (Diamond_collision_x[i] == round(sprite_x(Missile)) && Diamond_collision_y[i] == round(sprite_y(Missile)) && sprite_visible(Missile) == true) {
				score++;
				sprite_hide(Missile);
				create_diamonds();
			}
		}
	}

	//collisions
	//ship boundry box 
	if(mili_timer % 20 == 0 || mili_timer == 0 ) {
		collision = false;
		int Ship_collision_x[] = {round(sprite_x(Ship) + SHIP_WIDTH/2), round(sprite_x(Ship) + SHIP_WIDTH/2) + 1, round(sprite_x(Ship) + SHIP_WIDTH/2) - 1, round(sprite_x(Ship) + SHIP_WIDTH/2) + 2, round(sprite_x(Ship) + SHIP_WIDTH/2) - 2, round(sprite_x(Ship) + SHIP_WIDTH/2) + 3, round(sprite_x(Ship) + SHIP_WIDTH/2) - 3};
		int Ship_collision_y[] = {round(sprite_y(Ship)), round(sprite_y(Ship)) + 1, round(sprite_y(Ship)) + 1, round(sprite_y(Ship)) + 2, round(sprite_y(Ship)) + 2, round(sprite_y(Ship)) + 2, round(sprite_y(Ship)) + 2};
		for (int t = 0; t < 7; t++) {
			for (int y = 0; y < 9; y++) {
				if (collision == false) {
					if (Diamond_collision_x[y] == Ship_collision_x[t] && Diamond_collision_y[y] == Ship_collision_y[t] && sprite_visible(Diamonds) == true) {
						lives--;
						sprite_hide(Missile);
						create_ship();
						create_diamonds();
						collision = true;
					}
				}
			}
		}
	}
	
		// END screen if lives = 0
	if (lives == 0){
		clear_screen();
		sprite_draw(End_screen);
		show_screen();
		while (get_char() >= 0 ) {}
		while (loop == 0){
			int end = get_char();
			if (end == 'n')	{
				game_over = true;
				loop = 1;
			}
			if (end == 'y'){
				Start_help();
				sprite_hide(Missile);
				lives = 3;
				score = 0;
				game_time_m = 0;
				game_time_s = 0;
				mili_timer = 0;
				loop = 1;
			}
		}
		clear_screen();
	}
	
	// END screen
	if (key == 'q'){
		clear_screen();
		sprite_draw(End_screen);
		show_screen();
		while (get_char() >= 0 ) {}
		while (loop == 0){
			int end = get_char();
			if (end == 'n')	{
				game_over = true;
				loop = 1;
			}
			if (end == 'y'){
				Start_help();
				sprite_hide(Missile);
				lives = 3;
				score = 0;
				game_time_m = 0;
				game_time_s = 0;
				mili_timer = 0;
				loop = 1;
			}
		}
		clear_screen();
	}

}

int main (void) {
	setup_screen();
	while ( get_char() >= 0 ) {}
	screen_setup(); //creates sprites doesnt draw any
	Start_help();   //draws start screen
	create_diamonds();
	fire_one_missile();
	while ( !game_over ){
		
		game_setup();
		
		if ( update_screen ) {
			show_screen();
		}
		timer_pause(DELAY);
	} ;
	return 0;
}