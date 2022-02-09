
import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * @ClassName: TestJedis
 * @Description: Jedis测试
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/2/9 15:51
 */
public class TestJedis {
    public static void main(String[] args) {
        // 1. 创建jedis对象
        Jedis jedis = new Jedis("localhost", 6379);
        // 验证密码
        jedis.auth("yourpassword");

        // 2. 执行命令
        System.out.println(jedis.ping());
        jedis.set("remoteJedis", "successed");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Hellojson1", "json");
        jsonObject.put("jsontest", "1");
        String result = jsonObject.toJSONString();


        // 3. 操作事务
        Transaction multi = jedis.multi();
        try {
            multi.set("test3", result);
            multi.set("test4", result);
            // int i = 1 / 0; // 模拟失败
            multi.exec();   // 执行事务
        } catch (Exception e) {
            // 如果失败，抛弃事务
            multi.discard();
            e.printStackTrace();
        }

        // 4. 查看数据
        System.out.println(jedis.get("test1"));
        System.out.println(jedis.get("test2"));

        jedis.close();
    }
}
