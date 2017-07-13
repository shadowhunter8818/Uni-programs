#include <avr/io.h>
#include <util/delay.h>
#include <stdio.h>

#include <avr/interrupt.h>
#include <stdlib.h>
#include <math.h>

#include "lcd.h"
#include "graphics.h"
#include "cpu_speed.h"
#include "sprite.h"

#include "usb_serial.h"
	
	unsigned char* create_bitmap(unsigned char w, unsigned char h);
	void init_hardware(void);
	void game_start(void);
	void backgroud(void);
	void playagain(void);
	void create_sprites(void);
	void create_Alien(void);
	void Set_direction(void);
	void move_alien(void);
	void move_missile(void);
	void detect_hit(void);
	double get_system_time(void);
	void send_debug_string(char* string);
	void spawn_queen (void);	
	
	int start = 0;
	int lives = 3;
	int score = 0;
	int time_SS_count =0;
	int time_SS = 0;
	int time_mm = 0;
	int gameover = 0;
	double target_x = 0.0;
	double target_y = 0.0;
	int alien_attack_time = 0;
	int attack_in_progress = 0;
	int move_check = 1;
	double movex = 0.0;
	double movey = 0.0;
	double move_x = 0.0;
	double move_y = 0.0;
	int fire_direction = 0;
	int missile_fired = 0;
	int hitx = 0;
	int hity = 0;
	int alienhitx = 0;
	int alienhity = 0;
	int Alienhere = 1;
	int L = 0;
	int R = 0;
	int U = 0;
	int D = 0;
	int alien_spawned = 0;
	int alienspawntime = 11;
	int queen_spawn_time = 1;
	int queen_spawned = 0;
	int queen_hitx = 0;
	int queen_hity = 0;
	int queenlives = 3;
	int qhitx = 0;
	int qhity = 0;
	
	volatile unsigned long ovf_count = 0;
	int usb_serial_connect = 0;
	
	Sprite ship;
	Sprite direction;
	Sprite Alien;
	Sprite Missile;
	Sprite QueenLeft;
	Sprite Queenright;
	Sprite health1;
	Sprite health2;
	Sprite health3;
	
	unsigned char ship_img[7] = {
		0b00010000,
		0b00111000,
		0b01111110,
		0b00111000,
		0b00010000,
		0b00000000,
		0b00000000
	};
	
	unsigned char Direction_img[7] = {
		0b10000000,
		0b00000000,
		0b00000000,
		0b00000000,
		0b00000000,
		0b00000000,
		0b00000000
	};
	
	unsigned char alien_img[7] = {
		0b01000101,
		0b01111110,
		0b01011010,
		0b01011011,
		0b10011001,
		0b00000000,
		0b00000000
	};
	
	unsigned char missile_img[7] = {
		0b11000000,
		0b11000000,
		0b00000000,
		0b00000000,
		0b00000000,
		0b00000000,
		0b00000000
	};
	
	unsigned char Queen_Left[7] = {
		0b00111111,
		0b01100000,
		0b11000000,
		0b11000000,
		0b01100000,
		0b00111111,
		0b00000000
	};
	
	unsigned char Queen_right[7] = {
		0b11111000,
		0b00000110,
		0b00000011,
		0b00000011,
		0b00000110,
		0b11111100,
		0b00000000
	};
	
	unsigned char health[7] = {
		0b11000000,
		0b11000000,
		0b00000000,
		0b00000000,
		0b00000000,
		0b00000000,
		0b00000000
	};
	
