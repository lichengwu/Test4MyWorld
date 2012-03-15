/*
 * Copyright (c) 2010-2011 lichengwu
 * All rights reserved.
 * 
 */
package oliver.test.command;


/**
 *
 * @author lichengwu
 * @created 2012-2-22
 *
 * @version 1.0
 */
public class CommadController {
	
	private Command [] onCommands;
	private Command [] offCommands;
	
	public CommadController(){
		onCommands = new Command[5];
		offCommands = new Command[5];
		
		for(int i=0;i<5;i++){
			onCommands[i]=new NoCommand();
			offCommands[i]=new NoCommand();
		}
	}
	
	public void setCommand(int index,Command on,Command off){
		onCommands[index]=on;
		offCommands[index]=off;
	}
	
	public void ButtonOn(int index){
		onCommands[index].execute();
	}
	
	public void ButtonOff(int index){
		offCommands[index].execute();
	}
	
	class NoCommand implements Command{

		/**
         * @see oliver.test.command.Command#execute()
         */
        @Override
        public void execute() {
	        
        }
		
	}
	
}
