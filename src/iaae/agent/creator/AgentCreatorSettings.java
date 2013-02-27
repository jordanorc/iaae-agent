package iaae.agent.creator;

import iaae.ws.IAAEService;

public class AgentCreatorSettings {

	private IAAEService service;

	public AgentCreatorSettings() {
		this.service = new IAAEService();
	}

	public IAAEService getService() {
		return service;
	}

	public boolean hasDiscussion() {
		return !this.service.getDiscussions().isEmpty();
	}
}
