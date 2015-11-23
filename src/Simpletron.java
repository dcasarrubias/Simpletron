import java.util.*;

public class Simpletron {
	static int instructionCounter; //The instruction counter keeps tracks of the location memory that contains the instruction being performed.
	static int accumulator;//used to represent the accumulator register 
	static int operationCode; // picks off left two digits and place here.
	static int operand; // picks off right two digits and place here.
	static int instructionRegister; // next instructions to be performed wil be performed here
	static int memory[] = new int[100]; // creates an array type of int called memory that contains 100 ints. 

	

	public static void fetch() {  // fetches the contents of the location from memory
		
			instructionRegister=memory[instructionCounter]; // fetches the contents of the location from memory
			
		}
	

	public static void decode() {  // method to extract operationCode and operand from instruction register
operationCode=instructionRegister/100; // operation code extracted from instruction register
operand=instructionRegister%100;  // operand extracted from instruction register
		
	}

	public static void execute() {  // method to choose execution options
		
		switch(operationCode){  
		case 10:   // prompts user for integer and stores it in the location memory[operand]
		
			System.out.println("Enter an integer");  // prompts user for integer
		
		Scanner scan=new Scanner(System.in);  // Scanner
		memory[operand]=scan.nextInt();  // stores integer entered by user in the location memory[operand]
		
		
		break;
		
		case 11: // read a word from a specific location in memory to the screen 
			System.out.println(memory[operand]);  // reads a word from a specific location and prints to screen
			break;
		
			
		case 20://load 
		accumulator=memory[operand];  // loads a word from specific location in memory into the accumulator
		
		
		break;
		
		case 21://store
			memory[operand]=accumulator;  // stores a word from the accumulator into a specific location in memory
			break;
			
			
			
		case 30: //add
			accumulator+=memory[operand]; // adds a word from a specific location in memory to the word in the accumulator
			
			
			
			break;
			
		case 31: //subtract
			accumulator-=memory[operand];  // subtract a word from a specific location in memory from the word in the accumulator 
			break;
		case 32://divide
			try{
				
			accumulator/=memory[operand];  // divides a word from a specific location in memory to the word in the accumulator
		
			}catch(ArithmeticException e){
				System.out.println("***Attempted to divide by zero***"); // arithmetic exception prints out "Attempted to divide by zero") if number attempts to divide by zero
				
			}
			break;
		case 33: //multiply 
			accumulator*=memory[operand];  // multiply a word from a specific location in memory by the word in the accumulator
			break;
			
		case 40://branch
			instructionCounter=operand;  // branches to a specific location in memory
			break;
			
		case 41: //branchNeg
			if(accumulator<0){  // if the accumulator is negative, branches to a specific location in memory
				instructionCounter=operand;
			}
			break;
			
		case 42: //branchZero
			if(accumulator==0){  // if accumulator is zero branches to a specific location in memory
				instructionCounter=operand;
			}
			break;
		case 43://halt
			System.out.println("***S i m p l e t r o n  e x e c u t i o n terminated*** ");  // halts the program when completed its task
			
			break;
			default:
				System.out.println("***Simpletron execution abnormally terminated***");  // default if program enters code that is nonexistent in switch 
		}
		
			
		}
		
	
	public static void dump(){ 
		System.out.println("REGISTERS:");
		System.out.println("accumulator           " + "+"+ accumulator);  // prints out accumulator
		System.out.println("instructionCounter     " + instructionCounter); // prints out instructionCounter
		System.out.println("instructionRegister   "+ "+" + instructionRegister); // prints out instructionRegister
		System.out.println("operationCode   "+ operationCode);  // prints out operationCode
		System.out.println("operand     " + operand);  // prints out operand
		System.out.println("MEMORY:");
		for(int y=0; y<10;y++){  // for loop prints memory location 
			System.out.printf("%02d  ", y);  // prints out memory location
		}
		System.out.println();  
		for(int x=0; x<=memory.length;x++){
		
			if(x%10==0){ // makes rows of ten
				
							System.out.println();
			}else{
				System.out.print(" "+ memory[x]+" "); // prints out memory location 
			}
		}
	}
	public static void main(String[] args) {
		instructionCounter = 0;  // initializes instructionCounter to zero
		operationCode = 0;  // initializes operationCode to zero
		operand = 0;  // initializes operand to zero
		instructionRegister = 0;  // initializes instructionRegister to zero
		Scanner input = new Scanner(System.in);
		
		/*
		 * Welcome prompt for simpletron
		 * Also includes instructions
		 */
		System.out.println("***Welcome to Simpletron! ***");
		System.out.println("***Please enter your program one instruction***");
		System.out.println("***(or data word) at a time. I will display***");
		System.out.println("***the location number and a question mark (?)***");
		System.out.println("***You then type the word for that location***");
		System.out.println("***Type -99999 to stop entering your program.***");
		
		
		
		for (int i = 0; i < memory.length; i++) {
			System.out.printf("%02d ?", i);
			int num = input.nextInt();
			if(num>99999||num<-99999){ // error checking. If user enters a number less than -99999 or greater than 99999 the program is terminated.
				System.out.println("****Simpletron execution abnormally terminated***");  // prints out error if condition is true
			}
			if (num == -99999) { // if user enters -99999 program execution begins
				System.out.println("***Program loading completed***"); // inform user that loading is complete
				System.out.println("***Program execution begins***"); // informs user that program execution begins
				break;
			} else {
				memory[i] = num;
			}
		}
		fetch();  // calls fetch method to begin execution
		decode(); // calls decode method to begin execution
		execute(); // calls execute method to begin execution
		dump();  // calls dump method to begin execution
	}

}
