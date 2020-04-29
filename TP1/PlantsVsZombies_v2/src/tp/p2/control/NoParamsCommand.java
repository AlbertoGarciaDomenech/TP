package tp.p2.control;

public abstract class NoParamsCommand extends Command {
	
	public NoParamsCommand(String commandText, String commandTextMsg, String helpTextMsg) {
		super(commandText, commandTextMsg, helpTextMsg);
	}

	public abstract NoParamsCommand parse(String[] commandWords, Controller controller); //
}
