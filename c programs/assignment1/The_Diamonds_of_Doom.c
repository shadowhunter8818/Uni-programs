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
/**/    "#           z, c : shoot bullet        #"                                      
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
/**/  "   ^   "                                                     
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
sprite_id Secondary_Missiles_left;
sprite_id Start_screen;
sprite_id End_screen;
sprite_id Ship;
sprite_id Diamonds[6];

int lives = 3;
int score = 0;
int score_current_life = 0;
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
	int x_diamond[] = {(2 + rand() % (screen_width() - DIAMOND_WIDTH-2)), (2 + rand() % (screen_width() - DIAMOND_WIDTH-2)), (2 + rand() % (screen_width() - DIAMOND_WIDTH-2)), (2 + rand() % (screen_width() - DIAMOND_WIDTH-2)), (2 + rand() % (screen_width() - DIAMOND_WIDTH-2)), (2 + rand() % (screen_width() - DIAMOND_WIDTH-2)), (2 + rand() % (screen_width() - DIAMOND_WIDTH-2)), (2 + rand() % (screen_width() - DIAMOND_WIDTH-2)), (2 + rand() % (screen_width() - DIAMOND_WIDTH-2)), (2 + rand() % (screen_width() - DIAMOND_WIDTH-2))};
	int y_diamond = 3;
	for (int y = 0; y < 10; y++){ 
		Diamonds[y] = sprite_create(x_diamond[y],y_diamond,DIAMOND_WIDTH,DIAMOND_HEIGHT,Diamond);
		sprite_turn_to(Diamonds[y],1,0);
		sprite_turn(Diamonds[y],-(40 + rand() % 110));
		sprite_step(Diamonds[y]);
	}
}

void Fire_Missile_right (void){
	int x_missile = sprite_x(Ship)+(SHIP_WIDTH / 2) + 3;
	int y_missile = screen_height()-2;
	Missile = sprite_create(x_missile,y_missile,MISSILE_WIDTH,MISSILE_HEIGHT,missile);
	sprite_draw(Missile);
	sprite_turn_to(Missile,0,-1);
	sprite_step(Missile);
}