/*
* Main - setup everything, then run the main loop
*/
int main() {
    set_clock_speed(CPU_8MHz);

    // Setup the hardware
    init_hardware();
	game_start();
	init_sprite(&ship,0,0,0,0,ship_img);
	init_sprite(&direction,0,0,0,0,Direction_img);
	init_sprite(&Alien,0,0,0,0,alien_img);
	init_sprite(&Missile,0,0,0,0,missile_img);
	init_sprite(&Queenright,0,0,0,0,Queen_right);
	init_sprite(&QueenLeft,0,0,0,0,Queen_Left);
	init_sprite(&health1,0,0,0,0,health);
	init_sprite(&health2,0,0,0,0,health);
	init_sprite(&health3,0,0,0,0,health);
	create_sprites();
	alien_attack_time = 2 + rand() % 2;
    // Run the main loop - check for presses (debounced), print the current count
   
    while (gameover != 1) {
		// Draw the border to the screen
		char console_input = usb_serial_getchar();
		backgroud();
		draw_sprite(&ship);
		draw_sprite(&direction);
		draw_sprite(&Alien);
		draw_sprite(&Missile);
		move_missile();
		if (queen_spawned == 1){
			draw_sprite(&QueenLeft);
			draw_sprite(&Queenright);
			if (queenlives >= 1) {
				draw_sprite(&health1);
			}
			if (queenlives >= 2) {
				draw_sprite(&health2);
			}
			if (queenlives == 3) {
				draw_sprite(&health3);
			}
		}
		if (Alienhere == 1) {
			move_alien();
		}
		detect_hit();
		
		//ship movement
		if (((PIND >> 1) & 0b1 || console_input == 'w') && ship.y != 12)  {
			ship.y--;
			direction.y = ship.y - 1;
			direction.x = ship.x + 3;
		} else {
			if (((PINB >> 7) & 0b1 || console_input == 's') && ship.y != 41) {
				ship.y++;
				direction.y = ship.y + 5;
				direction.x = ship.x + 3;
			} else {
				if (((PINB >> 1) & 0b1 || console_input == 'a') && ship.x != 1) {
					ship.x--;
					direction.y = ship.y + 2;
					direction.x = ship.x;
				} else {
					if (((PIND >> 0) & 0b1 || console_input == 'd') && ship.x != 76) {
						ship.x++;
						direction.y = ship.y + 2;
						direction.x = ship.x + 6;
					}
				}
			}
		}
		
		//fire control
		if ((((PINF>>5) & 0b1) || console_input == 'e') && missile_fired == 0)  {
			missile_fired = 1;
			fire_direction = 0;
			if (direction.y == (ship.y - 1) && direction.x == (ship.x + 3)) {
				fire_direction = 1;
				Missile.y = ship.y - 3;
				Missile.x = ship.x + 2;
			}
			if (direction.y == (ship.y + 5) && direction.x == (ship.x + 3)) { 
				fire_direction = 2;
				Missile.y = ship.y + 6;
				Missile.x = ship.x + 2;
			}
			if (direction.y == (ship.y + 2) && direction.x == (ship.x)) { 
				fire_direction = 3;
				Missile.y = ship.y + 2;
				Missile.x = ship.x - 2;
			}
			if (direction.y == (ship.y + 2) && direction.x == (ship.x + 6)) { 
				fire_direction = 4;
				Missile.y = ship.y + 2;
				Missile.x = ship.x + 7;
			}
		}
			
		//Alien attack and movement
		if (alien_attack_time == time_SS && attack_in_progress != 1){
			target_x = ship.x + 3;
			target_y = ship.y + 3;
			
			
			if (fabs(Alien.x - ship.x) > fabs(Alien.y - ship.y)) {
				move_x = fabs(Alien.x - ship.x)/ (fabs(Alien.x - ship.x) + fabs(Alien.y - ship.y));
				move_y = 1.0 - move_x;
			}
			
			if (fabs(Alien.x - ship.x) < fabs(Alien.y - ship.y)) {
				move_y = fabs(Alien.y - ship.y)/ (fabs(Alien.x - ship.x) + fabs(Alien.y - ship.y));
				move_x = 1.0 - move_y;
			}
			
			movex = 0;
			movey = 0;
			attack_in_progress = 1;
		}
		
		while (lives == 0) {
			playagain();
		}
        
		//Debugger code
		if (time_SS_count == 9) {
			char buff[80];
			int ship_placex = ship.x;
			int ship_placey = ship.y;
			sprintf(buff, "] The spaceships current position (x,y) is %d %d",ship_placex,ship_placey);
			send_debug_string(buff);
			if (direction.y == (ship.y - 1) && direction.x == (ship.x + 3)) {
				send_debug_string("] The spaceship is facing up");
			}
			if (direction.y == (ship.y + 5) && direction.x == (ship.x + 3)) {
				send_debug_string("] The spaceship is facing down");
			}
			if (direction.y == (ship.y + 2) && direction.x == ship.x) {
				send_debug_string("] The spaceship is facing to the left");
			}
			if (direction.y == (ship.y + 2) && direction.x == (ship.x + 6)) {
				send_debug_string("] The spaceship is facing to the right");
			}
		}
		
		while ( usb_serial_getchar() >= 0 ) {}
		
		
		
		//Queen spawn
		if (queen_spawned == 0 && queen_spawn_time == score) {
			spawn_queen();
			queen_spawned = 1;
			queen_spawn_time = score + 11;
			queenlives = 3;
		}
		
		//Alien spawn
		if (alienspawntime == score && alien_spawned == 0) {
			create_Alien();
			alien_spawned++;
			alienspawntime = score + 11;
			Alienhere = 1;
			attack_in_progress = 0;
			alien_attack_time = time_SS + 2 + rand() % 2;
			if (alien_attack_time > 60) {
				alien_attack_time = alien_attack_time - 60;
			}
			L = 0;
			R = 0;
			U = 0;
			D = 0;
		}
		
		time_SS_count++;
		if (time_SS_count == 10) {
			time_SS_count = 0;
			time_SS++;
			if (time_SS == 60) {
				time_mm++;
				time_SS = 0;
			}
		}
        show_screen();
		clear_screen();
		_delay_ms(50);
    }
	
return 0;
}

