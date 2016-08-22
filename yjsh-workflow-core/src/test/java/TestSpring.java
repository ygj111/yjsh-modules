import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.snaker.engine.SnakerEngine;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.Order;
import org.snaker.engine.entity.Task;
import org.snaker.engine.helper.StreamHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author yuqs
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class TestSpring {
	@Resource
	SnakerEngine engine;
    @Test
    public void test() {
    	System.out.println("ApplicationContext:"+engine);
    	
       // = applicationContext.getBean(SnakerEngine.class);
//        engine.process().deploy(StreamHelper.getStreamFromClasspath("leave.snaker"));
//        Map<String, Object> args = new HashMap<String, Object>();
//        args.put("receipt.operator", new String[]{"1"});
//        Order order = engine.startInstanceByName("contactFormFlow", 0, "2", args);
//        System.out.println("order=" + order);
//        List<Task> tasks = engine.query().getActiveTasks(new QueryFilter().setOrderId(order.getId()));
//        for(Task task : tasks) {
//            engine.executeTask(task.getId(), "1", args);
//        }
    }
}
