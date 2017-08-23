import net.sourceforge.htmlunit.corejs.javascript.tools.shell.Global;
import org.junit.Test;
import play.Application;
import play.api.Play;
import play.libs.ws.WS;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithServer;

import java.util.concurrent.CompletionStage;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static play.test.Helpers.*;

/**
 * Integration testing that involves starting up an application or a server.
 * <p>
 * https://www.playframework.com/documentation/2.5.x/JavaFunctionalTest
 */
public class IntegrationTest extends WithServer {

    private final String TEST_TOKEN = System.getenv("HARU_TEST_TOKEN");

    @Test
    public void testInServerThroughUrl() throws Exception {
        // Tests using a scoped WSClient to talk to the server through a port.
        try (WSClient ws = WS.newClient(9000)) {
            CompletionStage<WSResponse> stage = ws.url("/kanjis").setHeader("Authorization", "Bearer " + TEST_TOKEN).get();
            WSResponse response = stage.toCompletableFuture().get();
            String body = response.getBody();
            assertThat(body, body.length() > 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