void backgroud(void) {
	draw_line(0,0,83,0);
	draw_line(0,10,83,10);
	draw_line(0,47,83,47);
	draw_line(0,0,0,47);
	draw_line(83,0,83,47);
	
	char buff[80];
	draw_string(2, 2, "L:");
	sprintf(buff, "%1d", lives);
	draw_string(11, 2, buff);
	
	draw_string(20, 2, "T:");
	sprintf(buff, "%02d", time_mm);
	draw_string(30, 2, buff);
	draw_string(40, 2, ":");
	sprintf(buff, "%02d", time_SS);
	draw_string(45, 2, buff);
	
	draw_string(63, 2, "S:");
	sprintf(buff, "%02d", score);
	draw_string(72, 2, buff);
	
}

void playagain(void) {
	clear_screen();
	draw_string(8, 20, "Play again?");
	show_screen();
	while (((PINF>>6) & 0b1) || usb_serial_getchar() == 'e' || ((PINF>>5) & 0b1)) {
		_delay_ms(100);
		start = 0;
		lives = 3;
		score = 0;
		time_SS_count =0;
		time_SS = 0;
		time_mm = 0;
		game_start();
		create_sprites();
		alien_attack_time = 2 + rand() % 2;
		Alienhere = 1;
	}
}

void game_start(void) {
		//start screen
	while (start == 0) {
		draw_string(10, 2, "Alien Advance");
		draw_string(12, 10, "Mark Parsons");
		draw_string(20, 18, "N8590460");
		draw_string(5, 32, "Press a button");
		draw_string(10, 40, "to continue...");
		show_screen();
		if (usb_serial_connect == 0) {
			if (usb_serial_get_control()) {
				usb_serial_connect++;
				clear_screen();
				draw_string(12, 18, "Connected Via");
				draw_string(20, 26, "USB");
				show_screen();
				send_debug_string("] Welcome to Alien Advance");
				send_debug_string("] Movement - W,A,S,D");
				send_debug_string("] Firemissile - E");
				_delay_ms(3000);
			}
		}
		clear_screen();
		while ((PINF>>5) & 0b1 || (PINF>>6) & 0b1 || usb_serial_getchar() >= 0) {
		while ( usb_serial_getchar() >= 0 ) {}
		_delay_ms(100);
		start++;
		}
	}
	clear_screen();
	// game entrance countdown
	draw_string(40, 22, "3");
	show_screen();
	_delay_ms(300);
	draw_string(40, 22, "2");
	show_screen();
	_delay_ms(300);
	draw_string(40, 22, "1");	
	show_screen();
	_delay_ms(300);
	clear_screen();
}

