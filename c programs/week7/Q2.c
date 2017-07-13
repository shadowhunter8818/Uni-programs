//gcc Q2.c -o Q2
//gcc E3.c -std=gnu99 -I../ZDK -L../ZDK -lzdk -lncurses -o E3
#include <stdio.h>
#include <stdlib.h>

typedef unsigned char byte;

byte set_bit(byte register_value, int pin) {
    //  (a) Modify the register value by setting the designated pin
    //  to 1 without altering the other pins, and return the result.
    //  Hint: left shift; binary OR.
	register_value |= (1<<pin);
	return register_value; 
}

byte flip_bits(byte register_value) {
    //  (b) Flip all bits in the register value so that each bit that
    //  is originally 1 becomes 0, and each bit that is originally 0 
    //  becomes 1.
    //  Hint: binary NOT.
	register_value = ~register_value;
	return register_value;
}

byte clear_bit(byte register_value, int pin) {
    //  (c) Modify the register value by clearing the designated pin
    //  (that is, setting it to 0) without altering any other pins, and
    //  return the result.
    //  Hint: left shift; binary NOT; binary AND.
	register_value &= ~(1 << pin);
	return register_value;
}

byte toggle_bit(byte register_value, int pin) {
    //  (d) Modify the supplied register value by flipping the designated
    //  pin, leaving all other pins unchanged.
    //  Hint: left shift; binary XOR.
	register_value ^= 1<<pin;
	return register_value;
	
}


//-----------------------------------------------------------
#define REPEATS (5)
#define BYTE_COUNT (256)
#define PIN_COUNT (8)
#define SEED ((unsigned) 314159)
//-----------------------------------------------------------

/*
**  Convert a byte to an integer with digits that look like the binary 
**  representation of the byte.
**
**  For example:
**      binstr(0x0c) == 1100, and 
**      binstr(0xcc) == 11001100.
*/
int binstr(byte b) {
    int result = 0;

    for ( int i = 7; i >= 0; i-- ) {
        result = result * 10 + ((b >> i) & 1);
    }

    return result;
}
//-----------------------------------------------------------
int main(void) {
    srand(SEED);

    for ( int i = 0; i < REPEATS; i++ ) {
        byte register_value = rand() % BYTE_COUNT;
        int pin = rand() % PIN_COUNT;

        if ( i > 0 ) printf("\n");

        printf("   set_bit(%08d, %d) = %08d\n", binstr(register_value), pin, binstr(   set_bit(register_value, pin)));
        printf(" flip_bits(%08d   ) = %08d\n" , binstr(register_value),      binstr( flip_bits(register_value     )));
        printf(" clear_bit(%08d, %d) = %08d\n", binstr(register_value), pin, binstr( clear_bit(register_value, pin)));
        printf("toggle_bit(%08d, %d) = %08d\n", binstr(register_value), pin, binstr(toggle_bit(register_value, pin)));
    }
    return 0;
}


void process_game_controller( void ) {
    //  (f) Test the relevant pin in the Port D input register to determine
    //  if the Up switch is closed. If it is, turn on both LEDs without 
    //  disrupting any other pins.
	if ((PIND >> 1) & 0b1) {
		PORTB = PORTB | 0b00001100;
		} else {
    //  (g) OTHERWISE, test the relevant pin of the Port B input register
    //  to determine if the Down switch is closed. If it is, turn off both LEDs
    //  without affecting any other pins.
	if ((PINB >> 7) & 0b1) {
		PORTB =  PORTB & 0b11110011;
		} else {
    //  (h) OTHERWISE, test the relevant pin of the Port B input register
    //  to determine if the Left switch is closed. If it is, turn on the left LED
    //  and turn off the right LED without affecting any other pins.
	if ((PINB >> 1) & 0b1) {
		PORTB = PORTB | 0b00000100;
		PORTB = PORTB & 0b00001000;
		} else {
	//  (i) OTHERWISE, test the relevant pin of the Port D input register
    //  to determine if the Right switch is closed. If it is, turn on the right LED
    //  and turn off the left LED without affecting any other pins.
	if ((PIND >> 0) & 0b1) {
		PORTB = PORTB | 0b00001000;
		PORTB = PORTB & 0b00000100;
		} else {
    //  (j) OTHERWISE, test the relevant pin of the Port B input register
    //  to determine if the Centre switch is closed. If it is, toggle both LEDs
    //  without affecting any other pins.
	if ((PINB >> 0) & 0b1) {
		PORTB = PORTB | 0b00001100;
	}
		}
		}
		}
		}
}

