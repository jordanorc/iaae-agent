package iaae.agent.creator;

import iaae.models.Discussion;
import iaae.models.Thesis;
import iaae.ontology.ThesesGenerator;
import iaae.ws.IAAEService;
import jadex.bdi.runtime.Plan;

import java.io.InputStream;
import java.util.List;

/**
 * The hello world plan prints out a short welcome message.
 */
public class GenerateThesesPlan extends Plan {
	// -------- methods --------

	/**
	 * Handle the ping request.
	 */
	public void body() {
		IAAEService service = new IAAEService();
		ThesesGenerator generator = null;
		InputStream ontology = null;

		try {

			ontology = service.getOntology("ornitologia");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String agent_name = "agent-creator";

		// inicia gerador de teses
		generator = new ThesesGenerator(ontology);
		String log = "";

		boolean has_discussions = (boolean) getBeliefbase().getBelief(
				"has_discussions").getFact();
		System.out.println(has_discussions);
		try {
			List<Discussion> discussions = service.getDiscussions();
			if (discussions.size() == 0) {
				System.out.println("\nNo discussion found");
				log = "No discussion found";
				service.log(log, agent_name);
			} else {
				System.out.println("\nStarting theses generation");
				for (Discussion discussion : discussions) {
					log = "######## Generating theses for discussion "
							+ discussion.getPk() + "#######";

					service.log(log, agent_name);

					System.out.println(log);

					List<Thesis> theses = generator.getTheses("Ave",
							discussion.getNumber_theses());

					log = "Saving thesis";
					service.log(log, agent_name);
					System.out.println(log);

					service.saveTheses(discussion, theses);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// killAgent();

	}

	public void passed() {
		System.out.println("Executou o plano com sucesso!");
	}

	public void failed() {
		System.out.println("Falha na execução do plano!");
	}
}