void init_hardware(void) {
    // Initialising the LCD screen
    lcd_init(LCD_DEFAULT_CONTRAST);

    // Initalising the push buttons as inputs
    DDRF &= ~((1 << PF5) | (1 << PF6));
	
	// Initialise the USB serial
    usb_init();
	
	//initalise the joystick
	DDRB &= ~(1<<1);
	DDRB &= ~(1<<7);
	DDRD &= ~(1<<0);
	DDRD &= ~(1<<1);
	
	//initlize timer 1
	TCCR1B |= ((1 << CS12) | (1 << CS10));
	TIMSK1 |= (1 << TOIE1);
	
	// Enable interrupts globally
    sei();
}

void create_sprites(void) {
	ship.width = 6;
	ship.height = 5;
	ship.x = 3 + rand() % 75;
	ship.y = 12 + rand() % 25;
	direction.width = 1;
	direction.height = 1;
	direction.y = ship.y - 1;
	direction.x = ship.x + 3;
	Alien.width = 8;
	Alien.height = 5;
	Alien.x = 3 + rand() % 72;
	while ((Alien.x < ship.x && Alien.x > (ship.x - 8))||(Alien.x > ship.x && Alien.x < (ship.x + 8))){
		Alien.x = 3 + rand() % 72;
	}
	Alien.y = 12 + rand() % 25;
	while ((Alien.y < ship.y && Alien.y > (ship.y - 8))||(Alien.y > ship.y && Alien.y < (ship.y + 8))){
		Alien.y = 12 + rand() % 25;
	}
	Missile.width = 2;
	Missile.height = 2;
	Missile.x = 8;
	Missile.y = 6;
	QueenLeft.width = 8;
	QueenLeft.height = 6;
	Queenright.width = 8;
	Queenright.height = 6;
	QueenLeft.x = 90;
	Queenright.y = 90;
	QueenLeft.y = 90;
	Queenright.x = 90;
	health1.width = 2;
	health1.height = 2;
	health1.x = 4;
	health1.y = 60;
	health2.width = 2;
	health2.height = 2;
	health2.x = 3;
	health2.y = 60;
	health3.width = 2;
	health3.height = 2;
	health3.x = 3;
	health3.y = 60;
}

void create_Alien(void) {
	Alien.x = 3 + rand() % 72;
	while ((Alien.x < ship.x && Alien.x > (ship.x - 8))||(Alien.x > ship.x && Alien.x < (ship.x + 8))){
		Alien.x = 3 + rand() % 72;
	}
	Alien.y = 12 + rand() % 25;
	while ((Alien.y < ship.y && Alien.y > (ship.y - 8))||(Alien.y > ship.y && Alien.y < (ship.y + 8))){
		Alien.y = 12 + rand() % 25;
	}
}

