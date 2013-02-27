package iaae;

import jadex.base.Starter;

/**
 * Example that shows how an agent model can be created via the editable model
 * api. This model can be registered with a name at the BDI agent factory and
 * instances can be created based on this new model.
 */
public class AppStarter {
	/**
	 * Main for starting hello world agent.
	 */
	public static void main(String[] args) {

		Starter.main(new String[] { "-gui", "false", "-component",
				"iaae/agent/creator/AgentCreator.agent.xml" });

	}
}
