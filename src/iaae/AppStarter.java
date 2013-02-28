package iaae;

import jadex.base.Starter;

public class AppStarter {
	/**
	 * Main for starting hello world agent.
	 */
	public static void main(String[] args) {

		Starter.main(new String[] { "-gui", "false", "-component",
				"iaae/agent/riffle/AgentRiffle.agent.xml" });

	}
}