void fire_missile_left (void){
	int x_missile_l = sprite_x(Ship)+(SHIP_WIDTH / 2) - 3;
	int y_missile = screen_height()-2;
	Secondary_Missiles_left = sprite_create(x_missile_l,y_missile,MISSILE_WIDTH,MISSILE_HEIGHT,missile);
	sprite_draw(Secondary_Missiles_left);
	sprite_turn_to(Secondary_Missiles_left,0,-1);
	sprite_step(Secondary_Missiles_left);
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
	double missile_y_left = sprite_y(Secondary_Missiles_left);
	int ship_x = round(sprite_x(Ship));
	
	//setting help loop
	if (key == 'h') {
		clear_screen();
		screen_setup();
		Start_help();
		sprite_hide(Start_screen);
		sprite_hide(End_screen);
		clear_screen();
	}
	
	//drawing objects
	draw_formatted(1,1," Lives = %4d               # Score = %4d               # Time = %0.2d:%0.2d",lives, score, game_time_m, game_time_s);
	draw_borders();
	sprite_draw(Ship);
	for (int y = 0; y < 10; y++){ 
		sprite_draw(Diamonds[y]);
	}
	sprite_draw(Missile);
	sprite_draw(Secondary_Missiles_left);
	if (first_run_check == 0) {
		sprite_hide(Missile);
		sprite_hide(Secondary_Missiles_left);
		first_run_check++;
	}
	
	//Ship actions
	if (key == KEY_LEFT  && ship_x > 1 ) sprite_move (Ship,-1,0);
	if (key == KEY_RIGHT && ship_x + SHIP_WIDTH < screen_width()-1) sprite_move (Ship,+1,0);
	if (key == 'c' && sprite_visible(Missile) != true) {
		Fire_Missile_right();
		sprite_show(Missile);
	}
	if (key == 'z' && sprite_visible(Secondary_Missiles_left) != true) {
		fire_missile_left();
		sprite_show(Secondary_Missiles_left);
	}
	
	//Diamond and missile movement + rebound 
	//Diamond
	double diamond_x[10] = {sprite_x(Diamonds[0]), sprite_x(Diamonds[1]), sprite_x(Diamonds[2]), sprite_x(Diamonds[3]), sprite_x(Diamonds[4]), sprite_x(Diamonds[5]), sprite_x(Diamonds[6]), sprite_x(Diamonds[7]), sprite_x(Diamonds[8]), sprite_x(Diamonds[9])};
	double diamond_y[10] = {sprite_y(Diamonds[0]), sprite_y(Diamonds[1]), sprite_y(Diamonds[2]), sprite_y(Diamonds[3]), sprite_y(Diamonds[4]), sprite_y(Diamonds[5]), sprite_y(Diamonds[6]), sprite_y(Diamonds[7]), sprite_y(Diamonds[8]), sprite_y(Diamonds[9])};
	if(mili_timer % 20 == 0 || mili_timer == 0 ) {
		for (int y = 0; y < 10; y++){ 
			sprite_step(Diamonds[y]);
		}
	}
	if(mili_timer == 19 || mili_timer == 39 || mili_timer == 59 || mili_timer == 79 || mili_timer == 99){
		for (int i = 0; i < 10; i++) {
			if ( diamond_x[i] <= 2 || diamond_x[i] + DIAMOND_WIDTH >= (screen_width() - 1)) sprite_turn_to(Diamonds[i],-sprite_dx(Diamonds[i]),sprite_dy(Diamonds[i]));
			if ( round(diamond_y[i]) == 3) sprite_turn_to(Diamonds[i],sprite_dx(Diamonds[i]),-sprite_dy(Diamonds[i]));
			if (round(diamond_y[i] + 4) >= (screen_height()-2)) sprite_turn_to(Diamonds[i],sprite_dx(Diamonds[i]),-sprite_dy(Diamonds[i]));
		}
	}
	//Missile
	if(mili_timer == 0 || mili_timer % 10 == 0) {
		if(sprite_visible(Missile) == true) sprite_step(Missile);
		if ( missile_y == 3) sprite_hide(Missile);
	}
	if(mili_timer == 0 || mili_timer % 10 == 0) {
		if(sprite_visible(Secondary_Missiles_left) == true) sprite_step(Secondary_Missiles_left);
		if ( missile_y_left == 3) sprite_hide(Secondary_Missiles_left);
	}

	//missile hit/Diamond boundry box setup
	for (int y = 0; y < 10; y++){
		int Diamond_collision_x[] = {round(sprite_x(Diamonds[y]) + DIAMOND_WIDTH / 2), round(sprite_x(Diamonds[y]) + DIAMOND_WIDTH / 2), round(sprite_x(Diamonds[y]) + DIAMOND_WIDTH / 2) + 1, round(sprite_x(Diamonds[y]) + DIAMOND_WIDTH / 2) - 1, round(sprite_x(Diamonds[y]) + DIAMOND_WIDTH / 2), round(sprite_x(Diamonds[y]) + DIAMOND_WIDTH / 2) + 1, round(sprite_x(Diamonds[y]) + DIAMOND_WIDTH / 2) - 1,  round(sprite_x(Diamonds[y]) + DIAMOND_WIDTH / 2) + 2, round(sprite_x(Diamonds[y]) + DIAMOND_WIDTH / 2) - 2};
		int Diamond_collision_y[] = {round(sprite_y(Diamonds[y])) + 4, round(sprite_y(Diamonds[y])) + 3, round(sprite_y(Diamonds[y])) + 3, round(sprite_y(Diamonds[y])) + 3, round(sprite_y(Diamonds[y])) + 2, round(sprite_y(Diamonds[y])) + 2, round(sprite_y(Diamonds[y])) + 2, round(sprite_y(Diamonds[y])) + 2, round(sprite_y(Diamonds[y])) + 2};
		if(mili_timer == 0 || mili_timer == 9 || mili_timer == 19 || mili_timer == 29 || mili_timer == 39 || mili_timer == 49 || mili_timer == 59 || mili_timer == 69 || mili_timer == 79 || mili_timer == 89 || mili_timer == 99) {	
			for (int i = 0; i < 9; i++) {
				if (Diamond_collision_x[i] == round(sprite_x(Missile)) && Diamond_collision_y[i] == round(sprite_y(Missile)) && sprite_visible(Missile) == true && sprite_visible(Diamonds[y]) == true) {
					score++;
					score_current_life++;
					sprite_hide(Missile);
					sprite_hide(Diamonds[y]);
				}
				if (Diamond_collision_x[i] == round(sprite_x(Secondary_Missiles_left)) && Diamond_collision_y[i] == round(sprite_y(Secondary_Missiles_left)) && sprite_visible(Secondary_Missiles_left) == true && sprite_visible(Diamonds[y]) == true) {
					score++;
					score_current_life++;
					sprite_hide(Secondary_Missiles_left);
					sprite_hide(Diamonds[y]);
				}
			}
		}
	}
	
	
	if (score_current_life == 10 ) {
		create_diamonds();
		score_current_life = 0;
		for (int y = 0; y < 10; y++){
			sprite_show(Diamonds[y]);
		}
	}
	
	//collisions
	//ship boundry box 
	if(mili_timer % 20 == 0 || mili_timer == 0 ) {
		collision = false;
			for (int y = 0; y < 10; y++){
			int Diamond_collision_x[] = {round(sprite_x(Diamonds[y]) + DIAMOND_WIDTH / 2), round(sprite_x(Diamonds[y]) + DIAMOND_WIDTH / 2), round(sprite_x(Diamonds[y]) + DIAMOND_WIDTH / 2) + 1, round(sprite_x(Diamonds[y]) + DIAMOND_WIDTH / 2) - 1, round(sprite_x(Diamonds[y]) + DIAMOND_WIDTH / 2), round(sprite_x(Diamonds[y]) + DIAMOND_WIDTH / 2) + 1, round(sprite_x(Diamonds[y]) + DIAMOND_WIDTH / 2) - 1,  round(sprite_x(Diamonds[y]) + DIAMOND_WIDTH / 2) + 2, round(sprite_x(Diamonds[y]) + DIAMOND_WIDTH / 2) - 2};
			int Diamond_collision_y[] = {round(sprite_y(Diamonds[y])) + 4, round(sprite_y(Diamonds[y])) + 3, round(sprite_y(Diamonds[y])) + 3, round(sprite_y(Diamonds[y])) + 3, round(sprite_y(Diamonds[y])) + 2, round(sprite_y(Diamonds[y])) + 2, round(sprite_y(Diamonds[y])) + 2, round(sprite_y(Diamonds[y])) + 2, round(sprite_y(Diamonds[y])) + 2};
			int Ship_collision_x[] = {round(sprite_x(Ship) + SHIP_WIDTH/2), round(sprite_x(Ship) + SHIP_WIDTH/2) + 1, round(sprite_x(Ship) + SHIP_WIDTH/2) - 1, round(sprite_x(Ship) + SHIP_WIDTH/2) + 2, round(sprite_x(Ship) + SHIP_WIDTH/2) - 2, round(sprite_x(Ship) + SHIP_WIDTH/2) + 3, round(sprite_x(Ship) + SHIP_WIDTH/2) - 3};
			int Ship_collision_y[] = {round(sprite_y(Ship)), round(sprite_y(Ship)) + 1, round(sprite_y(Ship)) + 1, round(sprite_y(Ship)) + 2, round(sprite_y(Ship)) + 2, round(sprite_y(Ship)) + 2, round(sprite_y(Ship)) + 2};
			for (int t = 0; t < 7; t++) {
				for (int i = 0; i < 9; i++) {
					if (collision == false) {
						if (Diamond_collision_x[i] == Ship_collision_x[t] && Diamond_collision_y[i] == Ship_collision_y[t] && sprite_visible(Diamonds[y]) == true) {
							lives--;
							score_current_life = 0;
							sprite_hide(Missile);
							sprite_hide(Secondary_Missiles_left);
							create_ship();
							create_diamonds();
							sprite_show(Diamonds[y]);
							collision = true;
						}
					}
				}
			}
		}
	}
	
	// END screen if lives = 0
	if (lives == 0){
		clear_screen();
		screen_setup();
		sprite_hide(Start_screen);
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
				sprite_hide(Missile);
				sprite_hide(Secondary_Missiles_left);
				screen_setup();
				Start_help();
				sprite_hide(Start_screen);
				sprite_hide(End_screen);
				clear_screen();
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
		screen_setup();
		sprite_hide(Start_screen);
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
				sprite_hide(Missile);
				sprite_hide(Secondary_Missiles_left);
				screen_setup();
				Start_help();
				sprite_hide(Start_screen);
				sprite_hide(End_screen);
				clear_screen();
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
	screen_setup(); 
	Start_help(); 
	create_diamonds();
	Fire_Missile_right();
	fire_missile_left();
	while ( !game_over ){
		
		game_setup();
		
		if ( update_screen ) {
			show_screen();
		}
		timer_pause(DELAY);
	} ;
	return 0;
}