package cn.lichengwu.test;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 临时
 *
 * @author 佐井
 * @version 1.0
 * @created 2013-07-04 1:59 PM
 */
public class Main implements Serializable{
    private static final long serialVersionUID = 6954888713124249060L;

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enableDefaultTyping();
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_CONCRETE_AND_ARRAYS, JsonTypeInfo.As.PROPERTY);
        System.out.println(objectMapper.writeValueAsString(new Main()));
    }
}