void move_alien(void){
	if (time_SS_count == 0 || time_SS_count == 2 || time_SS_count == 5 || time_SS_count == 7) {
		if (attack_in_progress == 1) {
			if (Alien.y == 11) {
				Alien.y += 1;
			}
			if (Alien.y == 42) {
				Alien.y -= 1;
			}
			if (Alien.x == 1) {
				Alien.x += 1;
			}
			if (Alien.x == 75) {
				Alien.x -= 1;
			}
			movex += move_x;
			movey += move_y;
			if ((Alien.x < target_x || L == 1) && R != 1) {
				L =1;
				if (movex >= 1) {
					Alien.x += 1;
					movex--;
				}
			} else {
				if ((Alien.x > target_x || R == 1) && L != 1) {
					R = 1; 
					if (movex >= 1) {
						Alien.x -= 1;
						movex--;
					}
				}
			}
			
			if ((Alien.y < target_y || U == 1) && D != 1) {
				U = 1;
				if (movey >= 1) {
					Alien.y += 1;
					movey--;
				}
			} else {
					if ((Alien.y > target_y || D == 1) && U != 1) {
						D = 1;
						if (movey >= 1) {
						Alien.y -= 1;
						movey--;
						}
					}	
				}			
			if (Alien.y == 11 || Alien.x == 1 || Alien.y == 42 || Alien.x == 75) {
				attack_in_progress = 0;
				alien_attack_time = time_SS + 2 + rand() % 2;
				if (alien_attack_time > 60) {
					alien_attack_time = alien_attack_time - 60;
				}
				L = 0;
				R = 0;
				U = 0;
				D = 0;
			}
		}	
	}
}

void Set_direction(void) {
	direction.x = ship.x;
	direction.y = ship.y;
}

void move_missile(void) {
	move_check++;
	if (move_check == 2) {
		move_check = 0;
		if (Missile.x != 8 || Missile.y != 6){
			if (fire_direction == 1) {
				Missile.y--;
			}
			if (fire_direction == 2) {
				Missile.y++;
			}
			if (fire_direction == 3) {
				Missile.x--;
			}
			if (fire_direction == 4) {
				Missile.x++;
			}
			if (Missile.y == 10 || Missile.x == 0 || Missile.y == 46 || Missile.x == 82) {
				Missile.x = 8;
				Missile.y = 6;
				missile_fired = 0;
			}
		}
	}
}

