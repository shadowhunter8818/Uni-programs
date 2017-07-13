#include <avr/io.h>
#include <util/delay.h>
#include "cpu_speed.h"

void setupLed0( void ) {
    //  (a) Set the CPU speed to 8MHz (you must also be compiling at 8MHz).
	set_clock_speed(CPU_8MHz);
    //  (b) Configure the data direction register for Port B to use LED0 for 
    //  output. The data direction for LED0 is controlled by Pin 2. No other 
    //  pins should be affected.
	DDRB =  DDRB | 0b00000100;
    //  (c) Turn off LED0 (and all other outputs connected to Port B) by 
    //  clearing all bits in the Port B output register.
	PORTB = 0b00000000;
}

void turnOnLed0( void ) {
    //  (d) Set pin 2 of the Port B output register. No other pins should be 
    //  affected.
	PORTB = PORTB | 0b00000100;
}

void turnOffLed0( void ) {
    //  (e) Clear pin 2 of the Port B output register. No other pins should be
    //  affected.
	PORTB = PORTB & 0b11111011;
}



int main(void) {
    setupLed0();

    while ( 1 ) {
        turnOnLed0();
        _delay_ms(500);

        turnOffLed0();
        _delay_ms(500);
    }

    return 0;
}
