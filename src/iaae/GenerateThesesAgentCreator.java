package iaae;

import jadex.base.Starter;
import jadex.bridge.IComponentIdentifier;
import jadex.bridge.IExternalAccess;
import jadex.bridge.service.RequiredServiceInfo;
import jadex.bridge.service.search.SServiceProvider;
import jadex.bridge.service.types.cms.IComponentManagementService;
import jadex.commons.future.IFuture;
import jadex.commons.future.ThreadSuspendable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Example that shows how an agent model can be created via the editable model
 * api. This model can be registered with a name at the BDI agent factory and
 * instances can be created based on this new model.
 */
public class GenerateThesesAgentCreator {
	/**
	 * Main for starting hello world agent.
	 */
	public static void main(String[] args) {

		String[] defargs = new String[] { "-gui", "false", "-welcome", "false",
				"-cli", "false", "-printpass", "false" };
		String[] newargs = new String[defargs.length + args.length];
		System.arraycopy(defargs, 0, newargs, 0, defargs.length);
		System.arraycopy(args, 0, newargs, defargs.length, args.length);
		IFuture<IExternalAccess> platfut = Starter.createPlatform(newargs);
		ThreadSuspendable sus = new ThreadSuspendable();
		IExternalAccess platform = platfut.get(sus);
		System.out.println("Started platform: "
				+ platform.getComponentIdentifier());

		IComponentManagementService cms = SServiceProvider.getService(
				platform.getServiceProvider(),
				IComponentManagementService.class,
				RequiredServiceInfo.SCOPE_PLATFORM).get(sus);

		;
		String agent = ClassLoader.getSystemResource(
				"iaae/GenerateTheses.agent.xml").getPath();
		System.out.println(agent);
		IComponentIdentifier cid = cms.createComponent(null, agent, null, null)
				.get(sus);
		System.out.println("Started chat component: " + cid);



	}
}