void detect_hit(void) {
	queen_hitx = 0;
	queen_hity = 0;
	alienhitx = 0;
	alienhity = 0;
	hitx = 0;
	hity = 0;
	qhitx = 0;
	qhity = 0;
	
	//Alien Missile hit check
	for (int a = 0; a < 9; a++) {
		if ((Alien.x + a) == Missile.x || (Alien.x + a) == (Missile.x + 1)) {
			alienhitx = 1;
		}
	}
	for (int t = 0; t < 6; t++) {
		if ((Alien.y + t) == Missile.y || (Alien.y + t) == (Missile.y + 1)) {
			alienhity = 1;
		}
	}
	if (alienhitx == 1 && alienhity == 1) {
		score++;
		missile_fired = 0;
		Missile.x = 8;
		Missile.y = 6;
		send_debug_string("] Alien Killed");
		Alien.x = 60;
		Alien.y = 60;
		Alienhere = 0;
		alien_spawned = 0;
	}
	
	//Alien collision check
	for (int a = 0; a < 9; a++) {
		for (int s = 0; s < 7; s++) {
			if ((Alien.x + a) == (ship.x + s)) {
				hitx = 1;				
			}
		}
	}
	for (int t = 0; t < 6; t++) {
		for (int u = 0; u < 6; u++) {
			if ((Alien.y + t) == (ship.y + u)) {
				hity = 1;
			}
		}
	}
	if (hitx == 1 && hity == 1) {
		lives--;
		send_debug_string("] Player killed by alien");
		ship.x = 3 + rand() % 72;
		while ((Alien.x < ship.x && Alien.x > (ship.x - 8))||(Alien.x > ship.x && Alien.x < (ship.x + 8))){
		ship.x = 3 + rand() % 72;
		}
		ship.y = 12 + rand() % 28;
		while ((Alien.y < ship.y && Alien.y > (ship.y - 8))||(Alien.y > ship.y && Alien.y < (ship.y + 8))){
		ship.y = 12 + rand() % 28;
		}
		direction.x = 0;
		direction.y = 0;
	}
	
	//Queen missile hit
	for (int a = 0; a < 16; a++) {
		if ((QueenLeft.x + a) == Missile.x || (QueenLeft.x + a) == (Missile.x + 1)) {
			queen_hitx = 1;
		}
	}
	for (int t = 0; t < 7; t++) {
		if ((QueenLeft.y + t) == Missile.y || (QueenLeft.y + t) == (Missile.y + 1)) {
			queen_hity = 1;
		}
	}
	if (queen_hitx == 1 && queen_hity == 1) {
		queenlives--;
		missile_fired = 0;
		Missile.x = 8;
		Missile.y = 6;
		if (queenlives == 0) {
			score = score + 10;
			send_debug_string("] Queen Killed");
			QueenLeft.x = 60;
			QueenLeft.y = 60;
			queen_spawned = 0;
		}
	}
	
	//Queen collision detection
	for (int a = 0; a < 16; a++) {
		for (int s = 0; s < 7; s++) {
			if ((QueenLeft.x + a) == (ship.x + s)) {
				qhitx = 1;				
			}
		}
	}
	for (int t = 0; t < 7; t++) {
		for (int u = 0; u < 6; u++) {
			if ((QueenLeft.y + t) == (ship.y + u)) {
				qhity = 1;
			}
		}
	}
	if (qhitx == 1 && qhity == 1) {
		lives--;
		send_debug_string("] Player killed by queen");
		ship.x = 3 + rand() % 76;
		while ((QueenLeft.x < ship.x && QueenLeft.x > (ship.x - 16))||(QueenLeft.x > ship.x && QueenLeft.x < (ship.x + 16))){
		ship.x = 3 + rand() % 76;
		}
		ship.y = 12 + rand() % 33;
		while ((QueenLeft.y < ship.y && QueenLeft.y > (ship.y - 16))||(QueenLeft.y > ship.y && QueenLeft.y < (ship.y + 16))){
		ship.y = 12 + rand() % 33;
		}
		direction.x = 0;
		direction.y = 0;
	}
}

double get_system_time(void) {
    return (ovf_count * 65536 + TCNT1) * 1024.0 / 8000000.0;
}

void send_debug_string(char* string) {
	 char buff[80];
     sprintf(buff, "[DEBUG @ %06.2f",get_system_time());
	 usb_serial_write(buff,15);
     unsigned char char_count = 0;
     while (*string != '\0') {
         usb_serial_putchar(*string);
         string++;
         char_count++;
     }
     usb_serial_putchar('\r');
     usb_serial_putchar('\n');
 }

void spawn_queen (void) {
	QueenLeft.x = 3 + rand() % 68;
	while ((QueenLeft.x < ship.x && QueenLeft.x > (ship.x - 16))||(QueenLeft.x > ship.x && QueenLeft.x < (ship.x + 16))){
		QueenLeft.x = 3 + rand() % 68;
	}
	QueenLeft.y = 11 + rand() % 22;
	while ((QueenLeft.y < ship.y && QueenLeft.y > (ship.y - 16))||(QueenLeft.y > ship.y && QueenLeft.y < (ship.y + 16))){
		QueenLeft.y = 11 + rand() % 22;
	}
	Queenright.x = QueenLeft.x + 8;
	Queenright.y = QueenLeft.y;
	
	health1.x = QueenLeft.x + 4;
	health1.y = QueenLeft.y + 2;
	health2.x = health1.x + 3;
	health2.y = health1.y;
	health3.x = health2.x + 3;
	health3.y = health2.y;
}
 
ISR(TIMER1_OVF_vect) {
    ovf_count++;
}
