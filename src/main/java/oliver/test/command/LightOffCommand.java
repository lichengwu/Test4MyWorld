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
public class LightOffCommand implements Command {

	private Light light;
	
	public LightOffCommand(Light light){
		this.light = light;
	}
	
	/**
     * @see oliver.test.command.Command#execute()
     */
    @Override
    public void execute() {
    	light.off();
    }

}
