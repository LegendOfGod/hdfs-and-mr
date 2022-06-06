package common_friend_2;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;


/**
 * @author lqb
 * @date 2021/10/9 14:53
 */
public class Friend2Reducer extends Reducer<Text, Text,Text, Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        StringBuffer stringBuffer = new StringBuffer();
        for (Text value : values) {
            stringBuffer.append(value.toString()).append("-");
        }
        context.write(key,new Text(stringBuffer.toString()));
    }
}
